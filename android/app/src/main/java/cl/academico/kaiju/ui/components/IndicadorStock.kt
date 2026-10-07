package cl.academico.kaiju.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.LiveRegionMode

@Composable
fun IndicadorStock(enAlerta: Boolean) {
    if (enAlerta) Text("Stock bajo", color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.labelLarge,
        modifier = Modifier.semantics { liveRegion = LiveRegionMode.Polite })
}
