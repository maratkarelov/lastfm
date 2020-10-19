package com.example.lastfm.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lastfm.data.models.entity.ArtistEntity

@Dao
interface ArtistsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeList(data: List<ArtistEntity>)

    @Query("select * from artists where country=:country order by name")
    fun getLocalArtists(country: String): List<ArtistEntity>

    @Query("delete from artists where country=:country")
    fun clearTable(country: String)

}