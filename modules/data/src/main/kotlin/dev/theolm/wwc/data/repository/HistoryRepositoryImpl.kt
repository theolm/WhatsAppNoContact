package dev.theolm.wwc.data.repository

import dev.theolm.wwc.data.datasource.AppDataStore
import dev.theolm.wwc.domain.models.History
import dev.theolm.wwc.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

internal class HistoryRepositoryImpl(private val dataSource: AppDataStore) : HistoryRepository {
    override fun observeHistory(): Flow<List<History>> =
        dataSource.getAppLocalData().map { it.history }

    override suspend fun addHistory(history: History) {
        val current = dataSource.getAppLocalData().first()
        val newHistoryList = current.history.toMutableList().apply {
            add(history)
        }.sortedByDescending { it.timestamp }

        dataSource.updateAppLocalData(
            current.copy(history = newHistoryList)
        )
    }

    override suspend fun removeHistory(history: History) {
        val current = dataSource.getAppLocalData().first()
        val newHistoryList = current.history.toMutableList().apply {
            remove(history)
        }

        dataSource.updateAppLocalData(
            current.copy(history = newHistoryList)
        )
    }

    override suspend fun clearHistory() {
        val current = dataSource.getAppLocalData().first()
        dataSource.updateAppLocalData(
            current.copy(history = emptyList())
        )
    }
}
