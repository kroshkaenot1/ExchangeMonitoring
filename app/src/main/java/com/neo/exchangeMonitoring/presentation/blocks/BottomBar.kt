package com.neo.exchangeMonitoring.presentation.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HotelClass
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.ExchancgeMontoring.R

const val WEIGHT_OF_COLUMNS = 1f

@Composable
fun BottomAppBar(modifier: Modifier = Modifier, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(dimensionResource(id = R.dimen.forty_five_dp)),
    ) {
        IconButton(
            onClick = { navController.navigate("currencies") },
            modifier = modifier
                .weight(WEIGHT_OF_COLUMNS)
                .fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Default.TrendingUp,
                contentDescription = stringResource(id = R.string.poplular_column_bar_descr),
            )
        }
        IconButton(
            onClick = { navController.navigate("favorites") },
            modifier = modifier
                .weight(WEIGHT_OF_COLUMNS)
                .fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Filled.HotelClass,
                contentDescription = stringResource(id = R.string.favorite_column_bar_descr),
            )
        }
    }
}