package com.neo.exchangeMonitoring.domain.usecase

import com.neo.exchangeMonitoring.domain.model.Currency
import com.neo.exchangeMonitoring.domain.repository.CurrencyRepository
import java.math.BigDecimal
import java.util.LinkedList

class GetAllCurrenciesUseCase(val currencyRepository: CurrencyRepository) {
    fun execute():LinkedList<Currency>{
        return currencyRepository.getAllCurrency()
    }
}