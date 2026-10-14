# Auditoría independiente Kaiju (Claude), 14-10-2026

> **Vigencia:** el mismo día, con autorización de Vicente, se desarrolló el prototipo que corrige H-02, H-04 a H-08, H-10 a H-13, H-15 y H-16. El estado actual está en [doc 17](17-prototipo-guia-11.md). H-01 (stock inicial) y el cálculo del ajuste siguen pendientes de respuesta; H-03, H-09 y H-18 no dependen del código.

Auditoría en lectura y análisis. No se modificó la aplicación, los originales ni el Excel; no hubo commits, push, publicación ni emuladores. Única escritura en el repositorio: este archivo (sin commit). Verificaciones técnicas ejecutadas en una copia temporal, eliminada al terminar.

Leyenda de evidencia: **[L]** leído por mí en fuente/código; **[E]** prueba ejecutada hoy por mí; **[H]** prueba histórica de otro agente (07-10); **[M]** declaración manual de Vicente; **[R]** razonamiento por inspección, no reproducido.

---

## 1. Inventario de fuentes, revisión y limitaciones

| Elemento | Resultado |
|---|---|
| Revisión auditada | HEAD local `9455d57` = `origin/main` remoto (`git ls-remote`, hoy). El código Android no cambia desde `9916a92`. |
| Instantánea vs proyecto activo | `diff -r`: `android/` y `docs/` idénticos; solo difieren `.gradle/` (caché) y `android/gradle/gradle-daemon-jvm.properties` (no versionado). |
| Integridad | `INVENTARIO-PAQUETE.json`: 105/105 archivos con SHA256 correcto. `manifest.json` de fuentes coincide. Los dos adjuntos de esta conversación (PDF y XLSX en Descargas) son idénticos byte a byte a C-01 y N-04. |
| Leído completo | Caso C-01 (PDF, 4 páginas); Guía 10 (A-10); Guía 11 (A-11); guía extra (N-EXTRA); cuestionario IEEE 830 (N-830); diagramas A-10-D1/D2; plantilla original N-04 y copia `kaiju-requerimientos-guia-10.xlsx` celda por celda (valores, rellenos, fórmulas, listas); los 21 archivos Kotlin, manifiesto, Gradle, 2 tests; README; docs 02, 08, 11, 12, 13, 14, 15. |
| Leído parcialmente | Docs 01 (§1, §8–10), 03–07, 09 (encabezados y notas de vigencia), 10 (estructura). Guías 07–09: solo búsquedas puntuales (GitHub/Trello/StateFlow). Ejemplo Agenda: no revisado (referencia pedagógica). |
| No disponible | Guías 12/13, encargo AVA y rúbrica completa, acta de entrevista, material ficticio prometido (C-01 §6), tablero Trello (Vicente confirma hoy que **no existe**: trabaja solo y únicamente con Git). |
| Límites | Los TXT de guías no contienen los diagramas interactivos (permiso, ciclo de vida). No verifiqué visibilidad del repositorio (sin `gh`). La documentación oficial Android se contrastó con mi conocimiento hasta junio 2026, sin consulta web en vivo. |

---

## 2. Qué problema resolvemos y qué pide el profesor (resumen para estudiar)

**Necesidad de Kaiju [L C-01 §2.3–2.4]:** saber cuánto hay de cada producto, registrar cada entrada/salida y ver a tiempo qué reponer, porque hoy usan planillas dispersas y el stock registrado no calza con el real. *Trazabilidad* = para cualquier producto, cuánto hay, qué movimientos tuvo y quién los hizo [L N-EXTRA l.50].

**Qué exige el curso, separado del cliente:**

