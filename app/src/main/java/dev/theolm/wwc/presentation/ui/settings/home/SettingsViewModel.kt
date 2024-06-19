package dev.theolm.wwc.presentation.ui.settings.home

import androidx.lifecycle.ViewModel
import dev.theolm.wwc.domain.models.Country
import dev.theolm.wwc.domain.usecase.ObserveSettingsUseCase
import kotlinx.coroutines.flow.map

class SettingsViewModel(observeSettings: ObserveSettingsUseCase) : ViewModel() {
    private val settingsFlow = observeSettings()
    val uiState = settingsFlow.map { settings ->
        SettingsUiState(
            selectedCountryCode = settings.selectedCountryCode
        )
    }
}

data class SettingsUiState(
    val selectedCountryCode: Country? = null,
)
