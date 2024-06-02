package dev.theolm.wwc

import android.app.Application
import dev.theolm.wwc.di.mainDiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(mainDiModule)
        }
    }
}
