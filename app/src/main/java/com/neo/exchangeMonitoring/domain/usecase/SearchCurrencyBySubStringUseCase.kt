package com.neo.exchangeMonitoring.domain.usecase

import com.neo.exchangeMonitoring.domain.model.Currency
import com.neo.exchangeMonitoring.domain.repository.CurrencyRepository
import com.neo.exchangeMonitoring.mapper.CurrencyDbEntityToCurrencyDomainMapper

class SearchCurrencyBySubStringUseCase(private val currencyRepository: CurrencyRepository) {
    private val currencyDbEntityToCurrencyDomainMapper = CurrencyDbEntityToCurrencyDomainMapper()

    suspend fun execute(name: String): List<Currency>{
        val currencyDbList =  currencyRepository.getAllCurrencyBySubString(name = name)
        return currencyDbList.map {
            currencyDbEntityToCurrencyDomainMapper.currencyDbEntityToCurrencyDomainMap(
                it
            )
        }
    }
}