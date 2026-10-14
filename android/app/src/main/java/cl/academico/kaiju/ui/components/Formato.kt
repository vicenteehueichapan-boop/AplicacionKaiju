package cl.academico.kaiju.ui.components

import cl.academico.kaiju.model.Rol
import cl.academico.kaiju.model.TipoMovimiento
import java.text.NumberFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

// Textos de presentación compartidos por las pantallas; no contienen reglas de negocio.

private val formatoPesos = NumberFormat.getIntegerInstance(Locale.forLanguageTag("es-CL"))
private val formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")

fun precioClp(precio: Int): String = "$ ${formatoPesos.format(precio)} CLP"

fun fechaHora(milisegundos: Long): String =
    formatoFecha.format(Instant.ofEpochMilli(milisegundos).atZone(ZoneId.systemDefault()))

fun TipoMovimiento.etiqueta(): String = when (this) {
    TipoMovimiento.ENTRADA -> "Entrada"
    TipoMovimiento.SALIDA_VENTA -> "Salida por venta"
    TipoMovimiento.SALIDA_CONSUMO -> "Salida por consumo"
    TipoMovimiento.AJUSTE -> "Ajuste"
}

fun Rol.etiqueta(): String = when (this) {
    Rol.ADMINISTRADOR -> "Administrador"
    Rol.VENDEDOR -> "Personal de venta"
    Rol.ENCARGADO_INVENTARIO -> "Encargado de inventario"
}
