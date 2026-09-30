# Auditoría documental del caso Kaiju

Fecha: 30-09-2026. Versión de trabajo: 0.1. Reunión con el cliente: aún no realizada.

## 1. Método y fuente

Se revisaron las cuatro páginas completas del caso, mediante extracción de texto y revisión visual de las páginas. Una segunda revisión independiente contrastó la clasificación. No se utilizaron fuentes web para completar el alcance.

Fuente C-01: `Caso_DSY1105_Aplicaciones_Móviles_KAIJU (1).pdf`, conservado en la raíz del repositorio. Fecha de entrega al docente indicada en §1: 07-09-2026. SHA-256: `61ed398d01c0497c9880e9e67ac161931182d12b8c84e962b93aaa87a4155d0b`.

La ruta mencionada por el usuario usa `Desktop/Semestre_2026-2/...`; el espacio de trabajo usa `Desktop/SEGUNDO_SEMESTRE/...`. Se comprobó que ambos PDF existen y tienen el mismo SHA-256. Esta auditoría utiliza la copia del espacio de trabajo, sin alterarla.

El archivo es un formato de caso para docentes y estudiantes. Se registra lo que declara; no se atribuyen todas sus frases a una declaración directa del cliente ni se considera una especificación firmada.

### Clasificación

| Estado | Significado |
|---|---|
| Explícito documental | El caso expresa el hecho, capacidad, dato o condición. Su detalle puede seguir pendiente. |
| Resultado esperado | El caso lo considera señal de utilidad; falta convertirlo en un criterio comprobable acordado. |
| Condicional / evaluable | El caso permite considerarlo o condiciona su aplicabilidad. No es alcance obligatorio confirmado. |
| Futuro deseable | Se menciona como evolución futura; no es obligación del MVP. |
| No definido | El documento no especifica una decisión. No significa que esté prohibida ni autorizada. |

Los IDs de este informe son identificadores del equipo para trazabilidad, no numeración del cliente. Las preguntas P-xx están en `02-preguntas-cliente.md`. No hay respuestas del cliente registradas. Las prioridades de preguntas son orden de preparación del equipo, no prioridad comercial confirmada.

## 2. Información explícita de contexto

| ID | Información declarada | Fuente |
|---|---|---|
| CT-01 | Título: Gestión y trazabilidad de inventario para Kaiju. Organización: Kaiju. Rubro: comercio / servicios / gestión de productos. | p. 1, §1 |
| CT-02 | Sede/coordinación: CITT San Bernardo / Paz Morales. Asignatura: DSY1105. | p. 1, §1 |
| CT-03 | Se administran productos, materiales e insumos recibidos, almacenados, utilizados o vendidos. | p. 1, §2.1 |
| CT-04 | El control actual usa registros manuales, planillas u otros mecanismos que requieren actualización constante. | p. 1, §2.2 |
| CT-05 | Se registran ingresos, salidas por venta o consumo, consultas de existencias y conteos para determinar reposición. Es descripción del proceso actual; no define una pantalla de conteos. | p. 1, §2.2 |
| CT-06 | Se describen posibles diferencias de stock, olvidos, poca visibilidad de bajas existencias y dificultades de reposición. Pueden generar quiebres, compras innecesarias, pérdida de tiempo y menor control. No se aportan mediciones. | p. 1, §2.3 |
| CT-07 | Se espera ordenar, actualizar y hacer trazable el proceso para disminuir errores, facilitar control y apoyar decisiones. | p. 1, §2.4 |

El rubro específico, los productos reales y la operación detallada de Kaiju no están descritos. No hay base para afirmar que sea una tienda de ropa ni para crear funcionalidades de compras a partir de la mención de compras innecesarias.

## 3. Capacidades y necesidades explícitas

Esta es una base de requerimientos a nivel de capacidad. Ninguna fila equivale por sí sola a una historia lista para implementar: faltan acciones precisas, reglas y criterios de aceptación.

