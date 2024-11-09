package dev.theolm.wwc.ui.settings.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CodeOff
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.theolm.wwc.R
import dev.theolm.wwc.domain.models.DefaultApp
import dev.theolm.wwc.domain.models.History
import dev.theolm.wwc.ext.getDate
import dev.theolm.wwc.ui.components.DialogButton
import dev.theolm.wwc.ui.components.ListScreen
import dev.theolm.wwc.ui.components.TwoOptionsDialog
import dev.theolm.wwc.ui.settings.components.DefaultListItem
import org.koin.compose.koinInject

@Composable
fun HistoryPage(
    onBackPress: () -> Unit,
    onItemClick: (String, DefaultApp) -> Unit,
    viewModel: HistoryViewModel = koinInject(),
) {
    val uiState by viewModel.uiState.collectAsState(initial = HistoryViewModel.UiState())
    HistoryPageContent(
        onBackPress = onBackPress,
        items = uiState.history,
        onStartChat = {
            onItemClick(it, uiState.selectedApp)
            viewModel.onItemClick(it)
        },
        onDeleteAll = {
            viewModel.onDeleteAllHistory()
        }
    )
}

@Composable
private fun HistoryPageContent(
    onBackPress: () -> Unit,
    onStartChat: (String) -> Unit,
    onDeleteAll: () -> Unit,
    items: List<History>,
) {
    var showStartChatDialog by remember { mutableStateOf(false) }
    var showDeleteAllDialog by remember { mutableStateOf(false) }
    var number by remember { mutableStateOf("") }

    ListScreen(
        title = stringResource(id = R.string.history),
        isEmpty = items.isEmpty(),
        topBarActions = {
            IconButton(
                onClick = {
                    showDeleteAllDialog = true
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.DeleteOutline,
                    contentDescription = stringResource(R.string.history_delete_button)
                )
            }
        },
        emptyContent = {
            EmptyContent()
        },
        onBackPress = onBackPress,
    ) {
        if (items.isNotEmpty()) {
            items(items) {
                DefaultListItem(
                    headline = it.number,
                    supporting = it.getDate(),
                    overline = null,
                    onClick = {
                        number = it.number
                        showStartChatDialog = true
                    }
                )
            }
        }
    }

    if (showStartChatDialog) {
        StartChatDialog(
            number = number,
            onPositive = {
                showStartChatDialog = false
                onStartChat(number)
            },
            onNegative = {
                showStartChatDialog = false
                number = ""
            }
        )
    }

    if (showDeleteAllDialog) {
        DeleteAllDialog(
            onPositive = {
                showDeleteAllDialog = false
                onDeleteAll()
            },
            onNegative = {
                showDeleteAllDialog = false
            }
        )
    }
}

@Composable
private fun StartChatDialog(
    number: String,
    onPositive: () -> Unit,
    onNegative: () -> Unit,
) {
    TwoOptionsDialog(
        title = stringResource(R.string.history_start_chat),
        body = stringResource(R.string.history_start_chat_message, number),
        positiveButton = DialogButton(
            text = stringResource(R.string.yes),
            onClick = onPositive
        ),
        negativeButton = DialogButton(
            text = stringResource(R.string.no),
            onClick = onNegative
        ),
        onDismiss = onNegative
    )
}

@Composable
private fun DeleteAllDialog(
    onPositive: () -> Unit,
    onNegative: () -> Unit,
) {
    TwoOptionsDialog(
        title = stringResource(R.string.history_delete_all_title),
        body = stringResource(R.string.history_delete_all_message),
        positiveButton = DialogButton(
            text = stringResource(R.string.history_delete_all_positive_button),
            onClick = onPositive
        ),
        negativeButton = DialogButton(
            text = stringResource(R.string.history_delete_all_positive_negative),
            onClick = onNegative
        ),
        onDismiss = onNegative
    )
}

@Preview
@Composable
private fun PreviewConfirmationDialog() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        StartChatDialog(
            number = "+555199708212",
            onNegative = {},
            onPositive = {}
        )
    }
}

@Composable
private fun EmptyContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(70.dp),
            imageVector = Icons.Rounded.CodeOff,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.history_empty_state),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview
@Composable
private fun PreviewEmptyScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        HistoryPageContent(
            onBackPress = {},
            onStartChat = {},
            items = emptyList(),
            onDeleteAll = {},
        )
    }
}

@Preview
@Composable
private fun PreviewScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        HistoryPageContent(
            onBackPress = {},
            onStartChat = {},
            onDeleteAll = {},
            items = listOf(
                History(
                    id = 1,
                    number = "12345",
                    timestamp = 1234567890
                ),
                History(
                    id = 2,
                    number = "9878644",
                    timestamp = 83217732
                )
            )
        )
    }
}
