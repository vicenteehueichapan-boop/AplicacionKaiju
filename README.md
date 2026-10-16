# Kaiju · DSY1105 · Inventario y trazabilidad

Integrante: **Vicente Hueichapan** (trabajo individual). Número de grupo y confirmación docente de la modalidad individual: pendientes.

App Android académica para el caso Kaiju (CITT San Bernardo): consultar existencias, registrar entradas y salidas con su historial, y detectar a tiempo los productos que hay que reponer. Kotlin, Jetpack Compose, MVVM y Room. Todos los datos son ficticios (caso sección 5.1). La entrevista con el cliente aún no se agenda; por indicación del docente (informada por Vicente el 14-10-2026) se avanza con el material entregado: Guías 10 y 11 y el caso. Nada se presenta como acuerdo del cliente. Después de la entrevista solo se cambia lo que el cliente pida o responda, citando el acta como origen.

## Abrir y revisar

1. En Android Studio, abrir **`android/`** (contiene `settings.gradle.kts`).
2. Sincronizar con SDK 36. La línea de comandos está verificada con JDK 21; Android Studio usa su propio JBR.
3. Ejecutar `app` en un teléfono físico por USB o Wi-Fi (más liviano que el emulador). El lector de códigos necesita Google Play services.
4. Seguir la [pauta de demostración](docs/17-prototipo-guia-11.md).

Verificación sin emulador, desde `android/`:

```powershell
.\gradlew.bat --no-daemon testDebugUnitTest assembleDebug lintDebug
```

Verificación del 15-10-2026: compilación correcta, **45 pruebas unitarias aprobadas**, **3 pruebas de Room aprobadas en el emulador** (`connectedDebugAndroidTest`) y lint sin errores (solo avisa de versiones más nuevas disponibles). Recorrido completo en el emulador con 29 [capturas](docs/evidencia/capturas/README.md). Contraste punto por punto con la rúbrica: **[docs/19-cumplimiento-entrega.md](docs/19-cumplimiento-entrega.md)**.

## Qué hace (Guía 10 sección 1)

| RF | Estado | Dónde |
|---|---|---|
| RF-01 Usuario de prueba y perfil activo | Hecho. Perfil visible en la barra superior; botón para cambiar de usuario. | Login, barra superior |
| RF-02 Catálogo con nombre, categoría, precio y disponible | Hecho | Catálogo |
| RF-03 Buscar por nombre o código y filtrar por categoría | Hecho | Catálogo |
| RF-04 Detalle con historial, más reciente primero | Hecho, con fecha, tipo, cantidad, usuario y motivo | Detalle |
| RF-05 Crear y editar productos (administrador) | Hecho. El producto nace con 0 unidades y la app ofrece registrar su entrada inicial ([decisión](docs/18-decisiones-diseno.md)) | Formulario |
| RF-06 Entrada | Hecho (inventario y administrador) | Movimiento |
| RF-07 Salida por venta o consumo | Hecho (venta: vendedor; venta y consumo: inventario) | Movimiento |
| RF-08 Ajuste con motivo | Hecho como conteo físico: se ingresa lo contado, la app muestra la diferencia, pide confirmar y la guarda con su motivo ([decisión](docs/18-decisiones-diseno.md)) | Movimiento |
| RF-09 Marca de stock bajo y alertas | Hecho, más aviso del teléfono | Catálogo, Alertas |
| RF-10 Datos al cerrar y reabrir | Hecho con Room | Toda la app |

Reglas (Guía 10 sección 1): RN-01 a RN-06 se revisan en `InventarioViewModel` antes de guardar. RN-05: un movimiento no se edita ni se borra (el repositorio no ofrece esas operaciones). Stock e historial se guardan en una sola transacción.

## Arquitectura

```text
Pantalla (ui/screen): muestra estado y avisa eventos
  -> ViewModel (viewmodel): valida campos, permisos y reglas RN
     -> Repositorio (repository): guarda y entrega; Room en el teléfono
  <- StateFlow: estado observable
```

