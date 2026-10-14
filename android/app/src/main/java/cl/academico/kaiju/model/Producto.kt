package cl.academico.kaiju.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Producto con los campos de A-10 §4. `precio` en pesos chilenos, sin decimales.
 * `stockActual` solo cambia al registrar un movimiento (A-10 §4); el formulario no lo edita.
 * Es también la tabla `productos` de Room (RF-10).
 */
@Entity(tableName = "productos")
data class Producto(
    @PrimaryKey val codigo: String,
    val nombre: String,
    val descripcion: String,
    val categoria: String,
    val tipo: String,
    val precio: Int,
    val stockActual: Int,
    val stockMinimo: Int,
    val detalle: String?,
)
