package dev.theolm.wwc.domain.repository

import dev.theolm.wwc.data.datasource.AppSettings
import kotlinx.coroutines.flow.Flow

interface AppSettingsRepository {
    fun getAppSettings(): Flow<AppSettings>

    suspend fun updateAppSettings(appSettings: AppSettings)
}
