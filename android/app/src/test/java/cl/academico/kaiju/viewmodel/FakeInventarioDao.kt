package cl.academico.kaiju.viewmodel

import cl.academico.kaiju.database.InventarioDao
import cl.academico.kaiju.model.Movimiento
import cl.academico.kaiju.model.Producto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

/**
 * DAO de prueba: guarda productos y movimientos en memoria en vez de SQLite, igual que
 * FakeEventoDao en el ejemplo de la Agenda. Cumple el contrato de [InventarioDao], así que
 * el repositorio real y las operaciones compuestas del DAO (registrar, carga inicial) se
 * prueban sin emulador.
 */
class FakeInventarioDao(productosPrevios: List<Producto> = emptyList()) : InventarioDao() {
    private data class Datos(val productos: List<Producto>, val movimientos: List<Movimiento>)

    private val datos = MutableStateFlow(Datos(productosPrevios, emptyList()))

    val productosGuardados: List<Producto> get() = datos.value.productos
    val movimientosGuardados: List<Movimiento> get() = datos.value.movimientos

    override fun productos(): Flow<List<Producto>> = datos.map { it.productos.sortedBy(Producto::codigo) }
    override fun movimientos(): Flow<List<Movimiento>> = datos.map { it.movimientos }
    override suspend fun contarProductos(): Int = datos.value.productos.size

    override suspend fun insertarProductos(productos: List<Producto>) =
        datos.update { it.copy(productos = it.productos + productos) }

    override suspend fun actualizarDatos(
        codigo: String, nombre: String, descripcion: String, categoria: String,
        tipo: String, precio: Int, stockMinimo: Int, detalle: String?,
    ) = datos.update { actual ->
        actual.copy(productos = actual.productos.map {
            if (it.codigo != codigo) it
            else it.copy(nombre = nombre, descripcion = descripcion, categoria = categoria,
                tipo = tipo, precio = precio, stockMinimo = stockMinimo, detalle = detalle)
        })
    }

    override suspend fun insertarMovimiento(movimiento: Movimiento) =
        datos.update { it.copy(movimientos = it.movimientos + movimiento) }

    override suspend fun cambiarStockSiNoCambio(codigo: String, anterior: Int, nuevo: Int): Int {
        if (datos.value.productos.none { it.codigo == codigo && it.stockActual == anterior }) return 0
        datos.update { actual ->
            actual.copy(productos = actual.productos.map { if (it.codigo == codigo) it.copy(stockActual = nuevo) else it })
        }
        return 1
    }
}
