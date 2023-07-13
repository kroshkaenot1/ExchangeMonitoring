package com.neo.exchangeMonitoring.domain.repository

import com.neo.exchangeMonitoring.data.database.entities.CurrencyDbEntity
import com.neo.exchangeMonitoring.utils.SortingStates

interface CurrencyRepository {
    suspend fun getAllCurrency(): List<CurrencyDbEntity>
    suspend fun changeCurrencyFavorite(id: Long)
    suspend fun getAllFavoriteCurrency(): List<CurrencyDbEntity>
    suspend fun getAllSortedCurrency(sortBy:Enum<SortingStates>,name:String): List<CurrencyDbEntity>
    suspend fun getAllSortedFavoriteCurrency(sortBy:Enum<SortingStates>,name:String): List<CurrencyDbEntity>
}