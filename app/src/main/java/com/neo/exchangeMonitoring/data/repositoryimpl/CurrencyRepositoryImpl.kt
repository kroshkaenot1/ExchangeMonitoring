package com.neo.exchangeMonitoring.data.repositoryimpl

import com.neo.exchangeMonitoring.data.database.dao.CurrencyDao
import com.neo.exchangeMonitoring.data.mapper.CurrencyRemoteToDatabaseEntityMapper
import com.neo.exchangeMonitoring.data.remote.APICurrencyService
import com.neo.exchangeMonitoring.data.remote.models.CurrencyRemote
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

    override suspend fun getAllCurrency(): List<CurrencyRemote> {
        //Тест добавления данных в room
        val cl = apiCurrencyService.getCurrencies().valute.values.toList().first()
        val currencyDbEntity =
            currencyRemoteToDatabaseEntityMapper.currencyRemoteToCurrencyDatabaseEntityMap(cl)
        currencyDao.clearCurrencyData()
        currencyDao.insertNewCurrencyData(currencyDbEntity)

        return apiCurrencyService.getCurrencies().valute.values.toList()
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