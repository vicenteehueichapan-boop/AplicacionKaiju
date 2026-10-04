# Auditoría del material recibido el 04-10-2026

Esta revisión complementa la auditoría del 30-09-2026. La solicitud actual es analizar y preparar el proceso; no desarrollar funcionalidades, completar ambigüedades ni modificar la plantilla original.

## 1. Fuentes y método

Se leyeron los tres textos completos y todas las hojas/celdas con contenido del Excel. Se inspeccionaron además las validaciones, colores de entrada, celdas combinadas, protección y fórmula de la rúbrica. Dos revisiones independientes contrastaron guía y cuestionario. No se ejecutaron macros, no se modificó el Excel ni se consultaron registros reales.

Copias exactas preservadas en `fuentes/2026-10-04/`:

| ID | Archivo | Naturaleza y autoridad | SHA-256 |
|---|---|---|---|
| N-01 | [caso-kaiju-texto.txt](fuentes/2026-10-04/caso-kaiju-texto.txt) | Texto pegado del caso; base documental del negocio. | `6e2ea9d557b4d2d076b4b649be36be0f8b74134fd45ce692b5dcc96fc81e1d8d` |
| N-02 | [guia-extra-kaiju.txt](fuentes/2026-10-04/guia-extra-kaiju.txt) | Guía académica de análisis y entrevista; exposición secundaria del caso. | `01e47bc58acfc62eca2c269447ae17918ed58a9dc97736ec4e711353d6ec9ea8` |
| N-03 | [cuestionario-ieee830.txt](fuentes/2026-10-04/cuestionario-ieee830.txt) | Preguntas de ejemplo y lista de revisión del SRS; no respuestas del cliente. | `9a8fce90e4776e5eb01a16e3af50878ee5a583fa2b7c68e20970d3ce345da99a` |
| N-04 | [plantilla-requerimientos-caso-kaiju.xlsx](fuentes/2026-10-04/plantilla-requerimientos-caso-kaiju.xlsx) | Formato de especificación con datos fijos del caso, restricciones del curso y control de entrega. | `52909d952cdd66afe1abb925636b5d4082b33e7bbb1f175eb6aa0bb74e22d7e1` |

Los originales proceden de los adjuntos de esta conversación y, para N-04, de Downloads. Los hashes de las copias deben coincidir con sus originales. No se copia el contenido de los ejemplos académicos como requerimientos de Kaiju.

El texto N-01 coincide con la extracción del PDF C-01 al normalizar espacios, puntuación y diacríticos. La comparación no es identidad binaria entre TXT y PDF, pero no detectó diferencias de contenido textual. No llegaron catálogo ficticio, movimientos ficticios ni reglas de stock como archivos separados; siguen anunciados en §6, no recibidos en estos materiales.

## 2. Qué cambió respecto de la auditoría anterior

| Tema | Antes | Información nueva y tratamiento |
|---|---|---|
| Caso del cliente | Cuatro páginas de contexto y capacidades, con ambigüedades. | N-01 reitera el mismo contenido. No hay acta o respuestas nuevas. |
| Entrevista | P-01 a P-20 preparadas por el equipo. | N-02 §7 da procedimiento; §8 fija entregables previos y posteriores. N-03 §1 permite adaptar preguntas. |
| Trazabilidad | Quién realizó movimientos no estaba definido en C-01. | N-02 §1, líneas 49-50, explica trazabilidad incluyendo quién los hizo. Es precisión de la guía docente que debe registrarse y cotejarse, no evidencia de que el cliente respondió P-11. |
| Formato SRS | Matrices Markdown propias. | N-04 establece hojas 1-9, IDs RF/RNF/RN/RD, origen, prioridad y verificabilidad. Los IDs RC/DT/RE de la auditoría no se eliminan: se enlazan a los nuevos IDs del borrador. |
| Persistencia | C-01 §3.3 permite evaluarla. | N-04, hoja 4, RD-03 (`A26:F26`): persistencia local con Room y/o DataStore, Esencial, origen Curso. Es obligación académica explícita. No implica operación offline completa ni sincronización automática. |
| Recursos nativos | C-01 §3.3 permite considerar notificaciones/cámara. | N-04, hoja 4, RD-04 (`A27:F27`): al menos dos recursos del dispositivo, Esencial, origen Curso. No confirma qué recursos ni sus funciones de negocio. |
| Calidad técnica | Prácticas académicas generales. | N-04 fija Kotlin/Android Studio/Compose y MVVM/validación en ViewModel; rúbrica/checklist añaden navegación, formularios, animaciones y evidencias de equipo. |
| Datos de prueba | Fixtures del equipo identificados como demostración. | N-02 §5 autoriza crear datos de prueba mientras llega material, declarándolos supuestos. No autoriza inventar reglas ni datos productivos. |

