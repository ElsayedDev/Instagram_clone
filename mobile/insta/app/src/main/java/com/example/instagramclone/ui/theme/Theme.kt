
package com.example.instagramclone.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    primaryVariant = Color.Black,
    onPrimary = Color.Black,
    secondaryVariant =Color.Blue ,
    secondary = Color.Blue,
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun InstagramCloneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    systemUiController : SystemUiController = rememberSystemUiController(),
    content: @Composable () -> Unit
) {

    systemUiController.setSystemBarsColor(
        color = Color.White
    )

    val colors = if (darkTheme) {
        LightColorPalette
    } else {
        LightColorPalette

    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

