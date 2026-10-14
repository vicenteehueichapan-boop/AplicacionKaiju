# Contexto completo para auditar Kaiju con Claude

Fecha de preparación: 14-10-2026. Este documento prepara una transferencia y una auditoría independiente; no modifica funcionalidades ni acredita una nueva evaluación. Iniciar con `15-prompt-auditoria-claude.md` y leer las fuentes completas.

## 1. Objetivo y necesidad

Proyecto semestral DSY1105, Desarrollo de Aplicaciones Móviles, Duoc UC, caso canalizado por CITT San Bernardo. Kaiju administra productos, materiales e insumos que recibe, almacena, utiliza o vende. Según C-01 §2, usa registros manuales/planillas; puede haber información distribuida, olvidos y diferencias entre stock registrado y disponible. La necesidad es mejorar control y trazabilidad, consulta de existencias y detección de reposición. No se conoce el rubro específico suficiente para afirmar que es una tienda de ropa.

La calidad se evalúa por cómo las funciones sustentadas ayudan a esa necesidad, además de la evidencia académica. Una interfaz atractiva, más pantallas o más librerías no demuestran utilidad. El posible trabajo futuro con el cliente no amplía el alcance del MVP.

C-01 §4.1 declara seis señales de éxito: stock actualizado y trazable; menos quiebres/diferencias; identificar más/menos vendidos; tendencias por período/categoría/tipo; registro más rápido; disponibilidad más clara para internos y público. No hay fórmulas, períodos ni metas numéricas confirmadas. Conservar las seis en trazabilidad, señalando lo no cubierto; no inventar un dashboard ni excluirlas por no estar todas en la primera base.

## 2. Personas y entorno

- Único integrante informado: **Vicente Hueichapan**. No inventar compañeros, número de grupo, aprobación del trabajo individual, docente ni colaboradores.
- Las guías 10/11 hablan de tres integrantes. La diferencia debe aclararse con el profesor, no simularse mediante tres autores o cuentas.
- Se trabaja en Android Studio sobre Windows, notebook con 8 GB de RAM. El usuario revisa personalmente en emulador y confirmó en la conversación que las comprobaciones propuestas funcionaron. No hay acta técnica con dispositivo, fecha exacta de ejecución o capturas anexadas.
- **No iniciar emuladores automáticamente**. El usuario quiere evitar sobrecargar el notebook. Compilar/pruebas de lógica están autorizadas en el proyecto, pero no son necesarias para esta transferencia documental.
- Se conversó usar un PC de 16 GB y acceso remoto. Es una alternativa de entorno, no una funcionalidad ni una infraestructura ya configurada.

## 3. Repositorio y estado real de Git

Repositorio autorizado: https://github.com/vicenteehueichapan-boop/AplicacionKaiju.git. Raíz local: `C:/Users/Vivobook Pro 15/Desktop/SEGUNDO_SEMESTRE/01_DSY1105_Desarrollo_Aplicaciones_Moviles/ProyectoCleinte`. Proyecto Android: subcarpeta `android/`; abrir su `settings.gradle.kts`, no el ejemplo de Agenda.

Al comenzar esta transferencia, rama `main`, último commit local `d8e89b7` (documentación de entrega), precedido por `9916a92` (base Android). Ambos se subieron el 07-10. Verificar HEAD y remoto durante la auditoría: estos datos son una referencia temporal, no un estado eterno. No se consultó hoy la visibilidad remota; la última constatada fue pública y Guía 07 pide privada. No cambiarla automáticamente.

Había un archivo local **no versionado**, `android/gradle/gradle-daemon-jvm.properties`, generado por `updateDaemonJvm`, con `toolchainVersion=25`. El README documenta JDK 21. No se conoce autoría ni fecha de ese archivo. Auditar compatibilidad y diferencia entre checkout local/remoto antes de cambiar nada. Se deja intacto y fuera de los commits de esta transferencia; la carpeta de auditoría lleva copia separada en `estado-local-no-versionado/` para que pueda inspeccionarse sin activarla en otro checkout.

GitHub no reemplaza Trello. No se aportó tablero ni evidencia de acceso del profesor. `docs/13-entrega-base-android.md` §8 tiene tarjetas preparadas, no un tablero creado. No publicar, crear servicios, enviar mensajes o invitar personas como efecto automático de una auditoría.

