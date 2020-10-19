package com.example.lastfm.data.models.response

import com.example.lastfm.data.models.Convertable
import com.example.lastfm.data.models.entity.ArtistEntity
import com.google.gson.annotations.SerializedName

data class Artist(
    val mbid: String,
    val name: String,
    val listeners: Int,
    @SerializedName("image")
    val images: ArrayList<Image>
) : Convertable<ArtistEntity> {
    override fun convert(): ArtistEntity {
        return ArtistEntity(0, name, listeners, images.find { it.size == "large" }?.text)
    }
}

data class ArtistTopWrapper(@SerializedName("topartists") val topartists: ArtistWrapper)
data class ArtistWrapper(@SerializedName("artist") val artist: List<Artist>)
data class Image(@SerializedName("#text") val text: String, val size: String)

