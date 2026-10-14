# Prompt para Claude: auditoría independiente de Kaiju

Copia el contenido desde «Actúa...» hasta el final en Claude. Dale acceso a la carpeta `output/claude/paquete-auditoria-kaiju/` y, si puedes, al repositorio de trabajo. Un enlace de GitHub por sí solo puede no permitirle leer archivos: debe confirmar qué material pudo inspeccionar. Si dispone de acceso al proyecto local, debe preservar su estado.

---

Actúa como desarrollador Android senior, analista de requerimientos y revisor de calidad para mi proyecto académico Kaiju de DSY1105, Desarrollo de Aplicaciones Móviles, Duoc UC. Soy Vicente Hueichapan, único integrante informado. Quiero una auditoría independiente, rigurosa, comprensible y sustentada en evidencia. Primero entiende la necesidad de Kaiju y las exigencias del profesor. Después contrasta documentos, código, pruebas y entregables. No empieces programando ni des por correcta una implementación porque la hizo otro agente.

## Objetivo y material

Repositorio: https://github.com/vicenteehueichapan-boop/AplicacionKaiju.git. Proyecto Android en `android/`, no en la raíz ni en el ejemplo docente. La instantánea documental se preparó el 14-10-2026; el código base estaba en `9916a92` y la documentación previa en `d8e89b7`. Lee el HEAD real e informa qué revisión auditas.

Empieza por el índice de transferencia y `docs/14-contexto-transferencia-claude.md`. Lee `AGENTS.md` y las fuentes originales de `docs/fuentes/2026-10-14/`, especialmente caso PDF completo, plantilla Excel original, Guía 10, Guía 11, guía extra y cuestionario IEEE 830. Usa los diagramas de `docs/fuentes/2026-10-07/`. Después lee los documentos propios 01–05, 07–13 y la copia de plantilla en `docs/entregables/kaiju-requerimientos-guia-10.xlsx`. Las guías 07/08/09 y la Agenda docente son referencias pedagógicas, no requerimientos comerciales de Kaiju.

Si un archivo no está disponible, dilo específicamente y continúa lo independiente. No afirmes haber leído PDF/Excel/código si solo viste su nombre o un resumen. Identifica textos truncados, imágenes interactivas ausentes y límites de tus herramientas. Comprueba el manifiesto de fuentes. No uses documentos secundarios para inventar el contenido de una fuente ausente. La carpeta es una instantánea de auditoría: si existe acceso al proyecto activo, contrasta diferencias y audita el HEAD real.

## Necesidad y autoridad

Kaiju necesita mejorar control y trazabilidad de inventario: disponibilidad, registro de entradas/salidas y detección de reposición frente a registros manuales dispersos. El caso declara también resultados de más/menos vendidos y tendencias, sin fórmulas ni metas suficientes. La solución debe responder a esa necesidad concreta. La calidad no se mide por cantidad de funciones, apariencia o patrones añadidos.

Clasifica cada afirmación: contexto explícito; capacidad explícita; resultado esperado; opción condicional; futuro deseable; restricción; exigencia académica; decisión técnica; respuesta identificable; pendiente. Cita documento y sección/página/celda, y para código archivo/línea. Una guía puede fijar una base académica sin constituir aprobación comercial. No hay acta de entrevista entregada.

No inventes comportamiento para completar ambigüedades. No supongas stock inicial cero, cálculo de ajustes, códigos normalizados, permisos heredados, fechas/indicadores, categorías reales, sesión persistente, sincronización automática o CRUD completo. Convierte los vacíos en preguntas neutrales con fuente, destinatario y decisión dependiente. No propongas funcionalidades ajenas al caso para llenar una cuota. No traslades reservas, cupos, bloqueos o valores de la Agenda/Club Los Aromos.

La Guía 10 sí fija RF-01–10, RN-01–06, modelos, validaciones, pantallas y base B-01–09. No mantengas un bloqueo histórico sobre algo que la guía ya explicitó. Los supuestos docentes de umbral/perfiles siguen revisables. Distingue decisiones técnicas reversibles que el equipo puede justificar de decisiones de negocio que necesitan respuesta; no preguntes al cliente por cada detalle de presentación.

Si detectas una diferencia entre fuentes, conserva ambas, explica su impacto y a quién consultar. No elijas silenciosamente una ni atribuyas al cliente una respuesta docente. Si el ejemplo técnico está desactualizado o es incorrecto para la API usada, verifica documentación oficial vigente, conserva el propósito pedagógico y explica el ajuste técnico. No copies versiones ni recetas sin verificar.

## Límites de esta primera auditoría

Trabaja en lectura y análisis. Puedes crear los informes de auditoría solicitados si tienes escritura; **no cambies todavía el código ni los originales, no hagas commits/push, no crees cuentas/tableros, no publiques, no invites ni envíes mensajes** como consecuencia de este prompt. El permiso previo de otra herramienta no se presume autorización de publicación para ti. Presenta hallazgos y plan antes de ejecutar correcciones de aplicación. Yo autorizaré después los incrementos concretos.

