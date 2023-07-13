package com.neo.exchangeMonitoring.presentation.blocks

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Header(modifier: Modifier = Modifier) {
    val settingForTextHeaders = mapOf(
        "Валюта" to 0.8f,
        "Наименование" to 1.4f,
        "Цена" to 1f,
        "Изменение за последний час" to 1.4f,
        "" to 0.8f
    )
    Row(
        modifier = modifier
            .border(BorderStroke(1.dp, Color.Black))
            .background(Color(19, 102, 46, 255))
            .padding(start = 6.dp, end = 1.dp)
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