package dev.theolm.wwc.domain.usecase

import dev.theolm.wwc.domain.repository.HistoryRepository

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
