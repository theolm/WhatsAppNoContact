package dev.theolm.wwc.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dev.theolm.wwc.data.datasource.AppDataStore
import dev.theolm.wwc.data.datasource.AppDataStoreImpl
import dev.theolm.wwc.data.datasource.DataStoreFileName
import dev.theolm.wwc.data.repository.AppSettingsRepositoryImpl
import dev.theolm.wwc.data.serializer.AppSettingsSerializer
import dev.theolm.wwc.domain.models.AppSettings
import dev.theolm.wwc.domain.repository.AppSettingsRepository
import org.koin.dsl.module

val dataModule = module {
    single<DataStore<AppSettings>> {
        DataStoreFactory.create(
            serializer = AppSettingsSerializer,
            produceFile = { get<Context>().dataStoreFile(DataStoreFileName) },
        )
    }

    single<AppDataStore> {
        AppDataStoreImpl(dataStore = get())
    }

    single<AppSettingsRepository> {
        AppSettingsRepositoryImpl(
            dataSource = get()
        )
    }
}
