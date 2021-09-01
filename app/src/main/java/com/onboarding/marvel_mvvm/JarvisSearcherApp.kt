package com.onboarding.marvel_mvvm

import android.app.Application
import com.onboarding.di.repositoryModule
import com.onboarding.di.useCasesModule
import com.onboarding.marvel_mvvm.di.viewModelModule
import org.koin.core.context.startKoin

class JarvisSearcherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(viewModelModule, useCasesModule, repositoryModule))
        }
    }
}
