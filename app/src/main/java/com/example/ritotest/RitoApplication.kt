package com.example.ritotest

import android.annotation.SuppressLint
import android.app.Application
import com.example.ritotest.di.apiModule
import com.example.ritotest.di.netModule
import com.example.ritotest.di.roomDataSourceModule
import com.example.ritotest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class RitoApplication : Application() {

    @SuppressLint("CheckResult")
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@RitoApplication)
            koin.loadModules(
                mutableListOf(
                    viewModelModule,
                    netModule,
                    apiModule,
                    roomDataSourceModule
                )
            )
        }
    }
}