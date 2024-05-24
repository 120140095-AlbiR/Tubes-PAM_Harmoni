package com.example.tubespam_harmoni

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth

// ProfileFragment.kt
class ProfileFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var tvEmail: TextView
    private lateinit var btnLogout: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        tvEmail = view.findViewById(R.id.tvEmail)
        btnLogout = view.findViewById(R.id.btnLogout)

        val userRepository = UserRepository(FirebaseAuth.getInstance())
        viewModel = ViewModelProvider(this, ViewModelFactory(userRepository, null)).get(UserViewModel::class.java)

        viewModel.user.observe(viewLifecycleOwner, Observer { user ->
            tvEmail.text = user?.email
        })

        btnLogout.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()
        }

        return view
    }
}
