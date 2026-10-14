package cl.academico.kaiju.viewmodel

import cl.academico.kaiju.repository.DatosDePrueba
import org.junit.Assert.*
import org.junit.Test

/** RF-01: selección validada de usuario de prueba. */
class SesionViewModelTest {
    private val vm = SesionViewModel(DatosDePrueba.usuarios)

    @Test fun ingresarSinSeleccionMuestraErrorSinActivarUsuario() {
        assertFalse(vm.ingresar()); assertNull(vm.estado.value.activo); assertNotNull(vm.estado.value.error)
    }
    @Test fun elegirUsuarioActivaSuPerfil() {
        val usuario = vm.usuarios.first()
        vm.seleccionar(usuario.id)
        assertTrue(vm.ingresar()); assertEquals(usuario, vm.estado.value.activo)
    }
    @Test fun seleccionInexistenteNoPermiteEntrar() {
        vm.seleccionar("inexistente")
        assertFalse(vm.ingresar()); assertNull(vm.estado.value.activo)
    }
    @Test fun cerrarSesionQuitaElPerfilActivo() {
        vm.seleccionar(vm.usuarios.last().id); vm.ingresar()
        vm.cerrarSesion()
        assertNull(vm.estado.value.activo); assertNull(vm.estado.value.seleccionado)
    }
}
