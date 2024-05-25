package com.example.tubespam_harmoni.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tubespam_harmoni.R
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        val tvEmail: TextView = view.findViewById(R.id.tvEmail)
        val tvUsername: TextView = view.findViewById(R.id.tvUsername)
        val btnLogout: Button = view.findViewById(R.id.btnLogout)

        currentUser?.let {
            tvEmail.text = it.email
            tvUsername.text = it.displayName ?: "Username"
        }

        btnLogout.setOnClickListener {
            auth.signOut()
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment())
            Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
