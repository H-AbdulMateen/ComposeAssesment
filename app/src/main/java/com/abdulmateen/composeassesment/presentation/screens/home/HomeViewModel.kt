package com.abdulmateen.composeassesment.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulmateen.composeassesment.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {
    val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()
    init {
        getUserDetails()
        fetchMedicineList()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

    private fun fetchMedicineList() {
        viewModelScope.launch {
            repository.fetchMedicineList().collect{data ->
                _uiState.update {
                    it.copy(
                        medicineList = data
                    )
                }
            }
        }
    }

    private fun getUserDetails() {
        viewModelScope.launch {
            repository.getUserInfo().collect{data ->
                _uiState.update {
                    it.copy(
                        email = data.email,
                        username = data.name
                    )
                }
            }
        }
    }




}