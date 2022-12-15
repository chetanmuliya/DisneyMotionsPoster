package cm.architecture.disneymotions.model.remote

import cm.architecture.disneymotions.model.data.Poster
import com.skydoves.sandwich.ApiResponse

interface DisneyService {

    suspend fun fetchDisneyPosterList(): ApiResponse<List<Poster>>
}