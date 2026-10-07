package cl.academico.kaiju.model

// Contrato de estado para la etapa posterior A-10 §8; no contiene una fórmula de ajuste supuesta.
data class FormularioMovimientoErrores(
    val producto: String? = null,
    val tipo: String? = null,
    val cantidad: String? = null,
    val motivo: String? = null,
)
data class FormularioMovimientoEstado(
    val codigoProducto: String? = null,
    val tipo: TipoMovimiento? = null,
    val cantidad: String = "",
    val motivo: String = "",
    val errores: FormularioMovimientoErrores = FormularioMovimientoErrores(),
)
