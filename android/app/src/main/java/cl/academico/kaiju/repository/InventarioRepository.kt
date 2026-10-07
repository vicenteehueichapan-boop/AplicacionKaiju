package cl.academico.kaiju.repository

import cl.academico.kaiju.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class InventarioRepository(productosIniciales: List<Producto> = DatosDePrueba.productos) {
    private val _productos = MutableStateFlow(productosIniciales.toList())
    val productos: StateFlow<List<Producto>> = _productos.asStateFlow()
    private val _movimientos = MutableStateFlow<List<Movimiento>>(emptyList())
    val movimientos: StateFlow<List<Movimiento>> = _movimientos.asStateFlow()
    val categorias: List<String> = DatosDePrueba.categorias
    val usuarios: List<Usuario> = DatosDePrueba.usuarios

    // Solo el ViewModel valida la solicitud antes de llamar al repositorio (A-10 §3).
    fun agregarProducto(producto: Producto) {
        _productos.value = _productos.value + producto
    }
}
