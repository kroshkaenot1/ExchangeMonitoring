package com.neo.exchangeMonitoring.data.repositoryimpl

import com.neo.exchangeMonitoring.data.database.dao.CurrencyDao
import com.neo.exchangeMonitoring.data.database.entities.CurrencyDbEntity
import com.neo.exchangeMonitoring.data.remote.APICurrencyService
import com.neo.exchangeMonitoring.data.remote.models.CurrencyRemote
import com.neo.exchangeMonitoring.domain.repository.CurrencyRepository
import com.neo.exchangeMonitoring.mapper.CurrencyRemoteToDatabaseEntityMapper
import com.neo.exchangeMonitoring.utils.SortingStatesCurrency
import com.neo.exchangeMonitoring.utils.compareToCurrencyDb
import javax.inject.Inject


class CurrencyRepositoryImpl @Inject constructor(
    private val apiCurrencyService: APICurrencyService,
    private val currencyDao: CurrencyDao,
    private val currencyRemoteToDatabaseEntityMapper: CurrencyRemoteToDatabaseEntityMapper
) :
    CurrencyRepository {
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

    override suspend fun getAllSortedCurrency(
        sortBy: Enum<SortingStatesCurrency>,
        name: String
    ): List<CurrencyDbEntity> =
        when (sortBy) {
            SortingStatesCurrency.SORT_BY_ALPHABET_IN_ASCENDING_ORDER -> {
                currencyDao.getAllCurrencySortedByAlphASC(name)
            }

            SortingStatesCurrency.SORT_BY_ALPHABET_IN_DESCENDING_ORDER -> {
                currencyDao.getAllCurrencySortedByAlphDESC(name)
            }

            SortingStatesCurrency.SORT_BY_VALUES_IN_ASCENDING_ORDER -> {
                currencyDao.getAllCurrencySortedByValuesASC(name)
            }

            SortingStatesCurrency.SORT_BY_VALUES_IN_DESCENDING_ORDER -> {
                currencyDao.getAllCurrencySortedByValuesDESC(name)
            }

            SortingStatesCurrency.NONE -> currencyDao.getAllCurrencyBySubString(name)
            else -> { emptyList() }
        }

    override suspend fun getAllSortedFavoriteCurrency(
        sortBy: Enum<SortingStatesCurrency>,
        name: String
    ): List<CurrencyDbEntity> =
        when (sortBy) {
            SortingStatesCurrency.SORT_BY_ALPHABET_IN_ASCENDING_ORDER -> {
                currencyDao.getAllFavoriteCurrencySortedByAlphASC(name)
            }

            SortingStatesCurrency.SORT_BY_ALPHABET_IN_DESCENDING_ORDER -> {
                currencyDao.getAllFavoriteCurrencySortedByAlphDESC(name)
            }

            SortingStatesCurrency.SORT_BY_VALUES_IN_ASCENDING_ORDER -> {
                currencyDao.getAllFavoriteCurrencySortedByValuesASC(name)
            }

            SortingStatesCurrency.SORT_BY_VALUES_IN_DESCENDING_ORDER -> {
                currencyDao.getAllFavoriteCurrencySortedByValuesDESC(name)
            }

            SortingStatesCurrency.NONE -> currencyDao.getAllFavoriteCurrencyBySubString(name)
            else -> { emptyList() }
        }

    override suspend fun changeCurrencyFavorite(id: Long) {
        currencyDao.changeCurrencyFavorite(id)
    }

    private suspend fun overwriteData(currencyRemoteList: List<CurrencyRemote>) {
        currencyDao.clearCurrencyData()
        currencyRemoteList.forEach { currencyRemote ->
            currencyDao.insertNewCurrencyData(
                currencyRemoteToDatabaseEntityMapper.currencyRemoteToCurrencyDatabaseEntityMap(
                    currencyRemote
                )
            )
        }
    }
}