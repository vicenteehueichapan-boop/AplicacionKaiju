package cl.academico.kaiju.viewmodel

import cl.academico.kaiju.model.Movimiento
import cl.academico.kaiju.model.Producto
import cl.academico.kaiju.repository.DatosDePrueba
import cl.academico.kaiju.repository.InventarioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

/** Mismo contrato que el repositorio Room, en memoria, para probar el ViewModel sin Android. */
class InventarioRepositoryEnMemoria(productosIniciales: List<Producto> = DatosDePrueba.productos) : InventarioRepository {
    private data class Datos(val productos: List<Producto>, val movimientos: List<Movimiento>)

    // Un único estado: productos e historial cambian juntos, como en la transacción de Room.
    private val datos = MutableStateFlow(Datos(productosIniciales, emptyList()))

    override val productos: Flow<List<Producto>> = datos.map { it.productos }
    override val movimientos: Flow<List<Movimiento>> = datos.map { it.movimientos }
    override val categorias: List<String> = DatosDePrueba.categorias

    val productosGuardados: List<Producto> get() = datos.value.productos
    val movimientosGuardados: List<Movimiento> get() = datos.value.movimientos

    override suspend fun agregarProducto(producto: Producto) =
        datos.update { it.copy(productos = it.productos + producto) }

    override suspend fun actualizarDatosProducto(producto: Producto) = datos.update { actual ->
        actual.copy(productos = actual.productos.map {
            if (it.codigo == producto.codigo) producto.copy(stockActual = it.stockActual) else it
        })
    }

    override suspend fun registrarMovimiento(movimiento: Movimiento, stockAnterior: Int, stockResultante: Int): Boolean {
        val producto = datos.value.productos.find { it.codigo == movimiento.codigoProducto }
        if (producto?.stockActual != stockAnterior) return false
        datos.update { actual ->
            actual.copy(
                productos = actual.productos.map {
                    if (it.codigo == movimiento.codigoProducto) it.copy(stockActual = stockResultante) else it
                },
                movimientos = actual.movimientos + movimiento,
            )
        }
        return true
    }
}
