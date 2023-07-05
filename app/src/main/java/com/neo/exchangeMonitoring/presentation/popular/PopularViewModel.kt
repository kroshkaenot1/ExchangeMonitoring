package com.neo.exchangeMonitoring.presentation.popular

import androidx.lifecycle.ViewModel
import com.neo.exchangeMonitoring.data.repositoryimpl.CurrencyRepositoryImpl
import com.neo.exchangeMonitoring.domain.model.Currency
import com.neo.exchangeMonitoring.domain.usecase.GetAllCurrenciesUseCase

class PopularViewModel : ViewModel() {
    private val getAllCurrenciesUseCase = GetAllCurrenciesUseCase(
        currencyRepository = CurrencyRepositoryImpl()
    )
    init {
    }
    override fun onCleared(){
        super.onCleared()
    }
    fun getAllCurrency() : List<Currency> {
        val cr = getAllCurrenciesUseCase.execute()
        return cr
    }
}