- `MainActivity` solo llama a `AppNavHost`. `navigation/` tiene las seis rutas y el menú inferior.
- Persistencia con la organización de la Guía de Room y el ejemplo docente: `database/` (`BaseDeDatosKaiju` con `obtenerInstancia`, `InventarioDao`), `repository/InventarioRepository` (único que conoce al DAO) y `viewmodel/InventarioViewModelFactory`. Las pruebas usan `FakeInventarioDao` en memoria, igual que `FakeEventoDao` del ejemplo, por eso las reglas se prueban sin Android.
- Los permisos y la Activity viven en `ui/`; el resultado del lector va al ViewModel (Guía 11 sección 4).
- Los diez productos se cargan solo la primera vez; después nunca se sobrescriben los cambios guardados.

## Decisiones propias (Guía 10 sección 6)

### Recursos nativos: ficha de decisión (Guía 11 sección 9)

Los dos recursos aparecen textualmente en el caso (caso sección 3.3): "notificaciones asociadas a niveles mínimos de stock" y "uso de la cámara para lectura de códigos QR o códigos de barras".

| Necesidad del caso | Recurso y API | Permiso y tipo | RF | Si la persona dice que no |
|---|---|---|---|---|
| El encargado de inventario y el administrador necesitan enterarse cuando un producto queda bajo su mínimo | Notificaciones · `NotificationCompat` con canal propio | `POST_NOTIFICATIONS`, tiempo de ejecución (Android 13+) | RF-09 | La pantalla Alertas sigue mostrando los productos; se ofrece "Activar en Ajustes" |
| Quien registra un movimiento necesita elegir el producto rápido y sin equivocarse de código | Cámara · Google code scanner (ML Kit) | Ninguno: Google Play services abre el lector | RF-06, RF-07 | Elegir el producto de la lista |

- **Flujo de permiso completo** (Guía 11 sección 4): se pide en contexto al tocar "Activar avisos" en Alertas, nunca al abrir la app. Si Android lo sugiere, se explica antes con un diálogo que permite decir "Ahora no". Tras un rechazo, la app sigue funcionando. Se vuelve a verificar al regresar a la app (`LifecycleResumeEffect`).
- **Manifiesto:** `POST_NOTIFICATIONS` y `uses-feature camera.any` con `required="false"`. Antes de abrir el lector se revisa que haya cámara.
- **Investigaciones aplicadas (Guía 11 sección 10):** Google code scanner (`ui/recursos/LectorCodigo.kt`) y canales de notificación (`ui/recursos/NotificacionesStock.kt`), cada una con su línea comentada.
- Las notificaciones se emiten cuando un movimiento hace **entrar** un producto en alerta (RN-06), no en cada salida. Es una decisión técnica reversible, no una regla del cliente.

### Animaciones

- La marca "Stock bajo" aparece y desaparece con `AnimatedVisibility` cuando un movimiento cruza el mínimo: comunica el cambio de estado.
- En el detalle, el número disponible cambia de color de forma gradual (`animateColorAsState`) al entrar o salir de alerta.
- El borde de la tarjeta de un producto en alerta se tiñe de rojo de forma gradual: se ve qué reponer sin leer números.
- Al navegar, la pantalla nueva aparece deslizándose: avanzar entra por la derecha y volver por la izquierda.
- Confirmación con Snackbar después de guardar un producto o un movimiento.

### Identidad visual

Nombre Kaiju. Tema oscuro Material 3 en negro y morado: fondo casi negro para leer cómodo en la bodega y morado (`#BB86FC`) para lo que se puede tocar. El rojo queda solo para stock bajo y errores; el verde, para el disponible normal y las entradas; el naranja, para las salidas. Cada pantalla tiene título, bajada e indicadores grandes; los títulos y cantidades van en negrita. Ícono propio: caja de inventario morada. Definido en `ui/theme/Theme.kt` (colores, tipografía y formas), `ui/components/Elementos.kt` (encabezado, indicadores, avatar y estado vacío) y `res/`.

### Módulo propio

**Pendiente.** Los candidatos que salen del caso (más y menos vendidos, tendencias; caso sección 4.1) necesitan saber cómo se mide y en qué período (preguntas P-13 y P-14 al cliente). Implementarlo sin esas respuestas sería inventar una métrica. La Guía 10 sección 9 indica recortar el módulo antes que la base.

## Decisiones revisables y pendientes

