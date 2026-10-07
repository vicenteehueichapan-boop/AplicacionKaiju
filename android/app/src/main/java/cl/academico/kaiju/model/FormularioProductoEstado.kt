package cl.academico.kaiju.model

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

data class FormularioProductoEstado(
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
)
