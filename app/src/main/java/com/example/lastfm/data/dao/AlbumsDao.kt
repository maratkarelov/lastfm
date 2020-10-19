package com.example.lastfm.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lastfm.data.models.entity.AlbumEntity

@Dao
interface AlbumsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeList(data: List<AlbumEntity>)

    @Query("select * from albums where artist=:artist")
    fun getLocalAlbums(artist: String): List<AlbumEntity>

    @Query("delete from albums where artist=:artist")
    fun clearTable(artist: String)

}