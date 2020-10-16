package com.example.ritotest.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ritotest.data.dao.EmployerDao
import com.example.ritotest.data.dao.WorkerDao
import com.example.ritotest.data.models.Employer
import com.example.ritotest.data.models.entity.PerfomerEntity

@Database(entities = [Employer::class, PerfomerEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getWorkerDao(): WorkerDao
    abstract fun getEmployerDao(): EmployerDao
}