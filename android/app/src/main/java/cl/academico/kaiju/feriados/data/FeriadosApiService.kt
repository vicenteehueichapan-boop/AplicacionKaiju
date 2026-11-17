package cl.academico.kaiju.feriados.data

import cl.academico.kaiju.feriados.model.FeriadoDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Contrato HTTP de Nager.Date. Retrofit genera la implementación.
 * GET https://date.nager.at/api/v3/PublicHolidays/{anio}/{pais}
 * El tipo de retorno es nulable porque una respuesta 204 (sin contenido) llega como null.
 */
interface FeriadosApiService {
    @GET("PublicHolidays/{anio}/{pais}")
    suspend fun feriadosPorAnio(
        @Path("anio") anio: Int,
        @Path("pais") pais: String,
    ): List<FeriadoDto>?
}

/** Crea el cliente Retrofit una sola vez. La URL base debe terminar en "/". */
object ClienteFeriados {
    const val URL_BASE = "https://date.nager.at/api/v3/"

    val servicio: FeriadosApiService by lazy {
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FeriadosApiService::class.java)
    }
}
