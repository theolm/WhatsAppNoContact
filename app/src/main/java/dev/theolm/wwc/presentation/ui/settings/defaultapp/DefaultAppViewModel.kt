package dev.theolm.wwc.presentation.ui.settings.defaultapp

import androidx.lifecycle.ViewModel
import dev.theolm.wwc.domain.models.DefaultApp
import dev.theolm.wwc.domain.usecase.ObserveSettingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow

class DefaultAppViewModel(
    observeSettingsUseCase: ObserveSettingsUseCase
) : ViewModel() {
    val uiState = MutableStateFlow(DefaultAppUiState())
}

data class DefaultAppUiState(
    val selectedApp: DefaultApp = DefaultApp.WhatsApp
)
