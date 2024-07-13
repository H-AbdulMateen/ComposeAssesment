package com.abdulmateen.composeassesment.presentation.screens.home

import com.abdulmateen.composeassesment.data.remote.dto.Medication

data class HomeUIState(
    val isLoading: Boolean = false,
    val email: String = "",
    val username: String = "",
    val medicineList: List<Medication> = emptyList()
)
