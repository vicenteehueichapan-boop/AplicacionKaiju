package cl.academico.kaiju.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val colores = lightColorScheme(
    primary = Color(0xFF006B60), onPrimary = Color.White,
    primaryContainer = Color(0xFFA0F2DE), onPrimaryContainer = Color(0xFF00201B),
    background = Color(0xFFF5F8F6), surface = Color(0xFFF5F8F6),
    secondaryContainer = Color(0xFFDCE5E0),
)

@Composable
fun KaijuTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = colores, content = content)
}
