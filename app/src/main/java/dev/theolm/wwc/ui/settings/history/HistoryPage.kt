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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.theolm.wwc.R
import dev.theolm.wwc.domain.models.History
import dev.theolm.wwc.ui.components.ListScreen
import dev.theolm.wwc.ui.settings.components.DefaultListItem
import org.koin.compose.koinInject

@Composable
fun HistoryPage(
    onBackPress: () -> Unit,
    viewModel: HistoryViewModel = koinInject(),
) {
    val uiState by viewModel.uiState.collectAsState(initial = HistoryViewModel.UiState())

    HistoryPageContent(
        onBackPress = onBackPress,
        items = uiState.history,
    )
}

@Composable
private fun HistoryPageContent(
    onBackPress: () -> Unit,
    items: List<History>,
) {
    ListScreen(
        title = stringResource(id = R.string.history),
        isEmpty = items.isEmpty(),
        emptyContent = {
            EmptyContent()
        },
        onBackPress = onBackPress,
    ) {
        if(items.isNotEmpty()) {
            items(items) {
                DefaultListItem(
                    headline = it.number,
                    supporting = it.timestamp.toString(),
                    overline = null,
                    onClick = {}
                )
            }
        }
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
fun PreviewEmptyScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        HistoryPageContent(
            onBackPress = {},
            items = emptyList()
        )
    }
}

@Preview
@Composable
fun PreviewScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        HistoryPageContent(
            onBackPress = {},
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