package cl.academico.kaiju.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cl.academico.kaiju.model.Producto
import cl.academico.kaiju.ui.theme.ColoresKaiju

/** Producto en el catálogo y en alertas: los cuatro datos de RF-02 y la marca de RN-06. */
@Composable
fun TarjetaProducto(producto: Producto, enAlerta: Boolean, abrir: () -> Unit) {
    // El borde y el disponible se tiñen de rojo de forma gradual cuando el producto entra en alerta.
    val borde by animateColorAsState(
        if (enAlerta) MaterialTheme.colorScheme.error.copy(alpha = 0.6f) else ColoresKaiju.borde, label = "bordeAlerta")
    val colorStock by animateColorAsState(
        if (enAlerta) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface, label = "colorStock")
    Card(
        onClick = abrir,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        border = BorderStroke(1.dp, borde),
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            AvatarCategoria(producto.categoria)
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(producto.nombre, style = MaterialTheme.typography.titleMedium, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text("${producto.codigo} · ${producto.categoria}", style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(precioClp(producto.precio), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
                IndicadorStock(enAlerta)
            }
            Spacer(Modifier.width(12.dp))
            Column(horizontalAlignment = Alignment.End) {
                Text("${producto.stockActual}", style = MaterialTheme.typography.headlineSmall, color = colorStock)
                Text(if (producto.stockActual == 1) "disponible" else "disponibles", style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