## 3. Auditoría del caso reiterado N-01

Se mantienen CT-01 a CT-07, RC-01 a RC-09, DT-01 a DT-07, RE-01 a RE-06, CO/RS/FU y las preguntas de `01-auditoria-caso.md` y `02-preguntas-cliente.md`. No hay nuevas prioridades comerciales ni criterios funcionales acordados.

Continúan abiertos: significado de gestionar; operaciones por perfil; autorización; datos obligatorios; categoría/tipo; unidades y rangos; relación venta-stock; ajustes y correcciones; umbrales; fórmulas/periodicidad; sincronización; alcance público; atributos condicionales. No elevar un dato como stock mínimo a una regla de comparación sin respuesta.

## 4. Auditoría de la guía extra N-02

| Sección | Qué aporta | Qué no permite concluir |
|---|---|---|
| §0 | Apoyo extra, cliente real, recorrido leer -> preparar -> entrevistar -> especificar -> revisar. Los ejemplos resueltos son del club ficticio. | Que las reservas, socios, bloqueos o tiempos de ese club pertenezcan a Kaiju. |
| §1 | Contexto y problema; explica trazabilidad como cantidad, movimientos y autor. | Autenticación concreta, historial inmutable, múltiples bodegas o auditoría técnica cerrada. |
| §2 | Perfiles; público general se conversa y se registra en Alcance. | Matriz definitiva de permisos ni inclusión automática del público. |
| §3 | Desglosar lo que existe y lo que ocurre, datos y relaciones; atributos variables. | Cardinalidades, entidades persistentes o tipos de negocio ya confirmados. |
| §4 | Diferenciar RF/RNF/RN; origen, prioridad y verificación. Indica que cifras/límites los confirma el cliente. | Usar 2 horas, 3 segundos, 15 minutos o 3 inasistencias del club en Kaiju. |
| §5 | MVP, límites de datos, opciones, materiales futuros y datos de prueba declarados. | Alcance final validado ni que las opciones del caso desaparezcan ante la cuota del curso. |
| §6 | Relacionar las seis señales de éxito con requerimientos. | Fórmulas, gráficos, metas numéricas o eficacia productiva demostrada. |
| §7 | Protocolo de entrevista, preguntas abiertas, cierre y acta. | Fecha, duración, modalidad, participantes concretos o cita ya agendada. |
| §8 | Borrador antes, mínimos después y revisión con cuestionario. | Que el equipo pueda completar la cuota mediante reglas supuestas. |

La guía es más explícita que nuestro orden anterior: primero preparación de la entrevista. Con la solicitud actual retomamos ese trabajo. No se borra la exploración visual previa ni se considera aprobada. Aclarar con docente si la base técnica puede avanzar en paralelo; no convertir la expresión “antes de abrir Android Studio” en una prohibición adicional inventada.

## 5. Entrevista: sí está prevista, sin logística fijada

N-02 §7, líneas 202-234, y N-03 §1, líneas 34-73, indican:

| Momento | Qué preparar o hacer |
|---|---|
| Antes | Leer caso, registrar abiertos, ordenar pauta por importancia, preparar presentación breve y asignar conducción, registro y control de tiempo. |
| Durante | Abrir con relato del proceso, pedir ejemplo de registro, comprender la razón de pedidos, no prometer funciones/fechas, cerrar resumiendo y solicitar correcciones. |
| Después | Acta el mismo día con fecha/asistentes/preguntas/respuestas; actualizar requerimientos con origen en entrevista; mantener abiertos como pendientes. |

La demostración del proceso debe ser ficticia o depurada: pedir cómo registran hoy no habilita copiar datos personales, planillas productivas o credenciales prohibidas. No se solicita grabación ni se presume permiso para grabar.

Faltan logística, plazo/canal de entrega, formato de acta y mecanismo de validación posterior. Deben preguntarse al docente/coordinación. `08-preparacion-entrevista.md` convierte este procedimiento en una pauta utilizable sin inventar participantes ni fechas.

