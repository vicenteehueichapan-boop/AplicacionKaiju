package cl.academico.kaiju.repository

import cl.academico.kaiju.database.InventarioDao
import cl.academico.kaiju.model.Movimiento
import cl.academico.kaiju.model.Producto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

/**
 * El único punto que conoce al DAO (A-10 §3). No valida: el ViewModel revisa las reglas RN
 * antes de llamar aquí. Si mañana cambia la fuente de datos, solo se toca esta clase.
 */
class InventarioRepository(private val dao: InventarioDao) {

    // La primera vez que se lee el catálogo se cargan los datos de prueba; después nunca se pisan.
    val productos: Flow<List<Producto>> = dao.productos().onStart { dao.cargarSiEstaVacio(DatosDePrueba.productos) }

    /** Movimientos en el orden en que se registraron. */
    val movimientos: Flow<List<Movimiento>> = dao.movimientos()
    val categorias: List<String> = DatosDePrueba.categorias

    suspend fun agregarProducto(producto: Producto) = dao.insertarProductos(listOf(producto))

    /** Actualiza los datos editables del formulario. No modifica código ni stockActual. */
    suspend fun actualizarDatosProducto(producto: Producto) = with(producto) {
        dao.actualizarDatos(codigo, nombre, descripcion, categoria, tipo, precio, stockMinimo, detalle)
    }

    /**
     * Guarda el movimiento y el stock resultante en una sola operación (A-10 §4).
     * Devuelve `false` sin guardar nada si el stock ya no es [stockAnterior], es decir,
     * si cambió después de que el ViewModel validó el movimiento.
     */
    suspend fun registrarMovimiento(movimiento: Movimiento, stockAnterior: Int, stockResultante: Int): Boolean =
        dao.registrarMovimiento(movimiento, stockAnterior, stockResultante)
}
