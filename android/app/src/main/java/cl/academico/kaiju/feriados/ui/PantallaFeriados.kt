package cl.academico.kaiju.feriados.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.academico.kaiju.feriados.data.FeriadosModulo
import cl.academico.kaiju.feriados.model.Feriado
import cl.academico.kaiju.feriados.viewmodel.ContenidoFeriados
import cl.academico.kaiju.feriados.viewmodel.FeriadosUiState
import cl.academico.kaiju.feriados.viewmodel.FeriadosViewModel
import cl.academico.kaiju.ui.components.EncabezadoPantalla
import cl.academico.kaiju.ui.theme.ColoresKaiju
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

/**
 * Punto de entrada desde la navegación: crea el ViewModel y le entrega el estado a la pantalla.
 * `productosEnAlerta` viene de los datos que la app ya tiene (la lista de Alertas).
 */
@Composable
fun PantallaFeriadosConectada(
    productosEnAlerta: Int,
    vm: FeriadosViewModel = viewModel { FeriadosViewModel(FeriadosModulo.repositorio) },
) {
    val estado by vm.estado.collectAsStateWithLifecycle()
    PantallaFeriados(
        estado = estado,
        productosEnAlerta = productosEnAlerta,
        alCambiarAnio = vm::actualizarAnio,
        alBuscar = vm::consultar,
        alReintentar = vm::reintentar,
    )
}

/** Pantalla sin lógica propia: solo dibuja el estado recibido (carga, éxito o error). */
@Composable
fun PantallaFeriados(
    estado: FeriadosUiState,
    productosEnAlerta: Int,
    alCambiarAnio: (String) -> Unit,
    alBuscar: () -> Unit,
    alReintentar: () -> Unit,
) {
    LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            EncabezadoPantalla("Feriados", "Calendario de reposición: anticipa los pedidos antes de cada feriado de Chile.")
        }
        item { BuscadorAnio(estado, alCambiarAnio, alBuscar) }

        when (val contenido = estado.contenido) {
            is ContenidoFeriados.Cargando -> item { IndicadorCarga() }
            is ContenidoFeriados.Error -> item { TarjetaError(contenido.mensaje, alReintentar) }
            is ContenidoFeriados.Exito -> {
                item { TarjetaResumen(contenido) }
                if (contenido.proximo != null) item { TarjetaStock(productosEnAlerta) }
                items(contenido.feriados, key = { "${it.fecha}-${it.nombre}" }) { feriado ->
                    FilaFeriado(feriado, destacado = feriado == contenido.proximo)
                }
            }
        }
    }
}

@Composable
private fun BuscadorAnio(estado: FeriadosUiState, alCambiar: (String) -> Unit, alBuscar: () -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.Top) {
        OutlinedTextField(
            value = estado.anioTexto,
            onValueChange = alCambiar,
            modifier = Modifier.weight(1f),
            label = { Text("Año") },
            singleLine = true,
            isError = estado.errorEntrada != null,
            supportingText = { Text(estado.errorEntrada ?: "Ejemplo: ${LocalDate.now().year}") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { alBuscar() }),
        )
        Button(
            onClick = alBuscar,
            enabled = estado.contenido !is ContenidoFeriados.Cargando,
            modifier = Modifier.padding(top = 8.dp).height(56.dp),
        ) {
            Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(6.dp))
            Text("Buscar")
        }
    }
}

@Composable
private fun IndicadorCarga() {
    Column(
        Modifier.fillMaxWidth().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        CircularProgressIndicator()
        Text("Consultando feriados…", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun TarjetaError(mensaje: String, alReintentar: () -> Unit) {
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)) {
        Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Warning, contentDescription = null, tint = MaterialTheme.colorScheme.onErrorContainer)
                Spacer(Modifier.width(12.dp))
                Text(mensaje, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onErrorContainer)
            }
            Button(onClick = alReintentar) {
                Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(6.dp))
                Text("Reintentar")
            }
        }
    }
}

