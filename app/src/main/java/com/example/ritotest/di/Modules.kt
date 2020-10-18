package com.example.ritotest.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.ritotest.data.AppDatabase
import com.example.ritotest.network.ServerAPI
import com.example.ritotest.network.SupportInterceptor
import com.example.ritotest.network.usecases.WorkersUseCase
import com.example.ritotest.ui.list.WorkersViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModule = module {
    viewModel { WorkersViewModel(get(), get()) }
}
val apiModule = module {
    fun provideServerApi(retrofit: Retrofit): ServerAPI {
        return retrofit.create(ServerAPI::class.java)
    }

    factory { provideServerApi(get()) }
}

val netModule = module {
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideHttpClient(cache: Cache): OkHttpClient {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("LoggingInterceptor", message)
            }
        })
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val support = SupportInterceptor()
        val okHttpClientBuilder = OkHttpClient.Builder()
            .cache(cache)
        okHttpClientBuilder
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(support)
            .followRedirects(false)
        return okHttpClientBuilder.build()
    }

    fun provideGson(): Gson {
        return GsonBuilder().setLenient().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://ws.audioscrobbler.com/2.0/")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    factory { provideCache(androidApplication()) }
    factory { provideHttpClient(get()) }
    factory { provideGson() }
    factory { provideRetrofit(get(), get()) }

    single { WorkersUseCase(get()) }
}
val roomDataSourceModule = module {

    // Room Database
    single {
        Room
            .databaseBuilder(androidApplication(), AppDatabase::class.java, "my-db")
            .fallbackToDestructiveMigration()
            .build()
    }

    // Expose DAOs
    single { get<AppDatabase>().getEmployerDao() }
    single { get<AppDatabase>().getWorkerDao() }
}
