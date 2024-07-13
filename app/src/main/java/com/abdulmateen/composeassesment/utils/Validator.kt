package com.abdulmateen.composeassesment.utils
import android.util.Patterns

object Validator {
    fun validateIsFieldEmpty(text: String): ValidationResult{
        return if (text.isEmpty()){
            ValidationResult(status = true, errorMessage = "Please Fill Field")
        }else{
            ValidationResult(status = false)
        }
    }

    fun validatePassword(text: String): ValidationResult{
        return if (text.isEmpty()){
            ValidationResult(status = true, errorMessage = "Please Fill Field")
        }else{
            ValidationResult(status = false)
        }
    }

    fun validateEmail(email: String): ValidationResult{
        return if (email.isEmpty()){
            ValidationResult(status = true, errorMessage = "Please Fill Field")
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            ValidationResult(status = true, errorMessage = "Please Enter valid Email")
        }else{
            ValidationResult(status = false)
        }
    }
}

data class ValidationResult(
    val status: Boolean = false,
    val errorMessage: String = ""
)