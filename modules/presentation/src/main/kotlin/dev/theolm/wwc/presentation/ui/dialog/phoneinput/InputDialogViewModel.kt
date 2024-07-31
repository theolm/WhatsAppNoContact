package dev.theolm.wwc.presentation.ui.dialog.phoneinput

import androidx.lifecycle.ViewModel
import dev.theolm.wwc.domain.models.Country
import dev.theolm.wwc.domain.models.DefaultApp
import dev.theolm.wwc.domain.usecase.ObserveSelectedAppUseCase
import dev.theolm.wwc.domain.usecase.ObserveSelectedCountryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class InputDialogViewModel(
    observeSelectedAppUseCase: ObserveSelectedAppUseCase,
    observeSelectedCountryUseCase: ObserveSelectedCountryUseCase,
) : ViewModel() {
    private val selectedAppFlow = observeSelectedAppUseCase()
    private val selectedCountryFlow = observeSelectedCountryUseCase()
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
