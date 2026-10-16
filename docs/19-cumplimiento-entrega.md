# Cumplimiento de la entrega · Kaiju

Fecha: 16-10-2026. Autor: Vicente Hueichapan (trabajo individual). Contraste punto por punto entre lo que piden las fuentes del curso y lo que hay en el proyecto, con la prueba de cada punto: código, prueba automática y captura del emulador.

Fuentes contrastadas, todas en `docs/fuentes/2026-10-14/` salvo la primera:

- **Reglas de Entrega y Presentación** (Evaluación 2), `reglas-entrega-presentacion.txt`, recibidas el 14-10-2026: requisitos de la sección 2, forma de entrega y rúbrica del Encargo.
- **Guía 10:** lista B-01 a B-09 (sección 8), RF y RN (sección 1), decisiones del equipo (sección 6) e indicadores (sección 9).
- **Guía 11:** actividad de recursos nativos (sección 9).
- **Guía de Room:** actividad de persistencia (sección 9) e investigación (sección 10).

Capturas: [docs/evidencia/capturas/](evidencia/capturas/README.md), tomadas el 15 y 16-10-2026 en el emulador Android 16 (API 36) con la versión final. Todos los datos que aparecen son ficticios.

## 1. Verificación ejecutada

| Qué | Resultado | Fecha |
|---|---|---|
| `testDebugUnitTest` | **45 pruebas, 0 fallos** (InventarioViewModelTest 19, MovimientosViewModelTest 20, PersistenciaViewModelTest 2, SesionViewModelTest 4) | 15-10-2026 |
| `connectedDebugAndroidTest` en el emulador | **3 pruebas de Room, 0 fallos** (InventarioDaoTest: carga inicial única, movimiento y stock juntos, rechazo si el stock cambió) | 15-10-2026 |
| `assembleDebug` | APK generado e instalado en el emulador | 15-10-2026 |
| `lintDebug` | 0 errores; solo avisos de versiones más nuevas de librerías, que no se actualizan sin necesidad | 15-10-2026 |
| Recorrido completo en el emulador | 29 capturas, de la 01 a la 27; todas las funciones respondieron como se esperaba | 15 y 16-10-2026 |

## 2. Requisitos del encargo (Reglas de Entrega, sección 2)

