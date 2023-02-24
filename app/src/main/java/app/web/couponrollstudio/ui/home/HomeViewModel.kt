package app.web.couponrollstudio.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.web.couponrollstudio.model.Store
import app.web.couponrollstudio.model.repository.StoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storeRepository: StoreRepository
) : ViewModel() {
    sealed class UiState {
        object Initial : UiState()
        object Loading : UiState()
        data class Success(val stores: List<Store>) : UiState()
        object Failure : UiState()
    }

    val uiState: MutableState<UiState> = mutableStateOf(UiState.Initial)

    fun onClick() {
        viewModelScope.launch {
            uiState.value = UiState.Loading
            runCatching {
                storeRepository.getStores()
            }.onSuccess {
                uiState.value = UiState.Success(stores = it)
            }.onFailure {
                uiState.value = UiState.Failure
            }
        }
    }
}

//data class HomeUiState(val itemList: List<Task> = listOf())