package com.example.tubespam_harmoni

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MusicViewModel(private val musicRepository: MusicRepository) : ViewModel() {
    private val _musicList = MutableLiveData<List<Music>>()
    val musicList: LiveData<List<Music>> get() = _musicList

    fun getPopularMusic() {
        musicRepository.getPopularMusic().enqueue(object : Callback<List<Music>> {
            override fun onResponse(call: Call<List<Music>>, response: Response<List<Music>>) {
                _musicList.value = response.body()
            }

            override fun onFailure(call: Call<List<Music>>, t: Throwable) {
                _musicList.value = emptyList()
            }
        })
    }

    fun searchMusic(query: String) {
        musicRepository.searchMusic(query).enqueue(object : Callback<List<Music>> {
            override fun onResponse(call: Call<List<Music>>, response: Response<List<Music>>) {
                _musicList.value = response.body()
            }

            override fun onFailure(call: Call<List<Music>>, t: Throwable) {
                _musicList.value = emptyList()
            }
        })
    }
}
