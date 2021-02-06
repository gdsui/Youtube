package com.arslan.youtube.di

import android.content.Context
import androidx.room.Room
import com.arslan.youtube.data.local.YoutubeDatabase
import com.arslan.youtube.data.network.YoutubeApi
import com.arslan.youtube.data.repository.YoutubeRepository
import com.arslan.youtube.ui.details.DetailsViewModel
import com.arslan.youtube.ui.main.MainViewModel
import com.arslan.youtube.ui.noInternet.NoInternetViewModel
import com.arslan.youtube.ui.playlist.PlaylistViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://www.googleapis.com/"

val vmModule = module {
    viewModel { PlaylistViewModel(get()) }
    viewModel { MainViewModel() }
    viewModel { DetailsViewModel(get()) }
    viewModel { NoInternetViewModel() }
}

val appModule = module {
    single { androidContext().resources }
}

val networkModule = module {
    single { provideRetrofit(get()) }
    single { provideOkhttpClient() }
    factory { YoutubeApi.provideYoutubeApi(get()) }
}

val repositoryModule = module {
    factory { YoutubeRepository(get(), get()) }
}

val localModule = module {
    single { provideDatabase(androidContext()) }
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(client)
        .build()
}

fun provideOkhttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder() //для ограничения времени
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()
}

fun provideDatabase(context: Context): YoutubeDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        YoutubeDatabase::class.java,
        "word_database"
    )
        .allowMainThreadQueries()
        .build()
}