@Composable
private fun TarjetaResumen(contenido: ContenidoFeriados.Exito) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
        border = BorderStroke(1.dp, ColoresKaiju.borde),
    ) {
        Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            val proximo = contenido.proximo
            val dias = contenido.diasParaProximo
            if (proximo != null && dias != null) {
                Text("Próximo feriado", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(proximo.nombre, style = MaterialTheme.typography.titleMedium)
                Text("${fechaLarga(proximo.fecha)} · ${enDias(dias)}", style = MaterialTheme.typography.bodyMedium)
            } else if (contenido.esAnioActual) {
                Text("Este año ya no quedan feriados.", style = MaterialTheme.typography.titleMedium)
            } else {
                Text("Feriados de ${contenido.anio}", style = MaterialTheme.typography.titleMedium)
                Text("${contenido.feriados.size} feriados registrados.", style = MaterialTheme.typography.bodyMedium)
            }
            if (contenido.desdeCache) {
                Text(
                    "Datos reutilizados de una consulta anterior, sin nueva llamada a la API.",
                    style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

/** Une el calendario con el inventario: cuántos productos conviene reponer antes del próximo feriado. */
@Composable
private fun TarjetaStock(productosEnAlerta: Int) {
    val hayAlertas = productosEnAlerta > 0
    val colorTexto = if (hayAlertas) MaterialTheme.colorScheme.onErrorContainer else MaterialTheme.colorScheme.onSurface
    val texto = when (productosEnAlerta) {
        0 -> "Sin productos en alerta: no hay reposiciones urgentes antes de esa fecha."
        1 -> "1 producto está en alerta de stock: conviene reponerlo antes de esa fecha."
        else -> "$productosEnAlerta productos están en alerta de stock: conviene reponerlos antes de esa fecha."
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (hayAlertas) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.surfaceContainerHigh,
        ),
        border = if (hayAlertas) null else BorderStroke(1.dp, ColoresKaiju.borde),
    ) {
        Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                if (hayAlertas) Icons.Default.Warning else Icons.Default.CheckCircle,
                contentDescription = null,
                tint = if (hayAlertas) colorTexto else ColoresKaiju.exito,
            )
            Spacer(Modifier.width(12.dp))
            Text(texto, style = MaterialTheme.typography.bodyMedium, color = colorTexto)
        }
    }
}

@Composable
private fun FilaFeriado(feriado: Feriado, destacado: Boolean) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
        border = BorderStroke(
            if (destacado) 2.dp else 1.dp,
            if (destacado) MaterialTheme.colorScheme.primary else ColoresKaiju.borde,
        ),
    ) {
        Row(
            Modifier.fillMaxWidth().padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Surface(shape = MaterialTheme.shapes.small, color = MaterialTheme.colorScheme.primaryContainer) {
                Column(Modifier.width(56.dp).padding(vertical = 8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        feriado.fecha.dayOfMonth.toString(),
                        style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                    Text(
                        mesCorto(feriado.fecha),
                        style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            }
            Column(Modifier.weight(1f)) {
                Text(feriado.nombre, style = MaterialTheme.typography.titleSmall)
                Text(
                    "${diaSemana(feriado.fecha)} · ${if (feriado.esNacional) "Nacional" else "Regional"}",
                    style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            if (destacado) Text("Próximo", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
        }
    }
}

// Formato de fechas en español de Chile (solo presentación).
private val localeChile: Locale = Locale.forLanguageTag("es-CL")
private val formatoFechaLarga: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE d 'de' MMMM", localeChile)

private fun fechaLarga(fecha: LocalDate): String = formatoFechaLarga.format(fecha).replaceFirstChar { it.uppercase() }

private fun diaSemana(fecha: LocalDate): String =
    fecha.dayOfWeek.getDisplayName(TextStyle.FULL, localeChile).replaceFirstChar { it.uppercase() }

private fun mesCorto(fecha: LocalDate): String =
    fecha.month.getDisplayName(TextStyle.SHORT, localeChile).trimEnd('.').uppercase(localeChile)

private fun enDias(dias: Long): String = when (dias) {
    0L -> "es hoy"
    1L -> "es mañana"
    else -> "en $dias días"
}
