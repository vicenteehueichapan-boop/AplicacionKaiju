package cl.academico.kaiju.feriados.model

/**
 * DTO: copia fiel del JSON de Nager.Date (GET /PublicHolidays/{año}/CL).
 * Gson crea el objeto sin pasar por el constructor, así que un campo ausente llega como null:
 * por eso todos son nulables y se validan al convertir a [Feriado].
 */
data class FeriadoDto(
    val date: String?,
    val localName: String?,
    val name: String?,
    val countryCode: String?,
    val global: Boolean?,
    val types: List<String>?,
)
