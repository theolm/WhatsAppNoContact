package dev.theolm.wwc.ui.main.settings.home

import androidx.lifecycle.ViewModel
import dev.theolm.wwc.core.codes.Country
import dev.theolm.wwc.core.storage.AppDataStore
import kotlinx.coroutines.flow.map

class SettingsViewModel(appDataStore: AppDataStore) : ViewModel() {
    private val settingsFlow = appDataStore.getAppSettings()
    val uiState = settingsFlow.map { settings ->
        SettingsUiState(
            selectedCountryCode = settings.selectedCountryCode
        )
    }
}

data class SettingsUiState(
    val selectedCountryCode: Country? = null,
)
