package com.nm.infrastructure.net

data class ApiError(
    val status: Int? = null,
    val error: String? = null,
    val errorMessage: String? = null
)
