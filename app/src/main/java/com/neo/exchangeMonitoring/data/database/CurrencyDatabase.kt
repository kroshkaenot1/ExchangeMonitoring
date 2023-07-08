package com.neo.exchangeMonitoring.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.neo.exchangeMonitoring.data.database.dao.CurrencyDao
import com.neo.exchangeMonitoring.data.database.entities.CurrencyDbEntity

@Database(
    version = 1,
    entities = [CurrencyDbEntity::class]
)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao

    companion object {
        private var database: CurrencyDatabase? = null

        @Synchronized
        fun getInstance(context: Context): CurrencyDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(
                    context = context,
                    CurrencyDatabase::class.java,
                    name = "ExchangeMonitoring.db"
                ).build()
                database as CurrencyDatabase
            } else {
                database as CurrencyDatabase
            }
        }
    }
}