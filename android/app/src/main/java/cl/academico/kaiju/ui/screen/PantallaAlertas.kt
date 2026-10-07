package cl.academico.kaiju.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import cl.academico.kaiju.model.Producto
import cl.academico.kaiju.ui.components.TarjetaProducto

@Composable
fun PantallaAlertas(productos: List<Producto>, abrir: (String) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item { Text("Alertas", style = MaterialTheme.typography.headlineMedium) }
        item { Text("Cantidad disponible igual o menor al stock mínimo (RN-06).") }
        if (productos.isEmpty()) item { Text("No hay productos en alerta.") }
        items(productos, key = { it.codigo }) { producto ->
            TarjetaProducto(producto, enAlerta = true) { abrir(producto.codigo) }
        }
    }
}
