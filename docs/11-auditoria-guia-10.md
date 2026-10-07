# Auditoría de la Guía 10 y plan de avance Kaiju

Fecha: 07-10-2026. Fuente A-10: texto completo de «Guía N°10 Proyecto Kaiju: base del proyecto y avance», proporcionado por el usuario en esta conversación. Se cita por sección; no se recibió su archivo original ni el contenido de sus diagramas interactivos. Estado: análisis y planificación, sin programación.

## 1. Conclusión y autoridad de la fuente

La Guía 10 establece una base académica explícita para avanzar aun con entrevista pendiente. Ya no corresponde mantener todos los comportamientos de esa base bloqueados por ausencia de detalle en C-01. Sí corresponde distinguir su origen: la guía presenta una «solicitud base del cliente», pero la evidencia recibida es una instrucción docente, no una nueva respuesta identificable de Kaiju ni un acta validada.

Origen de trabajo propuesto: «A-10 §n, base académica; validación comercial pendiente». La guía identifica expresamente RN-06 y permisos por perfil como supuestos que pueden cambiar tras la entrevista. Las demás reglas son explícitas en la guía; no llamarlas acuerdos comerciales ya validados ni supuestos inventados por el equipo.

La solicitud actual del usuario es analizar antes de programar. Por eso no se crean aplicación, carpetas Kotlin, Trello, dependencias ni funcionalidades en esta revisión. Las actividades imperativas de la guía se registran como entregables posteriores, no como órdenes que debamos ejecutar ahora.

## 2. Qué se exige y cuándo

| Momento | Exigencia | Fuente | Límite |
|---|---|---|---|
| Sesión de armado | Organizar modelo/repositorio/datos; navegación y catálogo; tarjetas por pantalla y formulario, repartidas; al menos un commit de cada uno de los tres integrantes. | A-10 §7 | El mensaje de commit es sugerido. No inventar autores, commits ajenos ni reparto nominal. |
| Primera clase de semana 10 | Demostrar B-01 a B-09 en emulador. | A-10 §8 | Es una base funcionando, no solo documentación. Las pantallas pendientes pueden tener título según §7; el catálogo y formulario de creación sí tienen comportamiento exigido. |
| Después de la revisión, semana 10 | Formulario de movimientos con reglas, pantalla de alertas, persistencia local, dos recursos nativos y animaciones. | A-10 §8, guías 12 y 13 anunciadas | Las guías 12 y 13 no se han recibido en esta revisión. No copiar versiones o anticipar requisitos de su contenido. |
| Semana 11 | Parcial 2, GitHub, Trello y AVA. | A-10 §0 y §9 | Falta fecha/hora exacta y formato de entrega AVA. No calcularlos a partir de la fecha actual. |

La numeración de semana del esquema y el número de guía son cosas distintas. «0 de 9 puntos» es estado de la interfaz de la guía; no evidencia de nuestro avance.

## 3. Qué tenemos y brecha de la próxima revisión

Comprobación local del 07-10-2026: repositorio con documentación, fuentes, Excel preliminar, kit PDF y ejemplo Agenda excluido; no se encontraron archivos de una aplicación Android propia fuera del ejemplo. Historial local consultado con un único nombre de autor visible. Git estaba limpio y alineado con su referencia local de origin/main; esto no es una consulta de cambios remotos en vivo. No se verificó un tablero externo de Trello ni el Android Studio/emulador instalado.

