package com.example.lastfm.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lastfm.data.dao.AlbumsDao
import com.example.lastfm.data.dao.ArtistsDao
import com.example.lastfm.data.models.entity.AlbumEntity
import com.example.lastfm.data.models.entity.ArtistEntity

@Database(entities = [AlbumEntity::class, ArtistEntity::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getWorkerDao(): ArtistsDao
    abstract fun getAlbumDao(): AlbumsDao
}