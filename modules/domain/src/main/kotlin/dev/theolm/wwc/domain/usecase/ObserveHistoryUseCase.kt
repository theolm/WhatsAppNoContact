package dev.theolm.wwc.domain.usecase

import dev.theolm.wwc.domain.models.History
import dev.theolm.wwc.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow

interface ObserveHistoryUseCase {
    operator fun invoke(): Flow<List<History>>
}

internal class ObserveHistoryUseCaseImpl(
    private val repository: HistoryRepository,
) : ObserveHistoryUseCase {
    override fun invoke() = repository.observeHistory()
}