| ID | Qué revisará el profesor | Evidencia actual | Estado |
|---|---|---|---|
| B-01 | Proyecto abre y corre en emulador sin errores. | No hay app Android propia en este espacio. | Pendiente. |
| B-02 | Carpetas y archivos de §3. | Hay documentos de arquitectura anterior, no la estructura solicitada creada. | Pendiente. |
| B-03 | Producto, Movimiento, Usuario y enums. | Análisis conceptual anterior; no clases Kotlin propias. | Pendiente. |
| B-04 | Al menos 10 productos y 3 usuarios ficticios, uno por perfil, incluyendo stock bajo. | Hay ejemplos de apariencia; no conjunto de datos cargado en la app. | Pendiente. |
| B-05 | Seis rutas y menú inferior funcionando. | No NavHost propio. El diagrama interactivo no vino en el texto. | Pendiente. |
| B-06 | Catálogo desde ViewModel y marca de stock bajo. | Prototipos Lovable no acreditan implementación Android. | Pendiente. |
| B-07 | Validación de producto en ViewModel y producto nuevo visible en catálogo. | No formulario ni lógica propios. | Pendiente. |
| B-08 | README con nombres, descripción y decisiones justificadas de §6. | Descripción presente; nombres/grupo y decisiones finales faltan. | Parcial. |
| B-09 | Commits de todos los integrantes y Trello actualizado. | Git inicializado, remoto y commits propios; faltan evidencias de todo el equipo y tablero. | Parcial. |

No hay ningún B completo acreditado por las evidencias revisadas. Esto no invalida el trabajo documental: tenemos auditoría, preguntas, reglas de cuidado, preparación de entrevista, copia Excel preliminar, kit de reunión y exploración visual. Son insumos; no sustituyen la demostración B-01 a B-09.

## 4. Requerimientos y reglas que ahora sí están escritos

Numeración siguiente: la de A-10, no la del borrador del 04-10. Se reservará para la versión nueva de la plantilla, conservando la equivalencia histórica.

| ID A-10 | Capacidad explícita | Fuente / precisión |
|---|---|---|
| RF-01 | Seleccionar usuario de prueba, ingresar y adaptar lo visible a su perfil. | §1-2; §5 exige selección. No contraseña, registro, recuperación, servicio externo ni gestión completa de usuarios. |
| RF-02 | Catálogo con nombre, categoría, precio y cantidad disponible. | §1-2; «todos» dentro de los perfiles base, no prueba de acceso anónimo del público. |
| RF-03 | Buscar por nombre o código y filtrar por categoría. | §1-2. Semántica concreta de búsqueda pendiente. |
| RF-04 | Detalle y movimientos del producto, recientes primero. | §1-2. No gráfico ni edición del historial. |
| RF-05 | Crear y editar productos con formulario validado, solo administrador. | §1-2 y §5. No borrar productos ni CRUD de categorías. |
| RF-06 | Entrada, inventario y administrador. | §1; efecto en stock en §4. |
| RF-07 | Salida por venta o consumo; personal de venta para ventas y encargado de inventario. | §1. La tabla no concede expresamente esta acción al administrador ni aclara si inventario hace ambas variantes; preguntar. |
| RF-08 | Ajuste con motivo, inventario y administrador. | §1; forma matemática del ajuste pendiente. |
| RF-09 | Marca de stock bajo y pantalla de alertas con acceso a detalle. | §1-2. Alerta dentro de app no equivale a notificación del dispositivo. |
| RF-10 | Conservar datos al cerrar y reabrir la app. | §1. Exigencia final; repositorio en memoria es etapa inicial indicada en §3/§8. |

| ID A-10 | Regla base | Fuente |
|---|---|---|
| RN-01 | Una salida o venta no deja cantidad disponible negativa. | §1. No define por sí sola todos los límites de un ajuste. |
| RN-02 | Precio > 0 y stock mínimo >= 0. | §1; enteros y precio en pesos chilenos en §4-5. |
| RN-03 | Código obligatorio y único. | §1. Editar el mismo producto o cambiar su código requiere precisión. |
| RN-04 | Ajuste exige motivo. | §1. No vuelve obligatorio el motivo en otros tipos. |
| RN-05 | Movimiento conserva fecha, tipo, cantidad y autor; no se edita ni elimina; errores se corrigen con ajuste. | §1. No exige datos personales reales ni auditoría remota. |
| RN-06 | Stock bajo cuando cantidad disponible <= stock mínimo. | §1. La guía declara este umbral supuesto revisable tras entrevista. |

