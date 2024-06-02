package dev.theolm.wwc.ui.main.settings.defaultcode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.theolm.wwc.core.codes.Country
import dev.theolm.wwc.core.codes.CountryCodes
import dev.theolm.wwc.core.storage.AppDataStore
import dev.theolm.wwc.core.storage.AppSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DefaultCodeViewModel(private val appDataStore: AppDataStore) : ViewModel() {
    private val appSettings: Flow<AppSettings> = appDataStore.getAppSettings()
    val uiState: Flow<DefaultCodeUiState> = appSettings.map {
        DefaultCodeUiState(
            selectedCountry = it.selectedCountryCode
        )
    }

    fun onCountrySelected(country: Country?) {
        viewModelScope.launch {
            appSettings.firstOrNull()?.let {
                appDataStore.updateAppSettings(it.copy(selectedCountryCode = country))
            }
        }
    }
}

data class DefaultCodeUiState(
    val countries: List<Country> = CountryCodes.codes,
    val selectedCountry: Country? = null,
)
