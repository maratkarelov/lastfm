package com.example.lastfm.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class AlbumEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val playcount: Int,
    var imagePath: String?
){
    var artist: String? = null
}

