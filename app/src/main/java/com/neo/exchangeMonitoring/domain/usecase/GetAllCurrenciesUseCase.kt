package com.neo.exchangeMonitoring.domain.usecase


import com.neo.exchangeMonitoring.domain.model.Currency
import com.neo.exchangeMonitoring.domain.repository.CurrencyRepository
import com.neo.exchangeMonitoring.mapper.CurrencyDbEntityToCurrencyDomainMapper
import javax.inject.Inject

class GetAllCurrenciesUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val currencyDbEntityToCurrencyDomainMapper: CurrencyDbEntityToCurrencyDomainMapper
) {
    suspend fun execute(): List<Currency> {
        val currencyRemoteList = currencyRepository.getAllCurrency()
        return currencyRemoteList.map {
            currencyDbEntityToCurrencyDomainMapper.currencyDbEntityToCurrencyDomainMap(
                currencyDbEntity = it
            )
        }
    }
}