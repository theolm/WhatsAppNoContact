package dev.theolm.wwc.data.datasource

import androidx.datastore.core.DataStore
import dev.theolm.wwc.data.models.AppLocalData
import kotlinx.coroutines.flow.Flow

const val DataStoreFileName = "app_local_data.json"

internal interface AppDataStore {
    fun getAppLocalData(): Flow<AppLocalData>

    suspend fun updateAppLocalData(updatedData: AppLocalData)
}

internal class AppDataStoreImpl(private val dataStore: DataStore<AppLocalData>) : AppDataStore {
    override fun getAppLocalData(): Flow<AppLocalData> =
        dataStore.data

    override suspend fun updateAppLocalData(updatedData: AppLocalData) {
        dataStore.updateData { updatedData }
    }
}
