package com.example.tubespam_harmoni

import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SpotifyService {
    @GET("popular")
    fun getPopularMusic(): Call<List<Music>>

    @GET("search")
    fun searchMusic(@Query("q") query: String): Call<List<Music>>
}

object RetrofitInstance {
    private const val BASE_URL = "https://api.spotify.com/v1/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).addInterceptor(ChuckerInterceptor.Builder(context).build()).build())
            .build()
    }

    val spotifyService: SpotifyService by lazy {
        retrofit.create(SpotifyService::class.java)
    }
}
