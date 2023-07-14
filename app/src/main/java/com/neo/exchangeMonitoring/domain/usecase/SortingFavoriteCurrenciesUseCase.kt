package com.neo.exchangeMonitoring.domain.usecase

import com.neo.exchangeMonitoring.domain.model.Currency
import com.neo.exchangeMonitoring.domain.repository.CurrencyRepository
import com.neo.exchangeMonitoring.mapper.CurrencyDbEntityToCurrencyDomainMapper
import com.neo.exchangeMonitoring.utils.SortingStatesCurrency
import javax.inject.Inject

class SortingFavoriteCurrenciesUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val currencyDbEntityToCurrencyDomainMapper: CurrencyDbEntityToCurrencyDomainMapper
) {
    suspend fun execute(sortBy: Enum<SortingStatesCurrency>, name: String): List<Currency> {
        val currencyDbList = currencyRepository.getAllSortedFavoriteCurrency(sortBy, name)
        return currencyDbList.map {
            currencyDbEntityToCurrencyDomainMapper.currencyDbEntityToCurrencyDomainMap(
                currencyDbEntity = it
            )
        }
    }
}