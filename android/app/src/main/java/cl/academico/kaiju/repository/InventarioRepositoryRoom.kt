package cl.academico.kaiju.repository

import cl.academico.kaiju.model.Movimiento
import cl.academico.kaiju.model.Producto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

/** Repositorio con persistencia local en Room (RF-10): los datos sobreviven al cerrar la app. */
class InventarioRepositoryRoom(private val dao: InventarioDao) : InventarioRepository {

    override val productos: Flow<List<Producto>> =
        dao.productos().onStart { dao.cargarSiEstaVacio(DatosDePrueba.productos) }
    override val movimientos: Flow<List<Movimiento>> = dao.movimientos()
    override val categorias: List<String> = DatosDePrueba.categorias

    override suspend fun agregarProducto(producto: Producto) = dao.insertarProductos(listOf(producto))

    override suspend fun actualizarDatosProducto(producto: Producto) = with(producto) {
        dao.actualizarDatos(codigo, nombre, descripcion, categoria, tipo, precio, stockMinimo, detalle)
    }

    override suspend fun registrarMovimiento(movimiento: Movimiento, stockAnterior: Int, stockResultante: Int) =
        dao.registrarMovimiento(movimiento, stockAnterior, stockResultante)
}
