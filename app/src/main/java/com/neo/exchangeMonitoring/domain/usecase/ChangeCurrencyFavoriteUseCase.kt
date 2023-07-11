package com.neo.exchangeMonitoring.domain.usecase

import com.neo.exchangeMonitoring.domain.repository.CurrencyRepository

class ChangeCurrencyFavoriteUseCase(private val currencyRepository: CurrencyRepository) {
    suspend fun execute(id: Long){
        currencyRepository.changeCurrencyFavorite(id)
    }
}