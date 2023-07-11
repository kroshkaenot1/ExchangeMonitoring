package com.neo.exchangeMonitoring.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neo.exchangeMonitoring.domain.model.Currency
import com.neo.exchangeMonitoring.domain.usecase.ChangeCurrencyFavoriteUseCase
import com.neo.exchangeMonitoring.domain.usecase.GetFavoriteCurrenciesUseCase
import com.neo.exchangeMonitoring.domain.usecase.SearchFavoriteCurrencyBySubString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteCurrenciesViewModel @Inject constructor(
    private val getFavoriteCurrenciesUseCase: GetFavoriteCurrenciesUseCase,
    private val changeCurrencyFavoriteUseCase: ChangeCurrencyFavoriteUseCase,
    private val searchFavoriteCurrencyBySubString: SearchFavoriteCurrencyBySubString
) :
    ViewModel() {
    private val _listOfFavoriteCurrency: MutableStateFlow<List<Currency>> =
        MutableStateFlow(emptyList())

    val listOfFavoriteCurrency: StateFlow<List<Currency>> = _listOfFavoriteCurrency
    override fun onCleared() {
        super.onCleared()
    }

    init {
        getAllFavoriteCurrency()
    }

    fun getAllFavoriteCurrency() {
        viewModelScope.launch {
            val currencyFavoriteList = getFavoriteCurrenciesUseCase.execute()
            _listOfFavoriteCurrency.emit(currencyFavoriteList)
        }
    }

    fun changeCurrencyFavorite(id: Long) {
        viewModelScope.launch { changeCurrencyFavoriteUseCase.execute(id) }
    }

    fun getAllFavoriteCurrencyBySubString(name: String) {
        viewModelScope.launch {
            val currencyList = searchFavoriteCurrencyBySubString.execute(name)
            _listOfFavoriteCurrency.emit(currencyList)
        }
    }
}