Validaciones adicionales explícitas en §5: nombre obligatorio de al menos tres caracteres, categoría elegida de lista, producto y tipo de movimiento elegidos, cantidad entera positiva; salidas no superiores a disponible. Login requiere usuario seleccionado. Retroalimentación por campo e íconos, con validación en ViewModel. No introducir longitudes, máximos o normalización no definidos.

## 5. Pantallas, datos y arquitectura

Seis pantallas nombradas en §2: Login, Catálogo, DetalleProducto, FormularioProducto, Movimiento y Alertas. Tres destinos van en menú inferior, pero el texto no identifica inequívocamente cuáles: falta el diagrama. La pantalla Movimiento sirve para los cuatro tipos, no cuatro pantallas adicionales obligatorias. Crear/editar usa un mismo formulario base.

Modelo requerido por §4:

- Producto: codigo, nombre, descripcion, categoria, tipo, precio, stockActual, stockMinimo, detalle. Precio Int en pesos chilenos; detalle texto opcional. El formato de un texto de vencimiento no confirma filtrado por fechas.
- Movimiento: id, codigoProducto, tipo, cantidad, motivo, fecha, usuario. TipoMovimiento: ENTRADA, SALIDA_VENTA, SALIDA_CONSUMO, AJUSTE.
- Usuario: id, nombre, rol. Rol: ADMINISTRADOR, VENDEDOR, ENCARGADO_INVENTARIO. Los tres usuarios son personajes ficticios, no integrantes reales del grupo.
- Estados de formulario y errores según §3 y §5. La estructura enumera archivos de estado; §5 pide también clases de errores, sin imponer archivo separado para cada una.

Estructura base de §3: MainActivity, navigation (Rutas/AppNavHost), model, repository (InventarioRepository/DatosDePrueba), viewmodel (SesionViewModel/InventarioViewModel) y ui (screen/components/theme). Rutas como sealed class según la guía. No reutilizar paquete o versiones de Agenda.

Responsabilidades para el futuro desarrollo:

| Parte | Responsabilidad | Cuidado concreto |
|---|---|---|
| UI y componentes | Mostrar estado/errores, recibir interacción, avisar al ViewModel. | No calcular stock ni decidir validez. Un if de presentación para mostrar un error no es una validación de negocio. |
| SesionViewModel | Usuario de prueba activo y perfil. | Selección de prueba no es autenticación productiva. Definir alcance de la sesión sin asumir sesión persistente. |
| InventarioViewModel | Validar formularios y RN, preparar estado y coordinar operación con repositorio. | Una sola definición de cada regla; no repetir cálculo RN-06 en tarjeta, filtro y pantalla. |
| Repository | Entregar y guardar datos; empezar en memoria y sustituir almacenamiento después. | Stock y movimiento deben guardarse como una unidad: evitar que solo uno cambie. La transacción de almacenamiento pertenece a datos; no duplicar RN en UI. |
| Model | Datos y estados definidos por guía. | No introducir variantes, tablas o campos nuevos por costumbre. |
| Navigation | Rutas, menú y alcance compartido de ViewModels. | Evitar instancias separadas que pierdan el producto recién creado al navegar. |

§4 exige que stock e historial cambien en la misma operación. Separar sus escrituras sin garantizar consistencia generaría deuda y errores. El detalle de transacciones se decidirá al elegir persistencia; no se introduce un backend ni una nueva capa ahora. Un repositorio en memoria es una etapa planificada, no una promesa de que ya cumple RF-10.

## 6. Conflicto de IDs y tratamiento de los documentos anteriores

El borrador del 04-10 reservaba quince RF con otra numeración. A-10 ordena usar RF-01 a RF-10 para la base y comenzar los propios desde RF-11. No sobrescribir significados ni conservar dos requisitos activos con el mismo ID.

