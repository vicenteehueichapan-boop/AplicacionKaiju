package cl.academico.kaiju.feriados.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.academico.kaiju.feriados.data.FeriadosRepository
import cl.academico.kaiju.feriados.model.Feriado
import cl.academico.kaiju.feriados.model.ResultadoFeriados
import cl.academico.kaiju.feriados.model.TipoFallo
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.ChronoUnit

/** Lo que la pantalla puede estar mostrando: cargando, resultado o error. */
sealed interface ContenidoFeriados {
    data object Cargando : ContenidoFeriados
    data class Exito(
        val anio: Int,
        val feriados: List<Feriado>,
        val proximo: Feriado?,
        val diasParaProximo: Long?,
        val esAnioActual: Boolean,
        val desdeCache: Boolean,
    ) : ContenidoFeriados
    data class Error(val mensaje: String, val tipo: TipoFallo) : ContenidoFeriados
}

/** Estado completo de la pantalla: el campo de año, su validación y el contenido. */
data class FeriadosUiState(
    val anioTexto: String,
    val errorEntrada: String? = null,
    val contenido: ContenidoFeriados = ContenidoFeriados.Cargando,
)

/**
 * Valida el año, pide los datos al Repository y expone un único StateFlow de solo lectura.
 * La primera consulta se hace en `init`, una sola vez por ViewModel: recomponer o rotar el teléfono no repite la llamada.
 * `hoy` se inyecta para poder probar el cálculo del próximo feriado con una fecha fija.
 */
class FeriadosViewModel(
    private val repositorio: FeriadosRepository,
    private val hoy: () -> LocalDate = { LocalDate.now() },
) : ViewModel() {

    private val _estado = MutableStateFlow(FeriadosUiState(anioTexto = hoy().year.toString()))
    val estado: StateFlow<FeriadosUiState> = _estado.asStateFlow()

    private var consulta: Job? = null

    init {
        consultar()
    }

    fun actualizarAnio(texto: String) {
        _estado.update { it.copy(anioTexto = texto.take(6), errorEntrada = null) }
    }

    /** Valida la entrada; si es válida consulta, y si no, muestra el error sin hacer ninguna petición. */
    fun consultar() {
        val texto = _estado.value.anioTexto.trim()
        val anio = texto.toIntOrNull()
        val error = when {
            texto.isEmpty() -> "Escribe un año para consultar."
            anio == null || texto.length != 4 -> "El año debe tener 4 dígitos numéricos, por ejemplo ${hoy().year}."
            anio !in ANIO_MINIMO..ANIO_MAXIMO -> "Usa un año entre $ANIO_MINIMO y $ANIO_MAXIMO."
            else -> null
        }
        if (error != null || anio == null) {
            _estado.update { it.copy(errorEntrada = error) }
            return
        }

        consulta?.cancel()
        _estado.update { it.copy(anioTexto = texto, errorEntrada = null, contenido = ContenidoFeriados.Cargando) }
        consulta = viewModelScope.launch {
            val contenido = when (val resultado = repositorio.obtenerFeriados(anio)) {
                is ResultadoFeriados.Exito -> armarExito(anio, resultado)
                is ResultadoFeriados.Fallo -> ContenidoFeriados.Error(resultado.mensaje, resultado.tipo)
            }
            _estado.update { it.copy(contenido = contenido) }
        }
    }

    fun reintentar() = consultar()

    private fun armarExito(anio: Int, resultado: ResultadoFeriados.Exito): ContenidoFeriados.Exito {
        val fechaHoy = hoy()
        val esAnioActual = anio == fechaHoy.year
        val proximo = if (esAnioActual) resultado.feriados.firstOrNull { !it.fecha.isBefore(fechaHoy) } else null
        return ContenidoFeriados.Exito(
            anio = anio,
            feriados = resultado.feriados,
            proximo = proximo,
            diasParaProximo = proximo?.let { ChronoUnit.DAYS.between(fechaHoy, it.fecha) },
            esAnioActual = esAnioActual,
            desdeCache = resultado.desdeCache,
        )
    }

    companion object {
        const val ANIO_MINIMO = 2000
        const val ANIO_MAXIMO = 2100
    }
}
