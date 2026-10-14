package cl.academico.kaiju.model

/** Errores por campo del formulario de producto (A-10 §5). `null` = campo correcto. */
data class FormularioProductoErrores(
    val codigo: String? = null,
    val nombre: String? = null,
    val categoria: String? = null,
    val precio: String? = null,
    val stockMinimo: String? = null,
) {
    val hayErrores: Boolean
        get() = listOf(codigo, nombre, categoria, precio, stockMinimo).any { it != null }
}

/**
 * Estado del formulario de crear/editar producto (RF-05). Los números se guardan como texto
 * mientras se escriben y se convierten al validar, como en la Guía 09.
 */
data class FormularioProductoEstado(
    /** `null` al crear. Al editar, el código del producto: no se cambia porque identifica su historial. */
    val codigoEditado: String? = null,
    val codigo: String = "",
    val nombre: String = "",
    val descripcion: String = "",
    val categoria: String = "",
    val tipo: String = "",
    val precio: String = "",
    val stockMinimo: String = "",
    val detalle: String = "",
    val errores: FormularioProductoErrores = FormularioProductoErrores(),
    val mensaje: String? = null,
) {
    val esEdicion: Boolean get() = codigoEditado != null
}
