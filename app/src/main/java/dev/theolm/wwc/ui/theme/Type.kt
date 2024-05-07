package dev.theolm.wwc.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography =
    Typography().run {
        copy(
            bodyLarge = bodyLarge.applyLinebreak(),
            bodyMedium = bodyMedium.applyLinebreak(),
            bodySmall = bodySmall.applyLinebreak()
        )
    }

@OptIn(ExperimentalTextApi::class)
private fun TextStyle.applyLinebreak(): TextStyle = this.copy(lineBreak = LineBreak.Paragraph)

@OptIn(ExperimentalTextApi::class)
val preferenceTitle = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 20.sp,
    lineHeight = 24.sp,
    lineBreak = LineBreak.Paragraph
)

// Set of Material typography styles to start with
/* Other default text styles to override
titleLarge = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
),
labelSmall = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)
*/
