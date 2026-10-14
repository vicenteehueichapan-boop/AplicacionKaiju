package cl.academico.kaiju.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.academico.kaiju.database.BaseDeDatosKaiju
import cl.academico.kaiju.repository.InventarioRepository

/**
 * Construye el [InventarioViewModel] con su [InventarioRepository] sobre la base de datos real.
 * Es la única pieza que necesita el Context; así el ViewModel sigue siendo Kotlin puro y se
 * prueba con un DAO falso, sin emulador.
 */
class InventarioViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val repository = InventarioRepository(BaseDeDatosKaiju.obtenerInstancia(context).inventarioDao())

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = InventarioViewModel(repository) as T
}
