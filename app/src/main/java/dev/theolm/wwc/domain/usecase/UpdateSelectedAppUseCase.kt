package dev.theolm.wwc.domain.usecase

import dev.theolm.wwc.domain.models.DefaultApp
import dev.theolm.wwc.domain.repository.AppSettingsRepository
import kotlinx.coroutines.flow.first

interface UpdateSelectedAppUseCase {
    suspend operator fun invoke(selectedApp: DefaultApp)
}

internal class UpdateSelectedAppUseCaseImpl(
    private val repository: AppSettingsRepository,
) : UpdateSelectedAppUseCase {
    override suspend fun invoke(selectedApp: DefaultApp) {
        val appSettings = repository.getAppSettings().first()
        repository.updateAppSettings(
            appSettings = appSettings.copy(
                defaultApp = selectedApp
            )
        )
    }
}
