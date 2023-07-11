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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
            Scaffold(topBar = { TopAppBar(state = textState) },
                bottomBar = { BottomAppBar(navController = navController) })
            { paddingValues ->
                NavHost(navController = navController, startDestination = "currencies") {
                    composable("currencies") {
                        PopularCurrencies(
                            textState = textState,
                            paddingValue = paddingValues
                        )
                    }
                    composable("favorites") {
                        FavoriteCurrencies(
                            textState = textState,
                            paddingValue = paddingValues
                        )
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopAppBar(state: MutableState<TextFieldValue>, modifier: Modifier = Modifier) {
        TextField(
            value = state.value, onValueChange = { value -> state.value = value },
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
                    if (state.value != TextFieldValue("")) {
                        IconButton(onClick = { state.value = TextFieldValue("") }) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "",
                                modifier
                                    .padding(10.dp)
                                    .size(24.dp)
                            )
                        }
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Outlined.Sort,
                            contentDescription = "",
                            modifier = modifier
                                .padding(10.dp)
                                .size(24.dp)
                        )
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



