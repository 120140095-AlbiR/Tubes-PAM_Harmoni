package com.example.tubespam_harmoni

class MusicRepository(private val spotifyService: SpotifyService) {
    fun getPopularMusic() = spotifyService.getPopularMusic()
    fun searchMusic(query: String) = spotifyService.searchMusic(query)
}
