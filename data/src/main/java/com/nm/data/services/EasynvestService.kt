package com.nm.data.services

import com.nm.data.model.SimulateResponse
import com.nm.domain.entity.Simulate
import com.nm.infrastructure.net.RetrofitBuild.NO_AUTHENTICATION_HEADER
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface EasynvestService {

    @GET("simulate")
    @Headers(NO_AUTHENTICATION_HEADER)
    suspend fun getSimulation(
        @Query("investedAmount") investedAmount: String? = null,
        @Query("index") index: String? = null,
        @Query("rate") rate: String? = null,
        @Query("isTaxFree") isTaxFree: String? = null,
        @Query("maturityDate") maturityDate: String? = null
    ): Response<SimulateResponse>

}
