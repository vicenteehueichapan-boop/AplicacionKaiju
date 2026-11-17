package cl.academico.kaiju.feriados

import cl.academico.kaiju.feriados.data.FeriadosApiService
import cl.academico.kaiju.feriados.data.FeriadosRepository
import cl.academico.kaiju.feriados.model.FeriadoDto
import cl.academico.kaiju.feriados.model.TipoFallo
import cl.academico.kaiju.feriados.viewmodel.ContenidoFeriados
import cl.academico.kaiju.feriados.viewmodel.FeriadosViewModel
import cl.academico.kaiju.viewmodel.ReglaDispatcherPrincipal
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.io.IOException
import java.net.SocketTimeoutException
import java.time.LocalDate

/** Casos de la Guía 12: consulta exitosa, entrada inválida y falla de conexión, con una API falsa (sin red). */
class FeriadosViewModelTest {
    @get:Rule val reglaDispatcher = ReglaDispatcherPrincipal()

    private val hoy = LocalDate.of(2026, 11, 12)

    private fun dto(fecha: String, nombre: String) = FeriadoDto(fecha, nombre, nombre, "CL", true, listOf("Public"))

    // Desordenados a propósito: el Repository debe entregarlos ordenados por fecha.
    private val respuestaOk = listOf(
        dto("2026-12-25", "Navidad"),
        dto("2026-01-01", "Año Nuevo"),
        dto("2026-12-08", "Inmaculada Concepción"),
    )

    private class ApiFalsa(var respuesta: () -> List<FeriadoDto>?) : FeriadosApiService {
        var llamadas = 0
        override suspend fun feriadosPorAnio(anio: Int, pais: String): List<FeriadoDto>? {
            llamadas++
            return respuesta()
        }
    }

    private fun crear(api: ApiFalsa) = FeriadosViewModel(FeriadosRepository(api), hoy = { hoy })

    @Test fun consultaExitosaOrdenaLosFeriadosYCalculaElProximo() {
        val api = ApiFalsa { respuestaOk }
        val vm = crear(api)
        val exito = vm.estado.value.contenido as ContenidoFeriados.Exito
        assertEquals(LocalDate.of(2026, 1, 1), exito.feriados.first().fecha)
        assertEquals(LocalDate.of(2026, 12, 8), exito.proximo?.fecha)
        assertEquals(26L, exito.diasParaProximo)
        assertEquals(1, api.llamadas)
    }

    @Test fun repetirElMismoAnioUsaLaCacheSinNuevaLlamada() {
        val api = ApiFalsa { respuestaOk }
        val vm = crear(api)
        vm.consultar()
        val exito = vm.estado.value.contenido as ContenidoFeriados.Exito
        assertTrue(exito.desdeCache)
        assertEquals(1, api.llamadas)
    }

    @Test fun entradaInvalidaMuestraErrorYNoLlamaALaApi() {
        val api = ApiFalsa { respuestaOk }
        val vm = crear(api)
        listOf("", "abcd", "26", "1800", "2101").forEach { texto ->
            vm.actualizarAnio(texto)
            vm.consultar()
            assertNotNull("Debe rechazar \"$texto\"", vm.estado.value.errorEntrada)
        }
        assertEquals(1, api.llamadas) // Solo la consulta inicial.
    }

    @Test fun fallaDeConexionMuestraErrorYReintentarLoRecupera() {
        var hayRed = false
        val api = ApiFalsa { if (hayRed) respuestaOk else throw IOException("sin red") }
        val vm = crear(api)
        val error = vm.estado.value.contenido as ContenidoFeriados.Error
        assertEquals(TipoFallo.SIN_CONEXION, error.tipo)
        hayRed = true
        vm.reintentar()
        assertTrue(vm.estado.value.contenido is ContenidoFeriados.Exito)
    }

    @Test fun tiempoAgotadoSeInformaAparte() {
        val vm = crear(ApiFalsa { throw SocketTimeoutException("timeout") })
        val error = vm.estado.value.contenido as ContenidoFeriados.Error
        assertEquals(TipoFallo.TIEMPO_AGOTADO, error.tipo)
    }

    @Test fun respuestaSinFeriadosSeInformaComoSinDatos() {
        val vm = crear(ApiFalsa { emptyList() })
        val error = vm.estado.value.contenido as ContenidoFeriados.Error
        assertEquals(TipoFallo.SIN_DATOS, error.tipo)
    }
}
