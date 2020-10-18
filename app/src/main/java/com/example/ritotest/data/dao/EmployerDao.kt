package com.example.ritotest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.ritotest.data.models.Employer

@Dao
interface EmployerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeList(data: List<Employer>)

}