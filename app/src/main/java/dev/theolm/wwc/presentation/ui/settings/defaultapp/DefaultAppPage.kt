package dev.theolm.wwc.presentation.ui.settings.defaultapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.theolm.wwc.R
import dev.theolm.wwc.domain.models.DefaultApp
import dev.theolm.wwc.presentation.ui.components.ListScreen
import dev.theolm.wwc.presentation.ui.components.SelectableItemList
import dev.theolm.wwc.core.WhatsAppPackages
import org.koin.compose.koinInject

@Composable
fun DefaultAppPage(
    onBackPress: () -> Unit,
    viewModel: DefaultAppViewModel = koinInject(),
) {
    val uiState by viewModel.uiState.collectAsState(initial = DefaultAppUiState())

    DefaultAppPageContent(
        onBackPress = onBackPress,
        selectedApp = uiState.selectedApp,
        onAppSelect = {
            viewModel.onAppSelected(it)
        }
    )
}

@Composable
private fun DefaultAppPageContent(
    onBackPress: () -> Unit,
    onAppSelect: (DefaultApp) -> Unit,
    selectedApp: DefaultApp,
) {
    ListScreen(
        title = stringResource(id = R.string.select_app_page_title),
        onBackPress = onBackPress
    ) {
        item {
            SelectableItemList(
                headlineText = stringResource(id = R.string.select_app_wp),
                supportText = dev.theolm.wwc.core.WhatsAppPackages.Whatsapp,
                isSelected = selectedApp == DefaultApp.WhatsApp,
                onClick = {
                    onAppSelect(DefaultApp.WhatsApp)
                }
            )
        }

        item {
            SelectableItemList(
                headlineText = stringResource(id = R.string.select_app_wp4b),
                supportText = dev.theolm.wwc.core.WhatsAppPackages.WhatsappBuisness,
                isSelected = selectedApp == DefaultApp.WhatsAppBusiness,
                onClick = {
                    onAppSelect(DefaultApp.WhatsAppBusiness)
                }
            )
        }
    }
}

@Preview
@Composable
private fun DefaultAppPagePreview() {
    DefaultAppPageContent(
        onBackPress = {},
        selectedApp = DefaultApp.WhatsApp,
        onAppSelect = {}
    )
}
