package com.neo.exchangeMonitoring.domain.model

data class Currency(
    val charCode: String,
    val name: String,
    val price: Double,
    val difference: Double,
    val isFavorite: Boolean
)
