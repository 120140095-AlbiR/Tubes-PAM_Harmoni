package com.example.tubespam_harmoni

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MusicDao {
    @Query("SELECT * FROM music")
    fun getAll(): LiveData<List<Music>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(music: Music)

    @Delete
    suspend fun delete(music: Music)
}
