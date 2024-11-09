package dev.theolm.wwc.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Preview
@Composable
private fun PreviewTwoOptionsDialog() {
    TwoOptionsDialog(
        title = "Title",
        body = "This is the message of the dialog",
        positiveButton = DialogButton(
            text = "Positive",
            onClick = {}
        ),
        negativeButton = DialogButton(
            text = "Negative",
            onClick = {}
        ),
        onDismiss = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TwoOptionsDialog(
    title: String? = null,
    body: String? = null,
    positiveButton: DialogButton? = null,
    negativeButton: DialogButton? = null,
    onDismiss: () -> Unit = {},
) {
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
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                title?.let {
                    Text(
                        modifier = Modifier.padding(end = 18.dp),
                        text = title,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                body?.let {
                    Text(text = it)
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    negativeButton?.let {
                        TextButton(onClick = it.onClick) {
                            Text(it.text)
                        }
                    }

                    positiveButton?.let {
                        TextButton(onClick = it.onClick) {
                            Text(it.text)
                        }
                    }
                }
            }
        }
    }
}

internal data class DialogButton(
    val text: String,
    val onClick: () -> Unit
)
