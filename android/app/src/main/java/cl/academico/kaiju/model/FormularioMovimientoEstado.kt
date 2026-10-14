package cl.academico.kaiju.model

/** Errores por campo del formulario de movimiento (A-10 §5). `null` = campo correcto. */
data class FormularioMovimientoErrores(
    val producto: String? = null,
    val tipo: String? = null,
    val cantidad: String? = null,
    val motivo: String? = null,
) {
    val hayErrores: Boolean
        get() = listOf(producto, tipo, cantidad, motivo).any { it != null }
}

/** Estado del formulario de entrada, salida o ajuste (RF-06, RF-07, RF-08). */
data class FormularioMovimientoEstado(
    val codigoProducto: String? = null,
    val tipo: TipoMovimiento? = null,
    val cantidad: String = "",
    val motivo: String = "",
    val errores: FormularioMovimientoErrores = FormularioMovimientoErrores(),
    val mensaje: String? = null,
)
