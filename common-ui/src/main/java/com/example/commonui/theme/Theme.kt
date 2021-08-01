package com.example.commonui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

/**
 * For later
 */
private val DarkColorPalette = darkColors(
    primary = DarkerPurple,
    primaryVariant = LighterPurple,
    secondary = Teal
)

private val LightColorPalette = lightColors(
    primary = Purple,
    primaryVariant = LighterPurple,
    secondary = Teal,
    onPrimary = PrimaryTextColor
)

@Composable
fun ArthingyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = ArthingyTypography,
        shapes = Shapes,
        content = content
    )
}