Las decisiones de stock inicial y ajuste siguen la práctica de sistemas profesionales y están explicadas, con fuentes, en [docs/18-decisiones-diseno.md](docs/18-decisiones-diseno.md).

| ID | Qué falta | Mientras tanto |
|---|---|---|
| G10-02 | Stock con que nace un producto nuevo | Resuelto como en SAP y Odoo: nace con 0 y el stock entra con una Entrada. Revisable con el cliente |
| G10-01 | Si un ajuste suma, resta o fija la cantidad contada | Resuelto como conteo físico (Odoo): fija lo contado y guarda la diferencia. Revisable con el cliente |
| G10-04 | Si el administrador registra salidas | Se aplica literal la tabla de RF-07: no las registra |
| G10-03 / G10-06 | Cambiar códigos y semántica comercial de la búsqueda | El código no se edita; la búsqueda es parcial y no distingue mayúsculas (decisión técnica reversible) |

## Trabajo y seguimiento

Trabajo individual. GitHub registra el código y la [bitácora](docs/evidencia/BITACORA.md); el [tablero de Trello](https://trello.com/b/c2KIi6VL/kaiju-dsy1105-vicente-hueichapan) (privado; se comparte con el docente por invitación) se creó el 14-10-2026 con las cinco listas de la Guía 07 para ordenar el trabajo hasta la entrega. Todas las tarjetas están asignadas a Vicente Hueichapan. Las tarjetas de lo ya hecho indican la fecha y el commit reales.

Repositorio: [AplicacionKaiju](https://github.com/vicenteehueichapan-boop/AplicacionKaiju).

## Documentación

- **[Evidencia del código](docs/evidencia/CODIGO.md):** cada archivo por capa, con qué hace, qué requisito cumple y su prueba.
- **[Bitácora por fecha](docs/evidencia/BITACORA.md)** (desde el historial de Git) y **[tablero Trello](docs/evidencia/trello/TABLERO-KAIJU.md)** con las tarjetas por pantalla y formulario.
- **[Guion de demostración](docs/evidencia/demo/GUION-DEMOSTRACION.md)** y [códigos QR de prueba](docs/evidencia/demo/codigos-demo.html).
- **[Cumplimiento de la entrega](docs/19-cumplimiento-entrega.md):** requisito por requisito, con su código, prueba y captura.
- **[Capturas del emulador](docs/evidencia/capturas/README.md)** (29, del 15 y 16-10-2026) y **[evidencia final en Word](docs/evidencia/evidencia-final-kaiju-2026-10-16.docx)** con las capturas incluidas.
- [Evidencia de avance del 14-10 (Word)](docs/evidencia/evidencia-prototipo-kaiju-2026-10-14.docx): registro de ese día; la versión vigente es la final.
- Investigación de la Guía de Room (sección 10), con línea comentada en el código: relaciones con `@ForeignKey` (`model/Movimiento.kt`) y migraciones con esquema exportado (`database/BaseDeDatosKaiju.kt`, `app/schemas/`).
- [Prototipo, demostración y verificación](docs/17-prototipo-guia-11.md) y [auditoría independiente del 14-10](docs/16-auditoria-claude-2026-10-14.md).
- [Auditoría Guía 10](docs/11-auditoria-guia-10.md), [definición de base](docs/12-definicion-base-semana-10.md), [entrega base](docs/13-entrega-base-android.md).
- [Plantilla de requerimientos](docs/entregables/kaiju-requerimientos-guia-10.xlsx), [fuentes originales](docs/fuentes/2026-10-14/README.md).
- Antecedentes: [auditoría del caso](docs/01-auditoria-caso.md), [preguntas](docs/02-preguntas-cliente.md), [preparación de entrevista](docs/08-preparacion-entrevista.md).

## Configuración técnica

Paquete `cl.academico.kaiju`; SDK mínimo 26, objetivo 36; Java 17. AGP 9.3.3, Gradle 9.5.0, Kotlin integrado, Compose BOM 2026.02.01, Room 2.8.5 con KSP 2.3.12, code scanner 16.1.0. El esquema de la base se versiona en `android/app/schemas/`. No se actualizan versiones solo porque haya una más nueva.

Sin credenciales, datos productivos ni firmas. Respaldo en la nube desactivado: los datos son de prueba.
