package com.neo.exchangeMonitoring.domain.repository

import com.neo.exchangeMonitoring.data.database.entities.CurrencyDbEntity
import java.math.BigDecimal

interface CurrencyRepository {
    fun addCurrency(name: String, price: BigDecimal)
    suspend fun getAllCurrency(): List<CurrencyDbEntity>
    fun getCurrency()
    fun updatePrice(name: String, price: BigDecimal)
    fun deleteCurrency(name: String)
}