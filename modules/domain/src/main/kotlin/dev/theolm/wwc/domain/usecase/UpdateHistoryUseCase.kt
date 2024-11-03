package dev.theolm.wwc.domain.usecase

import dev.theolm.wwc.domain.models.History
import dev.theolm.wwc.domain.repository.HistoryRepository

interface UpdateHistoryUseCase {
    suspend operator fun invoke(history: History)
}

internal class UpdateHistoryUseCaseImpl(
    private val repository: HistoryRepository,
) : UpdateHistoryUseCase {

    override suspend fun invoke(history: History) {
        repository.updateHistory(history)
    }
}
