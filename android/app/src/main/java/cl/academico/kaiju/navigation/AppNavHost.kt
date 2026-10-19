package cl.academico.kaiju.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
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
import cl.academico.kaiju.model.TipoMovimiento
import cl.academico.kaiju.repository.DatosDePrueba
import cl.academico.kaiju.ui.components.etiqueta
import cl.academico.kaiju.ui.recursos.NotificacionesStock
import cl.academico.kaiju.ui.screen.*
import cl.academico.kaiju.ui.theme.KaijuTheme
import cl.academico.kaiju.viewmodel.InventarioViewModel
import cl.academico.kaiju.viewmodel.InventarioViewModelFactory
import cl.academico.kaiju.viewmodel.SesionViewModel

/**
 * NavHost, menú inferior y ViewModels compartidos por las seis pantallas (Guía 10 sección 3).
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
            val codigo = it.codigoParaEntrada
            // Un producto nuevo nace con 0 unidades: se ofrece ir directo a registrar su entrada.
            val resultado = snackbar.showSnackbar(it.mensaje, actionLabel = codigo?.let { "Registrar entrada" },
                duration = if (codigo != null) SnackbarDuration.Long else SnackbarDuration.Short)
            inventario.confirmacionMostrada()
            if (resultado == SnackbarResult.ActionPerformed && codigo != null) {
                inventario.nuevoMovimiento(codigo, TipoMovimiento.ENTRADA)
                nav.navigate(Rutas.Movimiento.ruta) { launchSingleTop = true }
            }
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
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
                    title = {
                        Column {
                            Text("KAIJU", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                            Text("${usuario.nombre} · ${usuario.rol.etiqueta()}", style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant)
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
            // Animación con propósito: al cambiar de pantalla, la nueva entra deslizándose y aparece suavemente,
            // así se entiende hacia dónde se navega (avanzar entra por la derecha; volver, por la izquierda).
            NavHost(
                navController = nav, startDestination = Rutas.Login.ruta, modifier = Modifier.padding(padding),
                enterTransition = { fadeIn(tween(250)) + slideInHorizontally(tween(250)) { it / 10 } },
                exitTransition = { fadeOut(tween(200)) },
                popEnterTransition = { fadeIn(tween(250)) + slideInHorizontally(tween(250)) { -it / 10 } },
                popExitTransition = { fadeOut(tween(200)) },
            ) {
                composable(Rutas.Login.ruta) {
                    PantallaLogin(sesion.usuarios, estadoSesion, sesion::seleccionar) {
                        if (sesion.ingresar()) nav.navigate(Rutas.Catalogo.ruta) { popUpTo(Rutas.Login.ruta) { inclusive = true } }
                    }
                }
                composable(Rutas.Catalogo.ruta) {
                    PantallaCatalogo(
                        productos = catalogo, total = productos.size, enAlerta = alertas.size, filtro = filtro,
                        categorias = inventario.categorias,
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
                        formularioMovimiento, productos, inventario.tiposPermitidos(rol), inventario::estaEnAlerta,
                        EventosMovimiento(
                            producto = inventario::seleccionarProductoMovimiento, tipo = inventario::seleccionarTipoMovimiento,
                            cantidad = inventario::actualizarCantidad, motivo = inventario::actualizarMotivo,
                            codigoLeido = inventario::codigoLeido, lectorNoDisponible = inventario::lectorNoDisponible,
                            registrar = { inventario.registrarMovimiento(usuario) },
                            confirmarAjuste = { inventario.confirmarAjuste(usuario) },
                            cancelarAjuste = inventario::cancelarAjuste,
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

/** Catálogo, Movimiento y Alertas (diagrama  de la Guía 10). Alertas solo para inventario y administrador (RF-09). */
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
                    // Cada pestaña parte limpia desde el Catálogo: no se guardan ni restauran las pantallas
                    // abiertas encima (Detalle, Movimiento), así "Catálogo" siempre vuelve al catálogo.
                    nav.navigate(destino.ruta) {
                        popUpTo(Rutas.Catalogo.ruta)
                        launchSingleTop = true
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