## 6. Entregables académicos: antes y después

| Etapa | Exigencia N-02 §8 | Estado documentado al 04-10-2026 |
|---|---|---|
| Antes | Hoja 1: negocio/problema/objetivo con palabras propias y glosario inicial. | Contenido propuesto en `09-borrador-previo-srs.md`; glosario provisional, no acordado. Excel intacto. |
| Antes | Hoja 2: perfiles y tareas. | Caso auditado; borrador disponible. Nivel técnico/frecuencia pendientes. |
| Antes | Primer borrador de requerimientos, aunque tenga dudas. | Candidatos documentales, sin declararlos especificación aprobada. |
| Antes | Pauta >=8 preguntas, por importancia, cada una con fuente; roles de conversación. | Pauta preparada; asignaciones nominales aún faltan. |
| Después | Acta con fecha, asistentes, preguntas y respuestas. | No se aportó acta en esta conversación; no se fabrica. |
| Después | >=8 RF, >=3 RNF y >=3 RN con ID, prioridad, origen y criterio. | Meta de entrega posterior; criterios, prioridades y reglas deben fundamentarse. No se afirma cumplirla ahora. |
| Después | Hoja 5: dentro/fuera y justificaciones, más pendientes. | Alcance preliminar documentado; decisiones de cliente todavía abiertas. |
| Después | Hoja 7: desglose y lógica de reglas principales. | Conceptos identificados; tipos/cardinalidades/reglas pendientes. |
| Después | Hoja 8: señales -> requerimientos. | Trazabilidad documental inicial; pruebas/pantallas definitivas pendientes. |
| Revisión | Cuestionario; reescribir lo no verificable. | Auditoría de incompletitud realizada, sin marcar todos los controles como cumplidos. |

## 7. Auditoría de la plantilla N-04

Hay **diez pestañas visibles**: Instrucciones más nueve hojas numeradas. “Hoja 1” en la guía significa `1 Introducción y Contexto`, no la primera pestaña física, que es Instrucciones.

| Hoja real | Contenido observado | Cómo proceder |
|---|---|---|
| Instrucciones | Amarillas: equipo; blancas: fijas; IDs no reutilizables, origen/prioridad, pendientes hoja 5. | Conservar fuente. En copia futura, retirar placeholders solo al sustituirlos por contenido sustentado. |
| 1 Introducción y Contexto | Identificación fija; propósito, alcance, referencias, glosario y contexto/dependencias. | Redactar con palabras propias; no etiquetar definiciones preliminares como acordadas con cliente. |
| 2 Roles | Tres perfiles internos y público condicionado; nivel técnico/frecuencia. | Completar capacidades expresas; mantener pendientes las características no recibidas. |
| 3 Requerimientos (RF) | 16 IDs RF precargados en cuatro grupos; prioridad/origen/criterio/pantalla. | 16 espacios no significan mínimo 16. Cada fila una obligación; IDs estables. |
| 4 Reglas, RNF y RD | Seis espacios RN, seis RNF y cinco RD fijos. | No tratar placeholders/espacios como seis reglas requeridas. Conservar RD fijos. |
| 5 Alcance | Dentro, fuera/futuro y pendientes con motivo/responsable/fecha. | No confundir “no confirmado” con “fuera”. Responsable y fecha por acordar si no existen. |
| 6 Pantallas | Diez números disponibles, perfil, origen de navegación, campos/validaciones y RF. | No exige diez pantallas; propuestas se identifican como tales. |
| 7 Modelo de Datos | Entidad, campos con tipo, persistencia y relaciones. | No elegir tipos o relaciones para rellenar casillas. Falta espacio explícito para lógica paso a paso que pide la guía. |
| 8 Trazabilidad | Seis señales fijas; ID/origen/pantalla/componente/prueba/IE; rúbrica fija. | Enlazar por IDs. Componente y prueba planificados no se marcan como implementados. |
| 9 Checklist y Aceptación | Control semanal técnico y criterios de entrega. | Solo marcar X con evidencia. No forma parte del alcance comercial de Kaiju. |