| Origen | Qué fija | Naturaleza |
|---|---|---|
| C-01 caso | Necesidades, datos, 6 señales de éxito, restricciones de datos ficticios | Contexto y resultados esperados del cliente (sin métricas) |
| A-10 Guía 10 | RF-01–10, RN-01–06, modelo, validaciones, 6 pantallas, B-01–09, pesos IE | **Exigencia académica explícita** ("solicitud base"); RN-06 y permisos por perfil son supuestos revisables (A-10 l.55) |
| A-10 §6 | 2 recursos, animaciones, módulo propio, identidad visual | **Decisión del equipo**, justificada en README |
| A-11 Guía 11 | Ficha de recursos, permisos, flujo concesión/rechazo, 2 investigaciones aplicadas | Exigencia académica |
| N-04 plantilla + N-EXTRA + N-830 | SRS en Excel, ≥8 preguntas antes; acta, ≥8 RF/3 RNF/3 RN después | Exigencia académica documental |
| Agenda / Club Los Aromos | Ejemplos | Pedagógicos, no requisitos |

---

## 3. Hallazgos por severidad

### Alta (afectan la revisión de base o un indicador completo)

**H-01 · B-07 no se cumple: el producto nuevo nunca aparece en el catálogo.** Incumplimiento.
- Fuente: A-10 l.177 (B-07: "el producto nuevo aparece en el catálogo"); A-10 l.119–125 (campos del formulario: código, nombre, categoría, precio, mínimo; **sin stock**); A-10 l.110 ("cada vez que se registra un movimiento, el ViewModel actualiza el stockActual").
- Evidencia [L]: `InventarioViewModel.kt:11-12` (`stockInicialAcordado: Int? = null`), `:56-60` (retorna sin guardar); `AppNavHost.kt:23` construye el ViewModel sin ese valor. Test `noSeGuardaSinRespuestaDeStockInicial` lo confirma [E].
- Análisis: el bloqueo de G10-02 era prudente, pero hoy cuesta un control completo de la revisión. La fuente sí da pistas: el formulario no pide stock y el stock "se explica" por movimientos (A-10 l.110, RN-05). Dos lecturas posibles, ambas sin inventar campos:
  - **Opción A:** el producto nace con 0 unidades disponibles y las unidades llegan con una ENTRADA (RF-06). No agrega campos; toda unidad queda trazada por un movimiento.
  - **Opción B:** agregar campo "stock inicial" y registrar una ENTRADA automática. Agrega un campo que A-10 §5 no enumera.
- Recomendación: preguntar hoy al docente con la pregunta cerrada de §6 (P-D1). Si no responde antes de la revisión, **tú decides** si autorizas la Opción A como *supuesto académico revisable* documentado (N-EXTRA l.182 permite supuestos marcados como tales; A-10 l.55 llama "supuestos razonables para avanzar" a decisiones de la base). No la implemento sin tu autorización.

**H-02 · Guía 11 sin avance: 0 de 4 entregables.** Pendiente; afecta IE 2.4.1 (15 %).
- Fuente: A-11 l.314–325 (ficha README, manifiesto, flujo de permiso, resultado al ViewModel, probar conceder/rechazar/rechazar dos veces, commit, capturas) y l.327–336 (≥2 investigaciones aplicadas con línea comentada).
- Evidencia [L]: `AndroidManifest.xml:1-10` sin `uses-permission` ni `uses-feature`; README.md:51 solo "propuestas"; no hay código de recursos.
- Recomendación: ver Incremento 5 y §5 de este informe (recursos con mayor sustento en el caso).

**H-03 · IE 2.3.2 (20 %) sin Trello y con modalidad individual no confirmada.** Riesgo/decisión pendiente.
- Fuente: A-10 l.159, l.181, l.195; A-07 l.156, l.196 (tablero y enlace al docente); A-11 l.325 (capturas **en Trello**). Plantilla hoja 9 B15 [L].
- Evidencia: Vicente confirma hoy que no hay Trello [M]. `git shortlog`: 11 commits, todos de un solo autor; un único commit de código (`9916a92`) contiene toda la base [L].
- Impacto: es el indicador de mayor peso. Un solo commit de código grande también debilita "trabajo constante" (A-10 l.195).
- Recomendación: preguntar al docente (P-D2) si acepta GitHub como único tablero/evidencia en trabajo individual o si debes crear Trello tú mismo (no lo crearé yo). Desde ya: commits pequeños por incremento.

