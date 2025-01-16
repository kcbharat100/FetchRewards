package com.bharat.fetchrewards.domain.use_case

import com.bharat.fetchrewards.domain.model.Reward
import com.bharat.fetchrewards.domain.repository.RewardRepository
import com.bharat.fetchrewards.utils.AppError
import com.bharat.fetchrewards.utils.Resource
import com.bharat.fetchrewards.utils.ResourceProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRewardUseCase @Inject constructor(
    private val repository: RewardRepository
) {
    suspend operator fun invoke(): Flow<Resource<Map<Int, List<Reward>>>> {

        return repository.getRewards().map { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Resource.Loading
                }

                is Resource.Success -> {

                   // Filter out rewards with null or empty names
                    val filteredData = resource.data.filter { !it.name.isNullOrBlank() }

                    // Group the data by listId and sort it
                    val sortedGroupedDataById  = filteredData.groupBy { it.listId }.toSortedMap()

                    //Sort the grouped data by name
                    val sortedGroupedDataByName = sortedGroupedDataById.mapValues { (_, rewardsList) ->
                        rewardsList.sortedBy { it.name?.filter { char -> char.isDigit()}?.toInt() }
                    }

                    // Return final data
                    Resource.Success(sortedGroupedDataByName)

                }

                is Resource.Error -> {
                    Resource.Error(resource.error)
                }
            }
        }
    }
}
