package dev.theolm.wwc.domain.usecase

import dev.theolm.wwc.domain.models.History
import dev.theolm.wwc.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.firstOrNull

interface ClearHistoryUseCase {
    suspend operator fun invoke()
}

internal class ClearHistoryUseCaseImpl(
    private val repository: HistoryRepository,
) : ClearHistoryUseCase {

    override suspend fun invoke() {
        repository.clearHistory()
    }
}
