package cl.academico.kaiju.model

/** Errores por campo del formulario de movimiento (Guía 10 sección 5). `null` = campo correcto. */
data class FormularioMovimientoErrores(
    val producto: String? = null,
    val tipo: String? = null,
    val cantidad: String? = null,
    val motivo: String? = null,
) {
    val hayErrores: Boolean
        get() = listOf(producto, tipo, cantidad, motivo).any { it != null }
}

/** Ajuste por conteo físico esperando confirmación: muestra el cambio antes de aplicarlo (RF-08). */
data class AjustePendiente(val producto: Producto, val contado: Int, val motivo: String) {
    val diferencia: Int get() = contado - producto.stockActual
}

/**
 * Estado del formulario de entrada, salida o ajuste (RF-06, RF-07, RF-08).
 * En un ajuste, [cantidad] es la cantidad contada; en los demás tipos, las unidades que entran o salen.
 */
data class FormularioMovimientoEstado(
    val codigoProducto: String? = null,
    val tipo: TipoMovimiento? = null,
    val cantidad: String = "",
    val motivo: String = "",
    val errores: FormularioMovimientoErrores = FormularioMovimientoErrores(),
    val mensaje: String? = null,
    val ajustePendiente: AjustePendiente? = null,
)