| RF histórico del borrador 04-10 | Relación con A-10 | Tratamiento propuesto |
|---|---|---|
| 01 consulta productos | RF-02 | Integrar capacidad en catálogo. |
| 02 registrar productos | RF-05 | Crear cubierto; edición ahora explícita por guía. |
| 03 consultar disponibilidad | RF-02 | Integrar sin duplicar requisito. |
| 04 ingresos | RF-06 | Renumerar con origen conservado. |
| 05 salidas | RF-07 | Precisar venta/consumo según base. |
| 06 ajustes | RF-08 | Añadir solo lo explicitado; operación matemática pendiente. |
| 07 registro de ventas | RF-07 parcialmente | La base trata salida por venta; no confirma documento comercial completo. |
| 08 revisar movimientos | RF-04 | Historial por producto, orden ahora explícito. |
| 09 gestionar categorías | No equivalente completo | Seleccionar categoría no equivale a gestionarla. Conservar necesidad documental pendiente. |
| 10 gestionar usuarios | No equivalente completo | Elegir usuario de prueba no equivale a administrar usuarios. |
| 11 gestionar indicadores | No cubierto por base | Mantener pendiente; evitar duplicidad con análisis concreto. |
| 12 más vendidos / 13 menos vendidos / 14 tendencias | Candidatos a módulo propio | No asignar automáticamente RF-11 a todos; elegir y precisar con evidencia. |
| 15 detectar reposición | RF-09 | Regla base RN-06 de origen docente, no respuesta del cliente. |

El Excel y kit PDF del 04-10 se conservan como versión histórica previa a A-10, no como especificación actualizada. Próxima tarea documental: generar una versión nueva con equivalencias, fuente y estado; revisar las preguntas parcialmente resueltas por la base académica. No borrar preguntas comerciales ni marcarlas como respondidas por el cliente. Actualizar también alcance, modelo, responsabilidades y trazabilidad; evitar que documentos antiguos bloqueen conductas ahora exigidas por el curso.

## 7. Ambigüedades concretas que siguen abiertas

| ID | Pregunta necesaria | Por qué y cuándo |
|---|---|---|
| G10-01 | ¿Ajustar significa sumar, restar o fijar la cantidad final? Si cantidad debe ser positiva, ¿cómo se representa una corrección que disminuye stock? ¿Qué límites tiene? | §1 RN-04/05, §4 y §5 no resuelven la operación. Bloquea ajuste, no catálogo/formulario base. |
| G10-02 | ¿Qué stock inicial debe tener un producto nuevo y cómo se establece? ¿Se registra un movimiento inicial? | Modelo tiene stockActual pero formulario no enumera ese campo. Aclarar antes de cerrar B-07; no imponer cero ni inventar campo por comodidad. |
| G10-03 | ¿Se permite cambiar el código al editar? ¿Cómo se conserva el vínculo del historial? ¿La unicidad ignora mayúsculas o espacios? | Código es vínculo en Movimiento; falta política de edición/normalización. Crear admite exigir unicidad explícita, sin inferir normalizaciones. |
| G10-04 | ¿Cuáles son los tres destinos del menú inferior y cómo cambia por perfil? ¿Administrador puede registrar salidas? ¿Inventario puede registrar ventas y consumo? | Diagrama no incluido y RF-07 no da matriz inequívoca. Necesario para cerrar B-05 y permisos. |
| G10-05 | ¿De dónde sale la lista de categorías? ¿Qué se exige en descripción/tipo/detalle? | Campos del modelo exceden los validados de §5. No construir CRUD de categorías ni obligatoriedades nuevas. |
| G10-06 | ¿Búsqueda exacta, parcial y sensible a mayúsculas? ¿Cómo se combina con el filtro y qué estado inicial se espera? | RF-03 define capacidad, no semántica. Precisar antes de cerrar búsqueda; no convertir preferencias del equipo en negocio confirmado. |
| G10-07 | ¿Qué fecha representa fecha, cómo se genera el id y qué información del usuario queda en el movimiento? ¿Cómo se desempata el historial? | Tipos/políticas no detallados. Identificar decisiones técnicas reversibles y consultar significado de negocio. |
| G10-08 | ¿Qué se conserva además del inventario: movimientos y sesión? ¿Qué significa cerrar/reabrir para la evaluación? | RF-10 y RN-05 sustentan conservar inventario e historial; persistencia de sesión no expresa. Confirmar condición de prueba, no asumir sesión recordada. |
| G10-09 | ¿Qué grado de funcionamiento de login, búsqueda y edición se revisa en semana 10, además del catálogo/creación? ¿Las otras rutas con título cumplen como indica §7? | §8 exige seis rutas; luego posterga lógica de movimientos/alertas. Distinguir navegación de implementación final. |
| G10-10 | ¿Fecha/hora de revisión y Parcial 2, formato AVA, alcance mínimo de módulo propio, y criterios de elección de persistencia de guías 12/13? | No aportados. La guía pide módulo pero recomienda recortarlo si falta tiempo; no asumir eliminación total aceptada. |

