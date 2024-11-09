package dev.theolm.wwc.ui.settings.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.theolm.wwc.domain.models.DefaultApp
import dev.theolm.wwc.domain.models.History
import dev.theolm.wwc.domain.usecase.AddHistoryUseCase
import dev.theolm.wwc.domain.usecase.ClearHistoryUseCase
import dev.theolm.wwc.domain.usecase.ObserveHistoryUseCase
import dev.theolm.wwc.domain.usecase.ObserveSelectedAppUseCase
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class HistoryViewModel(
    observeHistoryUseCase: ObserveHistoryUseCase,
    observeSelectedAppUseCase: ObserveSelectedAppUseCase,
    private val addHistoryUseCase: AddHistoryUseCase,
    private val clearHistoryUseCase: ClearHistoryUseCase,
): ViewModel() {
    private val historyFlow = observeHistoryUseCase()
    private val selectedAppFlow = observeSelectedAppUseCase()
    val uiState = historyFlow.combine(selectedAppFlow) { history, app ->
        UiState(history, app)
    }

    fun onItemClick(number: String) {
        viewModelScope.launch {
            addHistoryUseCase(number)
        }
    }

    fun onDeleteAllHistory() {
        viewModelScope.launch {
            clearHistoryUseCase()
        }
    }

    data class UiState(
        val history: List<History> = emptyList(),
        val selectedApp: DefaultApp = DefaultApp.WhatsApp,
    )
}