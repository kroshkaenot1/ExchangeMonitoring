package com.neo.exchangeMonitoring.presentation.blocks

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Sort
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.example.ExchancgeMontoring.R
import com.neo.exchangeMonitoring.utils.SortingStatesCurrency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    textState: MutableState<TextFieldValue>,
    sortState: MutableState<SortingStatesCurrency>,
    expanded: MutableState<Boolean>
) {
    TextField(
        value = textState.value, onValueChange = { value -> textState.value = value },
        modifier = modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = stringResource(id = R.string.top_bar_search_descr),
                modifier = modifier
                    .padding(dimensionResource(id = R.dimen.forteen_dp))
                    .size(dimensionResource(id = R.dimen.twenty_fore_dp))
            )
        },
        trailingIcon = {
            Row {
                if (textState.value != TextFieldValue("")) {
                    IconButton(onClick = { textState.value = TextFieldValue("") }) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = stringResource(id = R.string.top_bar_clear_descr),
                            modifier
                                .padding(dimensionResource(id = R.dimen.ten_dp))
                                .size(dimensionResource(id = R.dimen.twenty_fore_dp))
                        )
                    }
                }
                IconButton(onClick = { expanded.value = true }) {
                    Icon(
                        Icons.Outlined.Sort,
                        contentDescription = stringResource(id = R.string.top_bar_sort_descr),
                        modifier = modifier
                            .padding(dimensionResource(id = R.dimen.ten_dp))
                            .size(dimensionResource(id = R.dimen.twenty_fore_dp))
                    )
                }
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }) {
                    DropdownMenuItem(
                        text = { Text(stringResource(id = R.string.dropdown_menu_alph_asc)) },
                        onClick = {
                            sortState.value =
                                SortingStatesCurrency.SORT_BY_ALPHABET_IN_ASCENDING_ORDER
                        })
                    DropdownMenuItem(
                        text = { Text(stringResource(id = R.string.dropdown_menu_alph_desc)) },
                        onClick = {
                            sortState.value =
                                SortingStatesCurrency.SORT_BY_ALPHABET_IN_DESCENDING_ORDER
                        })
                    DropdownMenuItem(
                        text = { Text(stringResource(id = R.string.dropdown_menu_price_asc)) },
                        onClick = {
                            sortState.value =
                                SortingStatesCurrency.SORT_BY_VALUES_IN_ASCENDING_ORDER
                        })
                    DropdownMenuItem(
                        text = { Text(stringResource(id = R.string.dropdown_menu_price_desc)) },
                        onClick = {
                            sortState.value =
                                SortingStatesCurrency.SORT_BY_VALUES_IN_DESCENDING_ORDER
                        })
                    if (sortState.value != SortingStatesCurrency.NONE) {
                        DropdownMenuItem(
                            text = { Text(stringResource(id = R.string.dropdown_menu_discard_parametrs)) },
                            onClick = { sortState.value = SortingStatesCurrency.NONE })
                    }
                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            containerColor = Color.Gray
        )
    )
}