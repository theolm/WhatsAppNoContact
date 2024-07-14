package dev.theolm.wwc.domain.usecase

import dev.theolm.wwc.domain.models.Country
import dev.theolm.wwc.domain.repository.AppSettingsRepository
import kotlinx.coroutines.flow.first

interface UpdateSelectedCountryUseCase {
    suspend operator fun invoke(country: Country?)
}

internal class UpdateSelectedCountryUseCaseImpl(
    private val repository: AppSettingsRepository,
) : UpdateSelectedCountryUseCase {
    override suspend fun invoke(country: Country?) {
        val appSettings = repository.getAppSettings().first()
        repository.updateAppSettings(
            appSettings = appSettings.copy(
                selectedCountryCode = country
            )
        )
    }
}
