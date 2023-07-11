package com.neo.exchangeMonitoring.domain.usecase

import com.neo.exchangeMonitoring.domain.model.Currency
import com.neo.exchangeMonitoring.domain.repository.CurrencyRepository
import com.neo.exchangeMonitoring.mapper.CurrencyDbEntityToCurrencyDomainMapper

class GetFavoriteCurrenciesUseCase(private val currencyRepository: CurrencyRepository) {
    private val currencyDbEntityToCurrencyDomainMapper = CurrencyDbEntityToCurrencyDomainMapper()
    suspend fun execute(): List<Currency>{
        val currencyFavoriteList = currencyRepository.getAllFavoriteCurrency()
        return currencyFavoriteList.map { currencyDbEntityToCurrencyDomainMapper.currencyDbEntityToCurrencyDomainMap(
            it
        ) }
    }
}