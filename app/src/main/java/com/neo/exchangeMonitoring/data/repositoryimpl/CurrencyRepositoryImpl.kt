package com.neo.exchangeMonitoring.data.repositoryimpl

import com.neo.exchangeMonitoring.domain.model.Currency
import com.neo.exchangeMonitoring.domain.repository.CurrencyRepository
import java.math.BigDecimal
import java.util.LinkedList

class CurrencyRepositoryImpl:CurrencyRepository {
    override fun addCurrency(name: String, price: BigDecimal) {
        TODO("Not yet implemented")
    }

    override fun getAllCurrency() : LinkedList<Currency> {
        val currencies : LinkedList<Currency> = LinkedList<Currency>()
        for (i in 1..10){
            currencies.add(Currency(i, "$i usd", BigDecimal( i*2)))
        }
        return currencies
    }

    override fun getCurrency() {
        TODO("Not yet implemented")
    }

    override fun updatePrice(name: String, price: BigDecimal) {
        TODO("Not yet implemented")
    }

    override fun deleteCurrency(name: String) {
        TODO("Not yet implemented")
    }
}