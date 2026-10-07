package cl.academico.kaiju.navigation

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import cl.academico.kaiju.repository.InventarioRepository
import cl.academico.kaiju.viewmodel.*
import cl.academico.kaiju.ui.screen.*
import cl.academico.kaiju.ui.theme.KaijuTheme

@Composable
fun AppNavHost() {
    val inventario = viewModel { InventarioViewModel(InventarioRepository()) }
    val sesion = viewModel { SesionViewModel(inventario.repository) }
    val estadoSesion by sesion.estado.collectAsStateWithLifecycle()
    val productos by inventario.productos.collectAsStateWithLifecycle()
    val movimientos by inventario.movimientos.collectAsStateWithLifecycle()
    val formulario by inventario.formulario.collectAsStateWithLifecycle()
    val nav = rememberNavController()
    val entrada by nav.currentBackStackEntryAsState()
    val ruta = entrada?.destination?.route
    val rol = estadoSesion.activo?.rol
    val destinos = listOf(Rutas.Catalogo to "Catálogo", Rutas.Movimiento to "Movimiento", Rutas.Alertas to "Alertas")
    KaijuTheme {
        Scaffold(bottomBar = {
            if (estadoSesion.activo != null) NavigationBar {
                destinos.forEach { (destino, etiqueta) ->
                    // RF-09 limita la pantalla de alertas a los dos perfiles indicados.
                    if (destino != Rutas.Alertas || inventario.puedeVerAlertas(rol)) {
                        NavigationBarItem(selected = ruta == destino.ruta,
                            onClick = { nav.navigate(destino.ruta) {
                                popUpTo(Rutas.Catalogo.ruta) { saveState = true }
                                launchSingleTop = true; restoreState = true
                            } }, icon = { Icon(when (destino) {
                                Rutas.Movimiento -> Icons.Default.Edit
                                Rutas.Alertas -> Icons.Default.Warning
                                else -> Icons.AutoMirrored.Filled.List
                            }, contentDescription = null) }, label = { Text(etiqueta) })
                    }
                }
            }
        }) { padding ->
            NavHost(navController = nav, startDestination = Rutas.Login.ruta, modifier = Modifier.padding(padding)) {
                composable(Rutas.Login.ruta) {
                    PantallaLogin(sesion.usuarios, estadoSesion, sesion::seleccionar) {
                        if (sesion.ingresar()) nav.navigate(Rutas.Catalogo.ruta) { popUpTo(Rutas.Login.ruta) { inclusive = true } }
                    }
                }
                composable(Rutas.Catalogo.ruta) {
                    PantallaCatalogo(productos, inventario::estaEnAlerta, inventario.puedeCrear(rol),
                        abrir = { nav.navigate("detalle/${Uri.encode(it)}") },
                        crear = { inventario.nuevoFormulario(); nav.navigate(Rutas.Formulario.ruta) })
                }
                composable(Rutas.Detalle.ruta) { destino ->
                    val codigo = destino.arguments?.getString("codigo")
                    val producto = productos.find { it.codigo == codigo }
                    PantallaDetalleProducto(producto, movimientos.filter { it.codigoProducto == codigo }.sortedByDescending { it.fecha },
                        producto?.let(inventario::estaEnAlerta) ?: false,
                        registrar = { nav.navigate(Rutas.Movimiento.ruta) }, volver = { nav.popBackStack() })
                }
                composable(Rutas.Formulario.ruta) {
                    if (inventario.puedeCrear(rol)) PantallaFormularioProducto(formulario, inventario.categorias,
                        inventario::cambiar, guardar = { if (inventario.guardarProducto(rol)) nav.popBackStack() },
                        volver = { nav.popBackStack() })
                    else Text("Acceso reservado al administrador.", Modifier.padding(20.dp))
                }
                composable(Rutas.Movimiento.ruta) { PantallaMovimiento() }
                composable(Rutas.Alertas.ruta) {
                    if (inventario.puedeVerAlertas(rol)) PantallaAlertas(productos.filter(inventario::estaEnAlerta)) {
                        nav.navigate("detalle/${Uri.encode(it)}")
                    } else Text("Este perfil no tiene acceso a las alertas.", Modifier.padding(20.dp))
                }
            }
        }
    }
}
