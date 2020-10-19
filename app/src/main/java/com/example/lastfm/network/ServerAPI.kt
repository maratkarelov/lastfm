package com.example.lastfm.network

import com.example.lastfm.data.models.response.AlbumsTopWrapper
import com.example.lastfm.data.models.response.ArtistTopWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerAPI {
    @GET("2.0/")
    fun readArtists(
        @Query("method") method: String,
        @Query("country") country: String,
        @Query("api_key") api_key: String,
        @Query("format") format: String
    ): Call<ArtistTopWrapper>

    @GET("2.0/")
    fun readAlbums(
        @Query("method") method: String,
        @Query("artist") artist: String,
        @Query("api_key") api_key: String,
        @Query("format") format: String

    ): Call<AlbumsTopWrapper>
}
