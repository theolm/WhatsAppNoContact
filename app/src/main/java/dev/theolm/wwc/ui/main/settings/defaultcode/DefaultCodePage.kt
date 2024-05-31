package dev.theolm.wwc.ui.main.settings.defaultcode

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.theolm.wwc.R
import dev.theolm.wwc.core.codes.Country
import dev.theolm.wwc.ui.components.DefaultTopAppBar
import dev.theolm.wwc.ui.main.settings.LocalNavController
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject

@Serializable
object DefaultCodeRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultCodePage(viewModel: DefaultCodeViewModel = koinInject()) {
    val uiState by viewModel.uiState.collectAsState(initial = DefaultCodeUiState())

    val navController = LocalNavController.current
    val scrollBarBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBarBehavior.nestedScrollConnection),
        topBar = {
            DefaultTopAppBar(
                title = stringResource(id = R.string.default_code),
                scrollBarBehavior = scrollBarBehavior,
                onBackPress = {
                    navController?.popBackStack()
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding()),
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = 64.dp,
                start = 16.dp,
                end = 16.dp
            )
        ) {
            item {
                ListItem(
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable {
                            viewModel.onCountrySelected(null)
                        },
                    headlineContent = {
                        Text(stringResource(id = R.string.no_default_code))
                    },
                    supportingContent = {
                        Text(stringResource(id = R.string.no_default_code_support))
                    },
                    trailingContent = {
                        SelectedIcon(isSelected = uiState.selectedCountry == null)
                    }
                )
            }

            items(uiState.countries) { country ->
                ListItem(
                    country = country,
                    isSelected = country == uiState.selectedCountry,
                    onClick = {
                        viewModel.onCountrySelected(country)
                    }
                )
            }
        }
    }
}

@Composable
private fun ListItem(
    country: Country,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    ListItem(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(16.dp))
            .clickable(
                onClick = onClick
            ),
        headlineContent = {
            Text(country.name)
        },
        supportingContent = {
            Text(country.code)
        },
        trailingContent = {
            SelectedIcon(isSelected = isSelected)
        }
    )
}

@Composable
private fun SelectedIcon(isSelected: Boolean) {
    if (isSelected) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = stringResource(id = R.string.selected_item),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DefaultCodePage(DefaultCodeViewModel())
}
