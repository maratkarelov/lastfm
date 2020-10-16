package com.example.ritotest.data.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.ritotest.data.models.Employer

interface EmployerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeList(data: List<Employer>): Long

}