package dev.theolm.wwc.presentation.ui.settings.defaultcode

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.theolm.wwc.domain.models.Country
import dev.theolm.wwc.domain.usecase.ObserveSelectedCountryUseCase
import dev.theolm.wwc.domain.usecase.UpdateSelectedCountryUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DefaultCodeViewModel(
    observeSelectedCountry: ObserveSelectedCountryUseCase,
    private val updateSelectedCountry: UpdateSelectedCountryUseCase,
) : ViewModel() {
    private val appSettings: Flow<Country?> = observeSelectedCountry()
    val uiState = MutableStateFlow(DefaultCodeUiState())

    init {
        viewModelScope.launch {
            appSettings.collectLatest {
                uiState.emit(uiState.value.copy(selectedCountry = it))
            }
        }
    }

    fun onCountrySelected(country: Country?) {
        viewModelScope.launch {
            updateSelectedCountry(country)
        }
    }
}

data class DefaultCodeUiState(
    val searchField: TextFieldState = TextFieldState(),
    val countries: List<Country> = CountryCodes.codes,
    val selectedCountry: Country? = null,
)
