package cl.academico.kaiju.ui.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.academico.kaiju.model.Movimiento
import cl.academico.kaiju.model.Producto
import cl.academico.kaiju.ui.components.IndicadorStock
import cl.academico.kaiju.ui.components.etiqueta
import cl.academico.kaiju.ui.components.fechaHora
import cl.academico.kaiju.ui.components.precioClp

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
    // Animación con propósito: el número cambia de color de forma gradual al entrar o salir de alerta.
    val colorStock by animateColorAsState(
        if (enAlerta) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface, label = "colorStock")
    LazyColumn(contentPadding = PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item {
            TextButton(onClick = volver) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                Spacer(Modifier.width(4.dp))
                Text("Volver")
            }
        }
        if (producto == null) {
            item { Text("Producto no encontrado.") }
            return@LazyColumn
        }
        item {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(producto.nombre, style = MaterialTheme.typography.headlineMedium)
                Text("Código: ${producto.codigo}")
                Text("Categoría: ${producto.categoria}")
                if (producto.tipo.isNotBlank()) Text("Tipo: ${producto.tipo}")
                if (producto.descripcion.isNotBlank()) Text(producto.descripcion)
                producto.detalle?.let { Text("Detalle: $it") }
                Text("Precio: ${precioClp(producto.precio)}")
                Text("Disponible: ${producto.stockActual}", color = colorStock, style = MaterialTheme.typography.titleLarge)
                Text("Stock mínimo: ${producto.stockMinimo}")
                IndicadorStock(enAlerta)
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = registrar) {
                    Icon(Icons.Default.Refresh, contentDescription = null)
                    Spacer(Modifier.width(6.dp))
                    Text("Registrar movimiento")
                }
                if (puedeEditar) OutlinedButton(onClick = editar) {
                    Icon(Icons.Default.Edit, contentDescription = null)
                    Spacer(Modifier.width(6.dp))
                    Text("Editar")
                }
            }
        }
        item {
            HorizontalDivider()
            Text("Historial de movimientos", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 8.dp))
        }
        if (historial.isEmpty()) item { Text("Sin movimientos registrados.") }
        items(historial, key = { it.id }) { movimiento -> FilaMovimiento(movimiento) }
    }
}

@Composable
private fun FilaMovimiento(movimiento: Movimiento) {
    // Salidas restan; entradas suman; un ajuste guarda su diferencia con signo.
    val cambio = when {
        movimiento.tipo.esSalida -> -movimiento.cantidad
        else -> movimiento.cantidad
    }
    val texto = (if (cambio >= 0) "+" else "−") + kotlin.math.abs(cambio)
    ElevatedCard(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text("${movimiento.tipo.etiqueta()} · $texto", style = MaterialTheme.typography.titleSmall)
            Text("${fechaHora(movimiento.fecha)} · ${movimiento.usuario.nombre}", style = MaterialTheme.typography.bodySmall)
            movimiento.motivo?.let { Text("Motivo: $it", style = MaterialTheme.typography.bodySmall) }
        }
    }
}
