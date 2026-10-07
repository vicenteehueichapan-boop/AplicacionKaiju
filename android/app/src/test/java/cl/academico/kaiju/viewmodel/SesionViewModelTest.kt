package cl.academico.kaiju.viewmodel

import cl.academico.kaiju.repository.InventarioRepository
import org.junit.Assert.*
import org.junit.Test

class SesionViewModelTest {
    @Test fun ingresarSinSeleccionMuestraErrorSinActivarUsuario() {
        val vm = SesionViewModel(InventarioRepository())
        assertFalse(vm.ingresar()); assertNull(vm.estado.value.activo); assertNotNull(vm.estado.value.error)
    }
    @Test fun elegirUsuarioActivaSuPerfil() {
        val vm = SesionViewModel(InventarioRepository())
        val usuario = vm.usuarios.first()
        vm.seleccionar(usuario.id)
        assertTrue(vm.ingresar()); assertEquals(usuario, vm.estado.value.activo)
    }
    @Test fun seleccionInexistenteNoPermiteEntrar() {
        val vm = SesionViewModel(InventarioRepository())
        vm.seleccionar("inexistente")
        assertFalse(vm.ingresar()); assertNull(vm.estado.value.activo)
    }
}
