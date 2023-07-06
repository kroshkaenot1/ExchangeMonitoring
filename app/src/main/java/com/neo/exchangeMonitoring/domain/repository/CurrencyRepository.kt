package com.neo.exchangeMonitoring.domain.repository

import com.neo.exchangeMonitoring.data.remote.models.CurrencyRemote
import java.math.BigDecimal

interface CurrencyRepository {
    fun addCurrency(name:String,price:BigDecimal)
    suspend fun getAllCurrency() : List<CurrencyRemote>
    fun getCurrency()
    fun updatePrice(name:String,price: BigDecimal)
    fun deleteCurrency(name: String)
}