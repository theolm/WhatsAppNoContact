package dev.theolm.wwc.domain.usecase

import dev.theolm.wwc.domain.models.AppSettings
import dev.theolm.wwc.domain.repository.AppSettingsRepository

interface UpdateSettingsUseCase {
    suspend operator fun invoke(update: AppSettings)
}

internal class UpdateSettingsUseCaseImpl(
    private val repository: AppSettingsRepository,
) : UpdateSettingsUseCase {
    override suspend fun invoke(update: AppSettings) {
        repository.updateAppSettings(appSettings = update)
    }
}
