package dev.theolm.wwc.data.repository

import dev.theolm.wwc.data.datasource.AppDataStoreImpl
import dev.theolm.wwc.data.fakes.FakeDataStore
import dev.theolm.wwc.data.models.AppLocalData
import dev.theolm.wwc.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

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
}
