package cm.architecture.disneymotions.mapper

import cm.architecture.disneymotions.model.data.PosterErrorMessage
import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message

object ErrorResponseMapper: ApiErrorModelMapper<PosterErrorMessage> {
    override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): PosterErrorMessage {
        return PosterErrorMessage(apiErrorResponse.statusCode.code, apiErrorResponse.message())
    }
}