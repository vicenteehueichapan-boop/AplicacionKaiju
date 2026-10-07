package cl.academico.kaiju.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.academico.kaiju.model.Producto
import cl.academico.kaiju.model.Movimiento
import cl.academico.kaiju.ui.components.IndicadorStock

@Composable
fun PantallaDetalleProducto(producto: Producto?, movimientos: List<Movimiento>, enAlerta: Boolean,
    registrar: () -> Unit, volver: () -> Unit) {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)) {
        TextButton(onClick = volver) { Text("Volver") }
        if (producto == null) { Text("Producto no encontrado."); return@Column }
        Text(producto.nombre, style = MaterialTheme.typography.headlineMedium)
        Text("Código: ${producto.codigo}")
        Text("Categoría: ${producto.categoria}")
        Text(producto.descripcion)
        Text("Tipo: ${producto.tipo}")
        Text("Precio: $ ${producto.precio} CLP")
        Text("Disponible: ${producto.stockActual} · Mínimo: ${producto.stockMinimo}")
        IndicadorStock(enAlerta)
        producto.detalle?.let { Text(it) }
        OutlinedButton(onClick = registrar) { Text("Registrar movimiento") }
        HorizontalDivider()
        Text("Historial de movimientos", style = MaterialTheme.typography.titleLarge)
        if (movimientos.isEmpty()) Text("Sin movimientos registrados en esta base de prueba.")
        movimientos.forEach { Text("${it.tipo} · ${it.cantidad} · ${it.usuario.nombre}") }
    }
}
