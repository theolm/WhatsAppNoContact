package dev.theolm.wwc.ui.dialog.phoneinput

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.theolm.wwc.R
import dev.theolm.wwc.ext.removeInvalidCharacters
import dev.theolm.wwc.models.Country
import kotlinx.coroutines.delay

private const val ResetCounterDelay = 300L

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
            defaultCountryCode = Country(R.string.brazil, "+55"),
            onChange = {},
            onDone = {},
            onIgnoreDefaultCode = {},
            onCountryCodeClick = {}
        )
    }
}

@Preview(locale = "pt", showBackground = true)
@Composable
private fun EditTextPreview2() {
    Surface(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        tonalElevation = AlertDialogDefaults.TonalElevation,
    ) {
        PhoneInput(
            phoneNumber = "",
            defaultCountryCode = Country(R.string.brazil, "+55"),
            onChange = {},
            onDone = {},
            onIgnoreDefaultCode = {},
            onCountryCodeClick = {}
        )
    }
}

@Composable
internal fun PhoneInput(
    phoneNumber: String,
    defaultCountryCode: Country?,
    onCountryCodeClick: () -> Unit,
    onChange: (String) -> Unit,
    onIgnoreDefaultCode: () -> Unit,
    onDone: () -> Unit,
) {
    var backspaceCounter by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(backspaceCounter) {
        runCatching {
            delay(ResetCounterDelay)
            backspaceCounter = 0
        }
    }

    OutlinedTextField(
        modifier = Modifier
            .onKeyEvent {
                if (it.key == Key.Backspace) {
                    if (phoneNumber.isBlank()) {
                        backspaceCounter++
                        if (backspaceCounter == 2) {
                            onIgnoreDefaultCode.invoke()
                            backspaceCounter = 0
                        }
                    } else {
                        backspaceCounter = 0
                    }
                }
                return@onKeyEvent true
            }
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        value = phoneNumber,
        shape = RoundedCornerShape(12.dp),
        label = {
            Text(
                text = stringResource(id = R.string.main_dialog_input_label),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
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
            LeadIcon(
                defaultCountryCode = defaultCountryCode,
                onCountryCodeClick = onCountryCodeClick
            )
        },
        trailingIcon = {
            ClearIcon(
                phoneNumber = phoneNumber,
                onChange = onChange,
                onIgnoreDefaultCode = onIgnoreDefaultCode
            )
        },
        supportingText = {
            Text(text = stringResource(id = R.string.main_dialog_input_support))
        }
    )
}

@Composable
private fun LeadIcon(
    defaultCountryCode: Country?,
    onCountryCodeClick: () -> Unit,
) {
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
}

@Composable
private fun ClearIcon(
    phoneNumber: String,
    onChange: (String) -> Unit,
    onIgnoreDefaultCode: () -> Unit,
) {
    var clearButtonCounter by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(clearButtonCounter) {
        runCatching {
            delay(ResetCounterDelay)
            clearButtonCounter = 0
        }
    }

    IconButton(
        onClick = {
            if (phoneNumber.isBlank()) {
                clearButtonCounter++
                if (clearButtonCounter == 2) {
                    onIgnoreDefaultCode.invoke()
                    clearButtonCounter = 0
                }
            } else {
                onChange("")
                clearButtonCounter = 0
            }
        }
    ) {
        Icon(
            imageVector = Icons.Rounded.Close,
            contentDescription = null
        )
    }
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
