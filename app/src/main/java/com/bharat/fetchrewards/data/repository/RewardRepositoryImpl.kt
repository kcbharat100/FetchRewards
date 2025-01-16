package com.bharat.fetchrewards.data.repository


import com.bharat.fetchrewards.data.remote.ApiService
import com.bharat.fetchrewards.data.mappers.toReward
import com.bharat.fetchrewards.domain.model.Reward
import com.bharat.fetchrewards.domain.repository.RewardRepository
import com.bharat.fetchrewards.utils.AppError
import com.bharat.fetchrewards.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class RewardRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): RewardRepository {

    override suspend fun getRewards(): Flow<Resource<List<Reward>>> =
         flow {
             emit(Resource.Loading)
            try {
                val response = apiService.getRewards()
                val rewards = response.map{ it -> it.toReward()}
                emit(Resource.Success(rewards))

            } catch (e: IOException){
                 emit(Resource.Error(AppError.NetworkError))
             } catch (e: HttpException){
                 emit(Resource.Error(AppError.HttpError(e.code(), e.message())))
             } catch (e: Exception){
                 emit(Resource.Error(AppError.UnexpectedError(e.localizedMessage)))
            }

        }.flowOn(Dispatchers.IO)

}