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
class AddHistoryUseCaseTest {
    private lateinit var observeUseCase: ObserveHistoryUseCase
    private lateinit var addHistoryUseCase: AddHistoryUseCase

    @Before
    fun setUp() {
        val fakeDataStore = FakeDataStore(initialValue = AppLocalData())
        val appDataStore = AppDataStoreImpl(dataStore = fakeDataStore)
        val repo = HistoryRepositoryImpl(appDataStore)
        addHistoryUseCase = AddHistoryUseCaseImpl(repo)
        observeUseCase = ObserveHistoryUseCaseImpl(repo)
    }

    @Test
    fun `add history should return list with one item with id == 1`() = runTest {
        var list: List<History>? = null

        val job = launch {
            observeUseCase().collect {
                list = it
            }
        }

        addHistoryUseCase(number = "123", timestamp = 123)

        advanceUntilIdle()

        job.cancel()

        assertTrue(actual = list?.size == 1)
        assertEquals(expected = "123", actual = list?.first()?.number)
        assertEquals(expected = "123", actual = list?.first()?.number)
        assertEquals(expected = 1, actual = list?.first()?.id)
    }

    @Test
    fun `add history should return list with two items ordered by descending timestamp and with sequential ids`() = runTest {
        var list: List<History>? = null

        val job = launch {
            observeUseCase().collect {
                list = it
            }
        }

        addHistoryUseCase(number = "123", timestamp = 123)
        addHistoryUseCase(number = "456", timestamp = 456)

        advanceUntilIdle()

        job.cancel()

        assertTrue(actual = list?.size == 2)

        assertEquals(
            expected = History(
                id = 2,
                number = "456",
                timestamp = 456,
            ),
            actual = list?.first()
        )

        assertEquals(
            expected = History(
                id = 1,
                number = "123",
                timestamp = 123,
            ),
            actual = list?.last()
        )
    }
}