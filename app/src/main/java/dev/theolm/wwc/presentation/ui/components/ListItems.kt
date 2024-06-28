package dev.theolm.wwc.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.theolm.wwc.R

@Suppress("ModifierHeightWithText")
@Composable
fun SelectableItemList(
    headlineText: String,
    supportText: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    ListItem(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .roundedItem(onClick = onClick),
        headlineContent = {
            Text(headlineText)
        },
        supportingContent = {
            Text(supportText)
        },
        trailingContent = {
            SelectableItemListIcon(isSelected = isSelected)
        }
    )
}

@Composable
private fun SelectableItemListIcon(isSelected: Boolean) {
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
private fun SelectableItemListPreview() {
    SelectableItemList(
        headlineText = "Headline",
        supportText = "Supporting",
        isSelected = false,
        onClick = {}
    )
}

@Preview
@Composable
private fun SelectableItemListSelectedPreview() {
    SelectableItemList(
        headlineText = "Headline",
        supportText = "Supporting",
        isSelected = true,
        onClick = {}
    )
}