## 4. Fuentes y cómo tratarlas

`docs/fuentes/2026-10-14/README.md` y `manifest.json` identifican las nueve fuentes originales preservadas. Caso/plantilla/guías 9–10/cuestionario/guía extra coinciden exactamente con las versiones anteriores. La Guía 11 es nueva. Las guías 07/08 se incluyen como antecedentes. Diagramas de Guía 10: `docs/fuentes/2026-10-07/capas-proyecto.png` y `mapa-navegacion.png`.

Distinguir siempre:

1. Hechos y necesidades explícitos del caso.
2. Resultados esperados sin detalle suficiente.
3. Opciones condicionales o evolución futura.
4. Restricciones del caso.
5. Instrucciones académicas explícitas del profesor.
6. Decisiones técnicas reversibles del equipo.
7. Respuestas validadas del cliente/docente, cuando existan.
8. Preguntas y propuestas pendientes.

No hay acta ni respuestas comerciales nuevas recibidas. La Guía 10 permite construir la base académica antes de cerrar la entrevista; no implica que el cliente haya validado sus supuestos. Los documentos antiguos que dicen «no programar» registran una etapa histórica: la instrucción posterior autorizó la base. Ninguna auditoría previa es infalible ni reemplaza la fuente.

Los ejemplos de Agenda y Club Los Aromos enseñan razonamiento y estructura. No trasladar reservas, cupos, bloqueos, plazos, cantidades predeterminadas o pantallas ajenas a Kaiju. El ejemplo docente local está ignorado por Git; la carpeta de auditoría contiene una referencia separada a su código, sin modificarlo. No compilarlo ni convertirlo en nuestra app por defecto.

## 5. Base académica explícita de Guía 10

### RF, fuente A-10 §1

| ID | Exigencia | Perfil indicado |
|---|---|---|
| RF-01 | Ingresar con usuario de prueba, conocer perfil activo y mostrar lo correspondiente. | Todos |
| RF-02 | Catálogo con nombre, categoría, precio y cantidad disponible. | Todos |
| RF-03 | Buscar por nombre/código y filtrar por categoría. | Todos |
| RF-04 | Detalle con historial de movimientos, más recientes primero (§2). | Todos |
| RF-05 | Crear y editar productos con formulario validado. | Administrador |
| RF-06 | Registrar entrada por llegada de mercadería. | Inventario y administrador |
| RF-07 | Registrar salida por venta o consumo. | Vendedor para ventas; inventario, con detalles por aclarar |
| RF-08 | Ajuste de inventario indicando motivo. | Inventario y administrador |
| RF-09 | Marcar stock bajo y listar alertas; tocar alerta abre detalle (§2). | Inventario y administrador |
| RF-10 | Mantener datos al cerrar y reabrir mediante persistencia local. | Todos |

No añadir derechos del administrador a las salidas por herencia implícita. La marca en catálogo también aparece en B-06 y RF-02 es para todos; revisar presentación y acceso según ambas exigencias. RF propios continúan desde RF-11 cuando estén definidos.

### Reglas, fuente A-10 §1

- RN-01: salida/venta no deja disponibilidad bajo cero.
- RN-02: precio mayor a cero; mínimo mayor o igual a cero.
- RN-03: código obligatorio y único.
- RN-04: ajuste exige motivo.
- RN-05: movimiento registra fecha, tipo, cantidad y autor; no editar/borrar movimientos; corregir con ajuste.
- RN-06: alerta cuando cantidad disponible es igual o menor al mínimo.

El profesor identifica umbral RN-06 y permisos como supuestos revisables tras entrevista. Usarlos para la base académica con ese origen, sin presentarlos como reglas comerciales firmadas.

### Modelo y validaciones, A-10 §4–5

