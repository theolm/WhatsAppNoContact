package dev.theolm.wwc.domain.usecase

import dev.theolm.wwc.domain.models.DefaultApp
import dev.theolm.wwc.domain.repository.AppSettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ObserveSelectedAppUseCase {
    operator fun invoke(): Flow<DefaultApp>
}

internal class ObserveSelectedAppUseCaseImpl(
    private val repository: AppSettingsRepository,
) : ObserveSelectedAppUseCase {
    override fun invoke() = repository.getAppSettings().map { it.defaultApp }
}
