package dev.theolm.wwc.presentation.ui.settings.defaultcode

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.theolm.wwc.R
import dev.theolm.wwc.domain.models.Country
import dev.theolm.wwc.presentation.ui.components.ListScreen
import dev.theolm.wwc.presentation.ui.components.SelectableItemList
import dev.theolm.wwc.presentation.ui.theme.applyOpacity
import dev.theolm.wwc.presentation.ui.theme.harmonizeWithPrimary
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
        search = uiState.searchField,
        onBackPress = onBackPress,
        onCountrySelect = viewModel::onCountrySelected
    )
}

@Composable
private fun DefaultCodePageContent(
    selectedCountry: Country?,
    countries: List<Country>,
    search: TextFieldState,
    onBackPress: () -> Unit = {},
    onCountrySelect: (Country?) -> Unit = {},
) {
    ListScreen(
        title = stringResource(id = R.string.country_code_page_title),
        onBackPress = onBackPress
    ) {
        item {
            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                value = search,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            AnimatedVisibility(
                visible = search.text.isBlank(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                SelectableItemList(
                    headlineText = stringResource(id = R.string.no_default_code),
                    supportText = stringResource(id = R.string.no_default_code_support),
                    isSelected = selectedCountry == null,
                    onClick = {
                        onCountrySelect(null)
                    }
                )
            }
        }

        items(countries) { country ->
            val countryName = stringResource(id = country.name)
            if (search.countryMatchSearch(countryName)) {
                SelectableItemList(
                    headlineText = countryName,
                    supportText = country.code,
                    isSelected = country == selectedCountry,
                    onClick = {
                        onCountrySelect(country)
                    }
                )
            }
        }
    }
}

private fun TextFieldState.countryMatchSearch(countryName: String) =
    this.text.isBlank() || countryName.contains(this.text.toString(), ignoreCase = true)

@Preview
@Composable
private fun Preview() {
    DefaultCodePageContent(
        selectedCountry = CountryCodes.codes.first(),
        search = TextFieldState(),
        countries = CountryCodes.codes,
    )
}

@Preview
@Composable
private fun PreviewSearchBar() {
    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        value = TextFieldState(),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(
    value: TextFieldState,
    modifier: Modifier = Modifier,
) {
    val textColor = MaterialTheme.colorScheme.onSurfaceVariant.harmonizeWithPrimary()
    val containerColor = MaterialTheme.colorScheme.surfaceVariant
    val placeholderColor =
        MaterialTheme.colorScheme.onSurfaceVariant.harmonizeWithPrimary().applyOpacity(true)
    BasicTextField(
        modifier = modifier,
        state = value,
        lineLimits = TextFieldLineLimits.SingleLine,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences
        ),
        decorator = {
            TextFieldDefaults.DecorationBox(
                value = value.text.toString(),
                innerTextField = it,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null
                    )
                },
                placeholder = {
                    Text(text = "Search")
                },
                interactionSource = remember { MutableInteractionSource() },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    disabledTextColor = textColor,
                    errorTextColor = textColor,
                    focusedContainerColor = containerColor,
                    unfocusedContainerColor = containerColor,
                    disabledContainerColor = containerColor,
                    errorContainerColor = containerColor,
                    errorPlaceholderColor = placeholderColor,
                    disabledPlaceholderColor = placeholderColor,
                    focusedPlaceholderColor = placeholderColor,
                    unfocusedPlaceholderColor = placeholderColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    disabledLeadingIconColor = placeholderColor,
                    errorLeadingIconColor = placeholderColor,
                    focusedLeadingIconColor = textColor,
                    unfocusedLeadingIconColor = placeholderColor,
                ),
                shape = RoundedCornerShape(percent = 100),
            )
        }
    )
}