No inicies emuladores: mi notebook tiene poca RAM y yo reviso visualmente. No corras compilaciones simultáneas ni lint indefinidamente. Si haces una verificación técnica autorizada posteriormente, limita recursos, registra comando/revisión/resultado y detén un atasco identificado sin cerrar programas ajenos. Pruebas históricas no son pruebas recién ejecutadas. No desactives reglas para fingir un análisis limpio.

Preserva trabajo local y archivos no versionados. En particular, existe un `gradle-daemon-jvm.properties` local con Java 25, no incluido en el commit anterior, frente al JDK 21 documentado. En la carpeta de auditoría está separado para inspección. No lo actives, borres, sobrescribas ni incluyas en un commit sin auditar compatibilidad y procedencia.

## Trabajo que debes realizar

### 1. Auditoría documental y de requerimientos

Lee el caso de cuatro páginas y separa necesidades, datos, resultados, condiciones y restricciones. Contrástalo con guías 10/11 y plantilla. Conserva señales de éxito no cubiertas aunque el MVP inicial no las implemente. Registra trazabilidad bidireccional:

`necesidad → evidencia → RF/RN/RNF/RD → pantalla/modelo/lógica → prueba → entregable/indicador`.

Revisa los ocho atributos del cuestionario IEEE 830 y sus IDs C/NA/CP/CS/OI/VF/MD/TR/SF. No marques «confirmado por el cliente» sin acta/respuesta. «Gestionar» no confirma todas las operaciones; CP-02 incluye «cuando corresponda». No inventes métricas RNF, prioridades comerciales ni fechas para completar tablas.

La guía extra exige antes de entrevista hojas 1/2, borrador y al menos ocho preguntas priorizadas con origen; después acta, al menos ocho RF/tres RNF/tres RN con prioridad/origen/criterio, alcance justificado, modelo/lógica y trazabilidad. Verifica lo realmente preparado, lo pendiente y lo inaplicable por no haber entrevista. Separa preguntas al cliente y al profesor.

Audita el Excel original y la copia de trabajo. Respeta diez pestañas (Instrucciones + 1–9), campos previstos, celdas fijas, RD-01–05, listas, fórmulas y pesos. Los Markdown apoyan pero no reemplazan la plantilla exigida. Concilia numeración antigua de candidatos con RF-01–10 de Guía 10 sin duplicar ni perder necesidades. Las fechas y estados históricos no describen automáticamente el presente.

### 2. Auditoría del primer entregable B-01–09

Para cada control informa: exigencia literal y fuente, evidencia de código/documento/prueba, tipo de evidencia, brecha, dependencia, siguiente acción. Estados: comprobado, implementado sin comprobación, parcial, pendiente o bloqueado por dato concreto. No asignar nota ni 9/9 por tener archivos.

B-01 correr; B-02 estructura; B-03 modelos/enums; B-04 diez productos/tres usuarios ficticios y stock bajo; B-05 seis rutas/menú; B-06 catálogo desde ViewModel/marca; B-07 validar y producto nuevo visible; B-08 README/equipo/decisiones; B-09 commits reales/Trello.

El usuario informó que todo lo propuesto funcionó en práctica; clasifica como evidencia manual declarada, sin convertirlo en prueba de funciones ausentes. No está completo B-07: el código valida pero no guarda sin stock inicial. No existe Trello acreditado, ni grupo/aprobación de modalidad individual. No fabriques integrantes, commits, actas, capturas o pruebas.

### 3. Auditoría Android y prácticas

Inspecciona código y tests reales. Revisa estructura exacta docente, seis pantallas, flujo de eventos/estado, alcance e instancias de ViewModels/repositorio, navegación/back stack/perfiles y comportamiento ante rotación. Revisa si una pantalla valida negocio indebidamente; no confundas condiciones de presentación con RN.

Comprueba modelos y nombres de campos, precio Int CLP, semillas sintéticas separadas, conversiones/errores, duplicados, límites numéricos, estado del formulario, cancelación y pérdida de datos. Señala la diferencia entre rotación y muerte de proceso/persistencia. Revisa códigos de producto y relaciones con historial antes de edición.

Revisa la separación UI/ViewModel/repositorio de Guía 10. No impongas capas de dominio, use cases, Hilt, servidor, abstracciones o pruebas redundantes sin problema concreto. Evalúa mantenibilidad, cohesión, duplicación, acoplamiento y facilidad de probar reglas, con ejemplos localizados. La arquitectura debe ayudar a las necesidades del caso y al aprendizaje evaluado.

Comprueba RN-01–06 donde haya implementación. No certifiques movimientos porque exista un enum o porque la prueba de alerta pase. Stock/historial futuros deben actualizarse consistentemente; validar en ViewModel antes de guardar; registrar fecha/tipo/cantidad/autor y conservar historial. No diseñes la fórmula de ajustes sin respuesta.