| ID | Qué puede afirmarse | Evidencia textual breve y fuente | Qué falta definir / preguntas |
|---|---|---|---|
| RC-01 | Hay tareas de registro, consulta y gestión de productos. | “registro y consulta de productos”; “gestiona productos”, p. 2, §3.1 | Qué acciones comprende gestión; datos y reglas. P-01, P-03, P-06, P-07. |
| RC-02 | El administrador o encargado gestiona categorías. | “categorías”, p. 2, §3.1 | Acciones y significado respecto de tipo de producto. P-03, P-06. |
| RC-03 | Se requiere control y consulta de existencias; personal de venta consulta disponibilidad. | “control de existencias”; “consulta disponibilidad”, p. 2, §3.1 | Unidad, alcance y momento de actualización. P-08, P-09, P-15. |
| RC-04 | El encargado de inventario registra ingresos, salidas y ajustes. | “registra ingresos, salidas y ajustes”, p. 2, §3.1 | Tipos, causas, efectos y reglas; relación con venta/consumo. P-08, P-09, P-10. |
| RC-05 | Se registra y gestiona información de ventas. | “registra ventas”; “gestiona ... ventas”, p. 2, §3.1 | Qué constituye una venta; relación con stock y movimientos. P-03, P-10. |
| RC-06 | Se necesita revisar movimientos y contar con trazabilidad de inventario por producto. | “revisión del movimiento de artículos”, p. 2, §3.1; “trazable por producto”, p. 3, §4.1 | Información conservada, consulta y tratamiento de correcciones. P-09, P-11. |
| RC-07 | Se busca detectar necesidades de reposición y controlar niveles de stock. | “detectar oportunamente las necesidades de reposición”, p. 1, §2.4; p. 2, §3.1 | Definición de stock bajo, umbral y respuesta esperada. No confirma notificaciones. P-12, P-17. |
| RC-08 | El administrador o encargado gestiona usuarios. | “usuarios”, p. 2, §3.1 | Acciones concretas, perfiles y autorización. No confirma login, registro público ni administración CRUD completa. P-02, P-03, P-04. |
| RC-09 | El administrador o encargado gestiona indicadores. | “indicadores”, p. 2, §3.1 | Indicadores, acciones, fórmulas y presentación. P-03, P-13, P-14. |

### Perfiles descritos, sin matriz de permisos acordada

| Perfil mencionado | Tareas expresas | Límite de la evidencia |
|---|---|---|
| Administrador o encargado | Gestiona productos, categorías, stock, ventas, usuarios e indicadores. | No dice si son uno o dos perfiles, ni si tiene todas las acciones de los demás. |
| Personal de venta | Consulta disponibilidad y registra ventas. | No especifica exclusividad, permisos adicionales ni acceso a indicadores. |
| Encargado de inventario | Registra ingresos, salidas y ajustes; controla niveles de stock. | No especifica permisos detallados ni equivalencia con administrador. |
| Público general o cliente | Cuando corresponda, puede consultar productos, características y disponibilidad publicada. | Participación condicional. No confirmar inclusión ni exclusión todavía. |

Fuente: p. 2, §3.1. P-02, P-04 y P-05. “Personal autorizado” en §3.3 describe una condición de uso; no especifica el mecanismo de acceso.

## 4. Información de datos expresamente mencionada

§3.2 dice “se requiere considerar información como”. Confirma considerar estos conceptos en el análisis; no constituye un esquema cerrado, ni exige que todos sean campos obligatorios en un formulario. No se crean entidades ni tipos de datos en esta etapa.

