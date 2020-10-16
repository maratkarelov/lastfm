package com.example.ritotest.data.models.response

import com.example.ritotest.data.models.Convertable
import com.example.ritotest.data.models.entity.PerfomerEntity
import com.google.gson.annotations.SerializedName

data class Perfomer(
    val mbid: String,
    val name: String,
    val listeners: Int,
    @SerializedName("image")
    val images: ArrayList<Image>
) : Convertable<PerfomerEntity> {
    override fun convert(): PerfomerEntity {
        return PerfomerEntity(mbid, name, listeners, images.find { it.path == "large" }?.path)
    }
}

data class WorkersWrapper(@SerializedName("id") val workers: List<PerfomerEntity>)
data class Image(val path: String, val size: String)

