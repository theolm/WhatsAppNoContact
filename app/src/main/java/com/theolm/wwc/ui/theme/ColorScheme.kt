package com.theolm.wwc.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import material.io.color.scheme.Scheme

object ColorScheme {
    const val DEFAULT_SEED_COLOR = 0xFF415f76.toInt()

    fun colorSchemeFromColor(
        color: Int = DEFAULT_SEED_COLOR,
        isDarkTheme: Boolean = false
    ): ColorScheme {
        return if (isDarkTheme) darkColorSchemeFromColor(color) else lightColorSchemeFromColor(color)
    }

    private fun lightColorSchemeFromColor(color: Int = DEFAULT_SEED_COLOR): ColorScheme {
        val lightScheme = Scheme.light(color)!!
        with(lightScheme) {
            return lightColorScheme(
                primary = Color(primary),
                onPrimary = Color(onPrimary),
                primaryContainer = Color(primaryContainer),
                onPrimaryContainer = Color(onPrimaryContainer),
                secondary = Color(secondary),
                onSecondary = Color(onSecondary),
                secondaryContainer = Color(secondaryContainer),
                onSecondaryContainer = Color(onSecondaryContainer),
                tertiary = Color(tertiary),
                onTertiary = Color(onTertiary),
                tertiaryContainer = Color(tertiaryContainer),
                onTertiaryContainer = Color(onTertiaryContainer),
                error = Color(error),
                errorContainer = Color(errorContainer),
                onError = Color(onError),
                onErrorContainer = Color(onErrorContainer),
                background = Color(background),
                onBackground = Color(onBackground),
                surface = Color(surface),
                onSurface = Color(onSurface),
                surfaceVariant = Color(surfaceVariant),
                onSurfaceVariant = Color(onSurfaceVariant),
                outline = Color(outline),
                outlineVariant = Color(outlineVariant),
                inverseOnSurface = Color(inverseOnSurface),
                inverseSurface = Color(inverseSurface),
                inversePrimary = Color(inversePrimary),
            )
        }
    }

    private fun darkColorSchemeFromColor(color: Int = DEFAULT_SEED_COLOR): ColorScheme {
        val darkScheme = Scheme.dark(color)!!
        with(darkScheme) {
            return darkColorScheme(
                primary = Color(primary),
                onPrimary = Color(onPrimary),
                primaryContainer = Color(primaryContainer),
                onPrimaryContainer = Color(onPrimaryContainer),
                secondary = Color(secondary),
                onSecondary = Color(onSecondary),
                secondaryContainer = Color(secondaryContainer),
                onSecondaryContainer = Color(onSecondaryContainer),
                tertiary = Color(tertiary),
                onTertiary = Color(onTertiary),
                tertiaryContainer = Color(tertiaryContainer),
                onTertiaryContainer = Color(onTertiaryContainer),
                error = Color(error),
                errorContainer = Color(errorContainer),
                onError = Color(onError),
                onErrorContainer = Color(onErrorContainer),
                background = Color(background),
                onBackground = Color(onBackground),
                surface = Color(surface),
                onSurface = Color(onSurface),
                surfaceVariant = Color(surfaceVariant),
                onSurfaceVariant = Color(onSurfaceVariant),
                outline = Color(outline),
                outlineVariant = Color(outlineVariant),
                inverseOnSurface = Color(inverseOnSurface),
                inverseSurface = Color(inverseSurface),
                inversePrimary = Color(inversePrimary),
            )
        }
    }
}
