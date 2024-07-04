package dev.theolm.wwc

import android.app.Application
import dev.theolm.wwc.data.di.dataModule
import dev.theolm.wwc.presentation.di.presentationModule
import dev.theolm.wwc.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(presentationModule, domainModule, dataModule)
        }
    }
}
