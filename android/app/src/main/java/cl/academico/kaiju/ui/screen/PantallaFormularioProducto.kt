package cl.academico.kaiju.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.academico.kaiju.model.FormularioProductoEstado
import cl.academico.kaiju.ui.components.CampoFormulario
import cl.academico.kaiju.ui.components.EncabezadoPantalla
import cl.academico.kaiju.ui.components.MensajeError
import cl.academico.kaiju.ui.components.SelectorFormulario

/** Eventos del formulario de producto; cada uno llama a una función del ViewModel. */
class EventosFormularioProducto(
    val codigo: (String) -> Unit,
    val nombre: (String) -> Unit,
    val categoria: (String) -> Unit,
    val precio: (String) -> Unit,
    val stockMinimo: (String) -> Unit,
    val descripcion: (String) -> Unit,
    val tipo: (String) -> Unit,
    val detalle: (String) -> Unit,
    val guardar: () -> Unit,
    val volver: () -> Unit,
)

/** RF-05: crear o editar un producto. Solo muestra estado y errores; valida el ViewModel. */
@Composable
fun PantallaFormularioProducto(estado: FormularioProductoEstado, categorias: List<String>, eventos: EventosFormularioProducto) {
    val errores = estado.errores
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)) {
        TextButton(onClick = eventos.volver) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            Spacer(Modifier.width(4.dp))
            Text("Volver")
        }
        EncabezadoPantalla(if (estado.esEdicion) "Editar producto" else "Nuevo producto",
            "Campos obligatorios: código, nombre, categoría, precio y stock mínimo.")
        if (!estado.esEdicion) Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
            modifier = Modifier.padding(vertical = 6.dp)) {
            Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Info, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Spacer(Modifier.width(10.dp))
                Text("El producto se crea con 0 unidades. Después registra su entrada inicial.",
                    style = MaterialTheme.typography.bodyMedium)
            }
        }
        Seccion("Identificación")
        CampoFormulario(estado.codigo, "Código", if (estado.esEdicion) Icons.Default.Lock else Icons.Default.Info,
            errores.codigo, eventos.codigo, habilitado = !estado.esEdicion,
            ayuda = if (estado.esEdicion) "El código identifica el historial del producto y no se edita." else null)
        CampoFormulario(estado.nombre, "Nombre", Icons.Default.Edit, errores.nombre, eventos.nombre)
        SelectorFormulario("Elegir categoría", Icons.AutoMirrored.Filled.List, estado.categoria.ifBlank { null },
            categorias, { it }, errores.categoria, eventos.categoria)
        Seccion("Precio y stock")
        CampoFormulario(estado.precio, "Precio en pesos chilenos", Icons.Default.ShoppingCart, errores.precio,
            eventos.precio, numerico = true)
        CampoFormulario(estado.stockMinimo, "Stock mínimo", Icons.Default.Notifications, errores.stockMinimo,
            eventos.stockMinimo, numerico = true, ayuda = "Bajo o igual a este número, el producto queda en alerta.")
        Seccion("Información adicional")
        CampoFormulario(estado.descripcion, "Descripción (opcional)", Icons.Default.Create, null, eventos.descripcion)
        CampoFormulario(estado.tipo, "Tipo de producto (opcional)", Icons.Default.Build, null, eventos.tipo)
        CampoFormulario(estado.detalle, "Detalle (opcional)", Icons.Default.Add, null, eventos.detalle,
            ayuda = "Por ejemplo talla y color, lote o fecha de vencimiento.")
        estado.mensaje?.let { MensajeError(it, Modifier.padding(vertical = 8.dp)) }
        Button(onClick = eventos.guardar, modifier = Modifier.fillMaxWidth().height(52.dp), shape = MaterialTheme.shapes.medium) {
            Text(if (estado.esEdicion) "Guardar cambios" else "Crear producto", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
private fun Seccion(titulo: String) {
    Text(titulo, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(top = 10.dp, bottom = 2.dp))
}
