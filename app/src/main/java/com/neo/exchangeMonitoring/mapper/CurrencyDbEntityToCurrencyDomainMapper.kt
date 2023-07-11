package com.neo.exchangeMonitoring.mapper

import com.neo.exchangeMonitoring.data.database.entities.CurrencyDbEntity
import com.neo.exchangeMonitoring.domain.model.Currency

class CurrencyDbEntityToCurrencyDomainMapper {
    fun currencyDbEntityToCurrencyDomainMap(currencyDbEntity: CurrencyDbEntity): Currency =
        Currency(
            id = currencyDbEntity.id,
            charCode = currencyDbEntity.charCode,
            name = currencyDbEntity.name,
            price = currencyDbEntity.value,
            difference = currencyDbEntity.value - currencyDbEntity.previous,
            isFavorite = currencyDbEntity.isFavorite
        )
}