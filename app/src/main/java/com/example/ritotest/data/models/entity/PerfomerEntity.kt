package com.example.ritotest.data.models.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.ritotest.data.models.Convertable
import com.google.gson.annotations.SerializedName

@Entity(tableName = "workers")
data class PerfomerEntity(
    @PrimaryKey val mbid: String,
    val name: String,
    val listeners: Int,
    var imagePath: String?
)

data class WorkersWrapper(@SerializedName("id") val workers: List<PerfomerEntity>)
data class Image(val path: String, val size: String)

