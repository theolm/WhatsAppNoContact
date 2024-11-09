@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package dev.theolm.wwc.domain.usecase

import dev.theolm.wwc.data.datasource.AppDataStoreImpl
import dev.theolm.wwc.data.models.AppLocalData
import dev.theolm.wwc.data.repository.HistoryRepositoryImpl
import dev.theolm.wwc.domain.models.History
import dev.theolm.wwc.test.FakeDataStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class DeleteHistoryUseCaseTest {
    private lateinit var deleteHistoryUseCase: DeleteHistoryUseCase
    private lateinit var observeUseCase: ObserveHistoryUseCase
    private lateinit var addHistory: AddHistoryUseCase

    @Before
    fun setUp() {
        val fakeDataStore = FakeDataStore(initialValue = AppLocalData())
        val appDataStore = AppDataStoreImpl(dataStore = fakeDataStore)
        val repo = HistoryRepositoryImpl(appDataStore)
        deleteHistoryUseCase = DeleteHistoryUseCaseImpl(repo)
        observeUseCase = ObserveHistoryUseCaseImpl(repo)
        addHistory = AddHistoryUseCaseImpl(repo)
    }

    @Test
    fun `delete history should return empty list`() = runTest {
        var list: List<History>? = null

        val job = launch {
            observeUseCase().collect {
                list = it
            }
        }

        addHistory(number = "123", timestamp = 123)
        advanceUntilIdle()

        deleteHistoryUseCase(list!!.first())

        advanceUntilIdle()

        job.cancel()

        assertTrue(actual = list!!.isEmpty())
    }

    @Test
    fun `delete history should return list with one item`() = runTest {
        var list: List<History>? = null

        val job = launch {
            observeUseCase().collect {
                list = it
            }
        }

        addHistory(number = "123", timestamp = 123)
        addHistory(number = "456", timestamp = 456)
        advanceUntilIdle()

        deleteHistoryUseCase(list!!.first())

        advanceUntilIdle()

        job.cancel()

        assertTrue(actual = list!!.size == 1)
        assertEquals(
            expected = History(
                id = 1,
                number = "123",
                timestamp = 123,
            ),
            actual = list!!.first()
        )
    }

    @Test
    fun `delete on empty list does nothing`() = runTest {
        var list: List<History>? = null

        val job = launch {
            observeUseCase().collect {
                list = it
            }
        }

        deleteHistoryUseCase(History(1, "123", 123))

        advanceUntilIdle()

        job.cancel()

        assertTrue(actual = list!!.isEmpty())
    }
}
