package cl.academico.kaiju.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

/**
 * Marca de stock bajo (RN-06). Animación con propósito (IE 2.2.2): aparece o desaparece
 * suavemente cuando un movimiento cruza el mínimo, para que el cambio de estado se note.
 */
@Composable
fun IndicadorStock(enAlerta: Boolean) {
    AnimatedVisibility(visible = enAlerta, enter = fadeIn() + expandVertically(), exit = fadeOut() + shrinkVertically()) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.semantics { liveRegion = LiveRegionMode.Polite }) {
            Icon(Icons.Default.Warning, contentDescription = null, tint = MaterialTheme.colorScheme.error,
                modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(4.dp))
            Text("Stock bajo", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelLarge)
        }
    }
}
