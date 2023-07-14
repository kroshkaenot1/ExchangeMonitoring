package com.neo.exchangeMonitoring.domain.usecase

import com.neo.exchangeMonitoring.domain.model.Currency
import com.neo.exchangeMonitoring.domain.repository.CurrencyRepository
import com.neo.exchangeMonitoring.mapper.CurrencyDbEntityToCurrencyDomainMapper
import javax.inject.Inject

class GetFavoriteCurrenciesUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val currencyDbEntityToCurrencyDomainMapper: CurrencyDbEntityToCurrencyDomainMapper
) {
    suspend fun execute(): List<Currency> {
        val currencyFavoriteList = currencyRepository.getAllFavoriteCurrency()
        return currencyFavoriteList.map {
            currencyDbEntityToCurrencyDomainMapper.currencyDbEntityToCurrencyDomainMap(
                currencyDbEntity = it
            )
        }
    }
}