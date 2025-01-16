package com.bharat.fetchrewards.presentation.ui

import com.bharat.fetchrewards.domain.model.Reward

data class UIState (
    val isLoading: Boolean = false,
    val data: Map<Int, List<Reward>> = emptyMap(),
    val errorMessage : String? = null
)