Dirigir primero al docente las aclaraciones de la base académica; validar significado y necesidades del negocio con el cliente cuando corresponda. Si el equipo recibe autorización para decidir un detalle técnico, documentarlo con ese origen. Estas preguntas precisan lo ya pedido, no proponen módulos adicionales.

## 8. Decisiones propias exigidas por la sección 6

Se deben justificar en README: dos recursos nativos, animaciones, un módulo propio e identidad visual. No se han elegido todavía. Para revisión B-08 sí deben estar documentadas las decisiones, aunque su integración se trabaje después.

- Recursos: relacionar necesidad, recurso y RF. Cámara, galería, notificaciones y vibración son ideas, no elecciones hechas. Fotografías implican ampliar modelo/flujo de la base: no agregarlas automáticamente. Notificación del dispositivo necesita definición propia; RF-09 solo exige alertas dentro de la app.
- Animaciones: elegir qué cambio/resultado comunican; no añadirlas solo para aumentar efectos. Su obligación académica no fija animación concreta.
- Módulo: analizar candidatos con apoyo en C-01, como identificación de más/menos vendidos o tendencias; precisar medida y período antes de programar. Público y vencimiento siguen condicionados y no se incorporan por aparecer como ideas. La lista de ideas no exige todos los módulos.
- Identidad: nombre, colores y tipografía son decisiones del equipo, con contraste y legibilidad. Lovable sirve como referencia de apariencia; no entrega implementación Compose ni decide reglas.

## 9. Proceder por incrementos y con evidencia

Orden propuesto del equipo, no calendario inventado:

1. **Conciliar documentación.** Nueva versión de requisitos/IDs y preguntas G10; completar equipo/grupo y confirmar convocatoria. Salida: base académica diferenciada de validación comercial y decisiones técnicas.
2. **Definir el plan compartido.** Asignar personas reales; preparar tarjetas por pantalla y formulario vinculadas a RF/RN/B; decidir §6 con justificación. No duplicar alcance porque pantalla y formulario tengan tarjetas distintas.
3. **Crear base Android propia cuando el usuario indique desarrollo.** Verificar herramientas/versiones compatibles, estructura §3 y ejecución mínima. Salida: B-01/B-02 con evidencia, sin copiar dependencias antiguas.
4. **Modelo y repositorio en memoria.** Modelos/enums y al menos diez productos/tres usuarios ficticios; catálogo desde ViewModel. Salida: B-03/B-04 y base de B-06; datos iniciales coherentes y reconocibles como prueba.
5. **Sesión y navegación.** Seis rutas, destinos inferiores aclarados, sesión de prueba y estado compartido. Títulos provisionales donde permite §7. Salida: B-05, sin pérdida de estado al navegar.
6. **Catálogo y formulario de producto.** Marca RN-06, validaciones §5, creación visible inmediatamente; aclarar G10-02 antes de fijar stock inicial. Búsqueda/detalle/edición según alcance de revisión aclarado, siempre obligatorios para la base final. Salida: B-06/B-07 y verificación por campo.
7. **Cerrar revisión de semana 10.** Demostrar nueve controles y completar README/Git/Trello. Ningún integrante puede acreditar trabajo de los otros con commits impersonados.
8. **Completar operación de inventario.** Detalle/historial, movimientos, permisos y pantalla de alertas; resolver ajuste; aplicar RN-01 a RN-06 y guardar stock/movimiento juntos. No duplicar registro por tratar salida-venta como dos operaciones.
9. **Persistencia local.** Cambiar almacenamiento detrás del repositorio, conservar contrato útil y probar cierre/reapertura con datos e historial. No mezclar almacenamiento con pantallas ni recargar semillas encima de cambios guardados.
10. **Recursos y animaciones.** Implementar decisiones §6 con permisos/errores pertinentes, según guías recibidas. El módulo propio se desarrolla con detalle suficiente y se acota sin recortar la base obligatoria.
11. **Preparar Parcial 2.** Comprobar RF/RN, revisión integral en emulador, trazabilidad a rúbrica y entrega según AVA/GitHub/Trello. Registrar límites reales, no mostrar como listo un placeholder.

