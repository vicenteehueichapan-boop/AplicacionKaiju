package cl.academico.kaiju.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

// Piezas visuales compartidas por las pantallas: solo presentación, sin reglas de negocio.

/** Título y bajada de cada pantalla, para una jerarquía visual uniforme. */
@Composable
fun EncabezadoPantalla(titulo: String, bajada: String) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(titulo, style = MaterialTheme.typography.headlineMedium)
        Text(bajada, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

/** Número destacado con su etiqueta, por ejemplo "Disponible 12". Si cambia de color (normal ↔ alerta), cambia de forma gradual. */
@Composable
fun IndicadorClave(
    etiqueta: String,
    valor: String,
    icono: ImageVector,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    val colorAnimado by animateColorAsState(color, label = "colorIndicador")
    Card(modifier, colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)) {
        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Icon(icono, contentDescription = null, tint = colorAnimado, modifier = Modifier.size(20.dp))
            Text(valor, style = MaterialTheme.typography.headlineSmall, color = colorAnimado, fontWeight = FontWeight.Bold)
            Text(etiqueta, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

/** Círculo con la inicial de la categoría: ayuda a reconocer el tipo de producto de un vistazo. */
@Composable
fun AvatarCategoria(categoria: String, modifier: Modifier = Modifier) {
    Box(
        modifier.size(44.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center,
    ) {
        Text(categoria.take(1).uppercase(), style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer)
    }
}

/** Mensaje claro cuando una lista no tiene elementos. */
@Composable
fun EstadoVacio(icono: ImageVector, texto: String, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxWidth().padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Icon(icono, contentDescription = null, tint = MaterialTheme.colorScheme.outline, modifier = Modifier.size(40.dp))
        Text(texto, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center)
    }
}
