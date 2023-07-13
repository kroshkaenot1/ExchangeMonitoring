package com.neo.exchangeMonitoring.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.neo.exchangeMonitoring.data.database.entities.CurrencyDbEntity

@Dao
interface CurrencyDao {
    @Insert(entity = CurrencyDbEntity::class)
    suspend fun insertNewCurrencyData(currency: CurrencyDbEntity)

    @Query("DELETE FROM currency")
    suspend fun clearCurrencyData()

    @Query("SELECT * FROM currency")
    suspend fun getAllCurrency(): List<CurrencyDbEntity>

    @Query("UPDATE currency SET is_favorite = NOT is_favorite WHERE id =:id")
    suspend fun changeCurrencyFavorite(id: Long)

    @Query("SELECT * FROM currency WHERE is_favorite = 1")
    suspend fun getAllFavoriteCurrency(): List<CurrencyDbEntity>

    @Query("SELECT * FROM currency WHERE name LIKE '%' || :name || '%' OR char_code LIKE '%' || :name || '%'")
    suspend fun getAllCurrencyBySubString(name: String): List<CurrencyDbEntity>

    //поиск и сортировка неизбранных валют
    @Query("SELECT * FROM currency WHERE name LIKE '%' || :name || '%' OR char_code LIKE '%' || :name || '%' AND is_favorite = 1")
    suspend fun getAllFavoriteCurrencyBySubString(name: String): List<CurrencyDbEntity>

    @Query("SELECT * FROM currency WHERE name LIKE '%' || :name || '%' OR char_code LIKE '%' || :name || '%' ORDER BY name ASC ")
    suspend fun getAllCurrencySortedByAlphASC(name: String): List<CurrencyDbEntity>

    @Query("SELECT * FROM currency WHERE name LIKE '%' || :name || '%' OR char_code LIKE '%' || :name || '%' ORDER BY name DESC ")
    suspend fun getAllCurrencySortedByAlphDESC(name: String): List<CurrencyDbEntity>

    @Query("SELECT * FROM currency WHERE name LIKE '%' || :name || '%' OR char_code LIKE '%' || :name || '%' ORDER BY value ASC ")
    suspend fun getAllCurrencySortedByValuesASC(name: String): List<CurrencyDbEntity>

    @Query("SELECT * FROM currency WHERE name LIKE '%' || :name || '%' OR char_code LIKE '%' || :name || '%' ORDER BY value DESC ")
    suspend fun getAllCurrencySortedByValuesDESC(name: String): List<CurrencyDbEntity>

    //поиск и сортировка избранных валют
    @Query("SELECT * FROM currency WHERE (name LIKE '%' || :name || '%' OR char_code LIKE '%' || :name || '%') AND is_favorite = 1 ORDER BY name ASC")
    suspend fun getAllFavoriteCurrencySortedByAlphASC(name: String): List<CurrencyDbEntity>
    @Query("SELECT * FROM currency WHERE (name LIKE '%' || :name || '%' OR char_code LIKE '%' || :name || '%') AND is_favorite = 1 ORDER BY name DESC")
    suspend fun getAllFavoriteCurrencySortedByAlphDESC(name: String): List<CurrencyDbEntity>
    @Query("SELECT * FROM currency WHERE (name LIKE '%' || :name || '%' OR char_code LIKE '%' || :name || '%') AND is_favorite = 1 ORDER BY value ASC")
    suspend fun getAllFavoriteCurrencySortedByValuesASC(name: String): List<CurrencyDbEntity>
    @Query("SELECT * FROM currency WHERE (name LIKE '%' || :name || '%' OR char_code LIKE '%' || :name || '%') AND is_favorite = 1 ORDER BY value DESC")
    suspend fun getAllFavoriteCurrencySortedByValuesDESC(name: String): List<CurrencyDbEntity>

}