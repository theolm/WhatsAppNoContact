package dev.theolm.wwc.data.repository

import dev.theolm.wwc.data.datasource.AppDataStore
import dev.theolm.wwc.domain.models.AppSettings
import dev.theolm.wwc.domain.repository.AppSettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

internal class AppSettingsRepositoryImpl(
    private val dataSource: AppDataStore,
) : AppSettingsRepository {
    override fun getAppSettings(): Flow<AppSettings> =
        dataSource.getAppLocalData().map {
            AppSettings(
                selectedCountryCode = it.selectedCountryCode,
                defaultApp = it.defaultApp
            )
        }

    override suspend fun updateAppSettings(appSettings: AppSettings) {
        val currentData = dataSource.getAppLocalData().first()
        dataSource.updateAppLocalData(
            currentData.copy(
                selectedCountryCode = appSettings.selectedCountryCode,
                defaultApp = appSettings.defaultApp
            )
        )
    }
}
