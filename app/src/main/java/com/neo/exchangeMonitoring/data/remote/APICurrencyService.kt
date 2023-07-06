package com.neo.exchangeMonitoring.data.remote

import com.neo.exchangeMonitoring.data.remote.models.ListOfCurrencies
import retrofit2.http.GET

interface APICurrencyService {
    @GET("daily_json.js")
    suspend fun getCurrencies() : ListOfCurrencies
}