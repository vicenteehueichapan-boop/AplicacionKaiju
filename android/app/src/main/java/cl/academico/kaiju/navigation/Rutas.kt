package cl.academico.kaiju.navigation

sealed class Rutas(val ruta: String) {
    data object Login : Rutas("login")
    data object Catalogo : Rutas("catalogo")
    data object Detalle : Rutas("detalle/{codigo}")
    data object Formulario : Rutas("formulario")
    data object Movimiento : Rutas("movimiento")
    data object Alertas : Rutas("alertas")
}
