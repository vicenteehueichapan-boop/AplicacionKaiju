package cl.academico.kaiju.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

// Identidad visual (A-10 §6): verde petróleo con alto contraste para leer en bodega;
// el rojo se reserva para stock bajo y errores.
private val colores = lightColorScheme(
    primary = Color(0xFF006B60), onPrimary = Color.White,
    primaryContainer = Color(0xFFA0F2DE), onPrimaryContainer = Color(0xFF00201B),
    secondaryContainer = Color(0xFFDCE5E0),
    background = Color(0xFFF5F8F6), surface = Color(0xFFF5F8F6),
    error = Color(0xFFB3261E),
)

// Tipografía del sistema con títulos y cantidades más marcados.
private val base = Typography()
private val tipografia = Typography(
    headlineMedium = base.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
    titleLarge = base.titleLarge.copy(fontWeight = FontWeight.SemiBold),
    titleMedium = base.titleMedium.copy(fontWeight = FontWeight.SemiBold),
    titleSmall = base.titleSmall.copy(fontWeight = FontWeight.SemiBold),
)

@Composable
fun KaijuTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = colores, typography = tipografia, content = content)
}
