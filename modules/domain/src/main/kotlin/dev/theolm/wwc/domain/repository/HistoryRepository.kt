package dev.theolm.wwc.domain.repository

import dev.theolm.wwc.domain.models.History
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    fun observeHistory(): Flow<List<History>>
    suspend fun addHistory(history: History)
    suspend fun removeHistory(history: History)
    suspend fun clearHistory()
}
