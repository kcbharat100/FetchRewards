package com.bharat.fetchrewards.domain.repository

import com.bharat.fetchrewards.domain.model.Reward
import com.bharat.fetchrewards.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RewardRepository {
    suspend fun getRewards() : Flow<Resource<List<Reward>>>
}