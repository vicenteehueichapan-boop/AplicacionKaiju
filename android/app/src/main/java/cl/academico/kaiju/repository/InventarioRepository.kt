package cl.academico.kaiju.repository

import cl.academico.kaiju.model.Movimiento
import cl.academico.kaiju.model.Producto
import kotlinx.coroutines.flow.Flow

/**
 * Guarda y entrega los datos del inventario (A-10 §3). No valida: el ViewModel revisa
 * las reglas RN antes de llamar aquí. La app usa [InventarioRepositoryRoom]; las pruebas
 * usan una versión en memoria con este mismo contrato.
 */
interface InventarioRepository {
    val productos: Flow<List<Producto>>

    /** Movimientos en el orden en que se registraron. */
    val movimientos: Flow<List<Movimiento>>
    val categorias: List<String>

    suspend fun agregarProducto(producto: Producto)

    /** Actualiza los datos editables del formulario. No modifica código ni stockActual. */
    suspend fun actualizarDatosProducto(producto: Producto)

    /**
     * Guarda el movimiento y el stock resultante en una sola operación (A-10 §4).
     * Devuelve `false` sin guardar nada si el stock ya no es [stockAnterior], es decir,
     * si cambió después de que el ViewModel validó el movimiento.
     */
    suspend fun registrarMovimiento(movimiento: Movimiento, stockAnterior: Int, stockResultante: Int): Boolean
}
