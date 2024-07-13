package com.abdulmateen.composeassesment.presentation.screens.login


data class LoginUIState(
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val loading: Boolean = false,
    val passwordVisibility: Boolean = false,
    val hasEmailError: Boolean = false,
    val emailError: String = "",
    val hasNameError: Boolean = false,
    val nameError: String = "",
    val hasPasswordError: Boolean = false,
    val passwordError: String = ""
)