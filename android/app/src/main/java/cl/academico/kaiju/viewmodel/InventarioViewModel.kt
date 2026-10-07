package cl.academico.kaiju.viewmodel

import androidx.lifecycle.ViewModel
import cl.academico.kaiju.model.*
import cl.academico.kaiju.repository.InventarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class InventarioViewModel(
    internal val repository: InventarioRepository,
    // G10-02: null significa sin respuesta. No se asigna stock cero por omisión.
    private val stockInicialAcordado: Int? = null,
) : ViewModel() {
    val productos = repository.productos
    val movimientos = repository.movimientos
    val categorias = repository.categorias
    private val _formulario = MutableStateFlow(FormularioProductoEstado())
    val formulario = _formulario.asStateFlow()

    fun estaEnAlerta(producto: Producto): Boolean = producto.stockActual <= producto.stockMinimo
    fun puedeCrear(rol: Rol?) = rol == Rol.ADMINISTRADOR
    fun puedeVerAlertas(rol: Rol?) = rol == Rol.ADMINISTRADOR || rol == Rol.ENCARGADO_INVENTARIO
    fun nuevoFormulario() { _formulario.value = FormularioProductoEstado() }
    fun cambiar(estado: FormularioProductoEstado) {
        _formulario.value = estado.copy(mensaje = null)
    }

    // A-10 §5, RN-02/03. Comparación literal: no normaliza códigos sin acuerdo.
    fun validarProducto(estado: FormularioProductoEstado): FormularioProductoErrores {
        val precio = estado.precio.toIntOrNull()
        val minimo = estado.stockMinimo.toIntOrNull()
        return FormularioProductoErrores(
            codigo = when {
                estado.codigo.isBlank() -> "Escribe el código."
                productos.value.any { it.codigo == estado.codigo } -> "Este código ya existe."
                else -> null
            },
            nombre = if (estado.nombre.isBlank() || estado.nombre.length < 3) "Escribe al menos 3 caracteres." else null,
            categoria = if (estado.categoria !in categorias) "Elige una categoría." else null,
            precio = if (precio == null || precio <= 0) "Ingresa un entero mayor que cero." else null,
            stockMinimo = if (minimo == null || minimo < 0) "Ingresa un entero mayor o igual a cero." else null,
        )
    }

    fun guardarProducto(rol: Rol?): Boolean {
        val actual = _formulario.value
        if (!puedeCrear(rol)) {
            _formulario.value = actual.copy(mensaje = "Solo el administrador puede crear productos.")
            return false
        }
        val errores = validarProducto(actual)
        if (errores.hayErrores) {
            _formulario.value = actual.copy(errores = errores, mensaje = null)
            return false
        }
        val stock = stockInicialAcordado
        if (stock == null) {
            _formulario.value = actual.copy(errores = errores, mensaje = "Datos válidos. No se guardó: falta confirmar el stock inicial de un producto nuevo.")
            return false
        }
        repository.agregarProducto(Producto(actual.codigo, actual.nombre, actual.descripcion,
            actual.categoria, actual.tipo, actual.precio.toInt(), stock, actual.stockMinimo.toInt(),
            actual.detalle.ifBlank { null }))
        nuevoFormulario()
        return true
    }
}
