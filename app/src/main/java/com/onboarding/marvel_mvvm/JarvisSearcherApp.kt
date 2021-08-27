package com.onboarding.marvel_mvvm

import android.app.Application
import com.onboarding.marvel_mvvm.di.viewModelsModule
import org.koin.core.context.startKoin

class JarvisSearcherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(viewModelsModule))
        }
    }
}
