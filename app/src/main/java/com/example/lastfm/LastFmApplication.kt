package com.example.lastfm

import android.annotation.SuppressLint
import android.app.Application
import com.example.lastfm.di.apiModule
import com.example.lastfm.di.netModule
import com.example.lastfm.di.roomDataSourceModule
import com.example.lastfm.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class LastFmApplication : Application() {

    @SuppressLint("CheckResult")
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@LastFmApplication)
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