| ID | Conceptos mencionados | Estado y fuente | Pendiente |
|---|---|---|---|
| DT-01 | Código o identificador del producto, nombre, descripción. | Explícito documental, p. 2, §3.2 | Si código e identificador son alternativas o distintos; formato, unicidad y obligatoriedad. P-06, P-07. |
| DT-02 | Categoría, tipo de producto. | Explícito documental, p. 2, §3.2 | Diferencias, clasificación y reglas de relación. P-06. |
| DT-03 | Precio. | Explícito documental, p. 2, §3.2 | Significado, moneda, precisión y condiciones. No se deducen impuestos ni descuentos. P-06. |
| DT-04 | Cantidad disponible, stock mínimo. | Explícito documental, p. 2, §3.2 | Unidad, cálculo, alcance, validaciones y umbral. P-08, P-12. |
| DT-05 | Fecha y tipo de movimiento; cantidad de entrada o salida. | Explícito documental, p. 2, §3.2 | Fechas, clases de movimiento, relación de ajustes, reglas y trazabilidad. P-09, P-11. |
| DT-06 | Ventas realizadas; fechas, cantidades vendidas, categorías y productos para análisis. | Explícito documental, p. 2, §3.2 | Composición de la venta y cálculo de indicadores. P-10, P-13, P-14. |
| DT-07 | Talla, color, modelo, lote o fecha de vencimiento según tipo de producto. | Condicional: “se podrán considerar”, p. 2, §3.2 | Cuáles aplican y cómo se relacionan. No confirmar variantes ni control por lote. P-07. |

Faltan reglas de obligatoriedad, longitudes, rangos, valores admitidos, unidades y manejo de valores ausentes. No aplicar las validaciones del formulario de eventos a Kaiju.

## 5. Resultados esperados explícitos

Todos provienen de p. 3, §4.1. Se conserva su naturaleza de señales de éxito; no se inventan metas numéricas ni diseño visual.

| ID | Resultado expresado | Lo que no permite afirmar | Preguntas |
|---|---|---|---|
| RE-01 | Stock actualizado y trazable por producto. | Tiempo real, historial inmutable, autor del movimiento o refresco automático. | P-11, P-15 |
| RE-02 | Disminución de quiebres de stock y diferencias de inventario. | Porcentaje objetivo o eficacia productiva demostrable con datos ficticios. | P-18 |
| RE-03 | Identificación rápida de productos más vendidos y menos vendidos. | Ranking top 5, fórmula por ingresos, período predeterminado o inclusión de productos sin ventas. | P-13 |
| RE-04 | Visualización de tendencias de venta por período, categoría o tipo de producto. | Gráfico concreto, dashboard, filtros combinados, granularidad o exportación. | P-14 |
| RE-05 | Mayor rapidez en registro de entradas, salidas y ventas. | Tiempo máximo, cantidad de pasos o necesidad obligatoria de escáner. | P-18 |
| RE-06 | Disponibilidad más clara para usuarios internos y público general. | Inclusión definitiva de público, catálogo anónimo o publicación automática del stock real. | P-05, P-18 |

RE-03 y RE-04 son expectativas documentales que deben conservarse en el análisis; su falta de detalle no permite omitirlas del caso ni implementarlas arbitrariamente.

## 6. Condiciones y restricciones

