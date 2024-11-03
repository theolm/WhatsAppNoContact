package dev.theolm.wwc.data.repository

import dev.theolm.wwc.data.datasource.AppDataStoreImpl
import dev.theolm.wwc.data.models.AppLocalData
import dev.theolm.wwc.domain.models.History
import dev.theolm.wwc.domain.repository.HistoryRepository
import dev.theolm.wwc.test.FakeDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HistoryRepositoryTest {
    private lateinit var historyRepository: HistoryRepository

    @Before
    fun setup() {
        val fakeDataStore = FakeDataStore(initialValue = AppLocalData())
        val appDataStore = AppDataStoreImpl(dataStore = fakeDataStore)

        historyRepository = HistoryRepositoryImpl(appDataStore)
    }

    @Test
    fun `observe history - when fresh install - expects empty list`() = runTest {
        val history = historyRepository.observeHistory().first()
        assertEquals(expected = emptyList(), actual = history)
    }

    @Test
    fun `add history - when adding a history - expects history to be added`() = runTest {
        val history = historyRepository.observeHistory().first()
        assertEquals(expected = emptyList(), actual = history)

        val newHistory = History(id = 1, number = "123456789", timestamp = 0)

        historyRepository.addHistory(newHistory)

        val updatedHistory = historyRepository.observeHistory().first()

        assertTrue(updatedHistory.contains(newHistory))
    }

    @Test
    fun `add history - when adding a history - expects history to be added in descending timestamp order`() =
        runTest {
            val history = historyRepository.observeHistory().first()
            assertEquals(expected = emptyList(), actual = history)

            val newHistory1 = History(id = 1, number = "123456789", timestamp = 0)
            val newHistory2 = History(id = 2, number = "987654321", timestamp = 1)
            val newHistory3 = History(id = 3, number = "9876543212", timestamp = 3)

            historyRepository.addHistory(newHistory1)
            historyRepository.addHistory(newHistory2)
            historyRepository.addHistory(newHistory3)

            val updatedHistory = historyRepository.observeHistory().first()

            assertEquals(
                expected = listOf(
                    newHistory3,
                    newHistory2,
                    newHistory1
                ),
                actual = updatedHistory
            )
        }

    @Test
    fun `remove history - when removing a history - expects history to be removed`() = runTest {
        val history = historyRepository.observeHistory().first()
        assertEquals(expected = emptyList(), actual = history)

        val newHistory = History(id = 1, number = "123456789", timestamp = 0)

        historyRepository.addHistory(newHistory)

        var updatedHistory = historyRepository.observeHistory().first()

        assertTrue(updatedHistory.contains(newHistory))

        historyRepository.removeHistory(newHistory)

        updatedHistory = historyRepository.observeHistory().first()

        assertTrue(updatedHistory.isEmpty())
    }
}
