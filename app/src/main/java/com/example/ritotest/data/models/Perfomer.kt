package com.example.ritotest.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Perfomer(
    @PrimaryKey val mbid: String,
    val name: String,
    val listeners: Int,
    val image: ArrayList<Image>
)

data class WorkersWrapper(@SerializedName("id") val workers :List<Perfomer>)
data class Image(val path: String, val size: Size)
enum class Size(val size: String) { LARGE("large"), MEDIUM("medium"), SMALL("small") }

