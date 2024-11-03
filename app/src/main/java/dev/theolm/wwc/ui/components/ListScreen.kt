package dev.theolm.wwc.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val DefaultPadding = 16.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    title: String,
    onBackPress: () -> Unit,
    emptyContent: @Composable () -> Unit = {},
    isEmpty: Boolean = false,
    content: LazyListScope.() -> Unit
) {
    val scrollBarBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBarBehavior.nestedScrollConnection),
        topBar = {
            DefaultTopAppBar(
                title = title,
                scrollBarBehavior = scrollBarBehavior,
                onBackPress = onBackPress
            )
        }
    ) {
        if(isEmpty) {
            emptyContent()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding()),
                contentPadding = PaddingValues(
                    top = DefaultPadding,
                    bottom = 64.dp,
                    start = DefaultPadding,
                    end = DefaultPadding
                )
            ) {
                content()
            }
        }
    }
}

@Suppress("MagicNumber")
@Preview
@Composable
private fun ListScreenPreview() {
    ListScreen(
        title = "List Screen",
        onBackPress = {},
        content = {
            items(10) {
                SelectableItemList(
                    headlineText = "Headline $it",
                    supportText = "Supporting $it",
                    isSelected = it == 1,
                    onClick = {}
                )
            }
        }
    )
}
