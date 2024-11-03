package dev.theolm.wwc.ui.settings.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.theolm.wwc.R
import dev.theolm.wwc.domain.models.DefaultApp
import dev.theolm.wwc.ext.checkIfWpBusinessIsInstalled
import dev.theolm.wwc.ext.checkIfWpIsInstalled
import dev.theolm.wwc.models.Country
import dev.theolm.wwc.ui.components.ListScreen
import dev.theolm.wwc.ui.settings.components.DefaultListItem
import org.koin.compose.koinInject

@Composable
fun SettingsHomePage(
    onCountryCodeClick: () -> Unit,
    onDefaultAppClick: () -> Unit,
    onAboutClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onBackPress: () -> Unit,
    viewModel: SettingsViewModel = koinInject(),
) {
    val uiState by viewModel.uiState.collectAsState(initial = SettingsUiState())
    val showAppSelector = showAppSelector()
    SettingsHomePageContent(
        onBackPress = onBackPress,
        selectedCountryCode = uiState.selectedCountryCode,
        onCountryCodeClick = onCountryCodeClick,
        showAppSelection = showAppSelector,
        onDefaultAppClick = onDefaultAppClick,
        selectedApp = uiState.selectedApp,
        onAboutClick = onAboutClick,
        onHistoryClick = onHistoryClick
    )
}

@Suppress("LongParameterList")
@Composable
private fun SettingsHomePageContent(
    onBackPress: () -> Unit,
    selectedApp: DefaultApp,
    selectedCountryCode: Country?,
    onCountryCodeClick: () -> Unit,
    onDefaultAppClick: () -> Unit,
    onAboutClick: () -> Unit,
    onHistoryClick: () -> Unit ,
    showAppSelection: Boolean,
) {
    ListScreen(
        title = stringResource(id = R.string.settings),
        onBackPress = onBackPress
    ) {
        item {
            val support = selectedCountryCode?.let { country ->
                "${stringResource(id = country.name)} (${country.code})"
            } ?: stringResource(id = R.string.no_code_selected)
            DefaultListItem(
                headline = stringResource(id = R.string.select_code_headline),
                supporting = support,
                overline = stringResource(id = R.string.select_code_overline),
                onClick = onCountryCodeClick
            )
        }

        if (showAppSelection) {
            val supportTextRes = if (selectedApp == DefaultApp.WhatsApp) {
                R.string.select_app_wp
            } else {
                R.string.select_app_wp4b
            }

            item {
                DefaultListItem(
                    headline = stringResource(id = R.string.select_app_headline),
                    supporting = stringResource(id = supportTextRes),
                    overline = stringResource(id = R.string.select_app_overline),
                    onClick = onDefaultAppClick
                )
            }
        }

        item {
            DefaultListItem(
                headline = stringResource(id = R.string.history),
                supporting = stringResource(id = R.string.history_supporting),
                overline = null,
                onClick = onHistoryClick
            )
        }

        item {
            ListItem(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .clickable(
                        onClick = onAboutClick
                    ),
                headlineContent = {
                    Text(stringResource(id = R.string.about))
                },
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    SettingsHomePageContent(
        onBackPress = {},
        selectedCountryCode = Country(R.string.brazil, "+55"),
        onCountryCodeClick = {},
        showAppSelection = true,
        onDefaultAppClick = {},
        onAboutClick = {},
        onHistoryClick = {},
        selectedApp = DefaultApp.WhatsApp
    )
}

@Composable
private fun showAppSelector(): Boolean {
    val context = LocalContext.current
    return context.checkIfWpIsInstalled() && context.checkIfWpBusinessIsInstalled()
}
