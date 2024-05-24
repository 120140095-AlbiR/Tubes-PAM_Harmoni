package com.example.tubespam_harmoni

import com.google.firebase.auth.FirebaseAuth

class UserRepository(private val firebaseAuth: FirebaseAuth) {
    fun login(email: String, password: String) = firebaseAuth.signInWithEmailAndPassword(email, password)
    fun signUp(email: String, password: String) = firebaseAuth.createUserWithEmailAndPassword(email, password)
    fun getCurrentUser() = firebaseAuth.currentUser
    fun logout() = firebaseAuth.signOut()
}
