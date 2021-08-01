package com.example.commonui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = DarkerPurple,
    onPrimary = Black,
    primaryVariant = LighterPurple,
    secondary = Teal,
    onSecondary = Black,
    error = Red300,
    onError = Black
)

private val LightColorPalette = lightColors(
    primary = Purple,
    onPrimary = Black,
    primaryVariant = LighterPurple,
    secondary = Teal,
    onSecondary = Black,
    error = Red300,
    onError = Black
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
        shapes = ArthingyShapes,
        content = content
    )
}
