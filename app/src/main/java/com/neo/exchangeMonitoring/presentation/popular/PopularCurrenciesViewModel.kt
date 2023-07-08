package com.neo.exchangeMonitoring.presentation.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neo.exchangeMonitoring.domain.model.Currency
import com.neo.exchangeMonitoring.domain.usecase.GetAllCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularCurrenciesViewModel @Inject constructor(private val getAllCurrenciesUseCase: GetAllCurrenciesUseCase) :
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

    private fun getAllCurrency() {
        viewModelScope.launch {
            val cr = getAllCurrenciesUseCase.execute()
            _listOfCurrency.emit(cr)
        }

    }
}