package cl.academico.kaiju.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import cl.academico.kaiju.model.Movimiento
import cl.academico.kaiju.model.Producto
import kotlinx.coroutines.flow.Flow

/**
 * El DAO: las operaciones que Room sabe hacer sobre las tablas `productos` y `movimientos`.
 * Es una clase abstracta (no una interface, como en el ejemplo de la Agenda) porque necesita
 * dos operaciones compuestas con `@Transaction`. No hay `@Delete`: RN-05 prohíbe borrar movimientos.
 */
@Dao
abstract class InventarioDao {
    // Flow: Room vuelve a emitir la lista solo cuando la tabla cambia.
    @Query("SELECT * FROM productos ORDER BY codigo")
    abstract fun productos(): Flow<List<Producto>>

    // rowid conserva el orden de registro, incluso si dos movimientos tienen la misma fecha.
    @Query("SELECT * FROM movimientos ORDER BY rowid")
    abstract fun movimientos(): Flow<List<Movimiento>>

    @Query("SELECT COUNT(*) FROM productos")
    protected abstract suspend fun contarProductos(): Int

    @Insert
    abstract suspend fun insertarProductos(productos: List<Producto>)

    @Query(
        "UPDATE productos SET nombre = :nombre, descripcion = :descripcion, categoria = :categoria, " +
            "tipo = :tipo, precio = :precio, stockMinimo = :stockMinimo, detalle = :detalle WHERE codigo = :codigo"
    )
    abstract suspend fun actualizarDatos(
        codigo: String, nombre: String, descripcion: String, categoria: String,
        tipo: String, precio: Int, stockMinimo: Int, detalle: String?,
    )

    @Insert
    protected abstract suspend fun insertarMovimiento(movimiento: Movimiento)

    @Query("UPDATE productos SET stockActual = :nuevo WHERE codigo = :codigo AND stockActual = :anterior")
    protected abstract suspend fun cambiarStockSiNoCambio(codigo: String, anterior: Int, nuevo: Int): Int

    /** Historial y stock cambian juntos o no cambia ninguno (A-10 §4). */
    @Transaction
    open suspend fun registrarMovimiento(movimiento: Movimiento, stockAnterior: Int, stockResultante: Int): Boolean {
        if (cambiarStockSiNoCambio(movimiento.codigoProducto, stockAnterior, stockResultante) == 0) return false
        insertarMovimiento(movimiento)
        return true
    }

    /** Carga los datos de prueba solo la primera vez, sin pisar cambios guardados. */
    @Transaction
    open suspend fun cargarSiEstaVacio(productos: List<Producto>) {
        if (contarProductos() == 0) insertarProductos(productos)
    }
}
