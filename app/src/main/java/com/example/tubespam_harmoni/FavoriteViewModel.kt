package com.example.tubespam_harmoni

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: FavoriteRepository) : ViewModel() {

    // LiveData holding the list of favorite music
    val favoriteMusic: LiveData<List<Music>> = repository.getFavoriteMusic()

    // Function to add a music to favorites
    fun addFavorite(music: Music) = viewModelScope.launch {
        repository.addFavorite(music)
    }

    // Function to remove a music from favorites
    fun removeFavorite(music: Music) = viewModelScope.launch {
        repository.removeFavorite(music)
    }
}
