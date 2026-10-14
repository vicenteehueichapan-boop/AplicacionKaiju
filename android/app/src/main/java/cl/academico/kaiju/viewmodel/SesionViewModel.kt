package cl.academico.kaiju.viewmodel

import androidx.lifecycle.ViewModel
import cl.academico.kaiju.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SesionEstado(val seleccionado: Usuario? = null, val activo: Usuario? = null, val error: String? = null)

/** Usuario de prueba activo y su perfil (RF-01). No es autenticación real: no hay contraseñas. */
class SesionViewModel(val usuarios: List<Usuario>) : ViewModel() {
    private val _estado = MutableStateFlow(SesionEstado())
    val estado: StateFlow<SesionEstado> = _estado.asStateFlow()

    fun seleccionar(id: String) = _estado.update {
        it.copy(seleccionado = usuarios.find { usuario -> usuario.id == id }, error = null)
    }

    /** A-10 §5: el login exige elegir un usuario. */
    fun ingresar(): Boolean {
        val elegido = _estado.value.seleccionado
        if (elegido == null) {
            _estado.update { it.copy(error = "Elige un usuario de prueba.") }
            return false
        }
        _estado.update { it.copy(activo = elegido, error = null) }
        return true
    }

    /** Vuelve a la selección de usuario para usar otro perfil de prueba. */
    fun cerrarSesion() {
        _estado.value = SesionEstado()
    }
}
