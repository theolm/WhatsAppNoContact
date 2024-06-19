package dev.theolm.wwc.presentation.ui.settings.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.theolm.wwc.R
import dev.theolm.wwc.domain.models.Country
import dev.theolm.wwc.domain.models.DefaultApp
import dev.theolm.wwc.presentation.extensions.checkIfWpBusinessIsInstalled
import dev.theolm.wwc.presentation.extensions.checkIfWpIsInstalled
import dev.theolm.wwc.presentation.ui.components.DefaultTopAppBar
import org.koin.compose.koinInject

@Composable
fun SettingsHomePage(
    onCountryCodeClick: () -> Unit,
    onDefaultAppClick: () -> Unit,
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
        selectedApp = uiState.selectedApp
    )
}

@Suppress("LongParameterList")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsHomePageContent(
    onBackPress: () -> Unit,
    selectedApp: DefaultApp,
    selectedCountryCode: Country?,
    onCountryCodeClick: () -> Unit,
    onDefaultAppClick: () -> Unit,
    showAppSelection: Boolean,
) {
    val scrollBarBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBarBehavior.nestedScrollConnection),
        topBar = {
            DefaultTopAppBar(
                title = stringResource(id = R.string.settings),
                scrollBarBehavior = scrollBarBehavior,
                onBackPress = onBackPress
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding()),
            contentPadding = PaddingValues(top = 16.dp, bottom = 64.dp, start = 16.dp, end = 16.dp)
        ) {
            item {
                val support = selectedCountryCode?.let { country ->
                    "${stringResource(id = country.name)} (${country.code})"
                } ?: stringResource(id = R.string.no_code_selected)
                SettingsItem(
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
                    SettingsItem(
                        headline = stringResource(id = R.string.select_app_headline),
                        supporting = stringResource(id = supportTextRes),
                        overline = stringResource(id = R.string.select_app_overline),
                        onClick = onDefaultAppClick
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsItem(
    headline: String,
    supporting: String,
    overline: String?,
    onClick: () -> Unit,
) {
    ListItem(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable(
                onClick = onClick
            ),
        headlineContent = {
            Text(headline)
        },
        supportingContent = {
            Text(supporting)
        },
        overlineContent = overline?.let {
            {
                Text(it)
            }
        }
    )
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
        selectedApp = DefaultApp.WhatsApp
    )
}

@Composable
private fun showAppSelector(): Boolean {
    val context = LocalContext.current
    return context.checkIfWpIsInstalled() && context.checkIfWpBusinessIsInstalled()
}
