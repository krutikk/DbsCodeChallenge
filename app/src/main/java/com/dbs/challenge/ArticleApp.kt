package com.dbs.challenge

import android.app.Application
import com.dbs.challenge.core.di.applicationModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ArticleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ArticleApp)
            androidLogger()
            modules(applicationModules)
        }
    }
}