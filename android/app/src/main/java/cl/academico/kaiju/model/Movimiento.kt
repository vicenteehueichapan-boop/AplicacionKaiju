package cl.academico.kaiju.model

enum class TipoMovimiento { ENTRADA, SALIDA_VENTA, SALIDA_CONSUMO, AJUSTE }

data class Movimiento(
    val id: String,
    val codigoProducto: String,
    val tipo: TipoMovimiento,
    val cantidad: Int,
    val motivo: String?,
    val fecha: Long,
    val usuario: Usuario,
)