- Producto: `codigo, nombre, descripcion, categoria, tipo, precio, stockActual, stockMinimo, detalle`. Detalle es texto opcional; no implementar variantes/lotes por su sola mención.
- Movimiento: `id, codigoProducto, tipo, cantidad, motivo, fecha, usuario`. Tipos: ENTRADA, SALIDA_VENTA, SALIDA_CONSUMO, AJUSTE.
- Usuario: `id, nombre, rol`. Roles: ADMINISTRADOR, VENDEDOR, ENCARGADO_INVENTARIO.
- Precio `Int` en CLP, sin decimales. Diez productos y tres usuarios ficticios, uno por rol; incluir stock bajo.
- Login exige selección. Producto exige código no repetido, nombre de al menos tres caracteres, categoría elegida, precio entero positivo y mínimo entero no negativo.
- Movimiento exige producto/tipo elegidos, cantidad entera positiva, salida no superior al disponible y motivo en ajuste.
- Validación en ViewModel con estado/errores; retroalimentación visual por campo e iconos.
- Cada movimiento futuro actualiza stock e historial en la misma operación; no dejar escrituras parciales.

### Arquitectura, A-10 §3 y diagramas

MainActivity llama a AppNavHost. `navigation/`: Rutas y AppNavHost. `model/`: modelos/enums y estados/errores de formularios. `repository/`: InventarioRepository y DatosDePrueba. `viewmodel/`: SesionViewModel e InventarioViewModel. `ui/screen/`: seis pantallas; `ui/components/`: TarjetaProducto e IndicadorStock; `ui/theme/`: tema.

Pantalla presenta estado y envía eventos; ViewModel valida RN antes de llamar al repositorio; repositorio guarda/entrega. Estado mediante StateFlow. Repositorio en memoria en esta etapa; después persistencia. No añadir Clean Architecture, servidor, frameworks de inyección o módulos Gradle por costumbre. Guía 11 precisa que permisos/Activity viven en UI y resultados/reglas pasan al ViewModel.

Rutas: Login, Catálogo, DetalleProducto, FormularioProducto, Movimiento, Alertas. Menú inferior: Catálogo/Movimiento/Alertas según perfil. La guía permite títulos provisionales en pantallas aún no desarrolladas (§7); esto no acredita funciones finales.

## 6. Primera revisión y Parcial 2

| B | Lo pedido | Estado comprobable del código / evidencia disponible |
|---|---|---|
| 01 | Abre y corre en emulador. | Compiló 07-10; usuario informó funcionamiento práctico después. Evidencia manual declarada, no nueva prueba automática. |
| 02 | Estructura §3. | Creada, inspeccionable. |
| 03 | Modelos/enums. | Creados, inspeccionables. |
| 04 | Diez productos/tres usuarios ficticios y stock bajo. | Semillas creadas, inspeccionables. |
| 05 | Seis rutas y menú. | Implementados; usuario confirmó la pauta en general. |
| 06 | Catálogo desde ViewModel con marca. | Implementado; misma confirmación manual. |
| 07 | Validación y producto nuevo visible en catálogo. | **Parcial**: validación; no guarda por stock inicial pendiente. |
| 08 | README con equipo, descripción y decisiones §6. | **Parcial**: Vicente, descripción/tema; recursos/animación propuestos, módulo/grupo pendientes. |
| 09 | Commits de integrantes y Trello actualizado. | **Parcial**: commits propios en GitHub, tablero/modalidad individual pendientes. |

No presentar 9/9 ni una nota inventada. B-01–06 tienen distintos tipos de evidencia; la declaración del usuario sobre funcionamiento no prueba alta, persistencia ni recursos inexistentes. Los contadores «0 de 9» y «0 de 35» del texto son estado del navegador de la guía, no medición del repositorio.

La sección 6 pide justificar dos recursos, animaciones, un módulo propio e identidad visual en README. Son decisiones académicas pendientes de concretar, no una lista de funcionalidades comerciales adicionales ya elegidas. La siguiente etapa incluye movimientos completos, persistencia, recursos/animaciones y cierre de la base. Parcial 2: semana 11 según A-10, sin fecha exacta/AVA aportados; no calcular un vencimiento a partir de hoy.

| Indicador | Peso | Validación requerida |
|---|---|---|
| IE 2.1.1 | 15% | Interfaz/navegación y coherencia visual. |
| IE 2.1.2 | 15% | Login/producto/movimiento y demás formularios aplicables, errores por campo e iconos. |
| IE 2.2.1 | 10% | Validación/RN en lógica, no en pantallas. |
| IE 2.2.2 | 10% | Animaciones justificadas por comunicación de estado/resultado. |
| IE 2.3.1 | 15% | Capas y persistencia local, no solo nombres de carpetas. |
| IE 2.3.2 | 20% | GitHub/Trello, participación real y continuidad. |
| IE 2.4.1 | 15% | Dos recursos seguros, funcionales y coherentes. |

