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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ExchancgeMontoring.R
import com.neo.exchangeMonitoring.domain.model.Currency
import com.neo.exchangeMonitoring.presentation.blocks.Header
import com.neo.exchangeMonitoring.utils.SortingStatesCurrency
import com.neo.exchangeMonitoring.utils.choiceSignDependingOnValue

val SEPARATOR_COLOR = Color(0xffe6f4fd)
val SECOND_COLUMN_COLOR_BACKGROUND = Color(0xffebf4fb)
const val WEIGHT_CHARCODE_COLUMN = 1f
const val WEIGHT_NAME_COLUMN = 1.5f
const val WEIGHT_PRICE_COLUMN = 1f
const val WEIGHT_DIFFERENCE_COLUMN = 1f
const val WEIGHT_FAVORITE_COLUMN = 0.8f
val NUMBERS_FONT = FontFamily(Font(R.font.consolas_regular))
val HEADER_FONT = FontFamily(Font(R.font.centurion))
val SECOND_COLUMN_FONT = FontFamily(Font(R.font.departura))

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PopularCurrencies(
    modifier: Modifier = Modifier,
    textState: MutableState<TextFieldValue>,
    sortState: MutableState<SortingStatesCurrency>,
    paddingValue: PaddingValues
) {

    val popularCurrenciesViewModel = hiltViewModel<PopularCurrenciesViewModel>()
    val currencies = popularCurrenciesViewModel.listOfCurrency.collectAsState().value
    LazyColumn(
        modifier = modifier
            .padding(paddingValue)
            .fillMaxSize()
    ) {
        stickyHeader {
            Header()
        }
        if (textState.value.text.isNotEmpty() || sortState.value != SortingStatesCurrency.NONE) {
            popularCurrenciesViewModel.getAllSortedCurrency(
                sortBy = sortState.value,
                name = textState.value.text
            )
        } else {
            popularCurrenciesViewModel.getAllCurrency()
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
            .border(BorderStroke(1.dp, SEPARATOR_COLOR))
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.three_dp))
    ) {
        Text(
            text = currency.charCode,
            modifier = modifier
                .weight(WEIGHT_CHARCODE_COLUMN)
                .padding(dimensionResource(id = R.dimen.ten_dp)),
            fontFamily = HEADER_FONT,
            textAlign = TextAlign.Center
        )
        Text(
            text = currency.name, modifier = modifier
                .background(SECOND_COLUMN_COLOR_BACKGROUND)
                .weight(WEIGHT_NAME_COLUMN)
                .padding(dimensionResource(id = R.dimen.forteen_dp)),
            fontFamily = SECOND_COLUMN_FONT,
            textAlign = TextAlign.Center
        )
        Text(
            text = String.format("%.2f", currency.price),
            modifier = modifier
                .fillMaxSize()
                .weight(WEIGHT_PRICE_COLUMN)
                .padding(dimensionResource(id = R.dimen.six_dp)),
            fontFamily = NUMBERS_FONT,
            textAlign = TextAlign.Center
        )
        val text = currency.difference.choiceSignDependingOnValue().first
        val textColor = currency.difference.choiceSignDependingOnValue().second
        Text(
            text = text,
            color = textColor,
            modifier = modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.six_dp))
                .weight(WEIGHT_DIFFERENCE_COLUMN),
            fontFamily = NUMBERS_FONT,
            textAlign = TextAlign.Center
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
                contentDescription = stringResource(id = R.string.change_favorite_currency),
                modifier = modifier.weight(WEIGHT_FAVORITE_COLUMN)
            )
        }
    }
}