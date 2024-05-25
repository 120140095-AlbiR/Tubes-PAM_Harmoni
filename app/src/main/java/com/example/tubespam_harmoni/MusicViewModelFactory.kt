package com.example.tubespam_harmoni

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MusicViewModelFactory(private val musicRepository: MusicRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MusicViewModel::class.java) -> MusicViewModel(musicRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
