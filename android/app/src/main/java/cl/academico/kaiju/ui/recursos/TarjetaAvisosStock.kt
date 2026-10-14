package cl.academico.kaiju.ui.recursos

import android.Manifest
import android.os.Build
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.compose.LifecycleResumeEffect

/**
 * Flujo completo del permiso de notificaciones (A-11 §4 y §9): se pide en contexto al tocar
 * "Activar avisos", se explica si Android lo sugiere, y si la persona dice que no la app sigue
 * funcionando con la lista de alertas y ofrece Ajustes para activarlo después.
 */
@Composable
fun TarjetaAvisosStock() {
    val context = LocalContext.current
    val activity = LocalActivity.current
    var activos by remember { mutableStateOf(NotificacionesStock.puedeNotificar(context)) }
    var rechazado by rememberSaveable { mutableStateOf(false) }
    var explicar by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { concedido ->
        activos = concedido && NotificacionesStock.puedeNotificar(context)
        rechazado = !concedido
    }

    // Se vuelve a verificar cada vez que la app regresa a primer plano, por ejemplo desde Ajustes (A-11 §5).
    LifecycleResumeEffect(Unit) {
        activos = NotificacionesStock.puedeNotificar(context)
        if (activos) rechazado = false
        onPauseOrDispose { }
    }

    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Notifications, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Avisos de stock bajo", style = MaterialTheme.typography.titleMedium)
            }
            if (activos) {
                Text("Activados. Recibirás una notificación cuando un movimiento deje un producto en alerta.")
            } else {
                val necesitaPermiso = NotificacionesStock.necesitaPermiso(context)
                Text(
                    if (necesitaPermiso && !rechazado)
                        "Activa los avisos para enterarte cuando un producto quede bajo su mínimo, aunque estés en otra pantalla."
                    else "Avisos desactivados. Esta lista sigue mostrando todos los productos en alerta."
                )
                if (necesitaPermiso) Button(onClick = {
                    // Tras un primer rechazo Android sugiere explicar; tras el segundo ya no muestra su diálogo.
                    if (activity != null && ActivityCompat.shouldShowRequestPermissionRationale(
                            activity, Manifest.permission.POST_NOTIFICATIONS)
                    ) explicar = true
                    else launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }) { Text("Activar avisos") }
                if (rechazado || !necesitaPermiso) {
                    TextButton(onClick = { context.startActivity(NotificacionesStock.intentAjustes(context)) }) {
                        Icon(Icons.Default.Settings, contentDescription = null)
                        Spacer(Modifier.width(6.dp))
                        Text("Activar en Ajustes")
                    }
                }
            }
        }
    }

    // La explicación solo existe donde el permiso se pide con diálogo (Android 13 o superior).
    if (explicar && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        AlertDialog(
            onDismissRequest = { explicar = false },
            icon = { Icon(Icons.Default.Notifications, contentDescription = null) },
            title = { Text("¿Activar avisos?") },
            text = { Text("Solo avisaremos cuando un producto quede con stock igual o menor a su mínimo. No enviamos publicidad.") },
            confirmButton = {
                TextButton(onClick = {
                    explicar = false
                    launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }) { Text("Continuar") }
            },
            dismissButton = {
                TextButton(onClick = {
                    explicar = false
                    rechazado = true
                }) { Text("Ahora no") }
            },
        )
    }
}
