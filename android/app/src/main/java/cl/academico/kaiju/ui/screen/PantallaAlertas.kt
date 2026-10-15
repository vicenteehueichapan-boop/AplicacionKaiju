package cl.academico.kaiju.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.academico.kaiju.model.Producto
import cl.academico.kaiju.ui.components.EncabezadoPantalla
import cl.academico.kaiju.ui.components.EstadoVacio
import cl.academico.kaiju.ui.components.TarjetaProducto
import cl.academico.kaiju.ui.recursos.TarjetaAvisosStock

/** RF-09: productos en alerta; al tocar uno se abre su detalle. */
@Composable
fun PantallaAlertas(productos: List<Producto>, abrir: (String) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item { EncabezadoPantalla("Alertas", "Productos con disponible igual o menor a su stock mínimo.") }
        if (productos.isNotEmpty()) item {
            // Resumen destacado: cuántos productos necesitan reposición.
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)) {
                Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Warning, contentDescription = null, tint = MaterialTheme.colorScheme.onErrorContainer)
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text("${productos.size} ${if (productos.size == 1) "producto necesita" else "productos necesitan"} reposición",
                            style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onErrorContainer)
                        Text("Toca uno para ver su detalle y registrar una entrada.", style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onErrorContainer)
                    }
                }
            }
        }
        item { TarjetaAvisosStock() }
        if (productos.isEmpty()) item { EstadoVacio(Icons.Default.CheckCircle, "Todo en orden: ningún producto está bajo su mínimo.") }
        items(productos, key = { it.codigo }) { producto ->
            TarjetaProducto(producto, enAlerta = true) { abrir(producto.codigo) }
        }
    }
}
