package com.neo.exchangeMonitoring.domain.usecase

import com.neo.exchangeMonitoring.data.mapper.CurrencyDbEntityToCurrencyDomainMapper
import com.neo.exchangeMonitoring.domain.model.Currency
import com.neo.exchangeMonitoring.domain.repository.CurrencyRepository

class GetAllCurrenciesUseCase(private val currencyRepository: CurrencyRepository) {
    private val currencyDbEntityToCurrencyDomainMapper = CurrencyDbEntityToCurrencyDomainMapper()
    suspend fun execute(): List<Currency> {
        val currencyRemoteList = currencyRepository.getAllCurrency()
        return currencyRemoteList.map {
            currencyDbEntityToCurrencyDomainMapper.currencyDbEntityToCurrencyDomainMap(
                it
            )
        }
    }
}