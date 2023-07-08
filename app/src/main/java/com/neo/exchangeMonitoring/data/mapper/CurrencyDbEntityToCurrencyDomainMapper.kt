package com.neo.exchangeMonitoring.data.mapper

import com.neo.exchangeMonitoring.data.database.entities.CurrencyDbEntity
import com.neo.exchangeMonitoring.domain.model.Currency

class CurrencyDbEntityToCurrencyDomainMapper {
    fun currencyDbEntityToCurrencyDomainMap(currencyDbEntity: CurrencyDbEntity): Currency =
        Currency(
            charCode = currencyDbEntity.charCode,
            name = currencyDbEntity.name,
            price = currencyDbEntity.value,
            difference = currencyDbEntity.value - currencyDbEntity.previous,
            isFavorite = currencyDbEntity.isFavorite
        )
}