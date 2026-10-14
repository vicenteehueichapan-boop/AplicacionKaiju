package cl.academico.kaiju.viewmodel

import cl.academico.kaiju.model.*
import cl.academico.kaiju.repository.DatosDePrueba
import cl.academico.kaiju.repository.InventarioRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

/** Movimientos e historial (RF-04, RF-06, RF-07, RF-08; RN-01, RN-04, RN-05). */
class MovimientosViewModelTest {
    @get:Rule val dispatcher = ReglaDispatcherPrincipal()

    private val dao = FakeInventarioDao()
    private val repository = InventarioRepository(dao)
    private var ahora = 1_000L
    private var siguienteId = 0
    private val vm by lazy { InventarioViewModel(repository, reloj = { ahora }, nuevoId = { "mov-${++siguienteId}" }) }

    private val admin = DatosDePrueba.usuarios.single { it.rol == Rol.ADMINISTRADOR }
    private val vendedor = DatosDePrueba.usuarios.single { it.rol == Rol.VENDEDOR }
    private val inventario = DatosDePrueba.usuarios.single { it.rol == Rol.ENCARGADO_INVENTARIO }

    private fun stock(codigo: String) = dao.productosGuardados.single { it.codigo == codigo }.stockActual

    private fun registrar(usuario: Usuario, codigo: String, tipo: TipoMovimiento, cantidad: String, motivo: String = ""): Boolean {
        vm.nuevoMovimiento(codigo)
        vm.seleccionarTipoMovimiento(tipo)
        vm.actualizarCantidad(cantidad)
        vm.actualizarMotivo(motivo)
        return vm.registrarMovimiento(usuario)
    }

    // ── Efecto en stock e historial (A-10 §4, RN-05) ──

    @Test fun entradaSumaStockYGuardaFechaTipoCantidadYAutor() {
        assertTrue(registrar(inventario, "DEMO-001", TipoMovimiento.ENTRADA, "5"))
        assertEquals(17, stock("DEMO-001"))
        val movimiento = dao.movimientosGuardados.single()
        assertEquals(TipoMovimiento.ENTRADA, movimiento.tipo)
        assertEquals(5, movimiento.cantidad)
        assertEquals(1_000L, movimiento.fecha)
        assertEquals(inventario, movimiento.usuario)
    }
    @Test fun ventaIgualALoDisponibleDejaCero() {
        assertTrue(registrar(vendedor, "DEMO-009", TipoMovimiento.SALIDA_VENTA, "5"))
        assertEquals(0, stock("DEMO-009"))
    }
    @Test fun salidaMayorALoDisponibleSeRechazaSinEscribir() {
        assertFalse(registrar(inventario, "DEMO-002", TipoMovimiento.SALIDA_CONSUMO, "4"))
        assertNotNull(vm.formularioMovimiento.value.errores.cantidad)
        assertEquals(3, stock("DEMO-002"))
        assertTrue(dao.movimientosGuardados.isEmpty())
    }
    @Test fun cantidadDebeSerEnteroPositivo() {
        listOf("0", "-2", "1.5", "", "abc").forEach {
            assertFalse("Cantidad '$it'", registrar(inventario, "DEMO-001", TipoMovimiento.ENTRADA, it))
            assertNotNull(vm.formularioMovimiento.value.errores.cantidad)
        }
        assertTrue(dao.movimientosGuardados.isEmpty())
    }
    @Test fun productoYTipoSonObligatorios() {
        vm.nuevoMovimiento()
        vm.actualizarCantidad("1")
        assertFalse(vm.registrarMovimiento(inventario))
        assertNotNull(vm.formularioMovimiento.value.errores.producto)
        assertNotNull(vm.formularioMovimiento.value.errores.tipo)
    }

    // ── Permisos por perfil según la tabla de A-10 §1 ──