| ID | Condición documentada | Fuente | Estado / límite |
|---|---|---|---|
| CO-01 | Uso principalmente desde teléfonos Android por personal autorizado. | p. 2, §3.3 | Explícito como escenario de uso. No fija versión mínima, tablets, otros sistemas ni mecanismo de acceso. P-04, P-15. |
| CO-02 | Se considera internet para sincronizar información. | p. 2, §3.3 | Condición descrita con alcance incompleto; no confirma backend, proveedor, frecuencia o algoritmo. P-15. |
| CO-03 | Persistencia local para consultas o registros temporales puede evaluarse. | p. 2, §3.3 | Evaluable, no obligación offline. P-16. |
| CO-04 | Pueden considerarse notificaciones de niveles mínimos y cámara para QR o barras, dentro del alcance académico. | p. 2, §3.3 | Opciones condicionadas. No permisos ni notificaciones obligatorios. P-17. |
| RS-01 | Desarrollo de un MVP móvil académico; no aplicación productiva definitiva. | p. 3, §5.1 | Restricción explícita. La oportunidad futura indicada por el usuario no amplía el MVP. |
| RS-02 | Uso de datos ficticios o anonimizados (§3.4); §5.1 enumera ficticios, sintéticos, agregados, públicos o debidamente anonimizados. | p. 2, §3.4; p. 3, §5.1 | Restricción explícita. Los públicos/agregados no autorizan datos personales reales ni registros productivos prohibidos. Para esta etapa basta la documentación; no se importan datos. |
| RS-03 | No usar datos personales reales, credenciales, llaves, tokens, respaldos, registros productivos ni accesos a sistemas internos. | p. 3, §5.1 | Restricción explícita. El caso no detalla un entorno de pruebas ni excepciones; antes de necesitar autenticación técnica de prueba aclarar compatibilidad con el docente. P-20. |
| RS-04 | Probar permisos sin capturar datos reales de personas. | p. 3, §5.1 | Restricción explícita, aplicable si se autoriza usar recursos del dispositivo. |
| RS-05 | No se exige integración con sistemas externos existentes para el MVP. | p. 2, §3.4; p. 3, §5.1 | Ausencia de exigencia; no es prohibición universal de infraestructura de pruebas ni pedido de integrar. |
| FU-01 | Es deseable evolución futura hacia herramientas de venta, catálogos digitales o facturación. | p. 2, §3.4 | Futuro deseable. No implementar integraciones en el MVP por esta frase. |

No hay objetivos cuantificados de rendimiento, accesibilidad, volumen, disponibilidad ni versiones Android. No fijar cifras ni obligaciones del cliente por cuenta del equipo.

## 7. Material de apoyo anunciado

| ID | Material / propósito | Revisión indicada | Estado constatado |
|---|---|---|---|
| MA-01 | Catálogo ficticio: ejemplificar estructura de inventario. | Institución | Anunciado en p. 4, §6; no encontrado entre los materiales proporcionados para esta revisión. |
| MA-02 | Movimientos ficticios: probar entradas, salidas y trazabilidad. | Institución / Empresa | Anunciado, no recibido en esta revisión. |
| MA-03 | Reglas generales de stock: definir alertas y comportamiento esperado. | Empresa | Anunciado, no recibido en esta revisión. |

P-19 solicita el material y su validación. Que MA-03 mencione alertas no resuelve si las notificaciones de CO-04 son obligatorias ni establece el umbral.

## 8. Ambigüedades y tensiones

No se identificó una contradicción lógica definitiva entre afirmaciones categóricas. Sí hay tensiones e información incompleta; no se resuelven eligiendo una sección sobre otra.

| ID | Hallazgo | Fuentes | Efecto / pregunta |
|---|---|---|---|
| AM-01 | Público condicionado por “cuando corresponda”, pero aparece en las señales de éxito; uso principalmente interno. | §3.1, §3.3, §4.1 | Pendiente decidir inclusión y disponibilidad publicada. P-05. |
| AM-02 | “Gestión” y tareas de perfiles no especifican acciones, permisos ni fronteras del MVP. | §3.1, §5.1 | Pendiente alcance detallado y autorización. P-01 a P-04. |
| AM-03 | Productos, materiales e insumos en contexto, pero capacidades expresadas sobre productos. | §2.1, §3.1 | No asumir tres entidades ni excluir materiales. P-06. |
| AM-04 | Información enumerada sin contrato de datos; atributos dependientes del tipo. | §3.2 | No definir formularios/modelo definitivo ni validaciones. P-06 a P-08. |
| AM-05 | Venta y consumo aparecen como salidas; no se explica relación entre ventas y movimientos ni ajustes. | §2.2, §3.1, §3.2 | No descontar stock automáticamente ni registrar dos veces por suposición. P-09, P-10. |
| AM-06 | Trazabilidad sin definición de qué se conserva ni cómo se revisa. | §2.4, §3.1, §4.1 | No inventar datos de auditoría ni historial inmutable. P-11. |
| AM-07 | Reposición y stock mínimo explícitos; reglas pendientes y notificaciones posibles. | §2.4, §3.2, §3.3, §6 | No inventar comparación < o <=, umbrales, frecuencia ni canal. P-12, P-17, P-19. |
| AM-08 | Actualización y sincronización consideradas, con persistencia local evaluable. | §3.3, §4.1 | No asumir operación offline, sincronización automática ni resolución de conflictos. P-15, P-16. |
| AM-09 | Indicadores y tendencias sin fórmulas, períodos, presentación ni medidas de éxito. | §3.1, §3.2, §4.1 | No crear dashboard, ranking concreto ni metas. P-13, P-14, P-18. |
| AM-10 | Alcance académico condiciona funciones; guías incluyen tareas genéricas y restricciones del caso limitan los datos/accesos. | §3.3, §3.4, §5.1 y guías | Aclarar con docente aplicación académica sin agregar negocio ni credenciales. P-17, P-20. |

