@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package dev.theolm.wwc.domain.usecase

import dev.theolm.wwc.data.datasource.AppDataStoreImpl
import dev.theolm.wwc.data.models.AppLocalData
import dev.theolm.wwc.data.repository.HistoryRepositoryImpl
import dev.theolm.wwc.domain.models.History
import dev.theolm.wwc.test.FakeDataStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class ObserveHistoryUseCaseTest {
    private lateinit var observeUseCase: ObserveHistoryUseCase
    private lateinit var addHistory: AddHistoryUseCase

    @Before
    fun setUp() {
        val fakeDataStore = FakeDataStore(initialValue = AppLocalData())
        val appDataStore = AppDataStoreImpl(dataStore = fakeDataStore)
        val repo = HistoryRepositoryImpl(appDataStore)
        observeUseCase = ObserveHistoryUseCaseImpl(repo)
        addHistory = AddHistoryUseCaseImpl(repo)
    }

    @Test
    fun `observe history should return empty list`() = runTest {
        val list = observeUseCase().first()
        assertTrue(actual = list.isEmpty())
    }

    @Test
    fun `observe history should return list with one item`() = runTest {
        var list: List<History>? = null

        val job = launch {
            observeUseCase().collect {
                list = it
            }
        }

        addHistory(number = "123", timestamp = 123)

        advanceUntilIdle()

        job.cancel()

        assertTrue(actual = list?.size == 1)
        assertEquals(expected = "123", actual = list?.first()?.number)
        assertEquals(expected = "123", actual = list?.first()?.number)
    }
}
