package cl.academico.kaiju.viewmodel

import androidx.lifecycle.ViewModel
import cl.academico.kaiju.model.Usuario
import cl.academico.kaiju.repository.InventarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SesionEstado(val seleccionado: Usuario? = null, val activo: Usuario? = null, val error: String? = null)

class SesionViewModel(private val repository: InventarioRepository) : ViewModel() {
    val usuarios = repository.usuarios
    private val _estado = MutableStateFlow(SesionEstado())
    val estado = _estado.asStateFlow()
    fun seleccionar(id: String) {
        _estado.value = _estado.value.copy(seleccionado = usuarios.find { it.id == id }, error = null)
    }
    fun ingresar(): Boolean {
        val elegido = _estado.value.seleccionado
        if (elegido == null) {
            _estado.value = _estado.value.copy(error = "Elige un usuario de prueba.")
            return false
        }
        _estado.value = _estado.value.copy(activo = elegido, error = null)
        return true
    }
}