Responsabilidades sugeridas, sin asignar nombres: A modelo/datos/repositorio; B navegación/sesión/catálogo/detalle; C formularios/estados/validaciones. Revisar juntos contratos y cambios que cruzan responsabilidades; cada bloque debe terminar integrado, no tres aplicaciones aisladas. Reparto orientativo basado en §7, a confirmar por el equipo real.

## 10. Prevenir deuda técnica y comprobar lo que importa

No es responsable prometer deuda cero. Podemos reducirla con decisiones explícitas, incrementos pequeños y verificaciones concretas:

- Una fuente de verdad para inventario y una instancia compartida donde corresponda. Las pantallas no mantienen copias editables independientes del stock.
- Validaciones y RN en ViewModel, según exigencia docente; sin repetir lógica en UI. No añadir capas, frameworks de inyección o servicios sin necesidad.
- Operación consistente de stock/historial, primero en memoria y después en almacenamiento. Un fallo no debe dejar escritura parcial.
- No sobrecargar detalle opcional con lógica de variantes/fechas que no se haya definido.
- Datos de prueba separados, reproducibles, con alertas desde el inicio; ningún dato real del cliente o persona.
- Mantener IDs y origen para que un cambio de umbral/permisos sea localizable. Separar responsabilidades facilita cambios, pero no garantiza que todo cambio futuro afecte solo un archivo: persistencia o modelo también pueden necesitar adaptación.
- Verificar conversiones y límites sin desbordamientos; entradas numéricas inválidas producen errores de campo, no cierres de app. No fijar límites comerciales por límites del tipo numérico sin documentarlos.
- Pruebas pertinentes al comportamiento: duplicado frente a edición del mismo producto (una vez precisada), precio cero, mínimo cero, nombre corto, salida igual a disponible o superior, motivo ausente en ajuste, igualdad stock/mínimo, historial y permisos. Para ajuste usar reglas aclaradas, no fórmulas inventadas.
- Comprobar que volver del formulario muestre el producto, navegar no reinicie datos y reabrir la app tras persistencia conserve modificaciones. No escribir tests de carpetas que solo repitan nombres de archivo.
- Commits pequeños y explicables de cada integrante, tablero actualizado y revisión cruzada. Un commit de documentación no se describe como app funcionando.

## 11. Parcial 2 y límites del avance actual

A-10 §9 mantiene los pesos: interfaz/navegación 15%, formularios 15%, lógica 10%, animaciones 10%, estructura/persistencia 15%, GitHub/Trello 20%, recursos nativos 15%. Tener la base no cubre por sí solo animaciones ni dos recursos nativos; las carpetas no acreditan persistencia.

No se inicia desarrollo en esta revisión. El siguiente paso es conciliar la especificación y resolver primero G10-02/G10-04, datos reales del equipo y decisiones §6. Otros pendientes, especialmente el ajuste, no bloquean toda la base. La entrevista sigue siendo necesaria para validar comercialmente y modificar los supuestos identificados; no invalida la base explícita entregada por el profesor.
