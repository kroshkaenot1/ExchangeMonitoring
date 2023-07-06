package com.neo.exchangeMonitoring.data.repositoryimpl

import com.neo.exchangeMonitoring.data.remote.APICurrencyService
import com.neo.exchangeMonitoring.data.remote.models.CurrencyRemote
import com.neo.exchangeMonitoring.domain.repository.CurrencyRepository
import java.math.BigDecimal
import javax.inject.Inject


class CurrencyRepositoryImpl @Inject constructor(private val apiCurrencyService: APICurrencyService) : CurrencyRepository {
    override fun addCurrency(name: String, price: BigDecimal) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCurrency(): List<CurrencyRemote> {
       return apiCurrencyService.getCurrencies().valute.values.toList()
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