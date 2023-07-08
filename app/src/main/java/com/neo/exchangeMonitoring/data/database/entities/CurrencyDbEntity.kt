package com.neo.exchangeMonitoring.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "currency", indices = [Index("id")])
data class CurrencyDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "char_code") val charCode : String,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "value") val value : Double,
    @ColumnInfo(name = "previous") val previous : Double,
    @ColumnInfo(name = "is_favorite") val isFavorite : Boolean
)
