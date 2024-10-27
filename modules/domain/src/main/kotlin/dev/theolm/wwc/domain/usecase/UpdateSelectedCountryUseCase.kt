package dev.theolm.wwc.domain.usecase

import dev.theolm.wwc.domain.models.CountryCode
import dev.theolm.wwc.domain.repository.AppSettingsRepository
import kotlinx.coroutines.flow.first

interface UpdateSelectedCountryUseCase {
    suspend operator fun invoke(country: CountryCode?)
}

internal class UpdateSelectedCountryUseCaseImpl(
    private val repository: AppSettingsRepository,
) : UpdateSelectedCountryUseCase {
    override suspend fun invoke(country: CountryCode?) {
        val appSettings = repository.getAppSettings().first()
        repository.updateAppSettings(
            appSettings = appSettings.copy(
                selectedCountryCode = country
            )
        )
    }
}
