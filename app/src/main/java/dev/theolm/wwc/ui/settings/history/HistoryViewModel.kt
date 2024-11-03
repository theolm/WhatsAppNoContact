package dev.theolm.wwc.ui.settings.history

import androidx.lifecycle.ViewModel
import dev.theolm.wwc.domain.models.History
import dev.theolm.wwc.domain.usecase.ObserveHistoryUseCase
import kotlinx.coroutines.flow.map

class HistoryViewModel(
    observeHistoryUseCase: ObserveHistoryUseCase
): ViewModel() {
    val uiState = observeHistoryUseCase().map { history ->
        UiState(history)
    }

    data class UiState(
        val history: List<History> = emptyList()
    )
}