**H-04 · Persistencia local inexistente (RF-10, RD-03).** Pendiente planificado, no defecto.
- Fuente: A-10 l.46, l.72, l.194; N-04 hoja 4 C26 [L].
- Evidencia [L]: `InventarioRepository.kt:9-11` listas en memoria. README.md:44 lo declara correctamente.
- Dependencia: Guía 12 (no recibida) define la técnica "según lo que veamos en clases" (A-10 l.194).

**H-05 · Movimientos no implementados: RF-04 (historial), RF-06/07/08, RN-01/04/05.** Pendiente; A-10 l.184 lo ubica después de la revisión, pero es el núcleo de la trazabilidad del caso.
- Evidencia [L]: `PantallaMovimiento.kt:11-16` aviso; `InventarioRepository.kt:11` historial siempre vacío; `FormularioMovimientoEstado.kt` existe sin uso.
- Nota: sin movimientos, la señal de éxito "Stock actualizado y trazable por producto" (C-01 §4.1) no tiene cobertura funcional.

### Media (defectos o brechas de calidad localizadas)

**H-06 · Tras muerte de proceso la app queda en Catálogo sin perfil activo.** Defecto probable [R].
- Evidencia: `AppNavHost.kt:29` `rememberNavController()` guarda la pila en estado guardado; `AppNavHost.kt:56` saca Login de la pila; `SesionViewModel.kt:13` mantiene el usuario solo en memoria. Al recrear el proceso, la navegación restaura Catálogo y la sesión vuelve a `null`: menú inferior oculto (`AppNavHost.kt:36`), sin perfil, contra RF-01 ("la app sabe qué perfil está activo").
- Cómo comprobarlo sin emulador: en un teléfono físico, Opciones de desarrollador → "No conservar actividades"; ingresar, ir al inicio y volver.
- Recomendación acotada: si no hay sesión activa, volver a Login (no supone sesión persistente, G10-08 sigue abierto).

**H-07 · Iconos y retroalimentación de formularios débiles para IE 2.1.2.**
- Fuente: A-10 l.130 ("íconos adecuados"; `leadingIcon`/`trailingIcon`).
- Evidencia [L]: `PantallaFormularioProducto.kt:22` todos los campos usan el mismo icono `Edit`; sin icono de error; `:49` error de categoría como texto suelto; `PantallaLogin.kt:30` error sin icono ni asociación a campo.
- Recomendación: icono por significado (código, nombre, categoría, precio, mínimo) y `trailingIcon` de error cuando `isError`.

**H-08 · El orden del historial (regla de RF-04) vive en la navegación, no en el ViewModel.**
- Fuente: A-10 l.101 (reglas en ViewModel; pantalla solo muestra) y l.66 (más reciente primero).
- Evidencia [L]: `AppNavHost.kt:67` `filter{}.sortedByDescending{}` dentro del NavHost; no hay prueba.
- Recomendación: función `historial(codigo)` en `InventarioViewModel` con prueba de orden, al implementar movimientos.

**H-09 · Excel: hojas 5, 7 y 8 con secciones exigidas vacías.** Incumplimiento documental parcial.
- Fuente: N-EXTRA l.248–250; N-830 CP-05, TR-02, TR-03, SF-07.
- Evidencia [L], copia `kaiju-requerimientos-guia-10.xlsx`: hoja 5 A15:C20 ("Fuera del MVP") vacía, aunque C-01 §3.4/§5.1 da un caso explícito (integración externa no exigida); hoja 7 sin lógica paso a paso de reglas y C5:C8 sin persistencia; hoja 8 B6:F11 (señales), A15:F27 (matriz) y D30:D36 (cobertura IE) vacías.

**H-10 · Afirmaciones desactualizadas sobre lint y JDK.** Falta de evidencia corregida.
- README.md:22 y doc 13 §7 dicen que lint no concluye. **Hoy concluyó en 49 s [E]** con 1 error y 9 advertencias (ver §7). El único error es `local.properties:1` (`PropertyEscape`, archivo local no versionado), que hace fallar `lintDebug`.
- README.md:10 documenta JDK 21; Android Studio usa `jbr-25` (`.idea/misc.xml`), que muy probablemente generó `gradle-daemon-jvm.properties` (creado 07-10 16:00:06, 45 s antes que `.idea/`). La compilación con JDK 21 funciona [E]; con JDK 25 no la probé.

