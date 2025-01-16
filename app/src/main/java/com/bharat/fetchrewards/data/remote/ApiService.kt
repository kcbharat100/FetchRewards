package com.bharat.fetchrewards.data.remote

import com.bharat.fetchrewards.data.model.RewardDTO
import retrofit2.http.GET

interface ApiService {
    @GET("/hiring.json")
    suspend fun getRewards() : List<RewardDTO>
}