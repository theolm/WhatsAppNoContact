@file:Suppress("UnusedPrivateMember")

package dev.theolm.wwc.ui.main.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import dev.theolm.wwc.R

@Preview
@Composable
private fun Preview() {
    MainDialog({}, {})
}

@Preview(locale = "pt")
@Composable
private fun PreviewPt() {
    MainDialog({}, {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDialog(
    onDismiss: () -> Unit,
    onStart: (String) -> Unit,
) {
    var phoneNumber by remember { mutableStateOf("") }
    AlertDialog(
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
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
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

@Composable
private fun PhoneInput(
    phoneNumber: String,
    onChange: (String) -> Unit,
    onDone: () -> Unit,
) {
    TextField(
        value = phoneNumber,
        label = { Text(text = stringResource(id = R.string.main_dialog_input_label)) },
        onValueChange = onChange,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Phone,
            autoCorrect = false,
            imeAction = ImeAction.Done,
        ),
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
