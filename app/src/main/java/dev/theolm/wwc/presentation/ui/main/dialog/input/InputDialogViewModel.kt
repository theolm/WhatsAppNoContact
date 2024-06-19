package dev.theolm.wwc.presentation.ui.main.dialog.input

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

    val uiState = combine(inputFlow, selectedAppFlow, selectedCountryFlow) { input, app, country ->
        InputDialogUiState(
            inputField = input,
            selectedCountryCode = country,
            selectedApp = app,
            phoneNumber = country?.code.orEmpty() + input
        )
    }

    fun onInputChanged(input: String) {
        inputFlow.tryEmit(input)
    }
}

data class InputDialogUiState(
    val inputField: String = "",
    val selectedApp: DefaultApp = DefaultApp.WhatsApp,
    val selectedCountryCode: Country? = null,
    val phoneNumber: String = selectedCountryCode?.code.orEmpty() + inputField,
)
