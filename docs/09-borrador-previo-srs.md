# Borrador previo a la entrevista

Fecha: 04-10-2026. Preparación documental para N-02 §8 y las hojas 1-3/5 de N-04. No es el Excel rellenado, una especificación final ni un acta. Todas las prioridades comerciales y criterios funcionales incompletos siguen pendientes. No se han recibido respuestas nuevas del cliente.

Vigencia 07-10-2026: borrador histórico previo a A-10. Sus RF-01 a RF-15 no coinciden con la numeración ahora exigida por la Guía 10. Ver equivalencias y plan de conciliación en `11-auditoria-guia-10.md` §6. El Excel y kit de reunión del 04-10 no se han regenerado para esa base nueva; no mezclar IDs ni considerar que ya contienen las seis RN docentes.

## 1. Introducción y contexto propuestos

**Propósito del documento:** registrar qué necesita resolver el MVP académico de Kaiju y dejar identificadas las definiciones que debemos aclarar. Lo consultarán el cliente, el docente y el equipo para revisar alcance, reglas y evidencias.

**Alcance preliminar:** abordar consulta y control de inventario, registro/revisión de movimientos y ventas, necesidades de reposición e información comercial mencionada en el caso. La profundidad de cada capacidad, los permisos y la inclusión del público general permanecen por validar. No incluye por obligación integraciones productivas existentes ni una aplicación productiva definitiva. Fuente: C-01 §2.4, §3.1, §3.4, §5.1. No implica que todos los candidatos de este borrador sean alcance final priorizado.

**Contexto del negocio:** Kaiju recibe, conserva, utiliza o vende productos, materiales e insumos. El control actual depende de anotaciones y planillas que deben mantenerse al día. Fuente: C-01 §2.1-2.2.

**Problemática:** los registros pueden no reflejar las existencias disponibles; también pueden omitirse movimientos o detectarse tarde necesidades de reposición. Esto dificulta controlar recursos y tomar decisiones. Son problemas descritos, sin mediciones aportadas. Fuente: C-01 §2.3.

**Objetivo preliminar:** facilitar la consulta de existencias y el registro/revisión de movimientos para mejorar el control y la trazabilidad. Las condiciones para demostrar actualización, rapidez y disminución de diferencias requieren P-11, P-15 y P-18. Fuente: C-01 §2.4, §4.1. No fijar metas numéricas por cuenta del equipo.

**Perspectiva:** MVP Android académico; no se exige integrar sistemas existentes. Las conexiones futuras de venta, catálogo o facturación son deseables, no compromisos actuales. Fuente: C-01 §3.3-3.4, §5.1. Para tecnologías exigidas por curso, conservar RD-01 a RD-05 de N-04.

**Suposiciones y dependencias:** no se dan por resueltas ambigüedades de negocio. Dependemos del catálogo, movimientos de ejemplo, reglas de stock, respuestas del cliente y aclaraciones académicas. Los datos ficticios del prototipo son solo pruebas del equipo, autorizadas como tales por N-02 §5. No son material MA-01 recibido.

**Equipo, grupo y nombre académico:** pendientes de datos reales. Referencias: C-01, N-01 a N-04, guías 07-09 y documentación previa del equipo. No incluir acta de entrevista como referencia existente hasta recibirla/redactarla.

## 2. Glosario inicial, no acordado todavía

La columna de N-04 hoja 1 dice definición acordada con cliente, pero N-02 permite primer glosario antes de la reunión. Este borrador separa definición documental de acuerdo pendiente, para no falsear esa columna.

| Término | Base disponible | Qué aclarar |
|---|---|---|
| Producto | Artículo sobre el que se registran datos, cantidades y movimientos; C-01 §2.1/§3.2. | Relación con materiales/insumos y atributos; P-06, P-07. |
| Existencia / stock | Cantidad disponible mencionada en C-01 §3.2. | Unidad, alcance y actualización; P-08, P-15. |
| Movimiento | Ingreso, salida o ajuste mencionado en C-01 §3.1. | Tipos, fecha, efecto y correcciones; P-09, P-11. |
| Venta | Operación cuyo registro aparece en C-01 §3.1-3.2. | Datos y relación con movimiento/stock; P-10. |
| Stock mínimo | Dato mencionado en C-01 §3.2. | Umbral, comparación y conducta; P-12. |
| Trazabilidad | C-01 pide seguir inventario/movimientos; N-02 §1 incluye quién los hizo. | Qué debe conservarse y cómo identificar autor; P-11, P-22. |
| Categoría / tipo de producto | Dos términos enumerados en C-01 §3.2. | Definiciones y relación; P-06. |
| Disponibilidad publicada | Información condicional para público; C-01 §3.1. | Alcance y forma de publicación; P-05. |

No definir conceptos mediante ejemplos de ropa o del club ficticio.

## 3. Roles preliminares

