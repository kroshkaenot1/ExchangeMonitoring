package com.neo.exchangeMonitoring

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.HotelClass
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.outlined.Sort
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neo.exchangeMonitoring.presentation.favorite.FavoriteCurrencies
import com.neo.exchangeMonitoring.presentation.popular.PopularCurrencies
import com.neo.exchangeMonitoring.utils.SortingStates
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val textState = remember {
                mutableStateOf(TextFieldValue(""))
            }
            val expanded = remember {
                mutableStateOf(false)
            }
            val sortState = remember {
                mutableStateOf(SortingStates.NONE)
            }
            Scaffold(topBar = {
                TopAppBar(
                    textState = textState,
                    expanded = expanded,
                    sortState = sortState
                )
            },
                bottomBar = { BottomAppBar(navController = navController) })
            { paddingValues ->
                NavHost(navController = navController, startDestination = "currencies") {
                    composable("currencies") {
                        PopularCurrencies(
                            textState = textState,
                            paddingValue = paddingValues,
                            sortState = sortState
                        )
                    }
                    composable("favorites") {
                        FavoriteCurrencies(
                            textState = textState,
                            paddingValue = paddingValues,
                            sortState = sortState
                        )
                    }
                }
            }
        }
    }

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

    @Composable
    fun BottomAppBar(modifier: Modifier = Modifier, navController: NavController) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .height(45.dp),
        ) {
            IconButton(
                onClick = { navController.navigate("currencies") },
                modifier = modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                Icon(
                    imageVector = Icons.Default.TrendingUp,
                    contentDescription = "Популярное",
                )
            }
            IconButton(
                onClick = { navController.navigate("favorites") },
                modifier = modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                Icon(
                    imageVector = Icons.Filled.HotelClass,
                    contentDescription = "Избранное",
                )
            }
        }
    }
}



