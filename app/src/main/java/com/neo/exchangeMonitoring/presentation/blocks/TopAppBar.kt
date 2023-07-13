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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.neo.exchangeMonitoring.utils.SortingStates

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    textState: MutableState<TextFieldValue>,
    sortState: MutableState<SortingStates>,
    modifier: Modifier = Modifier,
    expanded: MutableState<Boolean>,

    ) {
    TextField(
        value = textState.value, onValueChange = { value -> textState.value = value },
        modifier = modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = modifier
                    .padding(14.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            Row {
                if (textState.value != TextFieldValue("")) {
                    IconButton(onClick = { textState.value = TextFieldValue("") }) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                            modifier
                                .padding(10.dp)
                                .size(24.dp)
                        )
                    }
                }
                IconButton(onClick = { expanded.value = true }) {
                    Icon(
                        Icons.Outlined.Sort,
                        contentDescription = "",
                        modifier = modifier
                            .padding(10.dp)
                            .size(24.dp)
                    )
                }
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }) {
                    DropdownMenuItem(
                        text = { Text("По возрастанию алфавита") },
                        onClick = {
                            sortState.value = SortingStates.SORT_BY_ALPHABET_IN_ASCENDING_ORDER
                        })
                    DropdownMenuItem(
                        text = { Text("По убыванию алфавита") },
                        onClick = {
                            sortState.value = SortingStates.SORT_BY_ALPHABET_IN_DESCENDING_ORDER
                        })
                    DropdownMenuItem(
                        text = { Text("По возрастанию значения") },
                        onClick = {
                            sortState.value = SortingStates.SORT_BY_VALUES_IN_ASCENDING_ORDER
                        })
                    DropdownMenuItem(
                        text = { Text("По убыванию значения") },
                        onClick = {
                            sortState.value = SortingStates.SORT_BY_VALUES_IN_DESCENDING_ORDER
                        })
                    if (sortState.value != SortingStates.NONE) {
                        DropdownMenuItem(
                            text = { Text(text = "Сбросить сортировку") },
                            onClick = { sortState.value = SortingStates.NONE })
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