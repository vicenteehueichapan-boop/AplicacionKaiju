package cl.academico.kaiju.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

enum class TipoMovimiento {
    ENTRADA, SALIDA_VENTA, SALIDA_CONSUMO, AJUSTE;

    /** RN-01 se aplica a las salidas por venta o por consumo. */
    val esSalida: Boolean get() = this == SALIDA_VENTA || this == SALIDA_CONSUMO
}

/**
 * Registro de un movimiento (A-10 §4). RN-05: guarda fecha, tipo, cantidad y quién lo
 * registró; no se edita ni se borra, por eso el repositorio no ofrece esas operaciones.
 */
@Entity(
    tableName = "movimientos",
    foreignKeys = [ForeignKey(entity = Producto::class, parentColumns = ["codigo"], childColumns = ["codigoProducto"])],
    indices = [Index("codigoProducto")],
)
data class Movimiento(
    @PrimaryKey val id: String,
    val codigoProducto: String,
    val tipo: TipoMovimiento,
    val cantidad: Int,
    val motivo: String?,
    /** Instante del registro en milisegundos. */
    val fecha: Long,
    @Embedded(prefix = "usuario_") val usuario: Usuario,
)
