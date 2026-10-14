package cl.academico.kaiju.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

/**
 * Campo de texto con ícono propio y retroalimentación por campo (IE 2.1.2): `isError`,
 * mensaje debajo e ícono de advertencia. El error lo calcula el ViewModel.
 */
@Composable
fun CampoFormulario(
    valor: String,
    etiqueta: String,
    icono: ImageVector,
    error: String?,
    onCambio: (String) -> Unit,
    numerico: Boolean = false,
    habilitado: Boolean = true,
    ayuda: String? = null,
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onCambio,
        label = { Text(etiqueta) },
        leadingIcon = { Icon(icono, contentDescription = null) },
        trailingIcon = { if (error != null) Icon(Icons.Default.Warning, contentDescription = "Error") },
        isError = error != null,
        supportingText = { (error ?: ayuda)?.let { Text(it) } },
        enabled = habilitado,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = if (numerico) KeyboardType.Number else KeyboardType.Text),
        modifier = Modifier.fillMaxWidth(),
    )
}

/** Lista desplegable con el mismo estilo de error que [CampoFormulario]. */
@Composable
fun <T> SelectorFormulario(
    etiqueta: String,
    icono: ImageVector,
    seleccion: String?,
    opciones: List<T>,
    texto: (T) -> String,
    error: String?,
    onElegir: (T) -> Unit,
) {
    var abierto by remember { mutableStateOf(false) }
    Column {
        Box {
            OutlinedButton(
                onClick = { abierto = true },
                modifier = Modifier.fillMaxWidth(),
                colors = if (error != null) ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
                else ButtonDefaults.outlinedButtonColors(),
            ) {
                Icon(icono, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(seleccion ?: etiqueta, modifier = Modifier.weight(1f))
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
            DropdownMenu(expanded = abierto, onDismissRequest = { abierto = false }) {
                opciones.forEach { opcion ->
                    DropdownMenuItem(text = { Text(texto(opcion)) }, onClick = { onElegir(opcion); abierto = false })
                }
            }
        }
        if (error != null) MensajeError(error, Modifier.padding(start = 16.dp, top = 4.dp))
    }
}

/** Mensaje de error con ícono, para errores que no pertenecen a un campo de texto. */
@Composable
fun MensajeError(texto: String, modifier: Modifier = Modifier) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.Warning, contentDescription = null, tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.size(16.dp))
        Spacer(Modifier.width(6.dp))
        Text(texto, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
    }
}
