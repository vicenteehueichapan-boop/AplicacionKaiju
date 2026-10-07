package cl.academico.kaiju.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.academico.kaiju.model.Producto
import cl.academico.kaiju.ui.components.TarjetaProducto

@Composable
fun PantallaCatalogo(productos: List<Producto>, alerta: (Producto) -> Boolean,
    puedeCrear: Boolean, abrir: (String) -> Unit, crear: () -> Unit) {
    Box(Modifier.fillMaxSize()) {
        LazyColumn(contentPadding = PaddingValues(16.dp, 12.dp, 16.dp, 96.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item { Text("Catálogo", style = MaterialTheme.typography.headlineMedium) }
            item { Text("${productos.size} productos de prueba · datos en memoria",
                style = MaterialTheme.typography.bodySmall) }
            items(productos, key = { it.codigo }) { producto ->
                TarjetaProducto(producto, alerta(producto)) { abrir(producto.codigo) }
            }
        }
        if (puedeCrear) FloatingActionButton(onClick = crear,
            modifier = Modifier.align(androidx.compose.ui.Alignment.BottomEnd).padding(16.dp)) {
            Icon(Icons.Default.Add, contentDescription = "Crear producto")
        }
    }
}
