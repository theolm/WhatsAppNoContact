@file:Suppress("UnusedPrivateMember")

package dev.theolm.wwc.presentation.ui.main.dialog.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import dev.theolm.wwc.R
import dev.theolm.wwc.domain.models.Country
import dev.theolm.wwc.presentation.extensions.removeInvalidCharacters
import dev.theolm.wwc.presentation.ui.settings.SettingsActivity
import org.koin.compose.koinInject

@Composable
fun PhoneInputDialog(
    onDismiss: () -> Unit,
    onStart: (String) -> Unit,
    viewModel: InputDialogViewModel = koinInject(),
) {
    val uiState by viewModel.uiState.collectAsState(initial = InputDialogUiState())
    PhoneInputDialogContent(
        phoneNumber = uiState.phoneNumber,
        inputField = uiState.inputField,
        selectedCountryCode = uiState.selectedCountryCode,
        onDismiss = onDismiss,
        onInputChange = { viewModel.onInputChanged(it) },
        onStart = onStart
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PhoneInputDialogContent(
    phoneNumber: String,
    inputField: String,
    selectedCountryCode: Country?,
    onDismiss: () -> Unit = {},
    onInputChange: (String) -> Unit = {},
    onStart: (String) -> Unit = {},
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
                        onDone = { onStart(phoneNumber) }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Buttons(
                        onNegative = onDismiss,
                        onPositive = { onStart(phoneNumber) }
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

@Suppress("ModifierHeightWithText")
@Composable
private fun PhoneInput(
    phoneNumber: String,
    defaultCountryCode: Country?,
    onCountryCodeClick: () -> Unit,
    onChange: (String) -> Unit,
    onDone: () -> Unit,
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        value = phoneNumber,
        shape = RoundedCornerShape(12.dp),
        label = { Text(text = stringResource(id = R.string.main_dialog_input_label)) },
        onValueChange = {
            it.removeInvalidCharacters().let { cleanPhone ->
                onChange.invoke(cleanPhone)
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Phone,
            autoCorrectEnabled = false,
            imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(
            onDone = { onDone.invoke() }
        ),
        singleLine = true,
        leadingIcon = {
            Row(
                modifier = Modifier.padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null
                )
                defaultCountryCode?.let {
                    CountryCodeField(
                        countryCode = it,
                        onClick = onCountryCodeClick
                    )
                }
            }
        },
        supportingText = {
            Text(text = stringResource(id = R.string.main_dialog_input_support))
        }
    )
}

@Composable
private fun CountryCodeField(
    countryCode: Country,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = countryCode.code
        )
    }
}

@Preview
@Composable
private fun Preview() {
    PhoneInputDialogContent(
        phoneNumber = "",
        inputField = "",
        selectedCountryCode = Country("Brazil", "BR"),
        onDismiss = {},
        onInputChange = {},
        onStart = {}
    )
}

@Preview(locale = "pt")
@Composable
private fun PreviewPt() {
    PhoneInputDialogContent(
        phoneNumber = "",
        inputField = "",
        selectedCountryCode = Country("Brazil", "BR"),
        onDismiss = {},
        onInputChange = {},
        onStart = {}
    )
}

@Preview(locale = "pt", showBackground = true)
@Composable
private fun EditTextPreview() {
    Surface(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        tonalElevation = AlertDialogDefaults.TonalElevation,
    ) {
        PhoneInput(
            phoneNumber = "997088821",
            defaultCountryCode = Country("Brasil", "+55"),
            onChange = {},
            onDone = {},
            onCountryCodeClick = {}
        )
    }
}
