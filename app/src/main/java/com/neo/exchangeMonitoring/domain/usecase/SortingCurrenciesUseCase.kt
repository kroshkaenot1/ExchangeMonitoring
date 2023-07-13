package com.neo.exchangeMonitoring.domain.usecase

import com.neo.exchangeMonitoring.domain.model.Currency
import com.neo.exchangeMonitoring.domain.repository.CurrencyRepository
import com.neo.exchangeMonitoring.mapper.CurrencyDbEntityToCurrencyDomainMapper
import com.neo.exchangeMonitoring.utils.SortingStates

class SortingCurrenciesUseCase(private val currencyRepository: CurrencyRepository) {
    private val currencyDbEntityToCurrencyDomainMapper = CurrencyDbEntityToCurrencyDomainMapper()
    suspend fun execute(sortBy: Enum<SortingStates>, name: String): List<Currency> {
        val currencyDbList = currencyRepository.getAllSortedCurrency(sortBy, name)
        return currencyDbList.map {
            currencyDbEntityToCurrencyDomainMapper.currencyDbEntityToCurrencyDomainMap(
                it
            )
        }
    }

}