package dev.theolm.wwc.presentation.ui.settings.defaultapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.theolm.wwc.domain.models.DefaultApp
import dev.theolm.wwc.domain.usecase.ObserveSelectedAppUseCase
import dev.theolm.wwc.domain.usecase.UpdateSelectedAppUseCase
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DefaultAppViewModel(
    observeSelectedAppUseCase: ObserveSelectedAppUseCase,
    private val updateSelectedAppUseCase: UpdateSelectedAppUseCase,
) : ViewModel() {
    private val selectedAppFlow = observeSelectedAppUseCase()
    val uiState = selectedAppFlow.map {
        DefaultAppUiState(
            selectedApp = it
        )
    }

    fun onAppSelected(defaultApp: DefaultApp) {
        viewModelScope.launch {
            updateSelectedAppUseCase(defaultApp)
        }
    }
}

data class DefaultAppUiState(
    val selectedApp: DefaultApp = DefaultApp.WhatsApp,
)
