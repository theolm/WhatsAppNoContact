package dev.theolm.wwc.ui.main.settings.defaultcode

import androidx.lifecycle.ViewModel
import dev.theolm.wwc.core.codes.Country
import dev.theolm.wwc.core.codes.CountryCodes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DefaultCodeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DefaultCodeUiState())
    val uiState: StateFlow<DefaultCodeUiState> = _uiState

    fun onCountrySelected(country: Country?) {
        _uiState.tryEmit(DefaultCodeUiState(selectedCountry = country))
    }
}

data class DefaultCodeUiState(
    val countries: List<Country> = CountryCodes.codes,
    val selectedCountry: Country? = null,
)