### Baja (presentación y mantenibilidad)

| ID | Hallazgo | Evidencia [L] | Recomendación |
|---|---|---|---|
| H-11 | Precio con formato distinto: tarjeta "$ 1.500", detalle "$ 1500" | `TarjetaProducto.kt:19` vs `PantallaDetalleProducto.kt:26` | Una función de formato en `ui/` |
| H-12 | Sin icono de app; identidad visual incompleta | `AndroidManifest.xml:2`; lint `MissingApplicationIcon` [E]; `Theme.kt:8-13` solo colores claros | Icono y tipografía en `ui/theme/` (A-10 l.139) |
| H-13 | No se ve qué perfil está activo ni se puede cambiar sin cerrar la app | doc 13 §3 paso 9; `AppNavHost.kt` sin acción de salida | Mostrar perfil; "Cambiar usuario" opcional (decisión técnica, no regla de negocio) |
| H-14 | Código con espacios finales pasa como "distinto" (`"DEMO-001 "`) | `InventarioViewModel.kt:35`; nombre cuenta espacios `:38` | Decidir con G10-03; recortar espacios es saneamiento técnico, mayúsculas es negocio |
| H-15 | El ViewModel expone su repositorio para crear el otro ViewModel | `InventarioViewModel.kt:10`, `AppNavHost.kt:24` | Resolver al introducir persistencia (el repositorio necesitará `Context`) |
| H-16 | Errores de campo quedan visibles al corregir hasta volver a validar | `InventarioViewModel.kt:24-26` | Limpiar el error del campo editado |
| H-17 | README no enlaza Drive con sketch/wireframe/mockup | A-07 l.196 | Enlazar si existe; doc 06 (Lovable) no es ese entregable |
| H-18 | Repositorio posiblemente público; A-07 l.170 pide privado | doc 14 §3 (última constatación) | Verificar tú en GitHub; no lo cambio |

### Lo revisado sin hallazgos

- Estructura §3 exacta (A-10 l.77–100): todos los paquetes y archivos presentes [L].
- Modelos con nombres de campo y enums de A-10 §4; precio `Int` [L].
- Diez productos y tres usuarios ficticios separados en `DatosDePrueba.kt`; cuatro en alerta (DEMO-002, 004, 006, 009), incluidos casos de igualdad [L].
- RN-06 definida una sola vez (`InventarioViewModel.kt:20`) y usada en catálogo, detalle y alertas [L].
- Validación de producto y login en ViewModel, con conversión segura (`toIntOrNull`) y permiso de creación revisado también en el ViewModel (`:47`), no solo en la UI [L][E].
- Estado con `StateFlow` privado/público como en Guía 09; ViewModels compartidos a nivel de Activity, sobreviven a la rotación [R].
- Sin datos reales, credenciales ni permisos innecesarios; `allowBackup=false` [L].
- Las 15 pruebas verifican reglas reales (límites, desbordamiento, igualdad RN-06, permisos), no repiten nombres de archivos [L][E].

---

## 4. Matriz B-01–B-09 (sin nota)

