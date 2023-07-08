package com.neo.exchangeMonitoring.domain.usecase

import com.neo.exchangeMonitoring.data.mapper.CurrencyRemoteToCurrencyDomainMapper
import com.neo.exchangeMonitoring.domain.model.Currency
import com.neo.exchangeMonitoring.domain.repository.CurrencyRepository

class GetAllCurrenciesUseCase(private val currencyRepository: CurrencyRepository) {
    private val currencyRemoteToCurrencyDomainMapper = CurrencyRemoteToCurrencyDomainMapper()
    suspend fun execute(): List<Currency> {
        val currencyRemoteList = currencyRepository.getAllCurrency()
        return currencyRemoteList.map {
            currencyRemoteToCurrencyDomainMapper.currencyRemoteToCurrencyDomainMap(
                it
            )
        }
    }
}