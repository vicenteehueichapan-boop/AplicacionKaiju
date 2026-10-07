package cl.academico.kaiju.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.academico.kaiju.model.Producto
import java.text.NumberFormat
import java.util.Locale

@Composable
fun TarjetaProducto(producto: Producto, enAlerta: Boolean, abrir: () -> Unit) {
    Card(onClick = abrir, modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
            Text("${producto.codigo} · ${producto.categoria}", style = MaterialTheme.typography.bodySmall)
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("$ ${NumberFormat.getIntegerInstance(Locale.forLanguageTag("es-CL")).format(producto.precio)} CLP")
                Text("Disponible: ${producto.stockActual}")
            }
            IndicadorStock(enAlerta)
        }
    }
}
