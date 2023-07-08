package com.neo.exchangeMonitoring.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_currency",
    indices = [Index("id")],
    foreignKeys = [ForeignKey(entity = CurrencyDbEntity::class,
        parentColumns = ["id"],
        childColumns = ["currency_id"],
        onDelete = CASCADE,
        onUpdate = CASCADE)])
data class FavoriteCurrenciesDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "currency_id") val currencyId : Long
)
