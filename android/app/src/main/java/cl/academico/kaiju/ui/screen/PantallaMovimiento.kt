package cl.academico.kaiju.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import cl.academico.kaiju.model.FormularioMovimientoEstado
import cl.academico.kaiju.model.Producto
import cl.academico.kaiju.model.TipoMovimiento
import cl.academico.kaiju.ui.components.CampoFormulario
import cl.academico.kaiju.ui.components.EncabezadoPantalla
import cl.academico.kaiju.ui.components.IndicadorClave
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
    val confirmarAjuste: () -> Unit,
    val cancelarAjuste: () -> Unit,
)

private fun TipoMovimiento.icono(): ImageVector = when (this) {
    TipoMovimiento.ENTRADA -> Icons.Default.KeyboardArrowUp
    TipoMovimiento.SALIDA_VENTA -> Icons.Default.ShoppingCart
    TipoMovimiento.SALIDA_CONSUMO -> Icons.Default.KeyboardArrowDown
    TipoMovimiento.AJUSTE -> Icons.Default.Create
}

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
    val esAjuste = estado.tipo == TipoMovimiento.AJUSTE
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)) {
        EncabezadoPantalla("Registrar movimiento", "Cada movimiento queda en el historial con fecha y usuario.")

        Text("1. Producto", style = MaterialTheme.typography.titleMedium)
        SelectorFormulario("Elegir producto", Icons.AutoMirrored.Filled.List, producto?.let(descripcion),
            productos, descripcion, errores.producto) { eventos.producto(it.codigo) }
        BotonLectorCodigo(eventos.codigoLeido, eventos.lectorNoDisponible)
        producto?.let {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                IndicadorClave("Disponible", "${it.stockActual}", Icons.AutoMirrored.Filled.List, Modifier.weight(1f),
                    color = if (it.stockActual <= it.stockMinimo) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.tertiary)
                IndicadorClave("Mínimo", "${it.stockMinimo}", Icons.Default.Notifications, Modifier.weight(1f))
            }
        }

        Text("2. Tipo de movimiento", style = MaterialTheme.typography.titleMedium)
        // Fichas en dos columnas: el tipo elegido queda resaltado en morado.
        tiposPermitidos.chunked(2).forEach { fila ->
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                fila.forEach { tipo ->
                    FilterChip(
                        selected = estado.tipo == tipo,
                        onClick = { eventos.tipo(tipo) },
                        label = { Text(tipo.etiqueta()) },
                        leadingIcon = { Icon(tipo.icono(), contentDescription = null) },
                        modifier = Modifier.weight(1f).height(48.dp),
                    )
                }
                if (fila.size == 1) Spacer(Modifier.weight(1f))
            }
        }
        errores.tipo?.let { MensajeError(it) }

        Text("3. Detalle", style = MaterialTheme.typography.titleMedium)
        CampoFormulario(estado.cantidad, if (esAjuste) "Cantidad contada" else "Cantidad", Icons.Default.Add,
            errores.cantidad, eventos.cantidad, numerico = true,
            ayuda = if (esAjuste) "Lo que hay físicamente. Se registra la diferencia con lo disponible." else null)
        CampoFormulario(estado.motivo, if (esAjuste) "Motivo del ajuste" else "Motivo (opcional)",
            Icons.Default.Create, errores.motivo, eventos.motivo)
        estado.mensaje?.let { MensajeError(it) }
        Button(onClick = eventos.registrar, modifier = Modifier.fillMaxWidth().height(52.dp), shape = MaterialTheme.shapes.medium) {
            Text(if (esAjuste) "Revisar ajuste" else "Registrar ${estado.tipo?.etiqueta()?.lowercase() ?: "movimiento"}",
                style = MaterialTheme.typography.titleMedium)
        }
    }

    // Confirmación antes de cambiar el stock por un conteo (como en Odoo): se ve el cambio exacto.
    estado.ajustePendiente?.let { ajuste ->
        val signo = if (ajuste.diferencia > 0) "+" else "−"
        AlertDialog(
            onDismissRequest = eventos.cancelarAjuste,
            icon = { Icon(Icons.Default.Create, contentDescription = null) },
            title = { Text("¿Confirmar ajuste?") },
            text = {
                Text("${ajuste.producto.nombre}: el disponible pasa de ${ajuste.producto.stockActual} a " +
                    "${ajuste.contado} (diferencia $signo${kotlin.math.abs(ajuste.diferencia)}).\nMotivo: ${ajuste.motivo}")
            },
            confirmButton = { TextButton(onClick = eventos.confirmarAjuste) { Text("Confirmar") } },
            dismissButton = { TextButton(onClick = eventos.cancelarAjuste) { Text("Cancelar") } },
        )
    }
}
