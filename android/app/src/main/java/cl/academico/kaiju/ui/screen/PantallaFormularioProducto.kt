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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.academico.kaiju.model.FormularioProductoEstado
import cl.academico.kaiju.ui.components.CampoFormulario
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
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)) {
        TextButton(onClick = eventos.volver) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            Spacer(Modifier.width(4.dp))
            Text("Volver")
        }
        Text(if (estado.esEdicion) "Editar producto" else "Crear producto", style = MaterialTheme.typography.headlineMedium)
        Text("Campos obligatorios: código, nombre, categoría, precio y stock mínimo.", style = MaterialTheme.typography.bodySmall)
        if (!estado.esEdicion) Text("El producto se crea con 0 unidades; sus existencias entran con una Entrada.",
            style = MaterialTheme.typography.bodySmall)
        Spacer(Modifier.height(8.dp))
        CampoFormulario(estado.codigo, "Código", if (estado.esEdicion) Icons.Default.Lock else Icons.Default.Info,
            errores.codigo, eventos.codigo, habilitado = !estado.esEdicion,
            ayuda = if (estado.esEdicion) "El código identifica el historial del producto y no se edita." else null)
        CampoFormulario(estado.nombre, "Nombre", Icons.Default.Edit, errores.nombre, eventos.nombre)
        SelectorFormulario("Elegir categoría", Icons.AutoMirrored.Filled.List, estado.categoria.ifBlank { null },
            categorias, { it }, errores.categoria, eventos.categoria)
        Spacer(Modifier.height(8.dp))
        CampoFormulario(estado.precio, "Precio en pesos chilenos", Icons.Default.ShoppingCart, errores.precio,
            eventos.precio, numerico = true)
        CampoFormulario(estado.stockMinimo, "Stock mínimo", Icons.Default.Notifications, errores.stockMinimo,
            eventos.stockMinimo, numerico = true, ayuda = "Bajo o igual a este número, el producto queda en alerta.")
        CampoFormulario(estado.descripcion, "Descripción (opcional)", Icons.Default.Create, null, eventos.descripcion)
        CampoFormulario(estado.tipo, "Tipo de producto (opcional)", Icons.Default.Build, null, eventos.tipo)
        CampoFormulario(estado.detalle, "Detalle (opcional)", Icons.Default.Add, null, eventos.detalle,
            ayuda = "Por ejemplo talla y color, lote o fecha de vencimiento.")
        estado.mensaje?.let { MensajeError(it, Modifier.padding(vertical = 8.dp)) }
        Button(onClick = eventos.guardar, modifier = Modifier.fillMaxWidth()) {
            Text(if (estado.esEdicion) "Guardar cambios" else "Crear producto")
        }
    }
}