Los colores de entrada existen en rangos vacíos; no basta mirar solo celdas con texto. Ejemplos `2 Roles!B6:D6`, `3 Requerimientos (RF)!C6`, `7 Modelo de Datos!A5:D5` y `9 Checklist y Aceptación!A6` son amarillos. Los títulos de módulo RF (`A5:F5`, y grupos siguientes) son azul claro pero contienen placeholders: la propia instrucción de RF pide escribir allí el módulo. Es una excepción funcional a la leyenda general; consultar si se necesita adaptar formato.

La plantilla no tiene hojas ocultas ni protección activa. Que permita editar no autoriza cambiar datos fijos. La única fórmula encontrada es `8 Trazabilidad!C37 = SUM(C30:C36)`: los pesos fuente suman 100. Esa suma no indica avance del equipo ni cobertura lograda. No se verificó el recálculo ni el aspecto en Excel nativo porque se realizó inspección de contenido/estructura sin editar.

### Restricciones académicas fijas que ahora debemos reconocer

| ID fijo | Ubicación (hoja 4) | Exigencia | Origen |
|---|---|---|---|
| RD-01 | fila 24 | Android, Kotlin, Android Studio y Jetpack Compose. | Curso |
| RD-02 | fila 25 | MVVM (ui/viewmodel/model), validación en ViewModel y errores por campo en interfaz. | Curso |
| RD-03 | fila 26 | Guardar datos relevantes en dispositivo con Room y/o DataStore. | Curso |
| RD-04 | fila 27 | Al menos dos recursos nativos; ejemplos cámara, notificaciones, galería. | Curso |
| RD-05 | fila 28 | Datos ficticios/sintéticos/anonimizados; sin datos personales reales, credenciales ni accesos Kaiju. | Caso y curso |

RD-01 a RD-05 llevan Esencial en la plantilla. Eso es prioridad académica de origen fijo; no convierte los RF del equipo en prioridades confirmadas por cliente. Los nombres de librerías no fijan versiones ni permiten copiar las del ejemplo docente.

### Rúbrica suministrada dentro de la plantilla

| IE | Descripción | Peso |
|---|---|---|
| 2.1.1 | Diseño visual y navegación | 15% |
| 2.1.2 | Formularios validados | 15% |
| 2.2.1 | Validación lógica (MVVM) | 10% |
| 2.2.2 | Animaciones | 10% |
| 2.3.1 | Arquitectura y persistencia | 15% |
| 2.3.2 | GitHub y Trello | 20% |
| 2.4.1 | Recursos nativos | 15% |

Fuente: N-04, `8 Trazabilidad!A30:D36`. La plantilla presenta estos pesos como Parcial 2; no se recibió una rúbrica externa con niveles de logro, fechas o instrucciones adicionales. GitHub/Trello es trabajo del equipo, expresamente no requerimiento del software (`D35`).

## 8. Auditoría del cuestionario N-03

Tiene 35 controles: 27 sobre ocho cualidades y ocho de estructura. Es herramienta para revisar la plantilla; declara que no es formulario para entregar (§0). El texto pegado “0 de 35” procede de su interfaz y no mide nuestro avance. Marcas de navegador no se transfieren automáticamente al equipo o al Excel.

| Cualidad | Qué comprobar en nuestro borrador |
|---|---|
| Correcto (C) | Fuente real para cada requisito; reglas/cálculos confirmados cuando proceda; no contradecir acta. |
| No ambiguo (NA) | Términos definidos; evitar rápido/fácil sin condiciones; misma lectura entre integrantes. |
| Completo (CP) | Perfiles aplicables, ciclos cuando correspondan, límites y pendientes con seguimiento. No inventar CRUD. |
| Consistente (CS) | Sin contradicción, términos únicos e IDs RF/RNF/RN/RD distintos. |
| Ordenado (OI) | Prioridad acordada y estabilidad; identificar decisiones que pueden cambiar. |
| Verificable (VF) | Criterio RF; métrica/umbral RNF cuando sea aplicable; prueba describible. |
| Modificable (MD) | Un requisito por fila; contenido único y referencias por ID; agrupación clara. |
| Trazable (TR) | Origen y relación con pantalla/datos/prueba; señales de éxito cubiertas. |

Los ejemplos de exportar, datos negativos, sin conexión, usuario sin permiso, respuesta <2 segundos con 4G o sincronización diaria no son decisiones Kaiju. CP-02 condiciona crear/consultar/modificar/eliminar con “cuando corresponda”. Las preguntas sirven para aclarar lo que se necesita, no para ampliar alcance mediante una lista genérica.

