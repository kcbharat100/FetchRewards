package com.bharat.fetchrewards.di.module

import com.bharat.fetchrewards.data.remote.ApiService
import com.bharat.fetchrewards.data.repository.RewardRepositoryImpl
import com.bharat.fetchrewards.domain.repository.RewardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideItemRepository(apiService: ApiService): RewardRepository {
        return RewardRepositoryImpl(apiService)
    }
}