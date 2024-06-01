@file:Suppress("UnusedPrivateMember")

package dev.theolm.wwc.ui.main.dialog.input

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import dev.theolm.wwc.R
import dev.theolm.wwc.core.ext.removeInvalidCharacters
import dev.theolm.wwc.ui.main.settings.SettingsActivity

@Preview
@Composable
private fun Preview() {
    InputDialog({}, {})
}

@Preview(locale = "pt")
@Composable
private fun PreviewPt() {
    InputDialog({}, {})
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
        PhoneInput("997088821", {}, {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDialog(
    onDismiss: () -> Unit,
    onStart: (String) -> Unit,
) {
    val context = LocalContext.current
    var phoneNumber by remember { mutableStateOf("") }

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
                        phoneNumber = phoneNumber,
                        onChange = { phoneNumber = it },
                        onDone = { onStart.invoke(phoneNumber) }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Buttons(
                        onNegative = onDismiss,
                        onPositive = { onStart.invoke(phoneNumber) }
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
    onChange: (String) -> Unit,
    onDone: () -> Unit,
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
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
        prefix = {
            CountryCodeField()
        },
        keyboardActions = KeyboardActions(
            onDone = { onDone.invoke() }
        ),
        singleLine = true,
        placeholder = { Text(text = stringResource(id = R.string.main_dialog_input_placeholder)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = null
            )
        },
        supportingText = {
            Text(text = stringResource(id = R.string.main_dialog_input_support))
        }
    )
}

@Composable
private fun CountryCodeField() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(end = 8.dp),
            text = "+55"
        )
    }
}
