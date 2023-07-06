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

data class CurrencyRemote(
    @SerializedName(value = "ID")
    val id: String,

    @SerializedName(value = "NumCode")
    val numCode: String,

    @SerializedName(value = "CharCode")
    val charCode: String,

    @SerializedName(value = "Nominal")
    val nominal: Long,

    @SerializedName(value = "Name")
    val name: String,

    @SerializedName(value = "Value")
    val value: Double,

    @SerializedName(value = "Previous")
    val previous: Double
)