| Perfil fijo del caso | Acciones documentadas | Información todavía pendiente |
|---|---|---|
| Administrador o encargado | Gestión de productos, categorías, stock, ventas, usuarios e indicadores. | Uno o dos perfiles, acciones concretas, permisos, nivel técnico y frecuencia. |
| Personal de venta | Consulta de disponibilidad y registro de ventas. | Permisos y condiciones de venta; nivel técnico y frecuencia. |
| Encargado de inventario | Registro de ingresos, salidas y ajustes; control de niveles de stock. | Reglas, permisos, nivel técnico y frecuencia. |
| Público general o cliente, cuando corresponda | Consulta de productos, características y disponibilidad publicada. | Inclusión del perfil y datos publicados; nivel técnico/frecuencia si se incluye. |

Fuente: C-01 §3.1 y N-04 hoja 2. No añadir perfiles ni asignar todos los permisos al administrador por costumbre.

## 4. RF candidatos con fuente, sin completar reglas

IDs reservados en este borrador del equipo para su futura incorporación a la plantilla; no reutilizarlos si un candidato se descarta. Son formulaciones de capacidad, no requisitos finales listos para programar. Prioridad de **todos los RF: pendiente de validación con cliente**. Pantallas definitivas: pendientes; la consulta de Lovable es propuesta previa del equipo, no aprobación.

| ID | Formulación documental preliminar | Origen | Relación anterior | Detalles/criterio pendientes |
|---|---|---|---|---|
| RF-01 | La aplicación permitirá consultar productos. | C-01 §3.1 | RC-01 | Información mostrada, perfil y alcance de consulta; P-01, P-04, P-06. |
| RF-02 | La aplicación permitirá registrar productos. | C-01 §3.1 | RC-01 | Actor, campos, obligatoriedad, identificación, validaciones y qué comprende gestión adicional; P-03, P-06, P-07. |
| RF-03 | El personal de venta podrá consultar disponibilidad. | C-01 §3.1 | RC-03 | Unidad, vigencia y forma de presentación; P-08, P-15. |
| RF-04 | El encargado de inventario podrá registrar ingresos. | C-01 §3.1 | RC-04 | Información, condiciones y efecto; P-08, P-09. |
| RF-05 | El encargado de inventario podrá registrar salidas. | C-01 §3.1 | RC-04 | Tipos, consumo/venta, información y efecto; P-09, P-10. |
| RF-06 | El encargado de inventario podrá registrar ajustes. | C-01 §3.1 | RC-04 | Significado, valores, corrección y efecto; P-08, P-09, P-11. |
| RF-07 | El personal de venta podrá registrar ventas. | C-01 §3.1 | RC-05 | Composición y relación con inventario; P-10. Gestión adicional de ventas pendiente P-03. |
| RF-08 | La aplicación permitirá revisar movimientos de artículos. | C-01 §3.1 | RC-06 | Perfil, datos conservados, alcance de revisión/corrección; P-04, P-11, P-22. |
| RF-09 | El administrador o encargado podrá gestionar categorías. | C-01 §3.1 | RC-02 | “Gestionar” no es operación verificable todavía; P-03, P-06. |
| RF-10 | El administrador o encargado podrá gestionar usuarios. | C-01 §3.1 | RC-08 | “Gestionar”, autorización y perfiles sin mecanismos supuestos; P-02, P-03, P-04. |
| RF-11 | El administrador o encargado podrá gestionar indicadores. | C-01 §3.1 | RC-09 | Qué comprende gestión y qué indicadores; P-03, P-13. |
| RF-12 | La aplicación permitirá identificar productos más vendidos. | C-01 §4.1 | RE-03 | Medida, período, universo y consulta; P-13. |
| RF-13 | La aplicación permitirá identificar productos menos vendidos. | C-01 §4.1 | RE-03 | Medida, período, universo y tratamiento de productos sin ventas; P-13. |
| RF-14 | La aplicación permitirá visualizar tendencias de venta por período, categoría o tipo de producto. | C-01 §4.1 | RE-04 | Dimensiones, cálculo, períodos y presentación; P-14. |
| RF-15 | La aplicación facilitará detectar necesidades de reposición. | C-01 §2.4 | RC-07 | Regla, umbral y respuesta; P-12, P-19. No confirma notificaciones. |

Las filas con verbos generales se mantienen explícitamente incompletas; no se presentan como cumplimiento de NA/VF del cuestionario. Tras entrevista, descomponer gestión cuando se conozcan operaciones, evitar duplicar RF-11 y RF-12/13/14, y registrar cualquier eliminación/reformulación con historial. No se ocultan faltas de cobertura como “gestión de stock” bajo una implementación supuesta.

No hay criterio final de aceptación en estas filas porque inventar una respuesta para hacerlas verificables violaría la solicitud. El criterio deberá usar datos, condición/acción y resultado acordados. La documentación de los pendientes sí está disponible para que el equipo llegue preparado.

## 5. RNF: tres ejes de consulta, no tres obligaciones completas

