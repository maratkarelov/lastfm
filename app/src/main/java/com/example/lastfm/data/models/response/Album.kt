package com.example.lastfm.data.models.response

import com.example.lastfm.data.models.Convertable
import com.example.lastfm.data.models.entity.AlbumEntity
import com.google.gson.annotations.SerializedName

data class Album(
    val mbid: String?,
    val name: String,
    val playcount: Int,
    @SerializedName("image")
    val images: ArrayList<Image>
) : Convertable<AlbumEntity> {
    override fun convert(): AlbumEntity {
        return AlbumEntity(0, name, playcount, images.find { it.size == "large" }?.text)
    }
}

data class AlbumsTopWrapper(@SerializedName("topalbums") val topalbums: AlbumsWrapper)
data class AlbumsWrapper(@SerializedName("album") val album: List<Album>)
