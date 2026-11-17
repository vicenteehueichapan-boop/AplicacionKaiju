package cl.academico.kaiju.feriados.model

import java.time.LocalDate
import java.time.format.DateTimeParseException

/** Modelo que usa la app: ya validado y con la fecha como [LocalDate]. */
data class Feriado(
    val fecha: LocalDate,
    val nombre: String,
    val nombreIngles: String,
    val esNacional: Boolean,
    val tipos: List<String>,
)

/** Convierte el DTO en modelo; devuelve null si el registro no trae una fecha o un nombre utilizable. */
fun FeriadoDto.aModelo(): Feriado? {
    val textoFecha = date ?: return null
    val fecha = try {
        LocalDate.parse(textoFecha)
    } catch (e: DateTimeParseException) {
        return null
    }
    val nombreLocal = localName ?: name ?: return null
    return Feriado(
        fecha = fecha,
        nombre = nombreLocal,
        nombreIngles = name ?: nombreLocal,
        esNacional = global ?: true,
        tipos = types.orEmpty(),
    )
}

/** Causas de fallo que la interfaz distingue para mostrar un mensaje claro. */
enum class TipoFallo { SIN_CONEXION, TIEMPO_AGOTADO, SIN_DATOS, SERVIDOR, RESPUESTA_INVALIDA }

/** Resultado de una consulta: el Repository nunca lanza excepciones hacia el ViewModel. */
sealed interface ResultadoFeriados {
    data class Exito(val feriados: List<Feriado>, val desdeCache: Boolean) : ResultadoFeriados
    data class Fallo(val tipo: TipoFallo, val mensaje: String) : ResultadoFeriados
}
