package com.neo.exchangeMonitoring.domain.repository

import com.neo.exchangeMonitoring.domain.model.Currency
import java.math.BigDecimal
import java.util.LinkedList

interface CurrencyRepository {
    fun addCurrency(name:String,price:BigDecimal)
    fun getAllCurrency() : LinkedList<Currency>
    fun getCurrency()
    fun updatePrice(name:String,price: BigDecimal)
    fun deleteCurrency(name: String)
}