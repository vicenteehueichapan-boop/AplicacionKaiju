package cl.academico.kaiju.ui.recursos

import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

/**
 * Recurso nativo 1 · cámara para leer el código del producto (C-01 §3.3, RF-06/RF-07).
 * La pantalla solo abre el lector; el código leído va al ViewModel, que busca el producto.
 * Si el teléfono no tiene cámara o el lector falla, la alternativa es elegir de la lista.
 */
@Composable
fun BotonLectorCodigo(onCodigo: (String) -> Unit, onNoDisponible: () -> Unit) {
    val context = LocalContext.current
    OutlinedButton(
        onClick = {
            // A-11 §7: revisar que el hardware exista antes de usarlo.
            if (!context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
                onNoDisponible()
                return@OutlinedButton
            }
            // Investigación A-11 §10 (Google code scanner): Google Play services abre su propio lector
            // y entrega solo el texto del código; por eso la app no declara ni pide el permiso CAMERA.
            GmsBarcodeScanning.getClient(context).startScan()
                .addOnSuccessListener { codigo -> codigo.rawValue?.let(onCodigo) ?: onNoDisponible() }
                .addOnFailureListener { onNoDisponible() }
            // Si la persona cancela, el formulario queda igual: no es un error.
        },
        modifier = Modifier.fillMaxWidth(),
    ) {
        Icon(Icons.Default.Search, contentDescription = null)
        Spacer(Modifier.width(8.dp))
        Text("Escanear código del producto")
    }
}
