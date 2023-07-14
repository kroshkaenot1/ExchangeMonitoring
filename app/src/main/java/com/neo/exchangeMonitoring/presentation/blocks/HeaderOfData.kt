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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ExchancgeMontoring.R

val BACKGROUND_COLOR_DATA = Color(19, 102, 46, 255)
@Composable
fun Header(modifier: Modifier = Modifier) {
    val settingForTextHeaders = mapOf(
        stringResource(id = R.string.header_data_charcode) to 0.8f,
        stringResource(id = R.string.header_data_name) to 1.4f,
        stringResource(id = R.string.header_data_price) to 1f,
        stringResource(id = R.string.header_data_difference) to 1.4f,
        "" to 0.8f
    )
    Row(
        modifier = modifier
            .border(BorderStroke(1.dp, Color.Black))
            .background(BACKGROUND_COLOR_DATA)
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