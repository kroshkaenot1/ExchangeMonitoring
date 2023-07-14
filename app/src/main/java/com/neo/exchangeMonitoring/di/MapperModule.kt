package com.neo.exchangeMonitoring.di

import com.neo.exchangeMonitoring.mapper.CurrencyDbEntityToCurrencyDomainMapper
import com.neo.exchangeMonitoring.mapper.CurrencyRemoteToDatabaseEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {
    @Singleton
    @Provides
    fun provideCurrencyRemoteToDatabaseEntityMapperToCurrencyRepositoryImpl() =
        CurrencyRemoteToDatabaseEntityMapper()
    @Singleton
    @Provides
    fun provideCurrencyDbEntityToCurrencyDomainMapperToUseCases() =
        CurrencyDbEntityToCurrencyDomainMapper()
}