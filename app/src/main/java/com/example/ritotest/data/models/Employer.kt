package com.example.ritotest.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Employer(
    @PrimaryKey val mbid: String,
    val name: String,
    val count: Int
)