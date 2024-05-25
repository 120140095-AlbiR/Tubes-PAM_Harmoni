package com.example.tubespam_harmoni

import androidx.lifecycle.LiveData

class FavoriteRepository(private val musicDao: MusicDao) {

    // Function to get all favorite music from the database
    fun getFavoriteMusic(): LiveData<List<Music>> {
        return musicDao.getAllFavorites()
    }

    // Function to add a music to favorites
    suspend fun addFavorite(music: Music) {
        musicDao.insert(music)
    }

    // Function to remove a music from favorites
    suspend fun removeFavorite(music: Music) {
        musicDao.delete(music)
    }
}
