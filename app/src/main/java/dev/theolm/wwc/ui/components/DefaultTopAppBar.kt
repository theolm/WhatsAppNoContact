package dev.theolm.wwc.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.theolm.wwc.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBar(
    title: String,
    onBackPress: (() -> Unit)? = null,
    scrollBarBehavior: TopAppBarScrollBehavior? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    LargeTopAppBar(
        title = {
            Text(title)
        },
        scrollBehavior = scrollBarBehavior,
        colors = TopAppBarDefaults.largeTopAppBarColors().copy(
            scrolledContainerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        actions = actions,
        navigationIcon = {
            onBackPress?.let {
                IconButton(onClick = it) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button_description)
                    )
                }
            }
        }
    )
}