## 9. Ambigüedades nuevas y preguntas al docente

| ID | Evidencia / tensión | Aclaración necesaria |
|---|---|---|
| D-01 | N-02 §7-8 prevé reunión, sin agenda/plazos. | Fecha, duración, modalidad, quién coordina, canal/plazos de entregas y seguimiento. |
| D-02 | N-02 §1 añade autor del movimiento respecto a C-01. | ¿Es precisión académica exigida? ¿Cómo contrastarla con cliente sin asumir autenticación? |
| D-03 | RD-03 esencial vs persistencia evaluable en C-01. | Qué datos deben persistirse para evaluación; separar esa exigencia de funcionamiento offline y sincronización. |
| D-04 | RD-04 esencial vs recursos opcionales en C-01. | Cuáles usos nativos coherentes puede elegir el equipo y cuáles requieren acuerdo del cliente; cómo evaluar si no se necesita cámara/notificaciones. |
| D-05 | Checklist hoja 9 B6 enumera Room y DataStore, pero RD-03/hoja 9 B12 dicen y/o. | ¿Se exige ambas dependencias o solo la pertinente a los datos? No instalar ambas por interpretación. |
| D-06 | Guía §8 pide lógica paso a paso en hoja 7; Excel solo tiene entidad/campos/persistencia/relaciones. | Dónde adjuntar lógica: anexo, sección adicional o archivo separado, sin alterar el original/fijos. |
| D-07 | Hoja 1 B18 pide definición acordada; guía pide primer glosario antes de entrevistar. | Cómo indicar glosario provisional y estado pendiente en el borrador. |
| D-08 | OI-03 pide estabilidad; RF/RNF no tienen columna de estado/estabilidad. | Cómo registrar estado/estabilidad y fuentes sin cambiar estructura exigida. |
| D-09 | Mínimos posteriores >=8 RF/3 RNF/3 RN vs reglas y métricas ausentes. | Cómo evaluar pendientes si cliente no aporta suficientes reglas; no llenar cuotas con restricciones técnicas ni cifras supuestas. |
| D-10 | RN usa ejemplos cumple/no cumple y no columna de aceptación titulada así. | Si esos ejemplos constituyen verificación suficiente y dónde poner criterio pedido en guía. |
| D-11 | VF-02 exige métrica/umbral para todo RNF; seguridad/plataforma pueden comprobarse por condiciones categóricas. | Formato admisible de verificación sin inventar segundos, porcentajes o límites. |
| D-12 | Disponibilidad pública RE-06 pero público condicional. | Cómo registrar cobertura si cliente decide no incluirlo; no declarar cubierto un perfil excluido. |
| D-13 | Instrucción de amarillas, módulo RF azul claro editable; sin campo para pauta/acta. | Excepciones de formato permitidas y dónde entregar anexos. |
| D-14 | Docente pide preparación antes de Android Studio; guías anteriores tienen entregables técnicos. | Qué orden/plazos de evaluación aplicar; mantener foco actual en análisis sin cancelar trabajo previo. |

Estos son pedidos de aclaración, no contradicciones resueltas. Para negocio, P-01 a P-20 se conservan y P-21 a P-28 complementan proceso, usuarios, excepciones y calidad. No hay prioridades comerciales validadas ni asignaciones del equipo conocidas.

## 10. Contexto actual y próximo trabajo

Tenemos base documental auditada, Git inicializado y conectado, preguntas, matriz de construcción y prototipos de apariencia. No consta acta, catálogo autorizado ni reglas entregadas; no consta aplicación Android propia. No re-inicializar Git ni crear un archivo INI sin una necesidad especificada.

Ahora corresponde revisar el [borrador previo SRS](09-borrador-previo-srs.md), completar datos de equipo reales y usar la [pauta](08-preparacion-entrevista.md). Cuando el usuario pida completar Excel, trabajar en una copia de N-04, mantener fijos y origen, señalar borradores/pendientes según formato acordado y revisar con N-03. Esta auditoría no exporta una planilla rellenada ni finge una entrega posterior a la reunión.

Después de entrevista: acta del mismo día -> respuestas validadas -> requerimientos/prioridades/criterios -> alcance -> modelo/reglas -> trazabilidad -> revisión -> construcción de capacidades suficientemente definidas. Base técnica y propuestas reversibles pueden planificarse aparte, sin convertirse en reglas del cliente.
