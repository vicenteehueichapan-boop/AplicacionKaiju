package cl.academico.kaiju.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.academico.kaiju.model.Producto
import cl.academico.kaiju.ui.components.TarjetaProducto
import cl.academico.kaiju.viewmodel.FiltroCatalogo

/** RF-02, RF-03 y RF-09: catálogo con buscador, filtro por categoría y marca de stock bajo. */
@Composable
fun PantallaCatalogo(
    productos: List<Producto>,
    total: Int,
    filtro: FiltroCatalogo,
    categorias: List<String>,
    alerta: (Producto) -> Boolean,
    puedeCrear: Boolean,
    buscar: (String) -> Unit,
    filtrar: (String?) -> Unit,
    abrir: (String) -> Unit,
    crear: () -> Unit,
) {
    Box(Modifier.fillMaxSize()) {
        LazyColumn(contentPadding = PaddingValues(16.dp, 12.dp, 16.dp, 96.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item { Text("Catálogo", style = MaterialTheme.typography.headlineMedium) }
            item {
                OutlinedTextField(
                    value = filtro.texto, onValueChange = buscar, singleLine = true,
                    label = { Text("Buscar por nombre o código") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = {
                        if (filtro.texto.isNotEmpty()) IconButton(onClick = { buscar("") }) {
                            Icon(Icons.Default.Clear, contentDescription = "Borrar búsqueda")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    item { FilterChip(selected = filtro.categoria == null, onClick = { filtrar(null) }, label = { Text("Todas") }) }
                    items(categorias) { categoria ->
                        FilterChip(selected = filtro.categoria == categoria, onClick = { filtrar(categoria) },
                            label = { Text(categoria) })
                    }
                }
            }
            item { Text("${productos.size} de $total productos", style = MaterialTheme.typography.bodySmall) }
            if (productos.isEmpty()) item {
                Text("No hay productos que coincidan con la búsqueda.", Modifier.fillMaxWidth().padding(24.dp))
            }
            items(productos, key = { it.codigo }) { producto ->
                TarjetaProducto(producto, alerta(producto)) { abrir(producto.codigo) }
            }
        }
        if (puedeCrear) FloatingActionButton(onClick = crear,
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)) {
            Icon(Icons.Default.Add, contentDescription = "Crear producto")
        }
    }
}
