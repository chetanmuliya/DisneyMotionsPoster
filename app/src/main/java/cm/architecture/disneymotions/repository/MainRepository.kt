package cm.architecture.disneymotions.repository

import cm.architecture.disneymotions.mapper.ErrorResponseMapper
import cm.architecture.disneymotions.model.data.Poster
import cm.architecture.disneymotions.model.local.PosterDao
import cm.architecture.disneymotions.model.remote.DisneyService
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import com.skydoves.sandwich.message
import timber.log.Timber

class MainRepository constructor(
    private val disneyService: DisneyService,
    private val posterDao: PosterDao
): Repository {

    fun loadDisneyPosters(onSuccess: () -> Unit) = flow {
        val posters: List<Poster> = posterDao.getPosterList()
        if (posters.isEmpty()) {
            // request API network request asynchronously.
            disneyService.fetchDisneyPosterList()
                // handles the success case when the API request gets a successful response.
                .suspendOnSuccess {
                    posterDao.insertPosterList(data)
                    emit(data)
                }
                /**
                 * handles error cases when the API request gets an error response.
                 * e.g., internal server error.
                 * maps the [ApiResponse.Failure.Error] to the [PosterErrorResponse] using the mapper.
                 */
                .onError(ErrorResponseMapper) {
                    Timber.d("[Code: $code]: $message")
                }
                // handles exceptional cases when the API request gets an exception response.
                // e.g., network connection error.
                .onException {
                    Timber.d(message())
                }
        } else {
            emit(posters)
        }
    }.onCompletion { onSuccess() }.flowOn(Dispatchers.IO)


    init {
        Timber.d("Injection MainRepository")
    }

}