Fuente: A-10 §9 y plantilla hoja 8. No convertir estos porcentajes en nota propia sin la rúbrica completa de evaluación.

## 7. Lo nuevo de Guía 11

A-11 §0/1/7: al menos dos recursos del dispositivo coherentes con la necesidad. Completar «[perfil] necesita [acción], por eso [recurso], vinculado a [RF]». No elegir ubicación/contactos/micrófono/fotos porque figuren en una tabla genérica.

A-11 §4–7: permisos en contexto, explicación cuando corresponda, alternativa al rechazo, verificación antes de usar y al volver de Ajustes; manejo de hardware ausente, cancelación y ciclo de vida; liberar sensores/cámara cuando no se usan. Mantener Activity/permisos en UI y procesar resultado/reglas en ViewModel. No guardar datos de personas ni pedir permisos innecesarios.

A-11 §9 pide:

1. Ficha README por cada recurso: necesidad del caso, recurso/API, permiso/tipo, RF y alternativa al rechazo.
2. Manifiesto/uses-feature pertinentes, flujo de permiso de ejecución para al menos un recurso. Si ambos no lo necesitan: flujo para notificaciones de stock bajo **o justificar por qué no aplica en README**, como permite la fuente. No convertir esto en obligación automática de un tercer recurso.
3. Probar concesión, rechazo y rechazo reiterado; capturas en Trello: permiso concedido y alternativa tras rechazo.
4. Commit descriptivo; mensaje sugerido `feat: recurso nativo con manejo de permisos` es sugerencia, no evidencia de ejecución.

A-11 §10 pide elegir **al menos dos** investigaciones de su lista y aplicarlas con una línea comentada en el código. Opciones: RequestMultiplePermissions, code scanner, LocalHapticFeedback, canales de notificación, acceso parcial de fotos/Photo Picker, observación de ciclo de vida y reinicio de permisos. No implementar todas ni añadir funciones para poder usar una API.

El texto incluye simplificaciones y ejemplos técnicos: Claude debe contrastar con documentación oficial actual permisos de cada API, hardware/versión Android, rationale/rechazo permanente, supervivencia ante muerte de proceso frente a rotación y TakePicture frente a CameraX. Si algo es incompatible, explicar la discrepancia y mantener el objetivo académico; no copiar una receta incorrecta ni atribuir una corrección técnica al cliente. Las guías 12 y 13 oficiales mencionadas aún no están en este paquete.

## 8. Documentación esperada y entrevista

Fuente N-EXTRA §7–8: antes de reunión, hojas 1/2 con contexto, problema, objetivo, glosario y roles; borrador; **al menos ocho preguntas**, priorizadas, con origen, y roles de conversación. Después: acta con fecha/asistentes/preguntas/respuestas; al menos ocho RF, tres RNF y tres RN con IDs/prioridad/origen/aceptación; alcance justificado y pendientes; modelo y lógica; trazabilidad a señales de éxito.

Las cuotas no autorizan inventar reglas ni métricas para llenar filas. Mantener responsables/fechas «por acordar» cuando no existan. Diferenciar pregunta al docente sobre evaluación de pregunta al cliente sobre negocio.

Plantilla original: Instrucciones + hojas 1 Introducción y Contexto, 2 Roles, 3 Requerimientos (RF), 4 Reglas/RNF/RD, 5 Alcance, 6 Pantallas, 7 Modelo, 8 Trazabilidad, 9 Checklist/Aceptación. Preservar nombres, celdas blancas fijas, IDs RD, listas, fórmulas/pesos/estilos; completar campos amarillos previstos en copia de trabajo. No reemplazar Excel por un documento libre.

RD fijas: Android/Kotlin/Studio/Compose; MVVM y validación en ViewModel; persistencia Room y/o DataStore; dos recursos; datos ficticios/sintéticos/anonimizados y sin accesos del cliente. Son académicas, no respuestas de entrevista.

