package com.neo.exchangeMonitoring.mapper

import com.neo.exchangeMonitoring.data.database.entities.CurrencyDbEntity
import com.neo.exchangeMonitoring.data.remote.models.CurrencyRemote

class CurrencyRemoteToDatabaseEntityMapper {
    fun currencyRemoteToCurrencyDatabaseEntityMap(currencyRemote: CurrencyRemote) =
        CurrencyDbEntity(
            charCode = currencyRemote.charCode,
            name = currencyRemote.name,
            value = currencyRemote.value / currencyRemote.nominal,
            previous = currencyRemote.previous / currencyRemote.nominal,
            isFavorite = false
        )
}