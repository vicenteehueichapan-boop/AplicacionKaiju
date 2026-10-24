# Kaiju · Inventario y trazabilidad

App Android para el caso **Kaiju** (DSY1105 · Desarrollo de Aplicaciones Móviles · Duoc UC 2026-2).

## Descripción

Kaiju controla hoy su inventario con registros manuales y planillas, lo que genera diferencias de stock, movimientos sin registrar y reposiciones tardías. Esta app lo resuelve en tres frentes:

- **Stock al día:** cada entrada, venta, consumo o ajuste actualiza la cantidad disponible al instante.
- **Trazabilidad:** cada movimiento queda registrado con fecha, tipo, cantidad, usuario y motivo, y no se edita ni se borra.
- **Alertas de reposición:** un producto entra en alerta cuando su disponible llega a su stock mínimo; la app lo marca, lo lista en Alertas y avisa al teléfono.

Cada perfil ve solo lo que le corresponde (administrador, personal de venta y encargado de inventario). Está construida con Kotlin, Jetpack Compose y Material 3, arquitectura MVVM y persistencia local con Room. Todos los datos son ficticios.

## Integrantes

- **Vicente Hueichapan**: lógica y datos (ViewModel, validaciones, Room y recursos nativos).
- **Diego Burgos** ([@DiegoBurgosL](https://github.com/DiegoBurgosL)): análisis y diseño (requerimientos, Trello, pantallas e identidad visual).

Trabajamos en pareja desde un mismo equipo; los commits hechos en conjunto indican a ambos con `Co-authored-by`.

## Funcionalidades implementadas

| Requerimiento | Funcionalidad | Pantalla |
|---|---|---|
| RF-01 | Ingresar con un usuario de prueba; la app muestra solo lo que corresponde a su perfil | Login |
| RF-02 | Catálogo con nombre, categoría, precio y cantidad disponible | Catálogo |
| RF-03 | Búsqueda por nombre o código y filtro por categoría | Catálogo |
| RF-04 | Detalle del producto con su historial, del más reciente al más antiguo | Detalle |
| RF-05 | Crear y editar productos con formulario validado (solo administrador) | Formulario |
| RF-06 | Registrar entradas | Movimiento |
| RF-07 | Registrar salidas por venta o por consumo | Movimiento |
| RF-08 | Registrar ajustes por conteo físico, con motivo y confirmación | Movimiento |
| RF-09 | Marca de stock bajo, pantalla de alertas y notificación al teléfono | Catálogo, Movimiento, Alertas |
| RF-10 | Los datos se conservan al cerrar y volver a abrir la app (Room) | Toda la app |

**Reglas de negocio:** se validan en `InventarioViewModel` antes de guardar: una salida nunca deja el stock bajo cero (RN-01); el precio es mayor que cero y el mínimo mayor o igual a cero (RN-02); el código es obligatorio y único (RN-03); todo ajuste exige motivo (RN-04); y un producto está en alerta cuando su disponible es igual o menor a su mínimo (RN-06). RN-05 se cumple en la base de datos: cada movimiento guarda fecha, tipo, cantidad y usuario, no existen operaciones para editarlo ni borrarlo, y el stock y su movimiento se guardan juntos en una sola transacción.

**Permisos por perfil:**

| Perfil | Puede |
|---|---|
| Administrador | Crear y editar productos, registrar entradas y ajustes, ver alertas |
| Personal de venta | Consultar y registrar salidas por venta |
| Encargado de inventario | Registrar entradas, salidas por venta o consumo y ajustes, ver alertas |

**Stock inicial y ajuste:** un producto nuevo se crea con 0 unidades y su stock entra con una Entrada (la app la ofrece al guardarlo), para que toda unidad tenga historial. El ajuste funciona como un conteo físico: se ingresa lo contado, la app muestra la diferencia, pide confirmar y guarda esa diferencia con su motivo.

## Pasos para ejecutar

**Requisitos:** Android Studio 2026.1 o superior (incluye el JDK) e internet la primera vez, para que Gradle descargue las dependencias.

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/vicenteehueichapan-boop/AplicacionKaiju.git
   ```
2. En Android Studio: **File → Open** y elegir la carpeta **`android/`**.
3. Esperar a que termine la sincronización de Gradle.
4. Crear o elegir un emulador en **Device Manager** (Android 8.0 o superior; se probó en Android 16, API 36).
5. Presionar **Run ▶** sobre el módulo `app`.
6. Ingresar con uno de los usuarios de prueba: **Administrador de prueba**, **Vendedor de prueba** o **Inventario de prueba**.

Pruebas unitarias, desde la carpeta `android/` (en Windows, `gradlew.bat` en vez de `./gradlew`):

```bash
./gradlew testDebugUnitTest
```

## Decisiones del proyecto

### Recursos nativos · ficha de decisión

Los dos recursos salen del caso: "notificaciones asociadas a niveles mínimos de stock" y "uso de la cámara para lectura de códigos QR o códigos de barras".

| Necesidad del caso | Recurso y API | Permiso y tipo | RF | Si el usuario dice que no |
|---|---|---|---|---|
| El encargado de inventario y el administrador necesitan enterarse cuando un producto llega a su mínimo | Notificaciones · `NotificationCompat` con canal propio | `POST_NOTIFICATIONS`, de tiempo de ejecución (Android 13+) | RF-09 | La pantalla Alertas sigue mostrando los productos y ofrece activarlo en Ajustes |
| Quien registra un movimiento necesita elegir el producto rápido y sin equivocarse de código | Cámara · Google code scanner (ML Kit) | Ninguno: Google Play services abre el lector y devuelve solo el código | RF-06, RF-07 | Elegir el producto de la lista |

- **Permiso en contexto:** se pide al tocar "Activar avisos" en Alertas, nunca al abrir la app. Si el usuario ya lo rechazó, la app explica antes de volver a pedirlo, con opción de decir "Ahora no". Al volver a la app se verifica otra vez (`LifecycleResumeEffect`).
- **Manifiesto:** declara `POST_NOTIFICATIONS` y la cámara como opcional (`uses-feature camera.any`, `required="false"`); antes de abrir el lector se revisa que el equipo tenga cámara.
- **MVVM:** el permiso se pide en la pantalla; el código leído y la decisión de avisar quedan en el ViewModel. La notificación se envía una vez, cuando un movimiento hace entrar un producto en alerta.
- **Emulador:** el lector necesita Google Play services con Play Store; si no está disponible, la app lo informa y ofrece elegir el producto de la lista.

### Animaciones

- **Marca "Stock bajo":** aparece y desaparece con `AnimatedVisibility` cuando un movimiento cruza el mínimo; se ve en la misma pantalla de Movimiento al registrar.
- **Color del disponible:** cambia de forma gradual con `animateColorAsState` al entrar o salir de alerta.
- **Navegación:** la pantalla nueva entra deslizándose; avanzar entra por la derecha y volver, por la izquierda.
- **Confirmación:** un Snackbar indica lo que se guardó y el disponible resultante.

### Identidad visual

Tema oscuro de Material 3 en negro, con acentos blancos y tarjetas con borde blanco suave, definido en `ui/theme/Theme.kt`. El rojo se reserva para stock bajo y errores; el verde, para el disponible normal y las entradas; el naranja, para las salidas. Cada pantalla tiene título, bajada e indicadores grandes, y la app tiene un ícono propio.

### Módulo propio

No incluido. Las opciones que plantea el caso (productos más y menos vendidos, tendencias de venta) requieren que el cliente defina cómo se miden y en qué período; se implementarán después de la entrevista. La base de la Guía 10 está completa.

## Estructura

```text
android/app/src/main/java/cl/academico/kaiju/
├── model/         datos: Producto, Movimiento, Usuario y estado de los formularios
├── database/      Room: BaseDeDatosKaiju (singleton) e InventarioDao
├── repository/    InventarioRepository (único que conoce al DAO) y datos de prueba
├── viewmodel/     SesionViewModel, InventarioViewModel (validaciones y reglas) y su Factory
├── navigation/    rutas, NavHost y menú inferior
└── ui/            pantallas, componentes, recursos nativos y tema
```

## Planificación

Tablero de Trello: [Kaiju · DSY1105 · Vicente Hueichapan](https://trello.com/b/c2KIi6VL/kaiju-dsy1105-vicente-hueichapan)
