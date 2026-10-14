package cl.academico.kaiju.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import cl.academico.kaiju.repository.DatosDePrueba
import cl.academico.kaiju.ui.components.etiqueta
import cl.academico.kaiju.ui.recursos.NotificacionesStock
import cl.academico.kaiju.ui.screen.*
import cl.academico.kaiju.ui.theme.KaijuTheme
import cl.academico.kaiju.viewmodel.InventarioViewModel
import cl.academico.kaiju.viewmodel.InventarioViewModelFactory
import cl.academico.kaiju.viewmodel.SesionViewModel

/**
 * NavHost, menú inferior y ViewModels compartidos por las seis pantallas (A-10 §3).
 * Los ViewModels se crean aquí una sola vez y se pasan a cada pantalla: así todas ven los mismos datos.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(
    inventario: InventarioViewModel = viewModel(factory = InventarioViewModelFactory(LocalContext.current)),
    sesion: SesionViewModel = viewModel { SesionViewModel(DatosDePrueba.usuarios) },
) {
    val contexto = LocalContext.current

    val estadoSesion by sesion.estado.collectAsStateWithLifecycle()
    val productos by inventario.productos.collectAsStateWithLifecycle()
    val catalogo by inventario.catalogo.collectAsStateWithLifecycle()
    val filtro by inventario.filtro.collectAsStateWithLifecycle()
    val alertas by inventario.alertas.collectAsStateWithLifecycle()
    val formulario by inventario.formulario.collectAsStateWithLifecycle()
    val formularioMovimiento by inventario.formularioMovimiento.collectAsStateWithLifecycle()
    val confirmacion by inventario.confirmacion.collectAsStateWithLifecycle()
    val avisoStockBajo by inventario.avisoStockBajo.collectAsStateWithLifecycle()

    val usuario = estadoSesion.activo
    val rol = usuario?.rol
    val nav = rememberNavController()
    val ruta = nav.currentBackStackEntryAsState().value?.destination?.route
    val snackbar = remember { SnackbarHostState() }

    // Si Android cerró el proceso, la navegación se restaura pero la sesión no: se vuelve a elegir usuario (RF-01).
    LaunchedEffect(usuario, ruta) {
        if (usuario == null && ruta != null && ruta != Rutas.Login.ruta) irALogin(nav)
    }
    LaunchedEffect(confirmacion) {
        confirmacion?.let {
            snackbar.showSnackbar(it)
            inventario.confirmacionMostrada()
        }
    }
    LaunchedEffect(avisoStockBajo) {
        avisoStockBajo?.let {
            NotificacionesStock.mostrar(contexto, it)
            inventario.avisoAtendido()
        }
    }

    KaijuTheme {
        Scaffold(
            topBar = {
                if (usuario != null) TopAppBar(
                    title = {
                        Column {
                            Text("Kaiju")
                            Text("${usuario.nombre} · ${usuario.rol.etiqueta()}", style = MaterialTheme.typography.bodySmall)
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            sesion.cerrarSesion()
                            inventario.nuevoMovimiento()
                        }) { Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Cambiar usuario") }
                    },
                )
            },
            bottomBar = { if (usuario != null) MenuInferior(nav, ruta, inventario.puedeVerAlertas(rol)) },
            snackbarHost = { SnackbarHost(snackbar) },
        ) { padding ->
            NavHost(navController = nav, startDestination = Rutas.Login.ruta, modifier = Modifier.padding(padding)) {
                composable(Rutas.Login.ruta) {
                    PantallaLogin(sesion.usuarios, estadoSesion, sesion::seleccionar) {
                        if (sesion.ingresar()) nav.navigate(Rutas.Catalogo.ruta) { popUpTo(Rutas.Login.ruta) { inclusive = true } }
                    }
                }
                composable(Rutas.Catalogo.ruta) {
                    PantallaCatalogo(
                        productos = catalogo, total = productos.size, filtro = filtro, categorias = inventario.categorias,
                        alerta = inventario::estaEnAlerta, puedeCrear = inventario.puedeGestionarProductos(rol),
                        buscar = inventario::actualizarBusqueda, filtrar = inventario::filtrarPorCategoria,
                        abrir = { nav.navigate(Rutas.Detalle.crear(it)) },
                        crear = {
                            inventario.nuevoFormulario()
                            nav.navigate(Rutas.Formulario.ruta)
                        },
                    )
                }
                composable(Rutas.Detalle.ruta) { destino ->
                    val codigo = destino.arguments?.getString("codigo").orEmpty()
                    val producto = productos.find { it.codigo == codigo }
                    val historial by remember(codigo) { inventario.historial(codigo) }.collectAsStateWithLifecycle(emptyList())
                    PantallaDetalleProducto(
                        producto = producto, historial = historial,
                        enAlerta = producto?.let(inventario::estaEnAlerta) ?: false,
                        puedeEditar = inventario.puedeGestionarProductos(rol),
                        registrar = {
                            inventario.nuevoMovimiento(codigo)
                            nav.navigate(Rutas.Movimiento.ruta) { launchSingleTop = true }
                        },
                        editar = {
                            inventario.editarProducto(codigo)
                            nav.navigate(Rutas.Formulario.ruta)
                        },
                        volver = { nav.popBackStack() },
                    )
                }
                composable(Rutas.Formulario.ruta) {
                    if (inventario.puedeGestionarProductos(rol)) PantallaFormularioProducto(
                        formulario, inventario.categorias,
                        EventosFormularioProducto(
                            codigo = inventario::actualizarCodigo, nombre = inventario::actualizarNombre,
                            categoria = inventario::seleccionarCategoria, precio = inventario::actualizarPrecio,
                            stockMinimo = inventario::actualizarStockMinimo, descripcion = inventario::actualizarDescripcion,
                            tipo = inventario::actualizarTipo, detalle = inventario::actualizarDetalle,
                            guardar = { if (inventario.guardarProducto(rol)) nav.popBackStack() },
                            volver = { nav.popBackStack() },
                        ),
                    )
                    else Text("Acceso reservado al administrador.", Modifier.padding(20.dp))
                }
                composable(Rutas.Movimiento.ruta) {
                    PantallaMovimiento(
                        formularioMovimiento, productos, inventario.tiposPermitidos(rol),
                        EventosMovimiento(
                            producto = inventario::seleccionarProductoMovimiento, tipo = inventario::seleccionarTipoMovimiento,
                            cantidad = inventario::actualizarCantidad, motivo = inventario::actualizarMotivo,
                            codigoLeido = inventario::codigoLeido, lectorNoDisponible = inventario::lectorNoDisponible,
                            registrar = { inventario.registrarMovimiento(usuario) },
                        ),
                    )
                }
                composable(Rutas.Alertas.ruta) {
                    if (inventario.puedeVerAlertas(rol)) PantallaAlertas(alertas) { nav.navigate(Rutas.Detalle.crear(it)) }
                    else Text("Este perfil no tiene acceso a las alertas.", Modifier.padding(20.dp))
                }
            }
        }
    }
}

/** Catálogo, Movimiento y Alertas (A-10-D2). Alertas solo para inventario y administrador (RF-09). */
@Composable
private fun MenuInferior(nav: NavHostController, ruta: String?, verAlertas: Boolean) {
    val destinos = buildList {
        add(Triple(Rutas.Catalogo, "Catálogo", Icons.AutoMirrored.Filled.List))
        add(Triple(Rutas.Movimiento, "Movimiento", Icons.Default.Refresh))
        if (verAlertas) add(Triple(Rutas.Alertas, "Alertas", Icons.Default.Warning))
    }
    NavigationBar {
        destinos.forEach { (destino, etiqueta, icono) ->
            NavigationBarItem(
                selected = ruta == destino.ruta,
                onClick = {
                    nav.navigate(destino.ruta) {
                        popUpTo(Rutas.Catalogo.ruta) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(icono, contentDescription = null) },
                label = { Text(etiqueta) },
            )
        }
    }
}

private fun irALogin(nav: NavHostController) {
    nav.navigate(Rutas.Login.ruta) {
        popUpTo(nav.graph.id) { inclusive = true }
        launchSingleTop = true
    }
}
