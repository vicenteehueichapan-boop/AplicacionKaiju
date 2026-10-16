# Evidencia del código · Kaiju

Recorrido ordenado del código Android, por capa, en el orden de la estructura de la Guía 10 sección 3 y la guía de Room. Para cada archivo: qué hace, qué requisito cumple y qué prueba lo verifica. Autor: Vicente Hueichapan (trabajo individual).

Ruta base: `android/app/src/main/java/cl/academico/kaiju/`

## Cómo viajan los datos

```text
Pantalla (ui/screen)  → avisa lo que hizo la persona
   ↓
ViewModel (viewmodel) → valida campos, permisos y reglas RN-01 a RN-06
   ↓
Repository (repository) → único que conoce al DAO; no valida
   ↓
DAO + Database (database) → Room guarda en SQLite, en el teléfono
   ↑
StateFlow → la pantalla se redibuja sola cuando cambian los datos
```

## 1. Entrada de la app

| Archivo | Qué hace | Requisito |
|---|---|---|
| `MainActivity.kt` | Solo llama a `AppNavHost()` | Guía 10 sección 3 |

## 2. Navegación (`navigation/`)

| Archivo | Qué hace | Requisito |
|---|---|---|
| `Rutas.kt` | `sealed class` con las 6 rutas | Guía 10 sección 2–3, B-05 |
| `AppNavHost.kt` | NavHost, menú inferior según perfil, ViewModels compartidos (con `InventarioViewModelFactory`), Snackbar, aviso de stock bajo, vuelta al login si se pierde la sesión | RF-01, B-05, guía de Room sección 6 |

## 3. Modelo (`model/`)

| Archivo | Qué hace | Requisito |
|---|---|---|
| `Producto.kt` | Campos de la Guía 10 sección 4; también es la tabla `productos` (`@Entity`) | B-03, RF-02, RF-10 |
| `Movimiento.kt` | Movimiento + enum `TipoMovimiento`; tabla `movimientos` con `@ForeignKey` hacia el producto | B-03, RN-05, investigación de Room |
| `Usuario.kt` | Usuario + enum `Rol` | B-03, RF-01 |
| `FormularioProductoEstado.kt` | Estado del formulario y errores por campo | Guía 10 sección 5, RF-05 |
| `FormularioMovimientoEstado.kt` | Estado del formulario de movimiento y errores | Guía 10 sección 5, RF-06 a RF-08 |

## 4. Base de datos (`database/`)

| Archivo | Qué hace | Requisito |
|---|---|---|
| `BaseDeDatosKaiju.kt` | `@Database` con singleton `obtenerInstancia`; exporta el esquema (investigación de migraciones) | RF-10, RD-03, guía de Room sección 5 |
| `InventarioDao.kt` | Consultas con `Flow`; registra movimiento y stock en una sola `@Transaction`; carga los datos de prueba solo la primera vez | RF-10, RN-05, Guía 10 sección 4 |

Esquema versionado: `android/app/schemas/`.

## 5. Repositorio (`repository/`)

| Archivo | Qué hace | Requisito |
|---|---|---|
| `InventarioRepository.kt` | Envuelve al DAO; el ViewModel solo le habla a él | Guía 10 sección 3, guía de Room sección 5 |
| `DatosDePrueba.kt` | 10 productos y 3 usuarios ficticios, 4 en stock bajo | B-04, caso sección 5.1 |

## 6. ViewModel (`viewmodel/`)

| Archivo | Qué hace | Requisito |
|---|---|---|
| `SesionViewModel.kt` | Usuario de prueba activo; error si no se elige; cambiar de usuario | RF-01 |
| `InventarioViewModel.kt` | Catálogo con búsqueda y filtro; alertas; historial ordenado; validación de producto y movimiento; permisos por perfil; reglas RN-01 a RN-06 | RF-02 a RF-09, IE 2.2.1 |
| `InventarioViewModelFactory.kt` | Arma el ViewModel con su Repository sobre Room | Guía de Room sección 6 |

## 7. Interfaz (`ui/`)

| Archivo | Qué hace | Requisito |
|---|---|---|
| `screen/PantallaLogin.kt` | Elegir usuario de prueba | RF-01 |
| `screen/PantallaCatalogo.kt` | Lista, buscador, filtro por categoría, botón + | RF-02, RF-03, RF-09 |
| `screen/PantallaDetalleProducto.kt` | Datos, historial y botones Registrar / Editar | RF-04 |
| `screen/PantallaFormularioProducto.kt` | Crear o editar, errores e íconos por campo | RF-05, IE 2.1.2 |
| `screen/PantallaMovimiento.kt` | Producto, tipo según perfil, cantidad y motivo | RF-06 a RF-08 |
| `screen/PantallaAlertas.kt` | Productos en alerta y tarjeta de avisos | RF-09 |
| `components/TarjetaProducto.kt` | Tarjeta con los 4 datos de RF-02; borde rojo animado si está en alerta | RF-02, IE 2.2.2 |
| `components/Elementos.kt` | Encabezado de pantalla, indicador clave, avatar de categoría y estado vacío | IE 2.1.1 |
| `components/IndicadorStock.kt` | Marca "Stock bajo" animada | RN-06, IE 2.2.2 |
| `components/CamposFormulario.kt` | Campo con ícono y error; selector desplegable | IE 2.1.2 |
| `components/Formato.kt` | Precio en CLP, fecha y nombres de tipos y perfiles | IE 2.1.1 |
| `recursos/NotificacionesStock.kt` | Recurso nativo 1: notificación con canal propio | Guía 11 secciones 4 y 10 |
| `recursos/TarjetaAvisosStock.kt` | Flujo completo del permiso de notificaciones | Guía 11 secciones 4, 5 y 9 |
| `recursos/LectorCodigo.kt` | Recurso nativo 2: lector de códigos sin permiso de cámara | Guía 11 secciones 6 y 10 |
| `theme/Theme.kt` | Tema oscuro negro y morado: colores, tipografía y formas | Guía 10 sección 6 |

Recursos de Android: `AndroidManifest.xml` (permiso y hardware opcional), `res/mipmap-anydpi` y `res/drawable` (íconos), `res/xml` (reglas de respaldo).

## 8. Pruebas (`android/app/src/test/` y `androidTest/`)

| Archivo | Qué comprueba | Cantidad |
|---|---|---|
| `InventarioViewModelTest.kt` | Validación del producto, edición, permisos, catálogo, búsqueda, filtro y alertas | 19 |
| `MovimientosViewModelTest.kt` | Entradas, salidas, RN-01, perfiles, ajuste por conteo con confirmación, historial, aviso y lector | 20 |
| `PersistenciaViewModelTest.kt` | Reabrir la app: lo guardado sigue | 2 |
| `SesionViewModelTest.kt` | Login y cambio de usuario | 4 |
| `FakeInventarioDao.kt` | DAO en memoria para probar sin emulador (como `FakeEventoDao` del ejemplo) | — |
| `androidTest/.../InventarioDaoTest.kt` | Room real en el emulador o teléfono: carga inicial y transacción | 3 |

Última verificación (15-10-2026): 45 pruebas unitarias aprobadas, 3 pruebas de Room aprobadas en el emulador, APK generado y lint sin errores. Capturas de cada pantalla en [capturas/](capturas/README.md).
