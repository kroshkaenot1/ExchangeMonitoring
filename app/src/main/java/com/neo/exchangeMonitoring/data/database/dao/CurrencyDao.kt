package com.neo.exchangeMonitoring.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.neo.exchangeMonitoring.data.database.entities.CurrencyDbEntity

@Dao
interface CurrencyDao {
    @Insert(entity = CurrencyDbEntity::class)
    suspend fun insertNewCurrencyData(currency:CurrencyDbEntity)
    @Query("DELETE FROM currency")
    suspend fun clearCurrencyData()

}