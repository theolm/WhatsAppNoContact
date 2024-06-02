package dev.theolm.wwc.ui.main.dialog.input

import androidx.lifecycle.ViewModel
import dev.theolm.wwc.core.codes.Country
import dev.theolm.wwc.core.storage.AppDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class InputDialogViewModel(appDataStore: AppDataStore) : ViewModel() {
    private val settingsFlow = appDataStore.getAppSettings()
    private val inputFlow = MutableStateFlow("")

    val uiState = settingsFlow
        .combine(inputFlow) { settings, input ->
            InputDialogUiState(
                inputField = input,
                selectedCountryCode = settings.selectedCountryCode
            )
        }

    fun onInputChanged(input: String) {
        inputFlow.tryEmit(input)
    }
}

data class InputDialogUiState(
    val inputField: String = "",
    val selectedCountryCode: Country? = null,
    val phoneNumber: String = selectedCountryCode?.code.orEmpty() + inputField
)
