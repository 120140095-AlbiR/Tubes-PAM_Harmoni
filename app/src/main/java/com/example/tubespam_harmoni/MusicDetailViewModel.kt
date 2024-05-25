package com.example.tubespam_harmoni

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.map


class MusicDetailViewModel(private val repository: FavoriteRepository) : ViewModel() {

    // Check if the music is in favorites
    fun isFavorite(musicId: String): LiveData<Boolean> {
        return repository.getFavoriteMusic().map { favorites ->
            favorites.any { it.id == musicId }
        }
    }

    // Add music to favorites
    fun addFavorite(music: Music) = viewModelScope.launch {
        repository.addFavorite(music)
    }

    // Remove music from favorites
    fun removeFavorite(music: Music) = viewModelScope.launch {
        repository.removeFavorite(music)
    }
}
