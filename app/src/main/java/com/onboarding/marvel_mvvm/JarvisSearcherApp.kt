package com.onboarding.marvel_mvvm

import android.app.Application
import com.onboarding.di.repositoryModule
import com.onboarding.di.useCasesModule
import com.onboarding.marvel_mvvm.di.viewModelModule
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.core.context.startKoin

class JarvisSearcherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(viewModelModule, useCasesModule, repositoryModule))
        }
        Realm.init(this)
        Realm.setDefaultConfiguration(
            RealmConfiguration.Builder()
                .name(DATABASE_NAME)
                .schemaVersion(DATABASE_VERSION)
                .build()
        )
    }

    companion object {
        private const val DATABASE_NAME = "JARVIS DATABASE"
        private const val DATABASE_VERSION: Long = 0
    }
}
