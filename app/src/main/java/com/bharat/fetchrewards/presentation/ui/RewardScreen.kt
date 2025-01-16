package com.bharat.fetchrewards.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bharat.fetchrewards.R
import com.bharat.fetchrewards.domain.model.Reward
import com.bharat.fetchrewards.presentation.viewmodel.RewardViewModel

@Composable
fun RewardsScreen(
    modifier: Modifier = Modifier,
    viewModel: RewardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Without using init block of viewmodel
//    LaunchedEffect(Unit) {
//        if (uiState.data.isEmpty() && !uiState.isLoading) {
//            viewModel.fetchData()
//        }
//    }

    when {
        uiState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        uiState.errorMessage != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = uiState.errorMessage!!,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
        uiState.data.isNotEmpty() -> {
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                uiState.data.forEach { (listId, rewardsList) ->
                    item {
                        CollapsibleRewardGroup(listId, rewardsList)
                    }
                }
            }
        }
        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.error_no_rewards),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun CollapsibleRewardGroup(listId: Int, rewards: List<Reward>, initiallyVisibleCount: Int = 3) {
    var isExpanded by remember { mutableStateOf(false) }
    val itemsToShow = if (isExpanded) rewards else rewards.take(initiallyVisibleCount)

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // ListId Header
        Text(
            text = stringResource(R.string.list_id, listId),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .clickable { isExpanded = !isExpanded }
                .padding(8.dp),
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )

        // Display rewards
        itemsToShow.forEach { reward ->
            RewardItem(reward)
        }

        if (rewards.size > initiallyVisibleCount) {
            Text(
                text = if (isExpanded) stringResource(R.string.show_less) else stringResource(R.string.show_more),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isExpanded = !isExpanded }
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
fun RewardItem(reward: Reward) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Reward Name
        Text(
            text = stringResource(R.string.reward_name, reward.name?: stringResource(R.string.unknown)),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        // Reward ID
//        Text(
//            text = "ID: ${reward.id}",
//            style = MaterialTheme.typography.bodySmall,
//            color = MaterialTheme.colorScheme.onSurfaceVariant
//        )
    }
}


