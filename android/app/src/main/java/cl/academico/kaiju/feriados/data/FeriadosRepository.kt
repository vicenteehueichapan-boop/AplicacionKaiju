package cl.academico.kaiju.feriados.data

import cl.academico.kaiju.feriados.model.Feriado
import cl.academico.kaiju.feriados.model.ResultadoFeriados
import cl.academico.kaiju.feriados.model.TipoFallo
import cl.academico.kaiju.feriados.model.aModelo
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import kotlin.coroutines.cancellation.CancellationException

/**
 * Único que conoce la API. Llama a Retrofit, convierte DTO a modelo, traduce errores a mensajes
 * y guarda en memoria las respuestas por año: consultar de nuevo el mismo año no vuelve a la red.
 */
class FeriadosRepository(
    private val api: FeriadosApiService,
    private val pais: String = "CL",
) {
    private val cache = mutableMapOf<Int, List<Feriado>>()
    private val candado = Mutex()

    suspend fun obtenerFeriados(anio: Int): ResultadoFeriados {
        candado.withLock { cache[anio] }?.let { return ResultadoFeriados.Exito(it, desdeCache = true) }

        return try {
            val feriados = api.feriadosPorAnio(anio, pais)
                .orEmpty()
                .mapNotNull { it.aModelo() }
                .sortedBy { it.fecha }
            if (feriados.isEmpty()) {
                ResultadoFeriados.Fallo(TipoFallo.SIN_DATOS, "La API no tiene feriados registrados para $anio.")
            } else {
                candado.withLock { cache[anio] = feriados }
                ResultadoFeriados.Exito(feriados, desdeCache = false)
            }
        } catch (e: CancellationException) {
            throw e // La corrutina fue cancelada (por ejemplo, el usuario buscó otro año): no es un error.
        } catch (e: HttpException) {
            if (e.code() == 400 || e.code() == 404) {
                ResultadoFeriados.Fallo(TipoFallo.SIN_DATOS, "La API no tiene feriados para $anio (código ${e.code()}).")
            } else {
                ResultadoFeriados.Fallo(TipoFallo.SERVIDOR, "El servicio respondió con un error (código ${e.code()}). Intenta nuevamente.")
            }
        } catch (e: SocketTimeoutException) {
            ResultadoFeriados.Fallo(TipoFallo.TIEMPO_AGOTADO, "El servicio tardó demasiado en responder. Revisa tu conexión e intenta de nuevo.")
        } catch (e: IOException) {
            ResultadoFeriados.Fallo(TipoFallo.SIN_CONEXION, "No hay conexión a internet. Revísala e intenta de nuevo.")
        } catch (e: Exception) {
            ResultadoFeriados.Fallo(TipoFallo.RESPUESTA_INVALIDA, "No se pudo interpretar la respuesta del servicio.")
        }
    }
}

/** Dependencias compartidas: un único Repository para que la caché sobreviva al cambiar de pantalla. */
object FeriadosModulo {
    val repositorio: FeriadosRepository by lazy { FeriadosRepository(ClienteFeriados.servicio) }
}
