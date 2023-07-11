package com.neo.exchangeMonitoring.presentation.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neo.exchangeMonitoring.domain.model.Currency
import com.neo.exchangeMonitoring.domain.usecase.GetAllCurrenciesUseCase
import com.neo.exchangeMonitoring.domain.usecase.ChangeCurrencyFavoriteUseCase
import com.neo.exchangeMonitoring.domain.usecase.SearchCurrencyBySubStringUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularCurrenciesViewModel @Inject constructor(
    private val getAllCurrenciesUseCase: GetAllCurrenciesUseCase,
    private val changeCurrencyFavoriteUseCase: ChangeCurrencyFavoriteUseCase,
    private val searchCurrencyBySubStringUseCase: SearchCurrencyBySubStringUseCase
) :
    ViewModel() {
    private val _listOfCurrency: MutableStateFlow<List<Currency>> =
        MutableStateFlow(emptyList())

    val listOfCurrency: StateFlow<List<Currency>> = _listOfCurrency

    init {
        getAllCurrency()
    }

    override fun onCleared() {
        super.onCleared()
    }
    fun getAllCurrency() {
        viewModelScope.launch {
            val cr = getAllCurrenciesUseCase.execute()
            _listOfCurrency.emit(cr)
        }
    }
    fun changeCurrencyFavorite(id: Long) {
        viewModelScope.launch { changeCurrencyFavoriteUseCase.execute(id) }
    }
    fun getAllCurrencyBySubString(name: String) {
        viewModelScope.launch {
            val currencyList = searchCurrencyBySubStringUseCase.execute(name)
            _listOfCurrency.emit(currencyList)
        }
    }
}