| B | Exigencia literal (A-10 l.165–181) | Evidencia y tipo | Estado | Brecha / siguiente acción |
|---|---|---|---|---|
| 01 | Abre y corre en emulador sin errores | APK compilado hoy [E]; funcionamiento declarado [M]; [H] 07-10 | Implementado; ejecución solo declarada | Registrar fecha, dispositivo y captura |
| 02 | Carpetas y archivos §3 | Estructura completa [L] | **Comprobado** | — |
| 03 | Producto, Movimiento, Usuario y enums | `model/*.kt` [L] | **Comprobado** | — |
| 04 | ≥10 productos, 3 usuarios ficticios, stock bajo | `DatosDePrueba.kt:8-24` [L] | **Comprobado** | — |
| 05 | 6 rutas funcionan y menú cambia de pantalla | `Rutas.kt`, `AppNavHost.kt` [L]; [M] | Implementado sin comprobación propia | H-06 al volver tras muerte de proceso |
| 06 | Catálogo desde ViewModel con marca de stock bajo | `AppNavHost.kt:26,60`; `IndicadorStock.kt` [L]; [M] | Implementado sin comprobación propia | — |
| 07 | Valida cada campo en ViewModel **y el producto nuevo aparece en el catálogo** | Validación [L][E]; alta bloqueada | **Parcial: incumplido el alta** | H-01: P-D1 o tu autorización |
| 08 | README con equipo, descripción y decisiones §6 | README.md:3, 46-55 [L] | Parcial | Decidir y justificar recursos, animación y módulo |
| 09 | Commits de todos y Trello al día | Commits propios [L]; sin Trello [M] | Parcial | P-D2 al docente |

---

## 5. Cobertura RF/RN/RD e indicadores del Parcial 2

### RF y RN de A-10

| ID | Estado en código | Evidencia |
|---|---|---|
| RF-01 | Implementado (selección, perfil, permisos de alertas/creación) | `SesionViewModel.kt`, tests [E]; H-06, H-13 |
| RF-02 | Implementado | `TarjetaProducto.kt:16-21` |
| RF-03 | **No implementado** | Sin buscador ni filtro en `PantallaCatalogo.kt` |
| RF-04 | Pantalla sí; historial siempre vacío | H-05, H-08 |
| RF-05 | Crear: valida sin guardar; **editar no existe** | H-01 |
| RF-06/07/08 | No implementados | H-05 |
| RF-09 | Implementado (marca y pantalla de alertas por perfil) | `AppNavHost.kt:78-81` |
| RF-10 | No implementado | H-04 |
| RN-01, RN-04, RN-05 | No implementadas (dependen de movimientos) | — |
| RN-02, RN-03, RN-06 | Implementadas y probadas | Tests [E] |
| RD-01, RD-02, RD-05 | Cumplidas | — |
| RD-03, RD-04 | No cumplidas | H-04, H-02 |

### Indicadores (pesos de A-10 l.189–196 y plantilla hoja 8 C30:C36; sin calcular nota)

| IE | Peso | Tenemos | Falta |
|---|---|---|---|
| 2.1.1 Interfaz y navegación | 15 % | 6 rutas, menú, paleta | Icono, tipografía, perfil visible, Movimiento real (H-11/12/13) |
| 2.1.2 Formularios validados | 15 % | Login y producto con error por campo | Formulario de movimiento; iconos adecuados (H-07) |
| 2.2.1 Validación en la lógica | 10 % | RN-02/03/06 en ViewModel | RN-01/04/05 (H-05) |
| 2.2.2 Animaciones | 10 % | Nada | Animaciones con propósito |
| 2.3.1 Estructura y persistencia | 15 % | Capas y repositorio separado | Persistencia (H-04) |
| 2.3.2 GitHub y Trello | 20 % | GitHub con historial propio | Trello/alternativa aceptada; commits frecuentes (H-03) |
| 2.4.1 Recursos nativos | 15 % | Nada | Dos recursos + permisos (H-02) |

### Señales de éxito (C-01 §4.1) — trazabilidad, ninguna se elimina

| Señal | Cobertura actual | Requerimientos que la cubrirían |
|---|---|---|
| Stock actualizado y trazable por producto | Parcial (solo stock visible) | RF-02, RF-04, RF-06–08, RN-05, RF-10 |
| Menos quiebres y diferencias | Parcial (alertas) | RF-09, RN-06, RF-08 |
| Más/menos vendidos | No cubierta | Candidato a módulo propio (RF-11+), requiere P-13 |
| Tendencias por período/categoría/tipo | No cubierta | Candidato a módulo propio, requiere P-14 |
| Registro más rápido | No cubierta; sin métrica | RF-06/07 + posible escáner; RNF-01 pendiente P-25 |
| Disponibilidad clara interna y pública | Interna parcial; pública condicional | RF-02/03; público depende de P-05 |

