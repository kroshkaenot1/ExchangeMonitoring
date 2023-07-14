package com.neo.exchangeMonitoring.data.remote.models

import com.google.gson.annotations.SerializedName


data class ListOfCurrencies (
    @SerializedName(value = "Date")
    val date: String,

    @SerializedName(value = "PreviousDate")
    val previousDate: String,

    @SerializedName(value = "PreviousURL")
    val previousURL: String,

    @SerializedName(value = "Timestamp")
    val timestamp: String,

    @SerializedName(value = "Valute")
    val valute: Map<String, CurrencyRemote>
)