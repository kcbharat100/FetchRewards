package com.bharat.fetchrewards.data.mappers

import com.bharat.fetchrewards.data.model.RewardDTO
import com.bharat.fetchrewards.domain.model.Reward

fun RewardDTO.toReward(): Reward {

    return Reward(
            id = this.id,
            listId = this.listId,
            name = this.name
            )
}