package cl.academico.kaiju.model

// Campos de A-10 §4. Los datos iniciales se proporcionan explícitamente.
data class Producto(
    val codigo: String,
    val nombre: String,
    val descripcion: String,
    val categoria: String,
    val tipo: String,
    val precio: Int,
    val stockActual: Int,
    val stockMinimo: Int,
    val detalle: String?,
)
