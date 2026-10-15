package cl.academico.kaiju.viewmodel

import cl.academico.kaiju.model.*
import cl.academico.kaiju.repository.DatosDePrueba
import cl.academico.kaiju.repository.InventarioRepository
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

/** Catálogo, alertas y formulario de producto (RF-02, RF-03, RF-05, RF-09; RN-02, RN-03, RN-06). */
class InventarioViewModelTest {
    @get:Rule val dispatcher = ReglaDispatcherPrincipal()

    private val dao = FakeInventarioDao()
    private val repository = InventarioRepository(dao)
    // Se crea al usarlo, cuando la regla ya instaló el dispatcher de prueba.
    private val vm by lazy { InventarioViewModel(repository) }
    private fun valido() = FormularioProductoEstado(codigo = "NUEVO", nombre = "Muestra",
        categoria = DatosDePrueba.categorias.first(), precio = "100", stockMinimo = "0")

    // ── Validación de producto (Guía 10 sección 5) ──

    @Test fun formularioVacioDaErroresPorCampo() {
        val errores = vm.validarProducto(FormularioProductoEstado())
        assertNotNull(errores.codigo); assertNotNull(errores.nombre)
        assertNotNull(errores.categoria); assertNotNull(errores.precio); assertNotNull(errores.stockMinimo)
    }
    @Test fun codigoRepetidoSeRechazaAunqueTengaEspacios() {
        assertNotNull(vm.validarProducto(valido().copy(codigo = "DEMO-001")).codigo)
        assertNotNull(vm.validarProducto(valido().copy(codigo = "  DEMO-001 ")).codigo)
    }
    @Test fun nombreDeTresCaracteresSinContarEspacios() {
        assertNotNull(vm.validarProducto(valido().copy(nombre = " ab ")).nombre)
        assertNull(vm.validarProducto(valido().copy(nombre = "abc")).nombre)
    }
    @Test fun categoriaDebePertenecerALaLista() {
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

    // ── Alta y edición (RF-05) ──

    private fun cargar(estado: FormularioProductoEstado) {
        vm.actualizarCodigo(estado.codigo); vm.actualizarNombre(estado.nombre)
        vm.seleccionarCategoria(estado.categoria); vm.actualizarPrecio(estado.precio)
        vm.actualizarStockMinimo(estado.stockMinimo)
    }

    @Test fun altaValidaApareceEnElCatalogoConCeroUnidades() {
        cargar(valido().copy(codigo = " NUEVO "))
        assertTrue(vm.guardarProducto(Rol.ADMINISTRADOR))
        val creado = vm.catalogo.value.single { it.codigo == "NUEVO" }
        assertEquals(0, creado.stockActual)
        assertEquals(11, vm.catalogo.value.size)
        assertNotNull("El código recién creado ya no se acepta", vm.validarProducto(valido()).codigo)
    }
    @Test fun altaOfreceRegistrarLaEntradaInicial() {
        cargar(valido())
        vm.guardarProducto(Rol.ADMINISTRADOR)
        assertEquals("NUEVO", vm.confirmacion.value?.codigoParaEntrada)
        assertEquals(FormularioProductoEstado(), vm.formulario.value)
    }
    @Test fun soloElAdministradorCreaOEdita() {
        cargar(valido())
        assertFalse(vm.guardarProducto(Rol.VENDEDOR))
        assertFalse(vm.guardarProducto(Rol.ENCARGADO_INVENTARIO))
        assertEquals(DatosDePrueba.productos, dao.productosGuardados)
    }
    @Test fun datosInvalidosNuncaLleganAlRepositorio() {
        assertFalse(vm.guardarProducto(Rol.ADMINISTRADOR))
        assertTrue(vm.formulario.value.errores.hayErrores)
        assertEquals(DatosDePrueba.productos, dao.productosGuardados)
    }
    @Test fun editarCambiaDatosPeroConservaCodigoYStock() {
        val original = DatosDePrueba.productos.first()
        vm.editarProducto(original.codigo)
        vm.actualizarCodigo("OTRO")
        vm.actualizarNombre("Cuaderno editado"); vm.actualizarPrecio("1700")
        assertTrue(vm.guardarProducto(Rol.ADMINISTRADOR))
        val editado = dao.productosGuardados.single { it.codigo == original.codigo }
        assertEquals("Cuaderno editado", editado.nombre)
        assertEquals(1700, editado.precio)
        assertEquals(original.stockActual, editado.stockActual)
        assertTrue(dao.productosGuardados.none { it.codigo == "OTRO" })
    }
    @Test fun editarNoMarcaSuPropioCodigoComoRepetido() {
        vm.editarProducto("DEMO-002")
        assertFalse(vm.validarProducto(vm.formulario.value).hayErrores)
    }
    @Test fun corregirUnCampoBorraSoloSuError() {
        vm.guardarProducto(Rol.ADMINISTRADOR)
        vm.actualizarNombre("Muestra")
        val errores = vm.formulario.value.errores
        assertNull(errores.nombre)
        assertNotNull(errores.codigo)
    }

    // ── Catálogo, búsqueda y alertas (RF-02, RF-03, RF-09) ──

    @Test fun catalogoMuestraLosDiezProductosDePrueba() {
        assertEquals(10, vm.catalogo.value.size)
    }
    @Test fun buscaPorNombreOCodigoParcialSinDistinguirMayusculas() {
        vm.actualizarBusqueda("LÁPIZ")
        assertEquals(listOf("DEMO-002"), vm.catalogo.value.map { it.codigo })
        vm.actualizarBusqueda("demo-01")
        assertEquals(listOf("DEMO-010"), vm.catalogo.value.map { it.codigo })
    }
    @Test fun filtroPorCategoriaSeCombinaConLaBusqueda() {
        vm.filtrarPorCategoria(DatosDePrueba.categorias[1])
        assertEquals(3, vm.catalogo.value.size)
        vm.actualizarBusqueda("caja")
        assertEquals(listOf("DEMO-005"), vm.catalogo.value.map { it.codigo })
        vm.filtrarPorCategoria(null); vm.actualizarBusqueda("")
        assertEquals(10, vm.catalogo.value.size)
    }
    @Test fun igualdadConMinimoEstaEnAlerta() {
        val p = DatosDePrueba.productos.first()
        assertTrue(vm.estaEnAlerta(p.copy(stockActual = 4, stockMinimo = 4)))
        assertTrue(vm.estaEnAlerta(p.copy(stockActual = 3, stockMinimo = 4)))
        assertFalse(vm.estaEnAlerta(p.copy(stockActual = 5, stockMinimo = 4)))
    }
    @Test fun listaDeAlertasUsaLaMismaRegla() {
        assertEquals(listOf("DEMO-002", "DEMO-004", "DEMO-006", "DEMO-009"), vm.alertas.value.map { it.codigo })
    }
    @Test fun permisosDeAlertasDeLaBaseAcademica() {
        assertTrue(vm.puedeVerAlertas(Rol.ADMINISTRADOR))
        assertTrue(vm.puedeVerAlertas(Rol.ENCARGADO_INVENTARIO))
        assertFalse(vm.puedeVerAlertas(Rol.VENDEDOR))
        assertFalse(vm.puedeVerAlertas(null))
    }
}