| Candidato | Base de la necesidad | Pendiente |
|---|---|---|
| RNF-01: rapidez de registro | C-01 §4.1 espera mayor rapidez en entradas, salidas y ventas. | Operaciones, condición de prueba, métrica y umbral acordado; P-18, P-25. |
| RNF-02: información actualizada | C-01 §4.1 y §3.3 mencionan actualización y sincronización. | Qué significa actualizado, demora admisible, escenarios y evidencia; P-15, P-18. |
| RNF-03: uso autorizado | C-01 §3.3 indica personal autorizado. | Alcance de acceso, acciones/info permitidas y verificación; P-04, P-26. Aclarar categoría/formato con docente. |

Prioridad, redacción verificable y criterio: pendientes. Estos ejes no cumplen todavía el mínimo posterior de tres RNF. No agregar segundos, 4G, porcentajes de disponibilidad, cierre por inactividad o mecanismos de login tomados de ejemplos.

RD-01 a RD-05 se conservan como restricciones académicas fijas en N-04. No renombrarlas como RNF para completar cantidades y no duplicarlas como nuevas peticiones del cliente.

## 6. RN: todavía no hay tres reglas completas para afirmar

El caso no establece explícitamente fórmulas, comparaciones, límites de cantidades ni transiciones que permitan cerrar tres reglas de negocio. No se asignan RN-01 a RN-03 a textos inventados.

Tres temas con evidencia para preguntar, no reglas confirmadas:

1. Valores/unidades permitidos y efectos de ingresos/salidas/ajustes: P-08, P-09, P-24.
2. Relación entre venta, movimiento y stock: P-10.
3. Stock mínimo, detección de reposición y comportamiento: P-12, MA-03.

Cuando haya respuestas, expresar una condición y resultado precisos, con ejemplo ficticio que la cumpla y otro que no, origen y prioridad. No asumir “prohibido stock negativo”, “venta descuenta automáticamente” o “alerta si cantidad <= mínimo”. Las restricciones de datos académicos no reemplazan reglas operativas de inventario.

## 7. Modelo lógico preliminar

| Concepto explícito | Tipo de información a analizar | Base disponible y límites |
|---|---|---|
| Producto | Elemento que existe | Identificador/nombre/descripción/categoría/tipo/precio; no todos obligatorios ni tipos definitivos. |
| Categoría / tipo | Clasificación mencionada | No fijar dos tablas ni jerarquías. |
| Movimiento | Hecho que ocurre | Fecha/tipo/cantidad entrada o salida; ajuste y autor pendientes de precisar. |
| Venta | Hecho que ocurre | Fechas/cantidades/productos/categorías para análisis; no definir cabecera/detalle ni su unión a movimiento. |
| Usuario/perfil | Participación en operación | Gestión y tareas expresas, sin atributos personales o modelo de login. |
| Cantidad disponible / mínimo | Información de existencias | No decidir si es almacenada, derivada o ajustada; P-08, P-12. |

Fuente: C-01 §3.1-3.2; N-02 §3. El desglose no es un esquema de base de datos aprobado. No se especifican cardinalidades, claves, Int/Double, unidades, tipos de fechas o modelo de variantes todavía. Los atributos talla/color/modelo/lote/vencimiento se mantienen condicionales. RD-03 fija necesidad académica de persistencia, no el contrato lógico del negocio.

## 8. Trazabilidad inicial de señales

| Señal (ID previo) | Candidatos relacionados | Qué impide afirmar cobertura completa |
|---|---|---|
| RE-01, stock actualizado/trazable | RF-03 a RF-06, RF-08; RNF-02 | Actualización, datos de historia y reglas pendientes. |
| RE-02, menos quiebres/diferencias | RF-04 a RF-06, RF-15 | Reglas y demostración de mejora no definidas; no prometer efecto productivo. |
| RE-03, más/menos vendidos | RF-12, RF-13 | Fórmulas, períodos y universo pendientes. |
| RE-04, tendencias | RF-14 | Condiciones, cálculo y presentación pendientes. |
| RE-05, rapidez de registro | RF-04 a RF-07; RNF-01 | Medida y umbral pendientes. |
| RE-06, disponibilidad interna/pública | RF-01, RF-03 para consulta interna | Público no confirmado; no marcar señal completa. |

La matriz final añadirá pantalla, componente, prueba e indicador de rúbrica, distinguiendo planificado de implementado. La suma de pesos no demuestra cobertura. El cumplimiento de GitHub/Trello se evidencia como trabajo del equipo, no como RF inventado.

## 9. Qué está listo para el siguiente paso

Tenemos textos base de contexto/roles, glosario provisional, quince RF candidatos, ejes RNF, temas para obtener RN y preguntas para resolverlos. Todavía faltan datos de equipo, convocatoria, acta, reglas, materiales y prioridades/criterios acordados.

Actualización de preparación: el usuario solicitó los documentos de apoyo. La copia de trabajo está en `entregables/kaiju-requerimientos-previos-entrevista.xlsx` y el kit editable en `10-kit-reunion-kaiju.md`, con PDF imprimible en `../output/pdf/kit-reunion-kaiju.pdf`. El original sigue sin modificar. La copia conserva pendientes y no marca hojas/checklist como terminadas. El siguiente paso es revisar con el equipo, completar convocatoria y repartir responsabilidades reales antes de entrevistar.
