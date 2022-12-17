package cm.architecture.disneymotions.model.remote

import cm.architecture.disneymotions.model.data.Poster
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface DisneyService {

    @GET("DisneyPosters.json")
    suspend fun fetchDisneyPosterList(): ApiResponse<List<Poster>>
}