package cm.architecture.disneymotions.model.remote

import android.app.Application
import cm.architecture.disneymotions.extension.toast
import cm.architecture.disneymotions.mapper.ErrorResponseMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.StatusCode
import com.skydoves.sandwich.map
import com.skydoves.sandwich.message
import com.skydoves.sandwich.operators.ApiResponseOperator
import com.skydoves.sandwich.operators.ApiResponseSuspendOperator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class GlobalResponseOperator<T> constructor(
    private val application: Application
) : ApiResponseSuspendOperator<T>(){

    override suspend fun onError(apiResponse: ApiResponse.Failure.Error<T>) =
        withContext(Dispatchers.Main){
            apiResponse.run {
                Timber.d(message())

                when (statusCode) {
                    StatusCode.InternalServerError -> application.toast("InternalServerError")
                    StatusCode.BadGateway -> application.toast("BadGateway")
                    else -> application.toast("$statusCode(${statusCode.code}): ${message()}")
                }

                map(ErrorResponseMapper){
                    Timber.d(message())
                }
            }
        }


    override suspend fun onException(apiResponse: ApiResponse.Failure.Exception<T>) =
        withContext(Dispatchers.Main) {
            apiResponse.run {
                Timber.d(message())
                application.toast(message())
            }
        }

    override suspend fun onSuccess(apiResponse: ApiResponse.Success<T>) = Unit
}