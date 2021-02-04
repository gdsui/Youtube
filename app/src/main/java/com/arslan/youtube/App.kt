package com.arslan.youtube

import android.app.Application
import com.arslan.youtube.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        // start Koin!
        startKoin {
            // Android context
            androidContext(this@App)
            // modules
            modules(vmModule, appModule, networkModule, repositoryModule, localModule)
        }
    }
}