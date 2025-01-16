package com.bharat.fetchrewards.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharat.fetchrewards.R
import com.bharat.fetchrewards.domain.use_case.GetRewardUseCase
import com.bharat.fetchrewards.presentation.ui.UIState
import com.bharat.fetchrewards.utils.AppError
import com.bharat.fetchrewards.utils.Resource
import com.bharat.fetchrewards.utils.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RewardViewModel @Inject constructor(
    private val getRewardUseCase: GetRewardUseCase,
    private val resourceProvider: ResourceProvider
): ViewModel() {

private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState

    init {
        fetchData()
    }


    fun fetchData(){
        viewModelScope.launch {

            //Emit Loading
            _uiState.value = UIState(isLoading = true)

            //Fetch data
            getRewardUseCase.invoke().collect{
                resource ->
                when(resource){

                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        _uiState.value = UIState(
                            isLoading = false,
                            data = resource.data,
                            errorMessage = null
                        )
                    }

                    is Resource.Error -> {
                        // Handle AppError explicitly and map to user-friendly error messages
                        val errorMessage = when (resource.error) {
                            is AppError.NetworkError -> resourceProvider.getString(R.string.error_network)

                            is AppError.HttpError -> resourceProvider.getString( R.string.error_http,
                                (resource.error as AppError.HttpError).message ?: resourceProvider.getString(R.string.error_unknown))

                            is AppError.UnexpectedError -> resourceProvider.getString(R.string.error_unexpected,
                                (resource.error as AppError.UnexpectedError).message ?: resourceProvider.getString(R.string.error_unknown)
                            )
                            else -> resourceProvider.getString(R.string.error_unknown)
                        }

                        _uiState.value = UIState(
                            isLoading = false,
                            data = emptyMap(),
                            errorMessage = errorMessage
                        )
                    }
                }
            }

        }
    }
}
