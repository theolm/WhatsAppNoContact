package dev.theolm.wwc.presentation.ui.settings.home

import androidx.lifecycle.ViewModel
import dev.theolm.wwc.domain.models.Country
import dev.theolm.wwc.domain.usecase.ObserveSelectedCountryUseCase
import kotlinx.coroutines.flow.map

class SettingsViewModel(observeSelectedCountry: ObserveSelectedCountryUseCase) : ViewModel() {
    private val selectedCountryFlow = observeSelectedCountry()
    val uiState = selectedCountryFlow.map { countryCode ->
        SettingsUiState(
            selectedCountryCode = countryCode
        )
    }
}

data class SettingsUiState(
    val selectedCountryCode: Country? = null,
)
