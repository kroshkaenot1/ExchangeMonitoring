package com.neo.exchangeMonitoring.di

import android.content.Context
import com.neo.exchangeMonitoring.data.database.CurrencyDatabase
import com.neo.exchangeMonitoring.data.database.dao.CurrencyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDao(@ApplicationContext context: Context) : CurrencyDao =
        CurrencyDatabase.getInstance(context = context).getCurrencyDao()
}