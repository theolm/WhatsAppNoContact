package dev.theolm.wwc.data.datasource

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow

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
