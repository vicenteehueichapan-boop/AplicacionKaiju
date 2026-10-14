package cl.academico.kaiju.viewmodel

import cl.academico.kaiju.model.Producto
import cl.academico.kaiju.model.TipoMovimiento
import cl.academico.kaiju.repository.DatosDePrueba
import cl.academico.kaiju.repository.InventarioRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

/**
 * RF-10: lo que ya estaba guardado aparece al volver a abrir la app. Un ViewModel nuevo sobre
 * el mismo DAO simula reabrirla, como el test de persistencia del ejemplo de la Agenda.
 */
class PersistenciaViewModelTest {
    @get:Rule val dispatcher = ReglaDispatcherPrincipal()

    @Test fun productosYaGuardadosNoSeReemplazanPorLosDePrueba() {
        val previo = Producto("PREV-1", "Producto guardado antes", "", DatosDePrueba.categorias.first(), "", 100, 3, 1, null)
        val vm = InventarioViewModel(InventarioRepository(FakeInventarioDao(listOf(previo))))
        assertEquals(listOf("PREV-1"), vm.catalogo.value.map { it.codigo })
    }

    @Test fun movimientoRegistradoSigueAlReabrir() = runTest {
        val dao = FakeInventarioDao()
        val antes = InventarioViewModel(InventarioRepository(dao))
        antes.nuevoMovimiento("DEMO-001")
        antes.seleccionarTipoMovimiento(TipoMovimiento.ENTRADA)
        antes.actualizarCantidad("4")
        assertTrue(antes.registrarMovimiento(DatosDePrueba.usuarios.last()))

        val reabierta = InventarioViewModel(InventarioRepository(dao))
        assertEquals(16, reabierta.producto("DEMO-001")?.stockActual)
        assertEquals(10, reabierta.catalogo.value.size)
        assertEquals(listOf(4), reabierta.historial("DEMO-001").first().map { it.cantidad })
    }
}
