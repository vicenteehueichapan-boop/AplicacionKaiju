package cl.academico.kaiju.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import cl.academico.kaiju.model.FormularioMovimientoEstado
import cl.academico.kaiju.model.Producto
import cl.academico.kaiju.model.TipoMovimiento
import cl.academico.kaiju.ui.components.CampoFormulario
import cl.academico.kaiju.ui.components.MensajeError
import cl.academico.kaiju.ui.components.SelectorFormulario
import cl.academico.kaiju.ui.components.etiqueta
import cl.academico.kaiju.ui.recursos.BotonLectorCodigo

/** Eventos del formulario de movimiento; cada uno llama a una función del ViewModel. */
class EventosMovimiento(
    val producto: (String) -> Unit,
    val tipo: (TipoMovimiento) -> Unit,
    val cantidad: (String) -> Unit,
    val motivo: (String) -> Unit,
    val codigoLeido: (String) -> Unit,
    val lectorNoDisponible: () -> Unit,
    val registrar: () -> Unit,
)

/** RF-06, RF-07 y RF-08: registrar entrada, salida o ajuste. Solo muestra los tipos del perfil activo. */
@Composable
fun PantallaMovimiento(
    estado: FormularioMovimientoEstado,
    productos: List<Producto>,
    tiposPermitidos: List<TipoMovimiento>,
    eventos: EventosMovimiento,
) {
    val errores = estado.errores
    val producto = productos.find { it.codigo == estado.codigoProducto }
    val descripcion: (Producto) -> String = { "${it.codigo} · ${it.nombre}" }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Registrar movimiento", style = MaterialTheme.typography.headlineMedium)
        Text("Cada movimiento queda en el historial del producto con fecha y usuario.",
            style = MaterialTheme.typography.bodySmall)
        SelectorFormulario("Elegir producto", Icons.AutoMirrored.Filled.List, producto?.let(descripcion),
            productos, descripcion, errores.producto) { eventos.producto(it.codigo) }
        BotonLectorCodigo(eventos.codigoLeido, eventos.lectorNoDisponible)
        producto?.let {
            Text("Disponible: ${it.stockActual} · Stock mínimo: ${it.stockMinimo}", style = MaterialTheme.typography.titleSmall)
        }
        Text("Tipo de movimiento", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 8.dp))
        Column(Modifier.selectableGroup()) {
            tiposPermitidos.forEach { tipo ->
                Row(Modifier.fillMaxWidth().selectable(estado.tipo == tipo, role = Role.RadioButton,
                    onClick = { eventos.tipo(tipo) }).padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = estado.tipo == tipo, onClick = null)
                    Spacer(Modifier.width(12.dp))
                    Text(tipo.etiqueta())
                }
            }
        }
        errores.tipo?.let { MensajeError(it) }
        CampoFormulario(estado.cantidad, "Cantidad", Icons.Default.Add, errores.cantidad, eventos.cantidad, numerico = true)
        CampoFormulario(estado.motivo,
            if (estado.tipo == TipoMovimiento.AJUSTE) "Motivo del ajuste" else "Motivo (opcional)",
            Icons.Default.Create, errores.motivo, eventos.motivo)
        estado.mensaje?.let { MensajeError(it, Modifier.padding(vertical = 4.dp)) }
        Button(onClick = eventos.registrar, modifier = Modifier.fillMaxWidth()) { Text("Registrar movimiento") }
    }
}
