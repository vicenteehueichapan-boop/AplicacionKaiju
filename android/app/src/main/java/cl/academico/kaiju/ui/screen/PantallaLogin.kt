package cl.academico.kaiju.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import cl.academico.kaiju.model.Usuario
import cl.academico.kaiju.viewmodel.SesionEstado

@Composable
fun PantallaLogin(usuarios: List<Usuario>, estado: SesionEstado, elegir: (String) -> Unit, ingresar: () -> Unit) {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("KAIJU", style = MaterialTheme.typography.headlineLarge)
        Text("Inventario · Base académica", style = MaterialTheme.typography.titleMedium)
        Text("Selecciona un usuario de prueba. Todos los datos son ficticios.")
        usuarios.forEach { usuario ->
            val seleccionado = estado.seleccionado?.id == usuario.id
            Row(Modifier.fillMaxWidth().selectable(seleccionado, role = Role.RadioButton,
                onClick = { elegir(usuario.id) }).padding(vertical = 12.dp)) {
                RadioButton(selected = seleccionado, onClick = null)
                Spacer(Modifier.width(12.dp))
                Text(usuario.nombre)
            }
        }
        estado.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        Button(onClick = ingresar, modifier = Modifier.fillMaxWidth()) { Text("Ingresar") }
    }
}
