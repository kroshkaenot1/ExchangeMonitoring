package com.neo.exchangeMonitoring.presentation.popular

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.neo.exchangeMonitoring.domain.model.Currency
import com.neo.exchangeMonitoring.utils.choiceSignDependingOnValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PopularCurrencies(
    modifier: Modifier = Modifier,
    textState: MutableState<TextFieldValue>,
    paddingValue: PaddingValues
) {

    val popularCurrenciesViewModel = hiltViewModel<PopularCurrenciesViewModel>()
    val currencies = popularCurrenciesViewModel.listOfCurrency.collectAsState().value
    var searchUsedBefore = false
    LazyColumn(
        modifier = modifier
            .background(Color(200, 164, 100))
            .padding(paddingValue)
            .fillMaxSize()
    ) {
        stickyHeader {
            Header()
        }
        if (textState.value.text.isNotEmpty()) {
            popularCurrenciesViewModel.getAllCurrencyBySubString(textState.value.text)
            searchUsedBefore = true
        } else {
            if (searchUsedBefore) {
                popularCurrenciesViewModel.getAllCurrency()
                searchUsedBefore = false
            }
        }
        itemsIndexed(currencies) { _, currency ->
            Currency(
                currency = currency,
                popularCurrenciesViewModel = popularCurrenciesViewModel
            )
        }
    }
}

@Composable
fun Currency(
    modifier: Modifier = Modifier,
    currency: Currency,
    popularCurrenciesViewModel: PopularCurrenciesViewModel
) {
    val isCurrencyChecked = remember {
        mutableStateOf(currency.isFavorite)
    }
    Row(
        modifier = modifier
            .border(BorderStroke(1.dp, Color.Gray))
            .fillMaxWidth()
            .padding(3.dp)
    ) {
        Text(
            text = currency.charCode,
            modifier = modifier
                .weight(0.8f)
                .padding(10.dp),
            fontWeight = FontWeight.Bold
        )

        Text(
            text = currency.name, modifier = modifier
                .weight(1.4f)
                .padding(6.dp)
        )

        Text(
            text = String.format("%.2f", currency.price) + "₽",
            modifier = modifier
                .fillMaxSize()
                .weight(1f)
                .padding(6.dp)
        )
        val text = currency.difference.choiceSignDependingOnValue().first
        val textColor = currency.difference.choiceSignDependingOnValue().second
        Text(
            text = text,
            color = textColor,
            modifier = modifier
                .fillMaxSize()
                .padding(6.dp)
                .weight(1f)
        )
        IconButton(onClick = {
            popularCurrenciesViewModel.changeCurrencyFavorite(currency.id)
            isCurrencyChecked.value = !isCurrencyChecked.value
        }) {
            Icon(
                imageVector = if (isCurrencyChecked.value) {
                    Icons.Filled.Star
                } else {
                    Icons.Outlined.StarOutline
                },
                contentDescription = "Избранное",
                modifier = modifier.weight(1f)
            )
        }
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    val settingForTextHeaders = mapOf(
        "Валюта" to 0.8f,
        "Наименование" to 1.4f,
        "Цена" to 1f,
        "Изменение за последний час" to 1.4f
    )
    Row(
        modifier = modifier
            .border(BorderStroke(1.dp, Color.Black))
            .background(Color(19, 102, 46, 255))
            .padding(start = 4.dp, end = 1.dp)
            .fillMaxWidth()
    ) {
        settingForTextHeaders.forEach {
            Text(
                text = it.key, modifier = modifier.weight(it.value),
                fontWeight = FontWeight.Bold
            )
        }
    }
}