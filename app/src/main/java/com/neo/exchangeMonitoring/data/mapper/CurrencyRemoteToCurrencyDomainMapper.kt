package com.neo.exchangeMonitoring.data.mapper

import com.neo.exchangeMonitoring.data.remote.models.CurrencyRemote
import com.neo.exchangeMonitoring.domain.model.Currency

class CurrencyRemoteToCurrencyDomainMapper {
    fun currencyRemoteToCurrencyDomainMap(currencyRemote: CurrencyRemote): Currency =
        Currency(
            charCode = currencyRemote.charCode,
            name = currencyRemote.name,
            price = currencyRemote.value,
            difference = currencyRemote.value - currencyRemote.previous
        )
}