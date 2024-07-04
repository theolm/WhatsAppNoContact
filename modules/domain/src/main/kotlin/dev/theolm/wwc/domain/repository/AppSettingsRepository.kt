package dev.theolm.wwc.domain.repository

import dev.theolm.wwc.domain.models.AppSettings
import kotlinx.coroutines.flow.Flow

interface AppSettingsRepository {
    fun getAppSettings(): Flow<AppSettings>

    suspend fun updateAppSettings(appSettings: AppSettings)
}
