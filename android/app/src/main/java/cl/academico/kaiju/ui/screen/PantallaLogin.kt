package cl.academico.kaiju.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import cl.academico.kaiju.model.Usuario
import cl.academico.kaiju.ui.components.MensajeError
import cl.academico.kaiju.ui.components.etiqueta
import cl.academico.kaiju.viewmodel.SesionEstado

/** RF-01: elegir un usuario de prueba. La validación de la selección está en SesionViewModel. */
@Composable
fun PantallaLogin(usuarios: List<Usuario>, estado: SesionEstado, elegir: (String) -> Unit, ingresar: () -> Unit) {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("KAIJU", style = MaterialTheme.typography.displaySmall, color = MaterialTheme.colorScheme.primary)
        Text("Inventario y trazabilidad", style = MaterialTheme.typography.titleMedium)
        Text("Elige un usuario de prueba. Todos los datos son ficticios.")
        Column(Modifier.selectableGroup()) {
            usuarios.forEach { usuario ->
                val seleccionado = estado.seleccionado?.id == usuario.id
                Row(Modifier.fillMaxWidth().selectable(seleccionado, role = Role.RadioButton,
                    onClick = { elegir(usuario.id) }).padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = seleccionado, onClick = null)
                    Spacer(Modifier.width(12.dp))
                    Icon(Icons.Default.Person, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text(usuario.nombre)
                        Text(usuario.rol.etiqueta(), style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
        estado.error?.let { MensajeError(it) }
        Button(onClick = ingresar, modifier = Modifier.fillMaxWidth()) { Text("Ingresar") }
    }
}
