package dev.theolm.wwc.data.repository

import dev.theolm.wwc.data.datasource.AppDataStore
import dev.theolm.wwc.domain.models.AppSettings
import dev.theolm.wwc.domain.repository.AppSettingsRepository
import kotlinx.coroutines.flow.Flow

internal class AppSettingsRepositoryImpl(
    private val dataSource: AppDataStore,
) : AppSettingsRepository {
    override fun getAppSettings(): Flow<AppSettings> =
        dataSource.getAppSettings()

    override suspend fun updateAppSettings(appSettings: AppSettings) =
        dataSource.updateAppSettings(appSettings)
}
