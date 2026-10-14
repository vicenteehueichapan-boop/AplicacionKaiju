package cl.academico.kaiju.ui.recursos

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import cl.academico.kaiju.R
import cl.academico.kaiju.model.Producto

/**
 * Recurso nativo 2 · notificación cuando un movimiento deja un producto en stock bajo
 * (C-01 §3.3, RF-09, RN-06). Si no hay permiso, la pantalla de alertas sigue mostrando lo mismo.
 */
object NotificacionesStock {
    private const val CANAL = "stock_bajo"

    /** Desde Android 13 (API 33) POST_NOTIFICATIONS se pide con diálogo; antes basta con declararlo. */
    fun necesitaPermiso(context: Context): Boolean =
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED

    /** Hay permiso y la persona no apagó las notificaciones de la app en Ajustes. */
    fun puedeNotificar(context: Context): Boolean =
        !necesitaPermiso(context) && NotificationManagerCompat.from(context).areNotificationsEnabled()

    fun mostrar(context: Context, producto: Producto) {
        // Se verifica justo antes de usar el recurso: el permiso pudo retirarse en Ajustes (A-11 §3).
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) return
        val gestor = NotificationManagerCompat.from(context)
        if (!gestor.areNotificationsEnabled()) return
        // Investigación A-11 §10 (canales): desde Android 8 cada notificación pertenece a un canal que la
        // persona puede silenciar por separado. Crearlo de nuevo no cambia su configuración.
        gestor.createNotificationChannel(
            NotificationChannelCompat.Builder(CANAL, NotificationManagerCompat.IMPORTANCE_DEFAULT)
                .setName("Stock bajo")
                .setDescription("Productos que quedan con stock igual o menor a su mínimo.")
                .build()
        )
        val abrirApp = context.packageManager.getLaunchIntentForPackage(context.packageName)?.let {
            PendingIntent.getActivity(context, 0, it, PendingIntent.FLAG_IMMUTABLE)
        }
        val notificacion = NotificationCompat.Builder(context, CANAL)
            .setSmallIcon(R.drawable.ic_notificacion_stock)
            .setContentTitle("Stock bajo: ${producto.nombre}")
            .setContentText("Disponible ${producto.stockActual}, mínimo ${producto.stockMinimo}.")
            .setContentIntent(abrirApp)
            .setAutoCancel(true)
            .build()
        gestor.notify(producto.codigo.hashCode(), notificacion)
    }

    /** Alternativa tras un rechazo definitivo: solo se puede activar desde Ajustes. */
    fun intentAjustes(context: Context): Intent =
        Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
}
