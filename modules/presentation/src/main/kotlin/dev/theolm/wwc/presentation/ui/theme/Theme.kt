package dev.theolm.wwc.presentation.ui.theme

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.Window
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.LineBreak
import androidx.core.view.WindowCompat
import com.google.android.material.color.DynamicColors
import com.google.android.material.color.MaterialColors
import dev.theolm.wwc.presentation.ui.theme.ColorScheme.DEFAULT_SEED_COLOR
import dev.theolm.wwc.presentation.ui.theme.ColorScheme.colorSchemeFromColor

fun Color.applyOpacity(enabled: Boolean): Color {
    return if (enabled) this else this.copy(alpha = 0.62f)
}

@Composable
fun Color.harmonizeWithPrimary(): Color {
    return Color(
        MaterialColors.harmonize(
            this.toArgb(),
            MaterialTheme.colorScheme.primary.toArgb()
        )
    )
}

private tailrec fun Context.findWindow(): Window? =
    when (this) {
        is Activity -> window
        is ContextWrapper -> baseContext.findWindow()
        else -> null
    }

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    isHighContrastModeEnabled: Boolean = false,
    seedColor: Int = DEFAULT_SEED_COLOR,
    isDynamicColorEnabled: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        DynamicColors.isDynamicColorAvailable() && isDynamicColorEnabled -> {
            val context = LocalContext.current
            if (darkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        }

        else -> colorSchemeFromColor(seedColor, darkTheme)
    }.run {
        if (isHighContrastModeEnabled && darkTheme) {
            copy(
                surface = Color.Black,
                background = Color.Black,
            )
        } else {
            this
        }
    }
    val window = LocalView.current.context.findWindow()
    val view = LocalView.current

    window?.let {
        WindowCompat.getInsetsController(it, view).isAppearanceLightStatusBars = darkTheme
    }

    ProvideTextStyle(value = LocalTextStyle.current.copy(lineBreak = LineBreak.Paragraph)) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

@Composable
fun PreviewThemeLight(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = colorSchemeFromColor(DEFAULT_SEED_COLOR, false),
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun PreviewThemeDark(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = colorSchemeFromColor(DEFAULT_SEED_COLOR, true),
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
