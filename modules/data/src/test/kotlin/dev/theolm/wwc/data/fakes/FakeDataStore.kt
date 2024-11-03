package dev.theolm.wwc.data.fakes

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeDataStore<T>(
    initialValue: T
) : DataStore<T> {
    private val _data = MutableStateFlow(initialValue)
    override val data: Flow<T> = _data

    override suspend fun updateData(transform: suspend (T) -> T): T {
        return transform(_data.value).also {
            _data.emit(it)
        }
    }
}
