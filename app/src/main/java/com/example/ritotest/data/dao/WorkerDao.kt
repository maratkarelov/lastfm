package com.example.ritotest.data.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.ritotest.data.models.Perfomer

interface WorkerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeList(data: List<Perfomer>): Long

}