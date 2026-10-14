package cl.academico.kaiju.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.academico.kaiju.model.Producto

/** Producto en el catálogo y en alertas: los cuatro datos de RF-02 y la marca de RN-06. */
@Composable
fun TarjetaProducto(producto: Producto, enAlerta: Boolean, abrir: () -> Unit) {
    Card(onClick = abrir, modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
            Text("${producto.codigo} · ${producto.categoria}", style = MaterialTheme.typography.bodySmall)
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(precioClp(producto.precio))
                Text("Disponible: ${producto.stockActual}", style = MaterialTheme.typography.titleSmall)
            }
            IndicadorStock(enAlerta)
        }
    }
}
