package cl.academico.kaiju.repository

import cl.academico.kaiju.model.*

// Fixtures sintéticos A-10 §4. No son catálogo, categorías ni personas reales de Kaiju.
object DatosDePrueba {
    val categorias = listOf("Papelería de prueba", "Embalaje de prueba", "Herramientas de prueba")
    val usuarios = listOf(
        Usuario("demo-admin", "Administrador de prueba", Rol.ADMINISTRADOR),
        Usuario("demo-venta", "Vendedor de prueba", Rol.VENDEDOR),
        Usuario("demo-inventario", "Inventario de prueba", Rol.ENCARGADO_INVENTARIO),
    )
    val productos = listOf(
        Producto("DEMO-001", "Cuaderno de muestra", "Producto ficticio", categorias[0], "Muestra", 1500, 12, 4, null),
        Producto("DEMO-002", "Lápiz de muestra", "Producto ficticio", categorias[0], "Muestra", 500, 3, 3, null),
        Producto("DEMO-003", "Carpeta de muestra", "Producto ficticio", categorias[0], "Muestra", 900, 8, 2, null),
        Producto("DEMO-004", "Papel de muestra", "Producto ficticio", categorias[0], "Muestra", 3000, 0, 2, null),
        Producto("DEMO-005", "Caja de muestra", "Producto ficticio", categorias[1], "Muestra", 1200, 20, 5, null),
        Producto("DEMO-006", "Cinta de muestra", "Producto ficticio", categorias[1], "Muestra", 1800, 2, 4, null),
        Producto("DEMO-007", "Bolsa de muestra", "Producto ficticio", categorias[1], "Muestra", 250, 30, 10, null),
        Producto("DEMO-008", "Regla de muestra", "Producto ficticio", categorias[2], "Muestra", 800, 7, 2, null),
        Producto("DEMO-009", "Tijera de muestra", "Producto ficticio", categorias[2], "Muestra", 2200, 5, 5, null),
        Producto("DEMO-010", "Grapadora de muestra", "Producto ficticio", categorias[2], "Muestra", 4500, 6, 1, null),
    )
}