### Recursos nativos con mayor sustento (recomendación, decisión tuya)

C-01 §3.3 nombra literalmente dos recursos: "notificaciones asociadas a niveles mínimos de stock" y "cámara para lectura de códigos QR o de barras". Con eso se completa la frase de A-11 l.22 sin inventar necesidades:

| Recurso / API | Permiso | RF | Si rechaza | Por qué |
|---|---|---|---|---|
| Notificación de stock bajo (`NotificationCompat` + canal) | `POST_NOTIFICATIONS`, tiempo de ejecución (Android 13+) | RF-09 | La pantalla de alertas sigue funcionando | Cumple el flujo completo de permiso que exige A-11 l.322 |
| Escáner Google code scanner (ML Kit) | Ninguno | RF-03, RF-06/07 | Escribir el código a mano | Apoya "registro más rápido"; requiere Google Play services |

README.md:51 propone hoy notificaciones y vibración; la vibración no tiene base en el caso (es una idea de A-10 l.136) y puede quedar como investigación §10 (`LocalHapticFeedback`). Las investigaciones §10 que encajan sin añadir funciones: **canales de notificación** y **Google code scanner**.

**Ajustes técnicos a la Guía 11** (documentación oficial, según mi conocimiento):
- A-11 l.196: el ViewModel **no** sobrevive a la muerte del proceso; solo `rememberSaveable` o `SavedStateHandle`. Para `TakePicture` hay que guardar la `Uri` así.
- A-11 l.68: "rechazar dos veces = no volver a preguntar" aplica desde Android 11; el proyecto admite Android 8–10 (`minSdk 26`), donde existe la casilla "No volver a preguntar".
- A-11 l.127–136: `shouldShowRequestPermissionRationale` devuelve `false` tanto antes de preguntar como tras el rechazo permanente; el ejemplo cae en la alternativa, pero no orienta a Ajustes. Conviene añadir ese texto.
- Una notificación generada al registrar un movimiento ocurre con la app abierta; avisar "con la app cerrada" requeriría trabajo en segundo plano, que no recomiendo añadir.

---

## 6. IEEE 830 y plan de actualización del Excel (sin modificar todavía)

Estructura de la copia: 10 hojas, celdas blancas, RD-01–05, listas y fórmula `SUM(C30:C36)=100` intactas [L].

| Característica | Estado | Celdas |
|---|---|---|
| Correcto (C-01–03) | C-01 cumple con origen citado; C-02 no aplica (sin acta); C-03 no cumple (honestamente declarado) | Hoja 3 D6:D17 |
| No ambiguo (NA) | Glosario provisional correcto; RF-03 ambiguo, declarado | Hoja 1 A18:B23 |
| Completo (CP) | CP-05 **falla**: "Fuera del MVP" vacío; CP-04 con fecha "por acordar" | Hoja 5 A15:C20, D23:D27 |
| Consistente (CS) | Cumple | — |
| Ordenado (OI) | RF con prioridad; RNF sin categoría ni prioridad; OI-02 sin cliente | Hoja 4 B15:B17, E15:E17 |
| Verificable (VF) | VF-01 parcial: criterios no "dado/cuando/entonces"; VF-02 falla (RNF sin métrica, correcto no inventarla) | Hoja 3 E6:E17; hoja 4 D15:D17 |
| Modificable (MD) | Cumple; hoja 6 repite "(A-10 §2; A-10-D2)" en cada celda | Hoja 6 B5:F10 |
| Trazable (TR) | TR-01 cumple; **TR-02 y TR-03 fallan** | Hoja 8 vacía |

