@file:Suppress("UnusedPrivateMember")

package dev.theolm.wwc.presentation.ui.dialog.phoneinput

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import dev.theolm.wwc.R
import dev.theolm.wwc.domain.models.Country
import dev.theolm.wwc.domain.models.DefaultApp
import dev.theolm.wwc.presentation.ui.settings.SettingsActivity
import org.koin.compose.koinInject

@Composable
fun PhoneInputDialog(
    onDismiss: () -> Unit,
    onStart: (String, DefaultApp) -> Unit,
    initialNumber: String? = null,
    viewModel: InputDialogViewModel = koinInject(),
) {
    val uiState by viewModel.uiState.collectAsState(initial = InputDialogUiState())
    PhoneInputDialogContent(
        inputField = uiState.inputField,
        selectedCountryCode = uiState.selectedCountryCode,
        onDismiss = onDismiss,
        onInputChange = { viewModel.onInputChanged(it) },
        onIgnoreDefaultCode = {
            viewModel.ignoreCountryCode()
        },
        onStart = {
            onStart(uiState.phoneNumber, uiState.selectedApp)
        }
    )

    LaunchedEffect(key1 = initialNumber) {
        if (initialNumber != null) {
            viewModel.onInputChanged(initialNumber)
        }
    }
}

@Suppress("LongParameterList")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PhoneInputDialogContent(
    inputField: String,
    selectedCountryCode: Country?,
    onDismiss: () -> Unit = {},
    onInputChange: (String) -> Unit = {},
    onIgnoreDefaultCode: () -> Unit = {},
    onStart: () -> Unit = {},
) {
    val context = LocalContext.current
    BasicAlertDialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
        )
    ) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            tonalElevation = AlertDialogDefaults.TonalElevation,
            shape = MaterialTheme.shapes.large,
        ) {
            Box {
                IconButton(
                    modifier = Modifier.align(Alignment.TopEnd),
                    onClick = {
                        SettingsActivity.start(context)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = stringResource(id = R.string.settings)
                    )
                }
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        modifier = Modifier.padding(end = 18.dp),
                        text = stringResource(id = R.string.main_dialog_title),
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = stringResource(id = R.string.main_dialog_text))
                    Spacer(modifier = Modifier.height(16.dp))
                    PhoneInput(
                        phoneNumber = inputField,
                        defaultCountryCode = selectedCountryCode,
                        onCountryCodeClick = {
                            SettingsActivity.startOnCountryCode(context)
                        },
                        onChange = onInputChange,
                        onIgnoreDefaultCode = onIgnoreDefaultCode,
                        onDone = onStart
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Buttons(
                        onNegative = onDismiss,
                        onPositive = onStart
                    )
                }
            }
        }
    }
}

@Composable
private fun Buttons(onNegative: () -> Unit, onPositive: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(onClick = onNegative) {
            Text(stringResource(id = R.string.main_dialog_negative_button))
        }
        TextButton(onClick = onPositive) {
            Text(stringResource(id = R.string.main_dialog_positive_button))
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PhoneInputDialogContent(
        inputField = "",
        selectedCountryCode = Country(R.string.brazil, "+55"),
        onDismiss = {},
        onInputChange = {},
        onStart = {}
    )
}

@Preview(locale = "pt")
@Composable
private fun PreviewPt() {
    PhoneInputDialogContent(
        inputField = "",
        selectedCountryCode = Country(R.string.brazil, "+55"),
        onDismiss = {},
        onInputChange = {},
        onStart = {}
    )
}
