package cl.academico.kaiju.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.academico.kaiju.model.*
import cl.academico.kaiju.repository.InventarioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

/** Búsqueda y filtro del catálogo (RF-03). */
data class FiltroCatalogo(val texto: String = "", val categoria: String? = null)

/** Mensaje después de guardar. Si trae [codigoParaEntrada], la pantalla ofrece registrar su entrada inicial. */
data class Confirmacion(val mensaje: String, val codigoParaEntrada: String? = null)

/**
 * Catálogo, formularios y reglas RN del inventario. Todas las reglas se revisan aquí,
 * antes de llamar al repositorio (Guía 10 sección 3); las pantallas solo muestran el estado.
 * Stock inicial y ajuste: el producto nace con 0 unidades y el ajuste fija la cantidad contada (ver README).
 */
class InventarioViewModel(
    private val repository: InventarioRepository,
    private val reloj: () -> Long = System::currentTimeMillis,
    private val nuevoId: () -> String = { UUID.randomUUID().toString() },
) : ViewModel() {

    val categorias: List<String> = repository.categorias

    val productos: StateFlow<List<Producto>> =
        repository.productos.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
    private val movimientos: StateFlow<List<Movimiento>> =
        repository.movimientos.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private val _filtro = MutableStateFlow(FiltroCatalogo())
    val filtro: StateFlow<FiltroCatalogo> = _filtro.asStateFlow()

    /** RF-02 y RF-03: productos que cumplen la búsqueda y la categoría elegida. */
    val catalogo: StateFlow<List<Producto>> = combine(productos, _filtro) { lista, filtro ->
        lista.filter { coincide(it, filtro) }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    /** RF-09: productos en alerta según RN-06. */
    val alertas: StateFlow<List<Producto>> = productos.map { lista -> lista.filter(::estaEnAlerta) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private val _formulario = MutableStateFlow(FormularioProductoEstado())
    val formulario: StateFlow<FormularioProductoEstado> = _formulario.asStateFlow()

    private val _formularioMovimiento = MutableStateFlow(FormularioMovimientoEstado())
    val formularioMovimiento: StateFlow<FormularioMovimientoEstado> = _formularioMovimiento.asStateFlow()

    // Mensaje breve que la pantalla muestra en un Snackbar después de guardar.
    private val _confirmacion = MutableStateFlow<Confirmacion?>(null)
    val confirmacion: StateFlow<Confirmacion?> = _confirmacion.asStateFlow()

    // Producto que acaba de quedar en alerta; la interfaz decide si puede notificarlo.
    private val _avisoStockBajo = MutableStateFlow<Producto?>(null)
    val avisoStockBajo: StateFlow<Producto?> = _avisoStockBajo.asStateFlow()

    // ── Reglas y permisos de Guía 10 sección 1 ──

    /** RN-06: alerta cuando lo disponible es igual o menor al mínimo. */
    fun estaEnAlerta(producto: Producto): Boolean = producto.stockActual <= producto.stockMinimo

    /** RF-05: solo el administrador crea y edita productos. */
    fun puedeGestionarProductos(rol: Rol?) = rol == Rol.ADMINISTRADOR

    /** RF-09: alertas para encargado de inventario y administrador. */
    fun puedeVerAlertas(rol: Rol?) = rol == Rol.ADMINISTRADOR || rol == Rol.ENCARGADO_INVENTARIO

    /**
     * Tipos que la tabla de Guía 10 sección 1 asigna a cada perfil: entrada (RF-06) y ajuste (RF-08)
     * para inventario y administrador; salida por venta para el vendedor y salidas para
     * inventario (RF-07). El administrador no figura en RF-07, por eso no registra salidas.
     */
    fun tiposPermitidos(rol: Rol?): List<TipoMovimiento> = when (rol) {
        Rol.ADMINISTRADOR -> listOf(TipoMovimiento.ENTRADA, TipoMovimiento.AJUSTE)
        Rol.VENDEDOR -> listOf(TipoMovimiento.SALIDA_VENTA)
        Rol.ENCARGADO_INVENTARIO -> TipoMovimiento.entries
        null -> emptyList()
    }

    fun producto(codigo: String?): Producto? = productos.value.find { it.codigo == codigo }

    // ── Catálogo (RF-03) ──

    fun actualizarBusqueda(texto: String) = _filtro.update { it.copy(texto = texto) }

    /** `null` muestra todas las categorías. */
    fun filtrarPorCategoria(categoria: String?) = _filtro.update { it.copy(categoria = categoria) }

    // Decisión técnica: coincidencia parcial y sin distinguir mayúsculas, en nombre o código.
    private fun coincide(producto: Producto, filtro: FiltroCatalogo): Boolean {
        val texto = filtro.texto.trim()
        val porTexto = texto.isEmpty() ||
            producto.nombre.contains(texto, ignoreCase = true) ||
            producto.codigo.contains(texto, ignoreCase = true)
        return porTexto && (filtro.categoria == null || producto.categoria == filtro.categoria)
    }

    /** RF-04: historial del producto, del más reciente al más antiguo. */
    fun historial(codigo: String): Flow<List<Movimiento>> = movimientos.map { lista ->
        // Invertir primero deja delante al último registrado cuando dos fechas coinciden.
        lista.filter { it.codigoProducto == codigo }.asReversed().sortedByDescending { it.fecha }
    }

    // ── Formulario de producto (RF-05) ──

    fun nuevoFormulario() {
        _formulario.value = FormularioProductoEstado()
    }

    fun editarProducto(codigo: String) {
        val producto = producto(codigo) ?: return
        _formulario.value = FormularioProductoEstado(
            codigoEditado = producto.codigo, codigo = producto.codigo, nombre = producto.nombre,
            descripcion = producto.descripcion, categoria = producto.categoria, tipo = producto.tipo,
            precio = producto.precio.toString(), stockMinimo = producto.stockMinimo.toString(),
            detalle = producto.detalle.orEmpty(),
        )
    }

    // Cada campo llama a una función: la pantalla avisa y el ViewModel decide (Guía 09).
    fun actualizarCodigo(valor: String) = _formulario.update {
        if (it.esEdicion) it else it.copy(codigo = valor, errores = it.errores.copy(codigo = null), mensaje = null)
    }
    fun actualizarNombre(valor: String) =
        _formulario.update { it.copy(nombre = valor, errores = it.errores.copy(nombre = null), mensaje = null) }
    fun seleccionarCategoria(valor: String) =
        _formulario.update { it.copy(categoria = valor, errores = it.errores.copy(categoria = null), mensaje = null) }
    fun actualizarPrecio(valor: String) =
        _formulario.update { it.copy(precio = valor, errores = it.errores.copy(precio = null), mensaje = null) }
    fun actualizarStockMinimo(valor: String) =
        _formulario.update { it.copy(stockMinimo = valor, errores = it.errores.copy(stockMinimo = null), mensaje = null) }
    fun actualizarDescripcion(valor: String) = _formulario.update { it.copy(descripcion = valor, mensaje = null) }
    fun actualizarTipo(valor: String) = _formulario.update { it.copy(tipo = valor, mensaje = null) }
    fun actualizarDetalle(valor: String) = _formulario.update { it.copy(detalle = valor, mensaje = null) }

    /** Guía 10 sección 5, RN-02 y RN-03. */
    fun validarProducto(estado: FormularioProductoEstado): FormularioProductoErrores {
        val codigo = estado.codigo.trim()
        val precio = estado.precio.trim().toIntOrNull()
        val minimo = estado.stockMinimo.trim().toIntOrNull()
        return FormularioProductoErrores(
            codigo = when {
                codigo.isEmpty() -> "Escribe el código."
                !estado.esEdicion && productos.value.any { it.codigo == codigo } -> "Este código ya existe."
                else -> null
            },
            nombre = if (estado.nombre.trim().length < 3) "Escribe al menos 3 caracteres." else null,
            categoria = if (estado.categoria !in categorias) "Elige una categoría." else null,
            precio = if (precio == null || precio <= 0) "Ingresa un entero mayor que cero." else null,
            stockMinimo = if (minimo == null || minimo < 0) "Ingresa un entero mayor o igual a cero." else null,
        )
    }

    /** Devuelve `true` si el producto quedó guardado. */
    fun guardarProducto(rol: Rol?): Boolean {
        val estado = _formulario.value
        if (!puedeGestionarProductos(rol)) {
            _formulario.update { it.copy(mensaje = "Solo el administrador puede crear o editar productos.") }
            return false
        }
        val errores = validarProducto(estado)
        _formulario.update { it.copy(errores = errores, mensaje = null) }
        if (errores.hayErrores) return false

        val datos = Producto(
            codigo = estado.codigo.trim(), nombre = estado.nombre.trim(), descripcion = estado.descripcion.trim(),
            categoria = estado.categoria, tipo = estado.tipo.trim(), precio = estado.precio.trim().toInt(),
            stockActual = 0, stockMinimo = estado.stockMinimo.trim().toInt(), detalle = estado.detalle.trim().ifBlank { null },
        )
        if (estado.esEdicion) {
            viewModelScope.launch { repository.actualizarDatosProducto(datos) }
            _confirmacion.value = Confirmacion("Producto actualizado.")
        } else {
            // Como en SAP u Odoo, la ficha nace sin unidades: las existencias entran con un movimiento
            // (Entrada), así cada unidad queda trazada (RN-05).
            viewModelScope.launch { repository.agregarProducto(datos) }
            _confirmacion.value = Confirmacion("Producto creado con 0 unidades.", codigoParaEntrada = datos.codigo)
        }
        nuevoFormulario()
        return true
    }

    // ── Formulario de movimiento (RF-06, RF-07, RF-08) ──

    /** Abre el formulario, con el producto (y el tipo) ya elegidos si se viene desde otra pantalla. */
    fun nuevoMovimiento(codigoProducto: String? = null, tipo: TipoMovimiento? = null) {
        _formularioMovimiento.value = FormularioMovimientoEstado(codigoProducto = codigoProducto, tipo = tipo)
    }

    fun seleccionarProductoMovimiento(codigo: String) = _formularioMovimiento.update {
        it.copy(codigoProducto = codigo, errores = it.errores.copy(producto = null, cantidad = null), mensaje = null)
    }
    fun seleccionarTipoMovimiento(tipo: TipoMovimiento) = _formularioMovimiento.update {
        it.copy(tipo = tipo, errores = it.errores.copy(tipo = null, cantidad = null, motivo = null), mensaje = null)
    }
    fun actualizarCantidad(valor: String) = _formularioMovimiento.update {
        it.copy(cantidad = valor, errores = it.errores.copy(cantidad = null), mensaje = null)
    }
    fun actualizarMotivo(valor: String) = _formularioMovimiento.update {
        it.copy(motivo = valor, errores = it.errores.copy(motivo = null), mensaje = null)
    }

    /** Resultado del lector de códigos: el ViewModel busca el producto y valida (Guía 11 sección 4). */
    fun codigoLeido(codigo: String) {
        val limpio = codigo.trim()
        if (producto(limpio) != null) seleccionarProductoMovimiento(limpio)
        else _formularioMovimiento.update {
            it.copy(errores = it.errores.copy(producto = "No hay un producto con el código $limpio."))
        }
    }

    /** Alternativa cuando el lector no puede usarse: elegir el producto de la lista. */
    fun lectorNoDisponible() = _formularioMovimiento.update {
        it.copy(mensaje = "No se pudo usar el lector de códigos. Elige el producto de la lista.")
    }

    /** Guía 10 sección 5, RN-01 y RN-04. */
    fun validarMovimiento(estado: FormularioMovimientoEstado, rol: Rol?): FormularioMovimientoErrores {
        val producto = producto(estado.codigoProducto)
        val cantidad = estado.cantidad.trim().toIntOrNull()
        return FormularioMovimientoErrores(
            producto = if (producto == null) "Elige un producto." else null,
            tipo = when {
                estado.tipo == null -> "Elige el tipo de movimiento."
                estado.tipo !in tiposPermitidos(rol) -> "Tu perfil no puede registrar este tipo de movimiento."
                else -> null
            },
            cantidad = when {
                // Ajuste por conteo físico: se ingresa lo contado; 0 es válido (producto agotado).
                estado.tipo == TipoMovimiento.AJUSTE -> when {
                    cantidad == null || cantidad < 0 -> "Ingresa la cantidad contada: un entero, 0 o más."
                    producto != null && cantidad == producto.stockActual ->
                        "Coincide con lo disponible (${producto.stockActual}): no hay diferencia que ajustar."
                    else -> null
                }
                cantidad == null || cantidad <= 0 -> "Ingresa un entero mayor que cero."
                estado.tipo?.esSalida == true && producto != null && cantidad > producto.stockActual ->
                    "La salida supera lo disponible (${producto.stockActual})."
                // Un Int suma hasta 2.147.483.647: más allá, el stock daría la vuelta y quedaría negativo (RN-01).
                estado.tipo == TipoMovimiento.ENTRADA && producto != null && producto.stockActual.toLong() + cantidad > Int.MAX_VALUE ->
                    "La cantidad es demasiado grande."
                else -> null
            },
            motivo = if (estado.tipo == TipoMovimiento.AJUSTE && estado.motivo.isBlank()) "Indica el motivo del ajuste." else null,
        )
    }

    /**
     * Registra el movimiento de [usuario] y actualiza el stock en la misma operación (Guía 10 sección 4).
     * Una entrada o salida se guarda de inmediato y devuelve `true`. Un ajuste válido no se guarda
     * todavía: queda en [FormularioMovimientoEstado.ajustePendiente] para que la persona confirme el cambio.
     */
    fun registrarMovimiento(usuario: Usuario?): Boolean {
        val estado = _formularioMovimiento.value
        val errores = validarMovimiento(estado, usuario?.rol)
        _formularioMovimiento.update { it.copy(errores = errores, mensaje = null) }
        if (errores.hayErrores || usuario == null) return false

        val tipo = estado.tipo ?: return false
        val producto = producto(estado.codigoProducto) ?: return false
        val cantidad = estado.cantidad.trim().toInt()
        if (tipo == TipoMovimiento.AJUSTE) {
            // Como en un conteo físico de Odoo: primero se muestra el cambio y se pide confirmación.
            _formularioMovimiento.update {
                it.copy(ajustePendiente = AjustePendiente(producto, contado = cantidad, motivo = estado.motivo.trim()))
            }
            return false
        }
        val stockResultante = if (tipo == TipoMovimiento.ENTRADA) producto.stockActual + cantidad else producto.stockActual - cantidad
        guardar(producto, tipo, cantidad, estado.motivo.trim().ifBlank { null }, stockResultante, usuario)
        return true
    }

    /** RF-08: aplica el ajuste confirmado. El stock pasa a ser lo contado y se guarda la diferencia (RN-05). */
    fun confirmarAjuste(usuario: Usuario?): Boolean {
        val pendiente = _formularioMovimiento.value.ajustePendiente ?: return false
        if (usuario == null || TipoMovimiento.AJUSTE !in tiposPermitidos(usuario.rol)) return false
        guardar(pendiente.producto, TipoMovimiento.AJUSTE, pendiente.diferencia, pendiente.motivo, pendiente.contado, usuario)
        return true
    }

    fun cancelarAjuste() = _formularioMovimiento.update { it.copy(ajustePendiente = null) }

    private fun guardar(producto: Producto, tipo: TipoMovimiento, cantidad: Int, motivo: String?, stockResultante: Int, usuario: Usuario) {
        val movimiento = Movimiento(
            id = nuevoId(), codigoProducto = producto.codigo, tipo = tipo, cantidad = cantidad,
            motivo = motivo, fecha = reloj(), usuario = usuario,
        )
        // Se conservan producto y tipo para registrar el siguiente movimiento más rápido.
        _formularioMovimiento.value = FormularioMovimientoEstado(codigoProducto = producto.codigo, tipo = tipo)
        viewModelScope.launch {
            if (repository.registrarMovimiento(movimiento, producto.stockActual, stockResultante)) {
                val texto = if (tipo == TipoMovimiento.AJUSTE) "Ajuste registrado" else "Movimiento registrado"
                _confirmacion.value = Confirmacion("$texto. Disponible: $stockResultante.")
                val actualizado = producto.copy(stockActual = stockResultante)
                if (!estaEnAlerta(producto) && estaEnAlerta(actualizado)) _avisoStockBajo.value = actualizado
            } else {
                _formularioMovimiento.update { it.copy(mensaje = "El stock cambió mientras registrabas. Revisa y vuelve a intentar.") }
            }
        }
    }

    fun confirmacionMostrada() {
        _confirmacion.value = null
    }

    fun avisoAtendido() {
        _avisoStockBajo.value = null
    }
}
