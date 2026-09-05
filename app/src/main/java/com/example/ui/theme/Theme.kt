package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = LoveDarkPrimary,
    onPrimary = LoveDarkOnPrimary,
    secondary = LoveDarkSecondary,
    tertiary = LoveDarkTertiary,
    background = LoveDarkBackground,
    surface = LoveDarkSurface,
    surfaceVariant = LoveDarkSurfaceVariant,
    onSurface = LoveDarkOnSurface,
    outline = LoveDarkOutline
  )

private val LightColorScheme =
  lightColorScheme(
    primary = LoveLightPrimary,
    onPrimary = LoveLightOnPrimary,
    secondary = LoveLightSecondary,
    tertiary = LoveLightTertiary,
    background = LoveLightBackground,
    surface = LoveLightSurface,
    surfaceVariant = LoveLightSurfaceVariant,
    onSurface = LoveLightOnSurface,
    outline = LoveLightOutline
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }

      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
