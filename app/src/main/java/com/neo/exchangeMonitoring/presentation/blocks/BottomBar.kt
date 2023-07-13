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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

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