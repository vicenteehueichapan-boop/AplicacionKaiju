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

// Identidad visual (Guía 10 sección 6): negro y morado, tema oscuro de Material 3.
// Alto contraste para leer en bodega; el rojo se reserva para stock bajo y errores.
private val colores = darkColorScheme(
    primary = Color(0xFFBB86FC), onPrimary = Color(0xFF21005D),
    primaryContainer = Color(0xFF4A2A8A), onPrimaryContainer = Color(0xFFEADDFF),
    secondary = Color(0xFFCCC2DC), onSecondary = Color(0xFF332D41),
    secondaryContainer = Color(0xFF2E2440), onSecondaryContainer = Color(0xFFE8DEF8),
    tertiary = Color(0xFF7FDBA8), onTertiary = Color(0xFF00391F),
    background = Color(0xFF0E0B14), onBackground = Color(0xFFE9E1F0),
    surface = Color(0xFF0E0B14), onSurface = Color(0xFFE9E1F0),
    surfaceVariant = Color(0xFF231C30), onSurfaceVariant = Color(0xFFCBC2D8),
    surfaceContainerLowest = Color(0xFF09070D), surfaceContainerLow = Color(0xFF15111D),
    surfaceContainer = Color(0xFF1A1524), surfaceContainerHigh = Color(0xFF221C2E),
    surfaceContainerHighest = Color(0xFF2B2438),
    outline = Color(0xFF8E8499), outlineVariant = Color(0xFF3D3449),
    error = Color(0xFFFF8A8A), onError = Color(0xFF5C0000),
    errorContainer = Color(0xFF5A1E24), onErrorContainer = Color(0xFFFFDAD6),
)

/** Colores con significado propio en la app, además de los del esquema de Material 3. */
object ColoresKaiju {
    /** Entradas y estado correcto. */
    val exito = Color(0xFF7FDBA8)
    /** Salidas de stock. */
    val salida = Color(0xFFFFB86B)
    /** Degradado de la marca, usado en el encabezado del ingreso. */
    val degradadoMarca = Brush.verticalGradient(listOf(Color(0xFF4A2A8A), Color(0xFF0E0B14)))
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
