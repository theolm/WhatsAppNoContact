package dev.theolm.wwc.domain.usecase

import dev.theolm.wwc.domain.models.History
import dev.theolm.wwc.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.firstOrNull

interface UpdateHistoryUseCase {
    suspend operator fun invoke(
        number: String,
        timestamp: Long,
    )
}

internal class UpdateHistoryUseCaseImpl(
    private val repository: HistoryRepository,
) : UpdateHistoryUseCase {

    override suspend fun invoke(number: String, timestamp: Long) {
        val current = repository.observeHistory().firstOrNull()?.maxByOrNull { it.id }
        val id = current?.id?.plus(1) ?: 1

        repository.addHistory(
            History(
                id = id,
                number = number,
                timestamp = timestamp,
            )
        )
    }
}
