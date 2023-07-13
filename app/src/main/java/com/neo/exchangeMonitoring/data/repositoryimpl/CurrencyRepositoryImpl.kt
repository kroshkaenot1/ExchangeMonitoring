package com.neo.exchangeMonitoring.data.repositoryimpl

import com.neo.exchangeMonitoring.data.database.dao.CurrencyDao
import com.neo.exchangeMonitoring.data.database.entities.CurrencyDbEntity
import com.neo.exchangeMonitoring.data.remote.APICurrencyService
import com.neo.exchangeMonitoring.data.remote.models.CurrencyRemote
import com.neo.exchangeMonitoring.domain.repository.CurrencyRepository
import com.neo.exchangeMonitoring.mapper.CurrencyRemoteToDatabaseEntityMapper
import com.neo.exchangeMonitoring.utils.compareToCurrencyDb
import javax.inject.Inject


class CurrencyRepositoryImpl @Inject constructor(
    private val apiCurrencyService: APICurrencyService,
    private val currencyDao: CurrencyDao
) :
    CurrencyRepository {
    private val currencyRemoteToDatabaseEntityMapper = CurrencyRemoteToDatabaseEntityMapper()

    override suspend fun getAllCurrency(): List<CurrencyDbEntity> {
        val currencyRemoteList = apiCurrencyService.getCurrencies().valute.values.toList()
        val currencyDbList = currencyDao.getAllCurrency()
        val currencyRemoteListCount = currencyRemoteList.count()
        val currencyDbListCount = currencyDbList.count()
        //проверка устаревших данных
        if (currencyRemoteListCount != currencyDbListCount) {
            overwriteData(currencyRemoteList)
            return currencyDao.getAllCurrency()
        }
        for (i in 0 until currencyDbListCount) {
            if (!currencyRemoteList[i].compareToCurrencyDb(currencyDbList[i])) {
                overwriteData(currencyRemoteList)
                return currencyDao.getAllCurrency()
            }
        }
        return currencyDbList
    }

    override suspend fun getAllFavoriteCurrency(): List<CurrencyDbEntity> {
        return currencyDao.getAllFavoriteCurrency()
    }

    override suspend fun getAllCurrencyBySubString(name: String): List<CurrencyDbEntity> {
        return currencyDao.getAllCurrencyBySubString(name)
    }

    override suspend fun getAllFavoriteCurrencyBySubString(name: String): List<CurrencyDbEntity> {
        return currencyDao.getAllFavoriteCurrencyBySubString(name)
    }

    override suspend fun changeCurrencyFavorite(id: Long) {
        currencyDao.changeCurrencyFavorite(id)
    }

    private suspend fun overwriteData(currencyRemoteList: List<CurrencyRemote>) {
        currencyDao.clearCurrencyData()
        currencyRemoteList.forEach {
            currencyDao.insertNewCurrencyData(
                currencyRemoteToDatabaseEntityMapper.currencyRemoteToCurrencyDatabaseEntityMap(
                    it
                )
            )
        }
    }
}