Cambios propuestos (cuando autorices):
1. Hoja 1 B9: "Vicente Hueichapan (trabajo individual; grupo y aceptación docente pendientes)" en lugar de "Integrantes pendientes". B14: añadir A-11, N-EXTRA, N-830 y este informe.
2. Hoja 3 E6:E17: reescribir criterios en forma "Dado…, cuando…, entonces…" sin añadir comportamiento.
3. Hoja 5 A15:C15: "Integración con sistemas externos" — "No se exige en el MVP (C-01 §3.4, §5.1)" — versión futura: sí. Otros "fuera" solo con fuente.
4. Hoja 7: completar la columna de persistencia tras Guía 12; agregar lógica paso a paso de RN-01, RN-05, RN-06 en filas libres (consultar D-06 de doc 07 sobre dónde va la lógica).
5. Hoja 8: completar B6:B11 con la tabla de señales de §5; matriz A15:F24 para RF-01–10 con pantalla, componente, prueba e IE; D30:D36.
6. Hoja 9 A7:A9: marcar con X solo lo comprobado (LazyColumn, NavHost, modelos/ViewModel); B10 parcial en nota.

---

## 7. Verificaciones ejecutadas hoy

| Comando (copia temporal de `android/` sin el archivo JDK 25) | Resultado |
|---|---|
| `GradleWrapperMain --no-daemon testDebugUnitTest assembleDebug`, JDK 21.0.12, revisión `9455d57` | **BUILD SUCCESSFUL** en 43 s; 42 tareas; **15/15 pruebas** (12 inventario, 3 sesión), 0 fallos; APK debug de 12,3 MB; sin advertencias del compilador |
| `lintDebug` (límite 300 s) | Terminó en 49 s: **1 error** (`local.properties:1 PropertyEscape`, archivo local) y **9 advertencias**: `OldTargetApi` (36 vs 37), versiones nuevas disponibles (Gradle 9.8.1, compileSdk 37, activity 1.13.0, lifecycle 2.11.0, navigation 2.10.2), `allowBackup` obsoleto en Android 12+, `MissingApplicationIcon` |

Notas: en este Windows Java no puede abrir su conexión local interna; se usó la misma opción temporal del 07-10 (`JAVA_TOOL_OPTIONS=-Djdk.net.unixdomain.tmpdir=Z:\kaiju-no-unix-sockets`), solo en el proceso. Las pruebas exigían una ruta corta (límite de 260 caracteres de Windows). No recomiendo actualizar versiones ahora: compila y funciona; actualizar solo si una librería nueva lo exige.

**No verificado:** ejecución visual, muerte de proceso (H-06), compilación con JDK 25, rendimiento en dispositivo.

---

## 8. Banco de preguntas priorizado (sin duplicar P-01–28 ni G10)

**Al docente (indispensables):**

| ID | Pregunta | Origen | Decisión dependiente |
|---|---|---|---|
| P-D1 | "El formulario de producto de la Guía 10 §5 no incluye stock. Para que el producto nuevo aparezca en el catálogo (B-07), ¿lo creamos con 0 unidades disponibles y el stock entra luego con una ENTRADA (RF-06), o prefiere un campo de stock inicial que genere una entrada automática?" | A-10 l.110, l.119–125, l.177; G10-02 | H-01, B-07 |
| P-D2 | "Trabajo solo y uso únicamente GitHub. ¿Acepta el trabajo individual y cómo debo evidenciar IE 2.3.2 y las capturas de la Guía 11 sin Trello: creo un tablero propio, uso GitHub Issues/Projects, o basta el historial de commits?" | A-10 l.195; A-11 l.325; A-07 l.156 | H-03, B-09 |
| P-D3 | "¿Fecha del Parcial 2, formato AVA y cuándo recibimos las Guías 12/13 (persistencia y cámara)?" | A-10 l.11, l.194; G10-10 | Orden de incrementos 4–5 |

**Al docente (no bloquean):** G10-01 (cálculo del ajuste; C-01 §2.2 menciona "conteos", lo que sugiere fijar la cantidad contada, pero no es respuesta). G10-04: implementar literal la tabla RF-07 (administrador sin salidas) salvo indicación contraria.

**Al cliente:** la pauta de 12 preguntas de doc 08 §3 ya cubre stock inicial (5), ajustes (5, 7), umbral (8), indicadores (9), notificaciones/cámara (11). No agregar preguntas nuevas.

---

## 9. Deuda y riesgos registrados

