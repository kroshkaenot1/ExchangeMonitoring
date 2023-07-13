package com.neo.exchangeMonitoring

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neo.exchangeMonitoring.presentation.blocks.BottomAppBar
import com.neo.exchangeMonitoring.presentation.blocks.TopAppBar
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
}



