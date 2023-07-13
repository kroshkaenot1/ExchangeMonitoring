package com.neo.exchangeMonitoring.di

import com.neo.exchangeMonitoring.domain.repository.CurrencyRepository
import com.neo.exchangeMonitoring.domain.usecase.ChangeCurrencyFavoriteUseCase
import com.neo.exchangeMonitoring.domain.usecase.GetAllCurrenciesUseCase
import com.neo.exchangeMonitoring.domain.usecase.GetFavoriteCurrenciesUseCase
import com.neo.exchangeMonitoring.domain.usecase.SortingCurrenciesUseCase
import com.neo.exchangeMonitoring.domain.usecase.SortingFavoriteCurrenciesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Singleton
    @Provides
    fun provideCurrencyRepositoryToGetAllCurrenciesUseCase(Impl: CurrencyRepository) =
        GetAllCurrenciesUseCase(currencyRepository = Impl)

    @Singleton
    @Provides
    fun provideCurrencyRepositoryToMakeCurrencyFavoriteUseCase(Impl: CurrencyRepository) =
        ChangeCurrencyFavoriteUseCase(currencyRepository = Impl)

    @Singleton
    @Provides
    fun provideCurrencyRepositoryToGetFavoriteCurrenciesUseCase(Impl: CurrencyRepository) =
        GetFavoriteCurrenciesUseCase(currencyRepository = Impl)

    @Singleton
    @Provides
    fun provideCurrencyRepositoryToSortingCurrenciesUseCase(Impl: CurrencyRepository) =
        SortingCurrenciesUseCase(currencyRepository = Impl)

    @Singleton
    @Provides
    fun provideCurrencyRepositoryToSortingFavoriteCurrenciesUseCase(Impl: CurrencyRepository) =
        SortingFavoriteCurrenciesUseCase(currencyRepository = Impl)
}