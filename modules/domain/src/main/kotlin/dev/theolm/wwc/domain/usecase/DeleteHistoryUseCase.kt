package dev.theolm.wwc.domain.usecase

import dev.theolm.wwc.domain.models.History
import dev.theolm.wwc.domain.repository.HistoryRepository

interface DeleteHistoryUseCase {
    suspend operator fun invoke(history: History)
}

internal class DeleteHistoryUseCaseImpl(
    private val repository: HistoryRepository,
) : DeleteHistoryUseCase {

    override suspend fun invoke(history: History) {
        repository.removeHistory(history)
    }
}
