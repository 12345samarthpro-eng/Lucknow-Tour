package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val LightColorScheme = lightColorScheme(
    primary = TerracottaPrimary,
    onPrimary = OnTerracotta,
    primaryContainer = TerracottaContainer,
    onPrimaryContainer = OnTerracottaContainer,
    secondary = GomtiTealSecondary,
    onSecondary = OnGomtiTeal,
    secondaryContainer = GomtiTealContainer,
    onSecondaryContainer = OnGomtiTealContainer,
    tertiary = SandstoneGoldTertiary,
    onTertiary = OnSandstoneGold,
    tertiaryContainer = SandstoneGoldContainer,
    onTertiaryContainer = OnSandstoneGoldContainer,
    background = ParchmentBackground,
    onBackground = OnParchment,
    surface = IvorySurface,
    onSurface = OnParchment,
    surfaceVariant = WarmSandSurfaceVariant,
    onSurfaceVariant = OnWarmSandVariant,
    outline = WarmOutline,
    outlineVariant = WarmOutlineVariant
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkTerracottaPrimary,
    onPrimary = DarkOnTerracotta,
    primaryContainer = DarkTerracottaContainer,
    onPrimaryContainer = DarkOnTerracottaContainer,
    secondary = DarkGomtiTealSecondary,
    onSecondary = DarkOnGomtiTeal,
    secondaryContainer = DarkGomtiTealContainer,
    onSecondaryContainer = DarkOnGomtiTealContainer,
    tertiary = DarkGoldTertiary,
    onTertiary = DarkOnGold,
    tertiaryContainer = DarkGoldContainer,
    onTertiaryContainer = DarkOnGoldContainer,
    background = DarkCharcoalBackground,
    onBackground = DarkOnCharcoal,
    surface = DarkEspressoSurface,
    onSurface = DarkOnCharcoal,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkOnSurfaceVariant,
    outline = DarkOutline,
    outlineVariant = DarkOutlineVariant
)

val LucknowShapes = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

@Composable
fun LucknowGuideTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = LucknowTypography,
        shapes = LucknowShapes,
        content = content
    )
}