| # | Requisito | Cómo se cumple | Prueba |
|---|---|---|---|
| 1 | Interfaz organizada y navegación clara entre todas las pantallas | 6 pantallas con encabezado y bajada; menú inferior según el perfil; barra superior con el usuario activo; "Volver" en detalle y formulario; transiciones al navegar | Capturas 01, 03, 12, 17, 24; `navigation/AppNavHost.kt` |
| 2 | Formularios validados con íconos y mensajes por campo | Login, producto y movimiento: cada campo tiene ícono, borde rojo, ícono de error y mensaje propio | Capturas 02, 05, 06, 10, 15; `ui/components/CamposFormulario.kt` |
| 3 | Validaciones en la lógica, no en el componente | Toda regla vive en `InventarioViewModel` y `SesionViewModel`; las pantallas solo muestran el estado y avisan eventos | 45 pruebas JVM sin interfaz, por ejemplo `datosInvalidosNuncaLleganAlRepositorio` |
| 4 | Animaciones con propósito | Ver sección 4 de este documento | Capturas 09b, 22 |
| 5 | MVVM y persistencia local real (Room) | Capas `model`, `database`, `repository`, `viewmodel`, `ui`; Room con DAO, `@Transaction` y singleton | Captura 26 (datos tras cerrar el proceso); InventarioDaoTest 3/3 |
| 6 | GitHub y Trello con actividad real | Más de 20 commits entre el 30-09 y el 16-10, con mensajes técnicos; tablero creado el 14-10 con tarjetas por pantalla y formulario | [BITACORA.md](evidencia/BITACORA.md), [tablero](https://trello.com/b/c2KIi6VL/kaiju-dsy1105-vicente-hueichapan) |
| 7 | Al menos dos recursos nativos integrados con coherencia | Notificaciones de stock bajo (con permiso) y lector de códigos (sin permiso) | Capturas 16 a 23 |

### Forma de entrega

| Exigencia | Estado |
|---|---|
| Repositorio de GitHub **público** | Cumplido: `visibility: public`, verificado en la API de GitHub el 15-10-2026. La Guía 07 pedía repositorio privado, pero las Reglas de Entrega son posteriores y mandan |
| Acceso para el docente | **Pendiente, lo hace Vicente:** invitar al docente como colaborador en GitHub (Settings → Collaborators) |
| Carpeta completa del proyecto (`app/`, `build.gradle`, etc.) | Cumplido: `android/` con `app/`, `build.gradle.kts`, `settings.gradle.kts` y el wrapper de Gradle |
| README con descripción, integrantes, funcionalidades y pasos para ejecutar | Cumplido: [README.md](../README.md) |
| Commits distribuidos y explicados técnicamente | Cumplido: todos usan `feat:`, `docs:` o `chore:` con descripción; ninguno dice solo "cambios" o "avance". Trabajo individual: todos son de Vicente |
| Código en el enlace de AVA grupal | **Pendiente, lo hace Vicente** antes del domingo 25-10-2026 a las 23:59 |
| No tocar GitHub después del plazo | Último commit planificado: sábado 24-10-2026 |

## 3. Rúbrica del Encargo (Parcial 2)

| Indicador | % | Qué hay | Dónde se ve |
|---|---|---|---|
| IE 2.1.1 Interfaz coherente, jerárquica, navegación funcional | 15 | Tema Material 3 oscuro negro y morado; encabezado y bajada en cada pantalla; indicadores grandes (productos, en alerta, disponible, mínimo, precio); rojo reservado para alertas y errores; menú inferior; el diseño se adapta a la rotación y las pantallas largas tienen desplazamiento | 01, 03, 12, 17, 27 |
| IE 2.1.2 Formularios completos, validación visual por campo, íconos | 15 | 3 formularios (login, producto, movimiento); íconos al inicio de cada campo; ícono y mensaje de error por campo; ayudas bajo los campos; el código queda bloqueado al editar | 02, 05, 06, 10, 13, 15 |
| IE 2.2.1 Lógica de validación centralizada y desacoplada | 10 | RN-01 a RN-06 y validación de campos en el ViewModel; el repositorio solo guarda; pruebas sin Android | `InventarioViewModel.kt`; 45 pruebas |
| IE 2.2.2 Animaciones funcionales | 10 | 5 animaciones con propósito (sección 4) | 09b, 22 y en vivo |
| IE 2.3.1 Estructura modular y persistencia local | 15 | Carpetas por capa (Guía 10 sección 3); Room según la guía (sección 5 de este documento) | 26; InventarioDaoTest |
| IE 2.3.2 GitHub y Trello | 20 | GitHub con historial desde el 30-09; Trello con 5 listas y tarjetas fechadas; un solo integrante | Bitácora y tablero |
| IE 2.4.1 Acceso seguro a dos recursos nativos | 15 | Ficha de decisión en el README; flujo completo del permiso (sección 6) | 16 a 23 |

## 4. Animaciones (Guía 10 sección 6)

| Animación | API | Qué comunica | Archivo |
|---|---|---|---|
| Marca "Stock bajo" aparece y desaparece | `AnimatedVisibility` | Que un movimiento hizo cruzar el mínimo (capturas 22 y 09b) | `ui/components/IndicadorStock.kt` |
| Número disponible cambia de color gradual | `animateColorAsState` | Paso de normal (verde) a alerta (rojo) | `ui/screen/PantallaDetalleProducto.kt` |
| Borde rojo de la tarjeta en alerta | `animateColorAsState` | Qué productos necesitan reposición, sin leer los números | `ui/components/TarjetaProducto.kt` |
| Transición entre pantallas | `fadeIn` + `slideInHorizontally` en el NavHost | Hacia dónde se navega: avanzar entra por la derecha y volver por la izquierda | `navigation/AppNavHost.kt` |
| Confirmación al guardar | `Snackbar` | Que el producto o movimiento quedó guardado, con el disponible resultante (capturas 07, 09, 14) | `navigation/AppNavHost.kt` |

## 5. Persistencia con Room (Guía de Room sección 9)

| Qué debe cumplir | Cómo se cumple |
|---|---|
| `@Entity` y `@PrimaryKey` en los modelos | `Producto` y `Movimiento` son tablas. **Clave de Producto:** su `codigo`, porque la Guía 10 lo exige único (RN-03) y es lo que identifica el historial. **Clave de Movimiento:** un `id` único generado al registrar. La guía permite elegir la clave "según su caso"; `autoGenerate` habría duplicado la identidad del producto |
| DAO con insertar y leer como `Flow` | `InventarioDao`: `productos()` y `movimientos()` devuelven `Flow`; `@Insert` para productos y movimientos; sin `@Delete`, porque RN-05 prohíbe borrar movimientos |
| `@Database` singleton (`obtenerInstancia`) | `database/BaseDeDatosKaiju.kt`, con `@Volatile` y `synchronized` |
| Repository que envuelve al DAO | `repository/InventarioRepository.kt` es la única clase que conoce al DAO |
| ViewModel recibe el Repository; Factory | `InventarioViewModel(repository)` e `InventarioViewModelFactory` |
| Verificar en el emulador: agregar, cerrar por completo y reabrir | Hecho el 15-10-2026: con `am force-stop` (proceso terminado) y luego reabriendo, DEMO-011 conserva el precio editado, su stock y sus 3 movimientos (captura 26) |
| Investigación aplicada con línea comentada (al menos una) | Dos: `@ForeignKey` en `model/Movimiento.kt` y migraciones con esquema exportado en `database/BaseDeDatosKaiju.kt` |

Además, stock y movimiento se guardan juntos en una `@Transaction`, como pide la Guía 10 sección 4. Si el stock cambió entre la validación y el guardado, no se guarda nada (prueba `siElStockCambioNoSeGuardaNada`).

## 6. Recursos nativos (Guía 11 sección 9)

| Exigencia | Cómo se cumple | Prueba |
|---|---|---|
| Ficha de decisión en el README | Tabla con necesidad, recurso, permiso, RF y alternativa | [README](../README.md) |
| Permisos y `uses-feature` en el manifiesto | `POST_NOTIFICATIONS`; `camera.any` con `required="false"` | `AndroidManifest.xml` |
| Flujo completo para un permiso de tiempo de ejecución | Se pide en contexto ("Activar avisos" en Alertas), nunca al abrir la app. Si se rechaza, la app sigue y ofrece Ajustes. Al volver a intentar, explica antes de pedir. Al volver a la app se verifica otra vez (`LifecycleResumeEffect`) | 18 diálogo del sistema; 19 rechazo con alternativa; 20 explicación propia; 21 concedido |
| El resultado del recurso va al ViewModel | El código leído llega a `InventarioViewModel.codigoLeido`, que busca y valida el producto | Pruebas `codigoLeidoExistenteSeleccionaElProducto` y `codigoLeidoInexistente…` |
| Probar conceder, rechazar y rechazar dos veces | Se probaron rechazar, explicar y conceder. El rechazo doble lo bloquea Android, que ya no muestra su diálogo: queda el camino de Ajustes | 18 a 21 |
| Funciona de verdad | Una venta dejó DEMO-011 en su mínimo y llegó la notificación "Stock bajo: Mochila de muestra" | 22 y 23 |

**Lector de códigos en el emulador:** el emulador no tiene Play Store, así que Google Play services no puede descargar el módulo del lector. La app lo detecta y muestra la alternativa: elegir el producto de la lista (captura 16). En un teléfono con Play Store, el lector abre la cámara y elige el producto leído; los QR de prueba están en `docs/evidencia/demo/`. **Para la defensa:** mostrar en vivo las notificaciones, que funcionan en el emulador, y el lector solo si se presenta con un teléfono físico.

## 7. Lista de la Guía 10 (sección 8)

| Control | Estado |
|---|---|
| B-01 Abre y corre sin errores | Cumplido en el emulador (capturas 01 a 27) |
| B-02 Carpetas y archivos de la sección 3 | Cumplido: `navigation`, `model`, `repository`, `viewmodel`, `ui/screen`, `ui/components`, `ui/theme`, más `database` (Room) y `ui/recursos` (Guía 11) |
| B-03 Producto, Movimiento y Usuario con sus enum | Cumplido, con los campos de la sección 4 |
| B-04 10 productos y 3 usuarios ficticios, algunos en stock bajo | Cumplido: 4 en alerta al empezar (captura 03) |
| B-05 6 rutas y menú inferior | Cumplido |
| B-06 Catálogo desde el ViewModel con marca de stock bajo | Cumplido (captura 03) |
| B-07 Formulario validado; el producto nuevo aparece en el catálogo | Cumplido (capturas 05 a 07b) |
| B-08 README con equipo, descripción y decisiones | Cumplido |
| B-09 Commits de todos y Trello al día | Cumplido para un integrante. La modalidad individual se informó al docente; falta su confirmación |

## 8. Funcionalidades RF-01 a RF-10 (Guía 10 sección 1)

| RF | Captura |
|---|---|
| RF-01 Usuario y perfil activo | 01, 02, 24 |
| RF-02 Catálogo | 03 |
| RF-03 Buscar y filtrar | 04, 07b |
| RF-04 Detalle con historial | 12, 26 |
| RF-05 Crear y editar (administrador) | 05 a 07b, 13, 14 |
| RF-06 Entrada | 08, 09 |
| RF-07 Salida por venta o consumo | 15, 22, 25 |
| RF-08 Ajuste con motivo | 10, 11, 12 |
| RF-09 Stock bajo y alertas | 03, 17, 22, 23 |
| RF-10 Persistencia | 26 |

Perfiles (tabla de la Guía 10 sección 1): el vendedor ve Catálogo y Movimiento, solo registra "Salida por venta" y no crea productos (capturas 24 y 25). El encargado de inventario registra entrada, venta, consumo y ajuste. El administrador crea y edita productos, registra entrada y ajuste, y ve las alertas.

## 9. Lo que queda fuera a propósito

| Punto | Motivo |
|---|---|
| Módulo propio (Guía 10 sección 6) | Los candidatos del caso (más y menos vendidos, tendencias) necesitan que el cliente defina medida y período (P-13, P-14). La Guía 10 sección 9 indica recortar el módulo antes que la base. No figura en las Reglas de Entrega |
| Carpeta de Drive con sketch, wireframe y mockup (Guía 07) | Entregable de una sesión de la Guía 07, no del encargo final. No se fabrica material de diseño con fecha anterior. Si el docente lo pide, se arma con fecha real |
| Respuestas del cliente | La entrevista no está agendada. Después de ella solo se cambia lo que el cliente pida, citando el acta |

## 10. Tareas de Vicente antes del plazo

1. Invitar al docente como colaborador en GitHub y en Trello.
2. Subir el código al enlace de AVA grupal (zip del repositorio) antes del domingo 25-10-2026 a las 23:59.
3. Hacer el último commit a más tardar el sábado 24-10 y no volver a tocar el repositorio.
4. Practicar la defensa con la app corriendo en el mismo equipo donde se va a presentar.
