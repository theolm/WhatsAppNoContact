package dev.theolm.wwc.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

private val DefaultCornerRadius = 16.dp

fun Modifier.roundedItem(onClick: () -> Unit) = clip(RoundedCornerShape(DefaultCornerRadius))
    .clickable(
        onClick = onClick
    )
