package dev.theolm.wwc.ui.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun DefaultListItem(
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
