package com.nm.infrastructure.net

import retrofit2.Response


fun <T, O> Response<T>.create(mapper: Mapper<T, O>): ResultResponse<O> {
    return ApiResponse.transformResponse(this).converter(mapper)
}

sealed class ApiResponse<T> {

    companion object {

        fun <T> transformResponse(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204 || (body is List<*> && body.size == 0)) ApiEmptyResponse(
                    ApiError(
                        response.code(),
                        response.message(),
                        response.message()
                    )
                )
                else ApiSuccessResponse(body = body)
            }
            else {
                ApiErrorResponse(
                    ApiError(
                        response.code(),
                        response.message(),
                        response.message()
                    )
                )
            }
        }
    }
}

class ApiEmptyResponse<T>(val error: ApiError) : ApiResponse<T>()
data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()
data class ApiErrorResponse<T>(val error: ApiError) : ApiResponse<T>()

private fun <T, O> ApiResponse<T>.converter(mapper: Mapper<T, O>): ResultResponse<O> {
    return when (this) {
        is ApiEmptyResponse -> EmptyResponse(
            error
        )
        is ApiSuccessResponse -> {
            val itemConverted = mapper.transform(body)
            SuccessResponse(itemConverted)
        }
        is ApiErrorResponse -> ErrorResponse(
            error
        )
    }
}