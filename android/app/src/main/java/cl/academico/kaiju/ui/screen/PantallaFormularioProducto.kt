package cl.academico.kaiju.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cl.academico.kaiju.model.FormularioProductoEstado

@Composable
private fun Campo(valor: String, etiqueta: String, error: String? = null,
    numerico: Boolean = false, cambiar: (String) -> Unit) {
    OutlinedTextField(value = valor, onValueChange = cambiar, label = { Text(etiqueta) },
        modifier = Modifier.fillMaxWidth(), isError = error != null,
        supportingText = { error?.let { Text(it) } }, singleLine = true,
        leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null) },
        keyboardOptions = KeyboardOptions(keyboardType = if (numerico) KeyboardType.Number else KeyboardType.Text))
}

@Composable
fun PantallaFormularioProducto(estado: FormularioProductoEstado, categorias: List<String>,
    cambiar: (FormularioProductoEstado) -> Unit, guardar: () -> Unit, volver: () -> Unit) {
    var menu by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        TextButton(onClick = volver) { Text("Volver") }
        Text("Crear producto", style = MaterialTheme.typography.headlineMedium)
        Text("Alta pendiente de confirmar el stock inicial. Puedes comprobar las validaciones.",
            style = MaterialTheme.typography.bodyMedium)
        Campo(estado.codigo, "Código", estado.errores.codigo) { cambiar(estado.copy(codigo = it)) }
        Campo(estado.nombre, "Nombre", estado.errores.nombre) { cambiar(estado.copy(nombre = it)) }
        Box {
            OutlinedButton(onClick = { menu = true }, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Default.Edit, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(estado.categoria.ifBlank { "Elegir categoría de prueba" })
            }
            DropdownMenu(expanded = menu, onDismissRequest = { menu = false }) {
                categorias.forEach { categoria -> DropdownMenuItem(text = { Text(categoria) },
                    onClick = { cambiar(estado.copy(categoria = categoria)); menu = false }) }
            }
        }
        estado.errores.categoria?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        Campo(estado.precio, "Precio en pesos chilenos", estado.errores.precio, true) { cambiar(estado.copy(precio = it)) }
        Campo(estado.stockMinimo, "Stock mínimo", estado.errores.stockMinimo, true) { cambiar(estado.copy(stockMinimo = it)) }
        Campo(estado.descripcion, "Descripción") { cambiar(estado.copy(descripcion = it)) }
        Campo(estado.tipo, "Tipo") { cambiar(estado.copy(tipo = it)) }
        Campo(estado.detalle, "Detalle (opcional)") { cambiar(estado.copy(detalle = it)) }
        estado.mensaje?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        Button(onClick = guardar, modifier = Modifier.fillMaxWidth()) { Text("Validar producto") }
    }
}