## 9. Qué todavía no implementar

| Decisión o comportamiento | Motivo y dependencia |
|---|---|
| Operaciones específicas para productos, categorías, usuarios e indicadores; eliminación física/lógica o estados activo/inactivo. | “Gestionar” no define esas operaciones. RC-01, RC-02, RC-08, RC-09; P-03. |
| Login, registro, recuperación de contraseña, proveedores de identidad y matriz de permisos. | Personal autorizado no define mecanismo ni reglas. P-02, P-04, P-20. |
| Catálogo público y publicación de disponibilidad. | Inclusión y publicación condicionadas. P-05. |
| Esquema definitivo de productos, variantes, lotes y vencimientos; validaciones obligatorias. | Contrato de datos incompleto y atributos condicionales. P-06 a P-08. |
| Descuento automático al vender, impedimento de stock negativo, reglas de ajuste y corrección. | Relación y reglas no descritas. P-08 a P-11. |
| Alertas con umbral, comparación, frecuencia o canal; compras o reposición automática. | Necesidad de reposición no define esas acciones. P-12, P-17, P-19. |
| Fórmulas de rankings, gráficos, dashboard o exportaciones. | Resultados sin detalle y formatos no solicitados. P-13, P-14. |
| Backend elegido, sincronización automática y comportamiento offline. | Condiciones de uso sin contrato operativo. P-15, P-16, P-20. |
| Cámara, escaneo QR/barras y notificaciones del dispositivo. | Opciones condicionadas al alcance académico. P-17. |
| Integraciones de venta, facturación o catálogo digital. | Evolución futura deseable, no exigencia del MVP. FU-01. |

Tampoco se incorporan pagos, impuestos, descuentos, devoluciones, proveedores, compras, múltiples sucursales/bodegas, importación de planillas, rentabilidad, imágenes de productos ni otras funciones por considerarlas habituales en inventario. No aparecen especificadas como funcionalidades del caso. No se preparan preguntas para venderlas o agregarlas: primero se aclaran los conceptos ya presentes.

## 10. Avance permitido en esta etapa

Se puede revisar y corregir esta matriz, preparar la reunión, reunir el material anunciado, completar datos reales del equipo y organizar documentación/Git. Se puede estudiar el ejemplo como referencia de separación de responsabilidades, sin trasladar sus reglas.

Antes de programar una capacidad, registrar su alcance acordado, reglas, datos, dependencias y criterios de aceptación derivados de una fuente. Si un detalle sigue abierto, conservar el bloqueo únicamente sobre las decisiones dependientes; no fingir que todo el caso es desconocido ni inventar una respuesta para avanzar.

No se definieron historias, pantallas, modelo de datos definitivo ni criterios de aceptación funcionales porque requerirían completar información aún pendiente. La base documental está identificada; la validación con el cliente continúa pendiente.
