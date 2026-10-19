package cl.academico.kaiju.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cl.academico.kaiju.model.Movimiento
import cl.academico.kaiju.model.Producto
import cl.academico.kaiju.model.TipoMovimiento
import cl.academico.kaiju.ui.components.AvatarCategoria
import cl.academico.kaiju.ui.components.EstadoVacio
import cl.academico.kaiju.ui.components.IndicadorClave
import cl.academico.kaiju.ui.components.IndicadorStock
import cl.academico.kaiju.ui.components.etiqueta
import cl.academico.kaiju.ui.components.fechaHora
import cl.academico.kaiju.ui.components.precioClp
import cl.academico.kaiju.ui.theme.ColoresKaiju

/** RF-04: datos del producto e historial de movimientos, del más reciente al más antiguo. */
@Composable
fun PantallaDetalleProducto(
    producto: Producto?,
    historial: List<Movimiento>,
    enAlerta: Boolean,
    puedeEditar: Boolean,
    registrar: () -> Unit,
    editar: () -> Unit,
    volver: () -> Unit,
) {
    // El disponible cambia de color de forma gradual al entrar o salir de alerta (IndicadorClave lo anima).
    val colorStock = if (enAlerta) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.tertiary
    LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            TextButton(onClick = volver) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                Spacer(Modifier.width(4.dp))
                Text("Volver")
            }
        }
        if (producto == null) {
            item { EstadoVacio(Icons.Default.Refresh, "Producto no encontrado.") }
            return@LazyColumn
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AvatarCategoria(producto.categoria)
                Spacer(Modifier.width(14.dp))
                Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(producto.nombre, style = MaterialTheme.typography.headlineSmall)
                    Text("${producto.codigo} · ${producto.categoria}", style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                    IndicadorStock(enAlerta)
                }
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                IndicadorClave("Disponible", "${producto.stockActual}", Icons.AutoMirrored.Filled.List, Modifier.weight(1f), colorStock)
                IndicadorClave("Mínimo", "${producto.stockMinimo}", Icons.Default.Notifications, Modifier.weight(1f))
                IndicadorClave("Precio", precioClp(producto.precio).removeSuffix(" CLP"), Icons.Default.ShoppingCart,
                    Modifier.weight(1f))
            }
        }
        val datos = listOfNotNull(
            producto.tipo.takeIf { it.isNotBlank() }?.let { "Tipo: $it" },
            producto.descripcion.takeIf { it.isNotBlank() },
            producto.detalle?.let { "Detalle: $it" },
        )
        if (datos.isNotEmpty()) item {
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)) {
                Column(Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    datos.forEach { Text(it, style = MaterialTheme.typography.bodyMedium) }
                }
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(onClick = registrar, modifier = Modifier.weight(1f), shape = MaterialTheme.shapes.medium) {
                    Icon(Icons.Default.Refresh, contentDescription = null)
                    Spacer(Modifier.width(6.dp))
                    Text("Registrar movimiento")
                }
                if (puedeEditar) OutlinedButton(onClick = editar, shape = MaterialTheme.shapes.medium) {
                    Icon(Icons.Default.Edit, contentDescription = null)
                    Spacer(Modifier.width(6.dp))
                    Text("Editar")
                }
            }
        }
        item {
            Text("Historial de movimientos", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 8.dp))
        }
        if (historial.isEmpty()) item { EstadoVacio(Icons.Default.Refresh, "Este producto todavía no tiene movimientos.") }
        items(historial, key = { it.id }) { movimiento -> FilaMovimiento(movimiento) }
    }
}

/** Un movimiento en la línea de tiempo: color e ícono según suma o resta stock. */
@Composable
private fun FilaMovimiento(movimiento: Movimiento) {
    // Salidas restan; entradas suman; un ajuste guarda su diferencia con signo.
    val cambio = if (movimiento.tipo.esSalida) -movimiento.cantidad else movimiento.cantidad
    val color = when {
        movimiento.tipo == TipoMovimiento.AJUSTE -> MaterialTheme.colorScheme.primary
        cambio >= 0 -> ColoresKaiju.exito
        else -> ColoresKaiju.salida
    }
    val icono = when {
        movimiento.tipo == TipoMovimiento.AJUSTE -> Icons.Default.Create
        cambio >= 0 -> Icons.Default.KeyboardArrowUp
        else -> Icons.Default.KeyboardArrowDown
    }
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)) {
        Row(Modifier.fillMaxWidth().padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(36.dp).clip(CircleShape).background(color.copy(alpha = 0.18f)), contentAlignment = Alignment.Center) {
                Icon(icono, contentDescription = null, tint = color)
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(movimiento.tipo.etiqueta(), style = MaterialTheme.typography.titleSmall)
                Text("${fechaHora(movimiento.fecha)} · ${movimiento.usuario.nombre}", style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
                movimiento.motivo?.let { Text("Motivo: $it", style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant) }
            }
            Text((if (cambio >= 0) "+" else "−") + kotlin.math.abs(cambio), style = MaterialTheme.typography.titleLarge, color = color)
        }
    }
}
