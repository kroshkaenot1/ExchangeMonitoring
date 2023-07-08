package com.neo.exchangeMonitoring.data.repositoryimpl

import com.neo.exchangeMonitoring.data.database.dao.CurrencyDao
import com.neo.exchangeMonitoring.data.database.entities.CurrencyDbEntity
import com.neo.exchangeMonitoring.data.mapper.CurrencyRemoteToDatabaseEntityMapper
import com.neo.exchangeMonitoring.data.remote.APICurrencyService
import com.neo.exchangeMonitoring.domain.repository.CurrencyRepository
import java.math.BigDecimal
import javax.inject.Inject


class CurrencyRepositoryImpl @Inject constructor(
    private val apiCurrencyService: APICurrencyService,
    private val currencyDao: CurrencyDao
) :
    CurrencyRepository {
    private val currencyRemoteToDatabaseEntityMapper = CurrencyRemoteToDatabaseEntityMapper()
    override fun addCurrency(name: String, price: BigDecimal) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCurrency(): List<CurrencyDbEntity> {
        val currencyRemoteList = apiCurrencyService.getCurrencies().valute.values.toList()
        currencyDao.clearCurrencyData()
        currencyRemoteList.forEach {
            currencyDao.insertNewCurrencyData(
                currencyRemoteToDatabaseEntityMapper.currencyRemoteToCurrencyDatabaseEntityMap(
                    it
                )
            )
        }
        return currencyDao.getAllCurrency()
    }

    override fun getCurrency() {
        TODO("Not yet implemented")
    }

    override fun updatePrice(name: String, price: BigDecimal) {
        TODO("Not yet implemented")
    }

    override fun deleteCurrency(name: String) {
        TODO("Not yet implemented")
    }
}