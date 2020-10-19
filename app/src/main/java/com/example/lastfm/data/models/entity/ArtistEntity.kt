package com.example.lastfm.data.models.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "artists")
data class ArtistEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val listeners: Int,
    var imagePath: String?
) : Parcelable {
    var country: String? = null

}

