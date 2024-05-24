package com.example.tubespam_harmoni

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    fun login(email: String, password: String) {
        userRepository.login(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _user.value = userRepository.getCurrentUser()?.let { User(it.uid, it.email ?: "") }
            } else {
                _user.value = null
            }
        }
    }

    fun signUp(email: String, password: String) {
        userRepository.signUp(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _user.value = userRepository.getCurrentUser()?.let { User(it.uid, it.email ?: "") }
            } else {
                _user.value = null
            }
        }
    }

    fun logout() {
        userRepository.logout()
        _user.value = null
    }
}
