package dev.theolm.wwc.domain.usecase

import dev.theolm.wwc.domain.models.AppSettings
import dev.theolm.wwc.domain.repository.AppSettingsRepository
import kotlinx.coroutines.flow.Flow

interface ObserveSettingsUseCase {
    operator fun invoke(): Flow<AppSettings>
}

internal class ObserveSettingsUseCaseImpl(
    private val repository: AppSettingsRepository,
) : ObserveSettingsUseCase {
    override fun invoke() = repository.getAppSettings()
}
