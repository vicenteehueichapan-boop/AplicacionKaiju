package cl.academico.kaiju.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import cl.academico.kaiju.model.Rol
import cl.academico.kaiju.model.Usuario
import cl.academico.kaiju.ui.components.MensajeError
import cl.academico.kaiju.ui.components.etiqueta
import cl.academico.kaiju.ui.theme.ColoresKaiju
import cl.academico.kaiju.viewmodel.SesionEstado

/** RF-01: elegir un usuario de prueba. La validación de la selección está en SesionViewModel. */
@Composable
fun PantallaLogin(usuarios: List<Usuario>, estado: SesionEstado, elegir: (String) -> Unit, ingresar: () -> Unit) {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        // Encabezado de marca con degradado gris → negro.
        Column(Modifier.fillMaxWidth().background(ColoresKaiju.degradadoMarca).padding(horizontal = 24.dp, vertical = 40.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("KAIJU", style = MaterialTheme.typography.displaySmall, color = MaterialTheme.colorScheme.onPrimaryContainer)
            Text("Inventario y trazabilidad", style = MaterialTheme.typography.titleMedium)
            Text("Stock al día, movimientos registrados y alertas de reposición.",
                style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("¿Con qué perfil entras?", style = MaterialTheme.typography.titleLarge)
            Text("Usuarios de prueba: todos los datos son ficticios.", style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
            Column(Modifier.selectableGroup(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                usuarios.forEach { usuario -> TarjetaPerfil(usuario, estado.seleccionado?.id == usuario.id) { elegir(usuario.id) } }
            }
            estado.error?.let { MensajeError(it) }
            Button(onClick = ingresar, modifier = Modifier.fillMaxWidth().height(52.dp), shape = MaterialTheme.shapes.medium) {
                Text("Ingresar", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Composable
private fun TarjetaPerfil(usuario: Usuario, seleccionado: Boolean, elegir: () -> Unit) {
    val (icono, descripcion) = when (usuario.rol) {
        Rol.ADMINISTRADOR -> Icons.Default.Person to "Productos, entradas, ajustes y alertas"
        Rol.VENDEDOR -> Icons.Default.ShoppingCart to "Consulta y registra ventas"
        Rol.ENCARGADO_INVENTARIO -> Icons.Default.Build to "Entradas, salidas, ajustes y alertas"
    }
    val color = MaterialTheme.colorScheme
    Card(
        modifier = Modifier.fillMaxWidth().selectable(seleccionado, role = Role.RadioButton, onClick = elegir),
        colors = CardDefaults.cardColors(containerColor = if (seleccionado) color.secondaryContainer else color.surfaceContainer),
        border = BorderStroke(if (seleccionado) 2.dp else 1.dp, if (seleccionado) color.primary else ColoresKaiju.borde),
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icono, contentDescription = null, tint = color.primary, modifier = Modifier.size(28.dp))
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(usuario.nombre, style = MaterialTheme.typography.titleMedium)
                Text("${usuario.rol.etiqueta()} · $descripcion", style = MaterialTheme.typography.bodySmall,
                    color = color.onSurfaceVariant)
            }
            RadioButton(selected = seleccionado, onClick = null)
        }
    }
}
