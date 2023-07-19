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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ExchancgeMontoring.R

val BACKGROUND_COLOR_DATA = Color(0xffb8dbef)
val SEPARATOR_COLOR = Color(0xffe6f4fd)
val HEADER_FONT = FontFamily(Font(R.font.centurion))
@Composable
fun Header(modifier: Modifier = Modifier) {
    val settingForTextHeaders = mapOf(
        stringResource(id = R.string.header_data_charcode) to 1f,
        stringResource(id = R.string.header_data_name) to 1.5f,
        stringResource(id = R.string.header_data_price) to 1f,
        stringResource(id = R.string.header_data_difference) to 1f,
        "" to 0.8f
    )
    Row(
        modifier = modifier
            .border(BorderStroke(1.dp, SEPARATOR_COLOR))
            .background(BACKGROUND_COLOR_DATA)
            .fillMaxWidth()
    ) {
        settingForTextHeaders.forEach {
            Text(
                text = it.key,
                modifier = modifier
                    .weight(it.value)
                    .padding(5.dp),
                color = Color.White,
                fontFamily = HEADER_FONT,
                textAlign = TextAlign.Center
            )
        }
    }
}