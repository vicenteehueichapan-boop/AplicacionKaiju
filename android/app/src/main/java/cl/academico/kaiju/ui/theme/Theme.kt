package cl.academico.kaiju.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Identidad visual (Guía 10 sección 6): negro con acentos y bordes blancos, tema oscuro de Material 3.
// Alto contraste para leer en bodega; el rojo se reserva para stock bajo y errores.
private val colores = darkColorScheme(
    primary = Color(0xFFFFFFFF), onPrimary = Color(0xFF000000),
    primaryContainer = Color(0xFF2C2C2C), onPrimaryContainer = Color(0xFFFFFFFF),
    secondary = Color(0xFFBDBDBD), onSecondary = Color(0xFF000000),
    secondaryContainer = Color(0xFF262626), onSecondaryContainer = Color(0xFFFFFFFF),
    tertiary = Color(0xFF7FDBA8), onTertiary = Color(0xFF00391F),
    background = Color(0xFF000000), onBackground = Color(0xFFF2F2F2),
    surface = Color(0xFF000000), onSurface = Color(0xFFF2F2F2),
    surfaceVariant = Color(0xFF1C1C1C), onSurfaceVariant = Color(0xFFB8B8B8),
    surfaceContainerLowest = Color(0xFF000000), surfaceContainerLow = Color(0xFF0A0A0A),
    surfaceContainer = Color(0xFF111111), surfaceContainerHigh = Color(0xFF181818),
    surfaceContainerHighest = Color(0xFF222222),
    outline = Color(0xFFE6E6E6), outlineVariant = Color(0xFF3A3A3A),
    error = Color(0xFFFF8A8A), onError = Color(0xFF5C0000),
    errorContainer = Color(0xFF5A1E24), onErrorContainer = Color(0xFFFFDAD6),
)

/** Colores con significado propio en la app, además de los del esquema de Material 3. */
object ColoresKaiju {
    /** Entradas y estado correcto. */
    val exito = Color(0xFF7FDBA8)
    /** Salidas de stock. */
    val salida = Color(0xFFFFB86B)
    /** Borde blanco suave de las tarjetas: separa cada bloque sobre el fondo negro. */
    val borde = Color(0x66FFFFFF)
    /** Degradado de la marca, usado en el encabezado del ingreso. */
    val degradadoMarca = Brush.verticalGradient(listOf(Color(0xFF2C2C2C), Color(0xFF000000)))
}

private val base = Typography()
private val tipografia = Typography(
    displaySmall = base.displaySmall.copy(fontWeight = FontWeight.Black, letterSpacing = 6.sp),
    headlineMedium = base.headlineMedium.copy(fontWeight = FontWeight.Bold),
    headlineSmall = base.headlineSmall.copy(fontWeight = FontWeight.Bold),
    titleLarge = base.titleLarge.copy(fontWeight = FontWeight.SemiBold),
    titleMedium = base.titleMedium.copy(fontWeight = FontWeight.SemiBold),
    titleSmall = base.titleSmall.copy(fontWeight = FontWeight.SemiBold),
    labelLarge = base.labelLarge.copy(fontWeight = FontWeight.SemiBold),
)

// Bordes redondeados generosos para tarjetas, botones y campos.
private val formas = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
)

@Composable
fun KaijuTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = colores, typography = tipografia, shapes = formas, content = content)
}
