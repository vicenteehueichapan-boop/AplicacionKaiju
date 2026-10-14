package cl.academico.kaiju.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import cl.academico.kaiju.model.Movimiento
import cl.academico.kaiju.model.TipoMovimiento
import cl.academico.kaiju.repository.DatosDePrueba
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/** Room en el teléfono: carga inicial única y movimiento + stock en una sola transacción (RF-10, A-10 §4). */
@RunWith(AndroidJUnit4::class)
class InventarioDaoTest {
    private lateinit var base: BaseDeDatosKaiju
    private lateinit var dao: InventarioDao
    private val usuario = DatosDePrueba.usuarios.first()

    @Before fun abrir() {
        val contexto = InstrumentationRegistry.getInstrumentation().targetContext
        base = Room.inMemoryDatabaseBuilder(contexto, BaseDeDatosKaiju::class.java).build()
        dao = base.inventarioDao()
    }

    @After fun cerrar() = base.close()

    private fun movimiento(id: String, cantidad: Int) =
        Movimiento(id, "DEMO-001", TipoMovimiento.ENTRADA, cantidad, null, 1L, usuario)

    @Test fun losDatosDePruebaSeCarganUnaSolaVez() = runTest {
        dao.cargarSiEstaVacio(DatosDePrueba.productos)
        dao.registrarMovimiento(movimiento("m1", 3), stockAnterior = 12, stockResultante = 15)
        dao.cargarSiEstaVacio(DatosDePrueba.productos)
        val productos = dao.productos().first()
        assertEquals(10, productos.size)
        assertEquals(15, productos.single { it.codigo == "DEMO-001" }.stockActual)
    }

    @Test fun movimientoYStockSeGuardanJuntos() = runTest {
        dao.cargarSiEstaVacio(DatosDePrueba.productos)
        assertTrue(dao.registrarMovimiento(movimiento("m1", 3), stockAnterior = 12, stockResultante = 15))
        assertEquals(listOf("m1"), dao.movimientos().first().map { it.id })
        assertEquals(usuario, dao.movimientos().first().single().usuario)
    }

    @Test fun siElStockCambioNoSeGuardaNada() = runTest {
        dao.cargarSiEstaVacio(DatosDePrueba.productos)
        assertFalse(dao.registrarMovimiento(movimiento("m1", 3), stockAnterior = 99, stockResultante = 102))
        assertTrue(dao.movimientos().first().isEmpty())
        assertEquals(12, dao.productos().first().single { it.codigo == "DEMO-001" }.stockActual)
    }
}
