package com.neo.exchangeMonitoring.utils

import androidx.compose.ui.graphics.Color
import com.neo.exchangeMonitoring.data.database.entities.CurrencyDbEntity
import com.neo.exchangeMonitoring.data.remote.models.CurrencyRemote

fun Double.choiceSignDependingOnValue(): Pair<String, Color> {
    return if (this > 0) {
        Pair("+" + String.format("%.2f", this) + "₽", Color.Green)
    } else {
        Pair(String.format("%.2f", this) + "₽", Color.Red)
    }
}

fun CurrencyRemote.compareToCurrencyDb(currencyDbEntity: CurrencyDbEntity): Boolean =
    this.name.hashCode() == currencyDbEntity.name.hashCode()
            && this.charCode.hashCode() == currencyDbEntity.charCode.hashCode()
            && this.value == currencyDbEntity.value
            && this.previous == currencyDbEntity.previous