Audita compatibilidad de AGP/Gradle/Kotlin/Compose/SDK/JDK con fuentes oficiales. El ejemplo del profesor sirve de referencia, no de configuración automáticamente vigente. No actualices todo a «lo último» ni añadas dependencias por preferencia. Revisa el archivo local Java 25 frente al checkout versionado.

Pruebas históricas: 15 unitarias pasaron el 07-10; APK generado. Lint quedó atascado y no concluyó; no está aprobado. No simules resultados recientes. Propón verificaciones necesarias basadas en riesgos, no tests que repitan nombres de archivos o copien la lógica que verifican. Registra limitaciones.

### 4. Guía 11 y Parcial 2

Audita §9: ficha README para dos recursos con necesidad, API, permiso/tipo, RF y alternativa; declaración pertinente en manifiesto/uses-feature; permiso en contexto, explicación, rechazo, rechazo reiterado y retorno desde Ajustes; resultado al ViewModel; commit y dos capturas Trello. Si ambos recursos no necesitan permiso, la guía permite justificar por qué no aplica o integrar el flujo de notificaciones. No obligues un tercer recurso sin analizar esa excepción.

Audita §10: elegir al menos dos investigaciones de la lista, aplicarlas en el proyecto y documentar una línea explicativa en código. No añadir todas las APIs ni fotografiar personas reales. Verificar las simplificaciones del material con documentación oficial cuando corresponda: permisos por API/versión, TakePicture/CameraX, rationale frente a rechazo permanente, hardware, ciclo de vida, estado tras muerte de proceso. No inventar necesidades para justificar ubicación/contactos/micrófono u otros recursos.

Revisa decisiones §6 de Guía 10: dos recursos, animaciones con propósito, módulo propio justificado e identidad visual. Las propuestas de notificaciones/vibración del README no son decisiones comerciales confirmadas ni implementaciones. El módulo propio es una obligación académica a definir con el caso; no una autorización para cualquier extra.

Matriz de cobertura de Parcial 2: IE 2.1.1 15%, IE 2.1.2 15%, IE 2.2.1 10%, IE 2.2.2 10%, IE 2.3.1 15%, IE 2.3.2 20%, IE 2.4.1 15%. Diferencia primera revisión de base y entrega final. Persistencia local exigida por RD-03/ RF-10 no está implementada. No asegurar nota con la tabla de pesos; falta encargo completo de AVA y guías 12/13.

### 5. Plan de continuación

Prioriza corregir incumplimientos de base, aclaraciones concretas, cierre de decisiones/documentación y luego incrementos hacia Parcial 2. Para cada incremento indica necesidad/RF/RN/RD/B/IE, archivos afectados, dependencias, criterio derivado de fuente, prueba útil y condición de terminado. Explica qué puede avanzar sin respuestas y qué parte sí debe esperar.

Revisa críticamente bloqueos previos: si una decisión es técnica y reversible, justifica su tratamiento; si afecta stock, permisos de negocio, contratos de datos o significado comercial, requiere evidencia. No decidas stock inicial/ajuste/búsqueda comercial mediante conveniencia. No prolongues un bloqueo genérico si la fuente actual ya resolvió el punto.

## Formato de la respuesta e informes

Entrega primero una explicación clara para mí, estudiante: qué problema resolvemos, qué pide ahora el profesor, qué existe, qué falta y qué debo entender/practicar. Después los detalles auditables.

Si puedes escribir, crea un informe nuevo y fechado, sin sobrescribir el historial, con estos apartados:

1. Inventario de fuentes/commit y limitaciones de acceso.
2. Hallazgos por severidad: incumplimiento, defecto, ambigüedad, decisión pendiente, riesgo técnico o falta de evidencia. Para cada uno: fuente, archivo/línea o hoja/celda, problema, impacto, recomendación acotada y dependencia. No presentar una posibilidad no verificada como fallo demostrado.
3. Matriz B-01–09 y cobertura IE/RD/RF/RN, sin nota inventada.
4. Auditoría IEEE 830 y plan de actualización de la plantilla original/copia de trabajo, indicando celdas afectadas; aún no modificar originales.
5. Banco de preguntas priorizadas con origen, cliente/docente destinatario, motivo y decisión dependiente; evita duplicados y preguntas que agreguen funciones no solicitadas.
6. Deuda/riesgos concretos, evidencia de verificaciones y pendientes. No prometer deuda cero o ausencia de fallos.
7. Plan por incrementos y pauta de demostración/estudio para el alumno.

Por cada hallazgo distingue evidencia leída, prueba ejecutada, prueba histórica y declaración manual del usuario. Si no encuentras un problema tras revisar, dilo con el alcance comprobado; no inventes hallazgos. Si encuentras un error en las auditorías previas, corrígelo en tu conclusión con fuente, sin tratar las notas del agente anterior como autoridad.

Termina con el próximo incremento concreto que recomiendas y las únicas respuestas humanas indispensables. **Espera mi autorización antes de cambiar la aplicación o publicar.** Mantén las ambigüedades abiertas y avanza después solo en partes sustentadas. Mi objetivo es aprender, cumplir la evaluación y resolver la necesidad real de Kaiju con una base mantenible y verificable.
