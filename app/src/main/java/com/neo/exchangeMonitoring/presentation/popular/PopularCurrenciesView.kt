package com.neo.exchangeMonitoring.presentation.popular

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.neo.exchangeMonitoring.data.remote.models.CurrencyRemote

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PopularCurrencies(modifier: Modifier = Modifier) {
    val popularCurrenciesViewModel = hiltViewModel<PopularCurrenciesViewModel>()
    val currencies = popularCurrenciesViewModel.listOfCurrency.collectAsState().value
    LazyColumn(
        modifier = modifier
            .background(Color(160, 164, 100)),
    ) {
        stickyHeader {
            Header()
        }
        itemsIndexed(currencies) { _, item ->
            Currency(
                item = item,
            )
        }
    }
}

@Composable
fun Currency(
    modifier: Modifier = Modifier,
    item: CurrencyRemote,
) {
    Row(
        modifier = modifier
            .border(BorderStroke(1.dp, Color.Gray))
            .fillMaxWidth()
            .padding(3.dp)
    ) {
        Text(
            text = item.charCode,
            modifier = modifier
                .weight(0.8f)
                .padding(10.dp),
            fontWeight = FontWeight.Bold
        )

        Text(
            text = item.name,
            modifier = modifier
                .weight(1.4f)
        )

        Text(
            text = String.format("%.2f", item.value) + "₽",
            modifier = modifier
                .fillMaxSize()
                .weight(1f)
        )
        val differenceBetweenValueAndPrevious = item.value - item.previous
        val textColor: Color
        val text: String
        if (differenceBetweenValueAndPrevious > 0) {
            textColor = Color.Green
            text = "+" + String.format("%.2f", differenceBetweenValueAndPrevious) + "₽"
        } else {
            textColor = Color.Red
            text = "-" + String.format("%.2f", differenceBetweenValueAndPrevious) + "₽"
        }

        Text(
            text = text,
            color = textColor,
            modifier = modifier
                .fillMaxSize()
                .padding(6.dp)
                .weight(1f)
        )
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .border(BorderStroke(1.dp, Color.Black))
            .fillMaxWidth()
    ) {
        Text(
            text = "Валюта",
            modifier = modifier
                .weight(0.8f)
                .padding(start = 4.dp),
            fontWeight = FontWeight.Bold
        )
        Text(text = "Наименование", modifier = modifier.weight(1.4f), fontWeight = FontWeight.Bold)
        Text(
            text = "Цена",
            modifier = modifier
                .weight(1f)
                .padding(start = 16.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Изменение за последний час",
            modifier = modifier.weight(1.4f),
            fontWeight = FontWeight.Bold
        )
    }
}