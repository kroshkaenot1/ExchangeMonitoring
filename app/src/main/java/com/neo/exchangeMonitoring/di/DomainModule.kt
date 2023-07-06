package com.neo.exchangeMonitoring.di

import com.neo.exchangeMonitoring.domain.repository.CurrencyRepository
import com.neo.exchangeMonitoring.domain.usecase.GetAllCurrenciesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideCurrencyRepositoryToGetAllCurrenciesUseCase(Impl: CurrencyRepository) =
        GetAllCurrenciesUseCase(currencyRepository = Impl)
}