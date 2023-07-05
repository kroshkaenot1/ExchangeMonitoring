package com.neo.exchangeMonitoring

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.neo.exchangeMonitoring.data.repositoryimpl.CurrencyRepositoryImpl
import com.neo.exchangeMonitoring.domain.model.Currency
import com.neo.exchangeMonitoring.domain.repository.CurrencyRepository
import com.neo.exchangeMonitoring.domain.usecase.GetAllCurrenciesUseCase
import com.neo.exchangeMonitoring.presentation.popular.PopularViewModel
import com.neo.exchangeMonitoring.ui.theme.ExchangeMonitoringTheme
import java.math.BigDecimal

class MainActivity : ComponentActivity() {

    private lateinit var popularViewModel:PopularViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        popularViewModel = ViewModelProvider(this).get(PopularViewModel::class.java)
        setContent {
            ExchangeMonitoringTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    First()
                }
            }
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun First(modifier: Modifier = Modifier){
        Column(Modifier.fillMaxSize()) {
            val cr = popularViewModel.getAllCurrency()
            cr.forEach {
                Text(text = "${it.id} Наименование валюты:${it.name} Цена:${it.price}P")
            }
        }
    }
}



