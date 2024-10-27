package dev.theolm.wwc.domain.usecase

import dev.theolm.wwc.domain.models.CountryCode
import dev.theolm.wwc.domain.repository.AppSettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ObserveSelectedCountryUseCase {
    operator fun invoke(): Flow<CountryCode?>
}

internal class ObserveSelectedCountryUseCaseImpl(
    private val repository: AppSettingsRepository,
) : ObserveSelectedCountryUseCase {
    override fun invoke() = repository.getAppSettings().map { it.selectedCountryCode }
}
