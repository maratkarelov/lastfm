package com.example.ritotest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ritotest.data.models.entity.PerfomerEntity

@Dao
interface WorkerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeList(data: List<PerfomerEntity>)

    @Query("select * from workers")
    fun getLocalWorkers(): List<PerfomerEntity>

}