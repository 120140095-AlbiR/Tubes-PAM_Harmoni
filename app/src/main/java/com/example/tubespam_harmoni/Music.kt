package com.example.tubespam_harmoni

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Music(
    val id: String,
    val title: String,
    val artist: String,
    val album: String,
    val duration: Long,
    val imageUrl: String
) : Parcelable

