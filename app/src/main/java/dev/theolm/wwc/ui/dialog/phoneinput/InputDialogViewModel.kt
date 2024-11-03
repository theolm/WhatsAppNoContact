package dev.theolm.wwc.ui.dialog.phoneinput

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.theolm.wwc.domain.models.DefaultApp
import dev.theolm.wwc.domain.usecase.AddHistoryUseCase
import dev.theolm.wwc.domain.usecase.ObserveSelectedAppUseCase
import dev.theolm.wwc.domain.usecase.ObserveSelectedCountryUseCase
import dev.theolm.wwc.models.Country
import dev.theolm.wwc.models.toCountry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class InputDialogViewModel(
    observeSelectedAppUseCase: ObserveSelectedAppUseCase,
    observeSelectedCountryUseCase: ObserveSelectedCountryUseCase,
    private val addHistoryUseCase: AddHistoryUseCase,
) : ViewModel() {
    private val selectedAppFlow = observeSelectedAppUseCase()
    private val selectedCountryFlow = observeSelectedCountryUseCase().map { it?.toCountry() }
    private val inputFlow = MutableStateFlow("")
    private val ignoreCodeFlow = MutableStateFlow(false)

    val uiState = combine(
        inputFlow,
        selectedAppFlow,
        selectedCountryFlow,
        ignoreCodeFlow
    ) { input, app, country, ignoreCode ->
        InputDialogUiState(
            inputField = input,
            selectedCountryCode = if (ignoreCode) null else country,
            selectedApp = app,
        )
    }

    fun onInputChanged(input: String) {
        inputFlow.tryEmit(input)
    }

    fun ignoreCountryCode() {
        ignoreCodeFlow.tryEmit(true)
    }

    fun onStartChat() {
        viewModelScope.launch {
            val currentState = uiState.first()
            addHistoryUseCase(
                number = currentState.phoneNumber,
                timestamp = System.currentTimeMillis(),
            )
        }
    }
}

data class InputDialogUiState(
    val inputField: String = "",
    val selectedApp: DefaultApp = DefaultApp.WhatsApp,
    val selectedCountryCode: Country? = null,
    val ignoreDefaultCode: Boolean = false,
) {
    val phoneNumber: String get() {
        return if (ignoreDefaultCode) {
            inputField
        } else {
            selectedCountryCode?.code.orEmpty() + inputField
        }
    }
}
