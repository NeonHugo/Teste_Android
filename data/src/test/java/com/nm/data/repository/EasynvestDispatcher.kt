package com.nm.data.repository

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class EasynvestDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return MockResponse().apply {
            when (request.path) {
                ESTABLISHMENT_LIST_URL -> {
                    setResponseCode(200).setBody(MockResponseFileReader(ESTABLISHMENT_LIST_SUCCESS).content)
                }
                else -> {
                    setResponseCode(404)
                }
            }
        }
    }

    companion object {
        private const val ESTABLISHMENT_LIST_URL =
            "/simulate?investedAmount=32323.0&index=CDI&rate=123&isTaxFree=false&maturityDate=2023-03-03"
        private const val ESTABLISHMENT_LIST_SUCCESS = "data-success.json"
    }

}