package com.nm.infrastructure.net

sealed class ResultResponse<T>
class EmptyResponse<T>(val error: ApiError) : ResultResponse<T>()
data class SuccessResponse<T>(val body: T) : ResultResponse<T>()
data class ErrorResponse<T>(val error: ApiError) : ResultResponse<T>()