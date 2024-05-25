package com.example.tubespam_harmoni

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _signUpResult = MutableLiveData<Result>()
    val signUpResult: LiveData<Result> = _signUpResult

    fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _signUpResult.value = Result.Success
                } else {
                    _signUpResult.value = Result.Error(task.exception?.message ?: "Unknown error")
                }
            }
    }

    sealed class Result {
        object Success : Result()
        data class Error(val message: String) : Result()
    }
}
