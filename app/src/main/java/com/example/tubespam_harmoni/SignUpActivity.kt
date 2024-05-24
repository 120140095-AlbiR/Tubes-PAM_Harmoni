package com.example.tubespam_harmoni

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth

// SignUpActivity.kt
class SignUpActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val userRepository = UserRepository(FirebaseAuth.getInstance())
        viewModel = ViewModelProvider(this, ViewModelFactory(userRepository, null)).get(UserViewModel::class.java)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnSignUp = findViewById(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            viewModel.signUp(email, password)
        }

        viewModel.user.observe(this, Observer { user ->
            user?.let {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })
    }
}
