package com.neo.exchangeMonitoring.domain.repository

import com.neo.exchangeMonitoring.data.database.entities.CurrencyDbEntity

interface CurrencyRepository {
    suspend fun getAllCurrency(): List<CurrencyDbEntity>
    suspend fun changeCurrencyFavorite(id: Long)
    suspend fun getAllFavoriteCurrency(): List<CurrencyDbEntity>
    suspend fun getAllCurrencyBySubString(name: String): List<CurrencyDbEntity>
    suspend fun getAllFavoriteCurrencyBySubString(name: String): List<CurrencyDbEntity>
}