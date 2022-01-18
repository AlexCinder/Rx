package com.example.rx.api

import com.example.rx.pojo.Data
import com.example.rx.pojo.RAWJson
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        private const val API = "ef62e3464e9735bbf341f66f68553101f6e111bcf523600cd62dc59473e08688"
        private const val QUERY_LIMIT = "limit"
        private const val QUERY_TO_SYMBOL = "tsym"
        private const val API_KEY = "api_key"
        private const val CURRENCY = "USD"
        private const val CRYPTO_CURRENCY = "BTC"
        private const val QUERY_FROM_SYMBOLS = "fsyms"
        private const val QUERY_TO_SYMBOLS = "tsyms"
    }

    @GET("top/totalvolfull")
    fun getTopCoinInfo(
        @Query(API_KEY) apiKey: String = API,
        @Query(QUERY_LIMIT) limit: Int = 20,
        @Query(QUERY_TO_SYMBOL) tSym: String = CURRENCY
    ): Single<Data>

    @GET("pricemultifull")
    fun getFullPrice(
        @Query(API_KEY) apiKey: String = API,
        @Query(QUERY_FROM_SYMBOLS) fSym: String = CRYPTO_CURRENCY,
        @Query(QUERY_TO_SYMBOLS) tSym: String = CURRENCY
    ): Single<RAWJson>
}