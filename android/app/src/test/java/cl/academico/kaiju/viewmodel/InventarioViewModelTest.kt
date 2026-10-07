package cl.academico.kaiju.viewmodel

import cl.academico.kaiju.model.*
import cl.academico.kaiju.repository.*
import org.junit.Assert.*
import org.junit.Test

class InventarioViewModelTest {
    private val repository = InventarioRepository()
    private val vm = InventarioViewModel(repository)
    private fun valido() = FormularioProductoEstado(codigo = "NUEVO", nombre = "Muestra",
        categoria = DatosDePrueba.categorias.first(), precio = "100", stockMinimo = "0")

    @Test fun formularioVacioDaErroresPorCampo() {
        val errores = vm.validarProducto(FormularioProductoEstado())
        assertNotNull(errores.codigo); assertNotNull(errores.nombre)
        assertNotNull(errores.categoria); assertNotNull(errores.precio); assertNotNull(errores.stockMinimo)
    }
    @Test fun codigoRepetidoSeRechaza() {
        assertNotNull(vm.validarProducto(valido().copy(codigo = repository.productos.value.first().codigo)).codigo)
    }
    @Test fun nombreCortoSeRechaza() { assertNotNull(vm.validarProducto(valido().copy(nombre = "ab")).nombre) }
    @Test fun categoriaDebePertenecerAListaDePrueba() {
        assertNotNull(vm.validarProducto(valido().copy(categoria = "desconocida")).categoria)
    }
    @Test fun precioDebeSerEnteroPositivoSinDesbordamiento() {
        listOf("0", "-1", "1.5", "abc", "2147483648").forEach {
            assertNotNull("Precio $it", vm.validarProducto(valido().copy(precio = it)).precio)
        }
    }
    @Test fun minimoAceptaCeroRechazaNegativosDecimalesYDesbordamiento() {
        assertNull(vm.validarProducto(valido()).stockMinimo)
        listOf("-1", "1.5", "2147483648").forEach {
            assertNotNull(vm.validarProducto(valido().copy(stockMinimo = it)).stockMinimo)
        }
    }
    @Test fun igualdadConMinimoEstaEnAlerta() {
        val p = repository.productos.value.first()
        assertTrue(vm.estaEnAlerta(p.copy(stockActual = 4, stockMinimo = 4)))
        assertTrue(vm.estaEnAlerta(p.copy(stockActual = 3, stockMinimo = 4)))
        assertFalse(vm.estaEnAlerta(p.copy(stockActual = 5, stockMinimo = 4)))
    }
    @Test fun noSeGuardaSinRespuestaDeStockInicial() {
        val antes = repository.productos.value
        vm.cambiar(valido())
        assertFalse(vm.guardarProducto(Rol.ADMINISTRADOR))
        assertEquals(antes, repository.productos.value)
        assertFalse(vm.formulario.value.errores.hayErrores)
        assertTrue(vm.formulario.value.mensaje!!.contains("stock inicial"))
    }
    @Test fun vendedorNoPuedeGuardarAunqueDatosValidos() {
        val antes = repository.productos.value
        vm.cambiar(valido())
        assertFalse(vm.guardarProducto(Rol.VENDEDOR))
        assertEquals(antes, repository.productos.value)
    }
    @Test fun datosInvalidosNuncaLleganAlRepositorio() {
        val antes = repository.productos.value
        assertFalse(vm.guardarProducto(Rol.ADMINISTRADOR))
        assertEquals(antes, repository.productos.value)
    }
    @Test fun altaSoloConStockProporcionadoExplicitamenteEnLaPrueba() {
        // Este 7 es un dato de esta prueba, no una política ni el valor de la app.
        val conRespuesta = InventarioViewModel(repository, stockInicialAcordado = 7)
        conRespuesta.cambiar(valido())
        assertTrue(conRespuesta.guardarProducto(Rol.ADMINISTRADOR))
        assertEquals(7, repository.productos.value.last().stockActual)
    }
    @Test fun permisosDeAlertasDeLaBaseAcademica() {
        assertTrue(vm.puedeVerAlertas(Rol.ADMINISTRADOR))
        assertTrue(vm.puedeVerAlertas(Rol.ENCARGADO_INVENTARIO))
        assertFalse(vm.puedeVerAlertas(Rol.VENDEDOR))
        assertFalse(vm.puedeVerAlertas(null))
    }
}
