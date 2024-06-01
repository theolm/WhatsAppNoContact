package dev.theolm.wwc.core.storage

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

const val DataStoreFileName = "app_settings.json"

interface AppDataStore {
    fun getAppSettings(): Flow<AppSettings>

    suspend fun updateAppSettings(appSettings: AppSettings)
}

class AppDataStoreImpl(private val dataStore: DataStore<AppSettings>) : AppDataStore {
    override fun getAppSettings(): Flow<AppSettings> =
        dataStore.data

    override suspend fun updateAppSettings(appSettings: AppSettings) {
        dataStore.updateData { appSettings }
    }
}

object FakeAppDataStore : AppDataStore {
    override fun getAppSettings(): Flow<AppSettings> = flowOf(AppSettings())

    override suspend fun updateAppSettings(appSettings: AppSettings) {
        // Do nothing
    }
}