package com.neo.exchangeMonitoring.data.remote.models

import com.google.gson.annotations.SerializedName

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
