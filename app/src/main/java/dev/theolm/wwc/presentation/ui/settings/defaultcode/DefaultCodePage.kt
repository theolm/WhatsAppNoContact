package dev.theolm.wwc.presentation.ui.settings.defaultcode

import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.theolm.wwc.R
import dev.theolm.wwc.domain.models.Country
import dev.theolm.wwc.presentation.ui.components.ListScreen
import dev.theolm.wwc.presentation.ui.components.SelectableItemList
import org.koin.compose.koinInject

@Composable
fun DefaultCodePage(
    onBackPress: () -> Unit,
    viewModel: DefaultCodeViewModel = koinInject(),
) {
    val uiState by viewModel.uiState.collectAsState(initial = DefaultCodeUiState())

    DefaultCodePageContent(
        selectedCountry = uiState.selectedCountry,
        countries = uiState.countries,
        onBackPress = onBackPress,
        onCountrySelect = viewModel::onCountrySelected
    )
}

@Composable
private fun DefaultCodePageContent(
    selectedCountry: Country?,
    countries: List<Country>,
    onBackPress: () -> Unit = {},
    onCountrySelect: (Country?) -> Unit = {},
) {
    ListScreen(
        title = stringResource(id = R.string.country_code_page_title),
        onBackPress = onBackPress
    ) {
        item {
            SelectableItemList(
                headlineText = stringResource(id = R.string.no_default_code),
                supportText = stringResource(id = R.string.no_default_code_support),
                isSelected = selectedCountry == null,
                onClick = {
                    onCountrySelect(null)
                }
            )
        }

        items(countries) { country ->
            SelectableItemList(
                headlineText = stringResource(id = country.name),
                supportText = country.code,
                isSelected = country == selectedCountry,
                onClick = {
                    onCountrySelect(country)
                }
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DefaultCodePageContent(
        selectedCountry = CountryCodes.codes.first(),
        countries = CountryCodes.codes,
    )
}
