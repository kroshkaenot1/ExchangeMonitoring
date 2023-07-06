package com.neo.exchangeMonitoring.domain.usecase

import com.neo.exchangeMonitoring.data.remote.models.CurrencyRemote
import com.neo.exchangeMonitoring.domain.repository.CurrencyRepository

class GetAllCurrenciesUseCase(private val currencyRepository: CurrencyRepository) {
    suspend fun execute():List<CurrencyRemote>{
        return currencyRepository.getAllCurrency()
    }
}