- Toda la operación de inventario (movimientos, persistencia, recursos, animaciones) está pendiente con ≈1 semana según A-10 l.17–28; recortar el módulo propio antes que la base (A-10 l.197).
- 52 preguntas abiertas (P-01–28, D-01–14, G10-01–10): riesgo de sobrebloqueo. Solo P-D1–D3 bloquean el próximo trabajo.
- Un único commit de código: riesgo para IE 2.3.2.
- Pruebas de recursos nativos: preferir **teléfono físico por USB o Wi-Fi** en lugar del emulador; consume menos RAM y A-11 l.263 lo recomienda para la cámara.
- No se promete ausencia de fallos: H-06 está razonado, no reproducido.

---

## 10. Plan por incrementos

| # | Qué | Fuente | Archivos | Depende de | Prueba útil | Terminado cuando |
|---|---|---|---|---|---|---|
| 0 | Enviar P-D1–D3; decidir §6 en README; corregir README/doc 13 sobre lint y JDK | A-10 §6, l.179 | README.md | — | — | Preguntas enviadas; ficha de decisiones |
| 1 | Cerrar B-07 + volver a Login sin sesión (H-06) + iconos (H-07) + precio (H-11) + icono app (H-12) | A-10 l.110, l.130, l.177 | `InventarioViewModel`, `AppNavHost`, `PantallaFormularioProducto`, `PantallaLogin`, `ui/` | P-D1 o tu autorización | Alta válida aparece en catálogo; código recién creado se rechaza como duplicado | B-07 completo y demostrable |
| 2 | RF-03 búsqueda/filtro y RF-05 edición con código no editable | A-10 l.39, l.41 | VM, `PantallaCatalogo`, formulario | Decisión técnica documentada (G10-03/06 siguen abiertas para negocio) | Filtro + búsqueda combinados; edición no duplica código | Criterios hoja 3 E8, E11 |
| 3 | Movimientos: entrada y salidas con RN-01/05, historial ordenado; ajuste cuando se resuelva G10-01 | A-10 l.42–53, l.110, l.126–129 | `InventarioRepository`, VM, `PantallaMovimiento`, detalle | G10-01 solo para ajuste | Salida = disponible pasa, > disponible falla; stock e historial cambian juntos; orden descendente | Stock trazable por producto |
| 4 | Persistencia local detrás del repositorio | A-10 l.72, l.194; RD-03 | `repository/` | Guía 12 | Cerrar y reabrir conserva productos e historial; semillas solo la primera vez | RF-10 |
| 5 | Recursos: ficha README, manifiesto, notificaciones con permiso completo, escáner, 2 investigaciones §10 | A-11 l.314–336 | Manifiesto, `ui/`, VM, README | Decisión §6; teléfono físico | Conceder, rechazar, rechazar dos veces, volver de Ajustes | IE 2.4.1 demostrable |
| 6 | Animaciones con propósito (marca de stock bajo, confirmación de guardado) | A-10 l.137 | `ui/components` | 3 | Visual | IE 2.2.2 |
| 7 | Actualizar Excel (§6) y módulo propio acotado si queda tiempo | N-EXTRA l.245–250 | Copia Excel | 1–5 | Revisión con N-830 | TR-02/TR-03 cumplidos |

## 11. Qué debes saber demostrar

1. Por qué la regla RN-06 está en el ViewModel y cómo se cambiaría el umbral en un solo lugar (`InventarioViewModel.kt:20`).
2. El recorrido Pantalla → ViewModel → Repositorio → StateFlow con el formulario de producto.
3. Por qué `toIntOrNull` evita cierres con "1.5" o "2147483648".
4. Diferencia entre rotación (el ViewModel sobrevive) y muerte de proceso (se pierde; por eso existe RF-10 y por eso H-06).
5. Qué es exigencia del profesor, qué es del cliente y qué decidiste tú; por qué el stock inicial era una duda real.
6. Para cada recurso: la frase "[perfil] necesita [acción], por eso [recurso], que cumple [RF]" y qué pasa si rechazan el permiso.