    @Test fun tiposPermitidosPorPerfil() {
        assertEquals(listOf(TipoMovimiento.SALIDA_VENTA), vm.tiposPermitidos(Rol.VENDEDOR))
        assertEquals(listOf(TipoMovimiento.ENTRADA, TipoMovimiento.AJUSTE), vm.tiposPermitidos(Rol.ADMINISTRADOR))
        assertEquals(TipoMovimiento.entries, vm.tiposPermitidos(Rol.ENCARGADO_INVENTARIO))
        assertTrue(vm.tiposPermitidos(null).isEmpty())
    }
    @Test fun vendedorNoPuedeRegistrarEntradas() {
        assertFalse(registrar(vendedor, "DEMO-001", TipoMovimiento.ENTRADA, "1"))
        assertNotNull(vm.formularioMovimiento.value.errores.tipo)
        assertEquals(12, stock("DEMO-001"))
    }
    @Test fun sinUsuarioActivoNoSeRegistra() {
        vm.nuevoMovimiento("DEMO-001"); vm.seleccionarTipoMovimiento(TipoMovimiento.ENTRADA); vm.actualizarCantidad("1")
        assertFalse(vm.registrarMovimiento(null))
        assertTrue(dao.movimientosGuardados.isEmpty())
    }

    // ── Ajuste (RF-08, RN-04): validado; su efecto espera G10-01 ──

    @Test fun ajusteSinMotivoSeRechaza() {
        assertFalse(registrar(admin, "DEMO-001", TipoMovimiento.AJUSTE, "2"))
        assertNotNull(vm.formularioMovimiento.value.errores.motivo)
    }
    @Test fun ajusteValidoNoCambiaStockMientrasFalteDefinirSuCalculo() {
        assertFalse(registrar(admin, "DEMO-001", TipoMovimiento.AJUSTE, "2", motivo = "Conteo"))
        assertFalse(vm.formularioMovimiento.value.errores.hayErrores)
        assertEquals(InventarioViewModel.MENSAJE_AJUSTE, vm.formularioMovimiento.value.mensaje)
        assertEquals(12, stock("DEMO-001"))
        assertTrue(dao.movimientosGuardados.isEmpty())
    }

    // ── Historial y avisos ──

    @Test fun historialDelMasRecienteAlMasAntiguoIncluidoEmpateDeFecha() = runTest {
        registrar(inventario, "DEMO-001", TipoMovimiento.ENTRADA, "1")
        ahora = 2_000L
        registrar(inventario, "DEMO-001", TipoMovimiento.ENTRADA, "2")
        registrar(inventario, "DEMO-001", TipoMovimiento.SALIDA_CONSUMO, "3")
        registrar(inventario, "DEMO-003", TipoMovimiento.ENTRADA, "9")
        val historial = vm.historial("DEMO-001").first()
        assertEquals(listOf("mov-3", "mov-2", "mov-1"), historial.map { it.id })
    }
    @Test fun avisaSoloCuandoUnMovimientoHaceEntrarEnAlerta() {
        // DEMO-001: 12 disponibles, mínimo 4. Quedar en 4 entra en alerta (RN-06).
        registrar(vendedor, "DEMO-001", TipoMovimiento.SALIDA_VENTA, "8")
        assertEquals(4, vm.avisoStockBajo.value?.stockActual)
        vm.avisoAtendido()
        // Ya estaba en alerta: una nueva salida no repite el aviso.
        registrar(vendedor, "DEMO-001", TipoMovimiento.SALIDA_VENTA, "1")
        assertNull(vm.avisoStockBajo.value)
    }
    @Test fun registroExitosoConfirmaYConservaProductoYTipo() {
        registrar(inventario, "DEMO-005", TipoMovimiento.ENTRADA, "1", motivo = "Llegada")
        assertNotNull(vm.confirmacion.value)
        val formulario = vm.formularioMovimiento.value
        assertEquals("DEMO-005", formulario.codigoProducto)
        assertEquals(TipoMovimiento.ENTRADA, formulario.tipo)
        assertEquals("", formulario.cantidad)
        assertEquals("", formulario.motivo)
    }

    // ── Lector de códigos: el resultado del recurso lo procesa el ViewModel ──

    @Test fun codigoLeidoExistenteSeleccionaElProducto() {
        vm.nuevoMovimiento()
        vm.codigoLeido(" DEMO-007 ")
        assertEquals("DEMO-007", vm.formularioMovimiento.value.codigoProducto)
    }
    @Test fun codigoLeidoInexistenteMuestraErrorSinCambiarSeleccion() {
        vm.nuevoMovimiento("DEMO-001")
        vm.codigoLeido("XYZ")
        assertEquals("DEMO-001", vm.formularioMovimiento.value.codigoProducto)
        assertNotNull(vm.formularioMovimiento.value.errores.producto)
    }
}