Cuestionario N-830: correcto, no ambiguo, completo, consistente, ordenado por importancia/estabilidad, verificable, modificable y trazable. Revisar IDs originales C/NA/CP/CS/OI/VF/MD/TR/SF, conservando «cuando corresponda» de CP-02: no fuerza CRUD completo. Marca «confirmado por cliente» solo con respuesta identificable. La hoja 9 es seguimiento académico, no SRS. No presentar entrevista o impactos medidos si no ocurrieron.

## 9. Implementación y verificaciones históricas

Código inspeccionado en esta transferencia: base Compose, dos ViewModels, repositorio en memoria; tres usuarios/diez productos ficticios; selección validada, catálogo, detalle, alertas y formulario. RN-06 centralizada. Categorías/tipos de semillas son de ensayo, no catálogo comercial. Sin permiso nativo declarado actualmente.

`guardarProducto` valida rol y campos y retorna sin guardar cuando `stockInicialAcordado` es null. En navegación no se proporciona valor. El test con 7 unidades pasa un dato explícito solo en ensayo; no es política aprobada. No presentar ese test como B-07 cumplido. Editar no existe; buscar/filtrar no existen; Movimiento es título/aviso; historial comienza vacío; persistencia/recursos/animaciones/módulo propio no existen.

Histórico 07-10: APK generado y 15 pruebas aprobadas (12 inventario, 3 sesión). Lint no concluyó: atasco observado en BidirectionalTextDetector/KotlinUFile; se detuvo para liberar recursos. No hay lint verde ni prueba automatizada de todas las rutas. No se recompiló hoy ni se atribuye vigencia automática a esas pruebas.

Configuración versionada: AGP 9.3.3, Gradle 9.5.0, Compose plugin 2.3.20, Kotlin integrado; BOM 2026.02.01; activity 1.12.4, lifecycle 2.10.0, navigation 2.9.7; compile/target 36, min 26, bytecode Java 17; paquete técnico provisional `cl.academico.kaiju`; Gradle 2 GB/dos trabajadores. Revisar con fuentes oficiales antes de migrar. Archivo local Java 25 ya señalado. No actualizar dependencias solo por existir una más reciente.

## 10. Pendientes que no deben rellenarse solos

G10-01 cálculo del ajuste; G10-02 stock inicial y posible movimiento inicial; G10-03 edición/normalización del código y vínculo del historial; G10-04 variantes de salida/permisos; G10-05 categorías y obligatoriedad de descripción/tipo/detalle; G10-06 semántica de búsqueda; G10-07 fecha/identidad de movimiento/desempate; G10-08 sesión y condición de reapertura; G10-09 alcance de la revisión; G10-10 fecha/AVA/módulo/persistencia. Preguntas completas en doc 11; P-01–28 en doc 02 y kit de entrevista.

No bloquear todo el trabajo por un pendiente parcial. Tampoco convertir toda decisión de presentación en pregunta comercial: evaluar si una elección técnica reversible cabe en la exigencia explícita y registrar su origen. Las decisiones que cambian efectos sobre stock, permisos de negocio, datos obligatorios, métricas comerciales o alcance deben tener evidencia suficiente. La auditoría debe poder cuestionar tanto una suposición implementada como un bloqueo previo excesivo.

## 11. Alcance del paquete

La carpeta `output/claude/paquete-auditoria-kaiju/` contiene fuentes originales, documentos propios, plantilla original/copia previa, código Android y tests versionados. Añade referencia docente y archivo local no versionado separados, sin cachés/build/credenciales/configuración SDK. No incluye APK de depuración ni `.git`: es una instantánea del 14-10, no un reemplazo del historial remoto ni del proyecto activo. `INVENTARIO-PAQUETE.json` lista cada archivo y hash. Empezar por `LEER-PRIMERO.md`.

Faltan acta de cliente, respuestas identificables a pendientes, material ficticio prometido por C-01 §6, encargo/rúbrica completos de AVA, guías 12/13, capturas y tablero Trello, grupo y aceptación del trabajo individual. Pedir solo los necesarios para el siguiente incremento. No prometer software sin fallos o deuda cero: buscar problemas verificables, corregirlos con pruebas pertinentes y registrar lo que quede.
