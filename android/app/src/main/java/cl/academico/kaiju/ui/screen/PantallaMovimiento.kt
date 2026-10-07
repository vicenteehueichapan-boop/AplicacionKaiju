package cl.academico.kaiju.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// A-10 §7 permite pantallas provisionales para la primera revisión.
@Composable
fun PantallaMovimiento() {
    Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Movimiento", style = MaterialTheme.typography.headlineMedium)
        Text("Pantalla preparada para la siguiente etapa de la guía.")
        Text("El registro de entradas, salidas y ajustes todavía no está implementado.")
    }
}
