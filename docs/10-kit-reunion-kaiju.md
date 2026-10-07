# Kit de reunión Kaiju

Preparación previa a entrevista | 04-10-2026

## Preparación de la reunión Kaiju

DSY1105 Desarrollo de Aplicaciones Móviles | Preparación del 04 de octubre de 2026

Objetivo: aclarar el caso y dejar evidencia de las respuestas para definir el MVP académico. Las capacidades documentadas tienen detalles pendientes; una pregunta o propuesta del equipo no equivale a un acuerdo del cliente.

### Qué pide el profesor antes de la entrevista

- Hoja 1 del Excel: negocio, problema y objetivo en palabras del equipo, más un primer glosario.

- Hoja 2: perfiles del caso y tareas de cada uno.

- Primer borrador de requerimientos, aun con dudas identificadas.

- Pauta con al menos ocho preguntas, ordenadas por importancia y con la parte del caso que las motiva; responsabilidades de conversación repartidas.

Fuente: Guía extra Kaiju N-02, §7 y §8. No indica fecha, hora, modalidad ni plazo de entrega.

### Qué queda preparado con este paquete

- Copia del Excel con contexto, glosario provisional, roles, quince RF candidatos, tres ejes RNF incompletos y pendientes. Las RN permanecen sin completar: no hay tres reglas operativas confirmadas.

- Doce preguntas principales con seguimiento, evidencia y espacio de notas; dudas docentes separadas y acta para completar tras la reunión.

### Qué completar antes de asistir

Equipo y grupo: ________________________  Cliente e interlocutor: ________________________

Fecha y hora: __________________________  Lugar o enlace: _____________________________

Duración acordada: _____________________  Canal y plazo de entrega: _____________________

Conducción: ___________________________  Registro: _________________________________

Control de tiempo: _____________________  Validación posterior del acta: _________________

Repartir las responsabilidades entre los integrantes reales; esta lista no supone un equipo de tres personas.

## Lo que sabemos y lo que falta precisar

### Base explícita del caso

Kaiju recibe, almacena, utiliza o vende productos, materiales e insumos. El control actual depende de registros manuales y planillas. Hay posibles diferencias de existencias, omisiones y dificultades de reposición; no se cuantifican. C-01 §2.1-2.4.

Los perfiles descritos son administrador o encargado; personal de venta; encargado de inventario; y público general o cliente cuando corresponda. Hay consulta/registro de productos, consulta de disponibilidad, registro de ventas, ingresos, salidas y ajustes, revisión de movimientos y gestión general por precisar. C-01 §3.1.

El caso enumera datos como identificador, nombre, descripción, categoría, tipo, precio, cantidad disponible, mínimo y datos de movimientos/ventas. Talla, color, modelo, lote y vencimiento son condicionales. La lista no define obligatoriedad ni tipos. C-01 §3.2.

Android para personal autorizado; Internet para sincronización considerado, persistencia local evaluable y recursos opcionales condicionados al alcance académico. No se exige integrar sistemas existentes. El proyecto es un MVP académico con material autorizado de prueba. C-01 §3.3-3.4 y §5.1.

### Ambigüedades que afectan la construcción

- P-01 a P-05: alcance, prioridad, perfiles, acciones de “gestión”, autorización y público condicional.

- P-06 a P-09: datos aplicables/obligatorios, identificador, categoría/tipo, unidades, existencia inicial y movimientos.

- P-10 a P-12 y P-22/P-24: relación venta-stock, historia, autor, correcciones, mínimo y reposición.

- P-13 a P-18 y P-23/P-25/P-26: cálculos, períodos, actualización, conexión, uso, opciones y aceptación.

- P-19: catálogo ficticio, ejemplos de movimientos y reglas de stock anunciados en §6 todavía sin recibir.

### Qué sigue sin autorización para definir

No cerrar CRUD, login o permisos; descuento automático por venta; prohibición de stock negativo; comparación con el mínimo; notificaciones; gráficos; sincronización automática; ni acceso público. Cada decisión depende de su respuesta. Tampoco copiar reglas del ejemplo del profesor.

La guía añade conocer quién hizo los movimientos (N-02 §1): se pregunta como precisión docente, sin atribuirla a una respuesta del cliente. Una condición no definida sigue pendiente; no se declara automáticamente fuera del alcance.

## Cómo conducir y qué practicar

### Presentación breve para ensayar

Somos el equipo de DSY1105 que trabaja el caso de gestión y trazabilidad de inventario de Kaiju. Leímos el documento recibido. Queremos comprender su proceso y aclarar los puntos que todavía no define, para preparar un MVP académico con datos ficticios. Al terminar resumiremos lo entendido para que pueda corregirnos.

### Responsabilidades durante la conversación

- Conducción: presenta, hace una pregunta por vez, escucha y usa seguimiento solo cuando falta el detalle.

- Registro: anota pregunta, respuesta, persona, condiciones, ejemplo y pendiente; separa lo dicho de la propuesta del equipo.

- Tiempo: comprueba temas cubiertos, evita repeticiones y reserva un cierre para leer acuerdos y pendientes. La distribución temporal se adapta a la duración real.

### Forma de preguntar

Empezar por el proceso actual. Después preguntar qué necesita en el MVP y para qué. Si una respuesta es general, pedir un ejemplo ficticio y precisar condición, acción y resultado. No ofrecer respuestas sugeridas como si fueran reglas existentes. Saltar seguimientos ya respondidos; no repetir lo que está escrito.

Si aparece una solicitud nueva: identificarla como ampliación, registrar su razón y quién la solicita; no incorporarla automáticamente al compromiso semestral. No prometer funciones, fechas ni continuidad laboral. Fuente: N-02 §7; método de registro del equipo.

### Práctica recomendada del equipo

- Explicar sin leer el negocio, problema y objetivo, distinguiendo capacidad explícita de detalle pendiente.

- Distinguir RF (qué hace el sistema), RN (condición operativa del negocio), RNF (calidad/condición comprobable) y RD (restricción académica).

- Simular una pregunta y anotar una respuesta ficticia solo en una hoja de ensayo; comprobar que no se transforme en acuerdo del cliente.

- Reformular una respuesta ambigua sin resolverla: “¿qué cantidad y en qué condición?”; ensayar un resumen que invite a corregir.

Esta práctica es recomendación de preparación, no una evaluación adicional inventada. Para esta reunión no hace falta construir funciones para rellenar vacíos. Las exigencias técnicas de la app se trabajan posteriormente con alcance suficiente.

## Pauta de entrevista preguntas 1 a 3

Orden propuesto por el equipo, no prioridad comercial. Hacer primero la pregunta principal; usar seguimientos según lo ya conversado. Todas las respuestas están pendientes.

### 01 Proceso actual

¿Cómo manejan hoy un producto desde que llega hasta que se utiliza o vende?

Seguimiento: Pedir un ejemplo ficticio de cómo anotan los cambios y revisan lo que queda.

Fuente y vínculo: C-01 §2.1-2.2; N-02 §7. P-21.

Por qué preguntamos: Conocer el proceso real y distinguirlo del comportamiento esperado del MVP.

Respuesta y persona / condición / pendiente:

Respuesta:

Persona:

Condiciones o pendientes:

### 02 Alcance del MVP

De las tareas del caso, ¿cuáles necesita cubrir este MVP para considerarlo útil?

Seguimiento: ¿Cuáles quedarían para después? ¿Quién valida el alcance? Precisar prioridad de cada tarea sin asignarla por nosotros.

Fuente y vínculo: C-01 §3.1, §4.1 y §5.1. P-01.

Por qué preguntamos: Priorizar capacidades descritas y evitar prometer profundidad o alcance no acordados.

Respuesta y persona / condición / pendiente:

Respuesta:

Persona:

Condiciones o pendientes:

### 03 Perfiles y gestión

¿Qué hace cada perfil en ese proceso?

Seguimiento: ¿Administrador o encargado es un perfil único? Precisar, uno por uno, qué significa gestionar productos, categorías, stock, ventas, usuarios e indicadores. ¿Cómo se determina autorización y qué acciones permite cada perfil?

Fuente y vínculo: C-01 §3.1 y §3.3. P-02/P-03/P-04.

Por qué preguntamos: No inferir operaciones CRUD, perfiles adicionales ni mecanismos de acceso.

Respuesta y persona / condición / pendiente:

Respuesta:

Persona:

Condiciones o pendientes:

## Pauta de entrevista preguntas 4 a 6

Orden propuesto por el equipo, no prioridad comercial. Hacer primero la pregunta principal; usar seguimientos según lo ya conversado. Todas las respuestas están pendientes.

### 04 Datos de los productos

¿Qué datos deben registrarse para representar e identificar sus productos?

Seguimiento: De la lista del caso, ¿cuáles siempre y cuáles según el producto? Aclarar código/identificador, categoría/tipo, precio y atributos condicionales, con un ejemplo ficticio.

Fuente y vínculo: C-01 §3.2. P-06/P-07.

Por qué preguntamos: Obtener significado, obligatoriedad y reglas de datos antes de diseñar formularios.

Respuesta y persona / condición / pendiente:

Respuesta:

Persona:

Condiciones o pendientes:

### 05 Cantidades y movimientos

¿Qué distingue un ingreso, una salida y un ajuste en su proceso?

Seguimiento: ¿En qué unidades y con qué valores se registran cantidades? ¿Cómo se obtiene la existencia inicial? Precisar datos, fecha, efecto y salidas por consumo.

Fuente y vínculo: C-01 §2.2 y §3.1-3.2. P-08/P-09.

Por qué preguntamos: Evitar asumir enteros, límites, signos, fechas o efectos de cada movimiento.

Respuesta y persona / condición / pendiente:

Respuesta:

Persona:

Condiciones o pendientes:

### 06 Registro de ventas

¿Qué información constituye una venta y cómo se registra?

Seguimiento: ¿Cómo se relaciona con existencias y movimientos para que el registro sea correcto? Pedir un ejemplo ficticio y las condiciones/excepciones que correspondan.

Fuente y vínculo: C-01 §2.2 y §3.1-3.2. P-10.

Por qué preguntamos: No decidir descuento automático, doble registro ni estructura de una venta.

Respuesta y persona / condición / pendiente:

Respuesta:

Persona:

Condiciones o pendientes:

## Pauta de entrevista preguntas 7 a 9

Orden propuesto por el equipo, no prioridad comercial. Hacer primero la pregunta principal; usar seguimientos según lo ya conversado. Todas las respuestas están pendientes.

### 07 Historia y correcciones

¿Qué necesitan conservar y consultar para seguir la historia de un producto?

Seguimiento: ¿Cómo se corrige un error? ¿Existen excepciones y quién las autoriza? La guía añade saber quién hizo los movimientos: ¿qué información necesitan para ello? Separar esa precisión docente.

Fuente y vínculo: C-01 §2.4, §3.1 y §4.1; N-02 §1. P-11/P-22/P-24.

Por qué preguntamos: Aclarar trazabilidad, autor y tratamiento de correcciones sin inventar historial inmutable ni login.

Respuesta y persona / condición / pendiente:

Respuesta:

Persona:

Condiciones o pendientes:

### 08 Mínimo y reposición

¿Cómo determinan que hay stock bajo o necesidad de reposición?

Seguimiento: ¿Cómo utilizan el mínimo y qué debe ocurrir al alcanzarlo? ¿Cuándo y por quién recibiríamos las reglas generales, el catálogo ficticio y ejemplos anunciados?

Fuente y vínculo: C-01 §2.3-2.4, §3.2 y §6. P-12/P-19.

Por qué preguntamos: Obtener reglas y material prometidos sin elegir comparación, alertas o valores.

Respuesta y persona / condición / pendiente:

Respuesta:

Persona:

Condiciones o pendientes:

### 09 Indicadores y aceptación

¿Qué indicadores necesitan consultar y para qué decisión los utilizan?

Seguimiento: Precisar cómo se calculan más/menos vendidos, universo, período y tendencias por categoría/tipo. ¿Qué ejemplo o evidencia ficticia permitiría aceptar las señales de éxito?

Fuente y vínculo: C-01 §3.1-3.2 y §4.1. P-13/P-14/P-18.

Por qué preguntamos: No asumir fórmula, productos sin ventas, top N, filtros, gráficos ni mejora cuantificada.

Respuesta y persona / condición / pendiente:

Respuesta:

Persona:

Condiciones o pendientes:

## Pauta de entrevista preguntas 10 a 12

Orden propuesto por el equipo, no prioridad comercial. Hacer primero la pregunta principal; usar seguimientos según lo ya conversado. Todas las respuestas están pendientes.

### 10 Uso y actualización

¿Quiénes necesitan compartir información y cuándo debe verse actualizada?

Seguimiento: Precisar teléfonos, frecuencia y experiencia de uso; qué ocurre sin conexión o con cambios simultáneos; espera aceptable y consecuencias de no poder usarla. ¿Qué información se limita a cada perfil?

Fuente y vínculo: C-01 §3.3 y §4.1; N-03 §1. P-15/P-16/P-23/P-25/P-26.

Por qué preguntamos: Definir condiciones y comprobación sin imponer tiempo real, umbrales o solución técnica.

Respuesta y persona / condición / pendiente:

Respuesta:

Persona:

Condiciones o pendientes:

### 11 Opciones del caso

El acceso público aparece condicionado: ¿corresponde incluirlo en este MVP?

Seguimiento: Si corresponde, precisar información y disponibilidad publicada. Para las opciones de cámara/lectura de códigos y notificaciones, preguntar si hay necesidad y qué comportamiento esperan. La evaluación de recursos nativos se aclara aparte con docente.

Fuente y vínculo: C-01 §3.1, §3.3 y §4.1. P-05/P-17.

Por qué preguntamos: Resolver condiciones sin convertir ejemplos opcionales en compromisos del cliente.

Respuesta y persona / condición / pendiente:

Respuesta:

Persona:

Condiciones o pendientes:

### 12 Cierre y validación

Al resumir lo conversado, ¿qué corregiría o falta preguntar?

Seguimiento: Leer decisiones y pendientes separados. ¿Por qué medio se valida el acta y quién responde lo que falta? Si es pertinente, consultar restricciones y cambios próximos P-27, sin ampliar automáticamente el alcance.

Fuente y vínculo: N-02 §7; N-03 §1. P-28/P-27.

Por qué preguntamos: Evitar acuerdos falsos y definir cómo se resuelven pendientes.

Respuesta y persona / condición / pendiente:

Respuesta:

Persona:

Condiciones o pendientes:

## Aclaraciones dirigidas al profesor

Son decisiones de evaluación o formato, no peticiones de funciones al cliente. Registrar por separado quién responde y la indicación recibida. Priorizar convocatoria, entregables y exigencias que afectan el alcance.

D-01 | Fecha, duración, modalidad, quién coordina, canal/plazos de entregas y seguimiento. Fuente: N-02 §7-8 prevé reunión, sin agenda/plazos.

D-02 | ¿Es precisión académica exigida? ¿Cómo contrastarla con cliente sin asumir autenticación? Fuente: N-02 §1 añade autor del movimiento respecto a C-01.

D-03 | Qué datos deben persistirse para evaluación; separar esa exigencia de funcionamiento offline y sincronización. Fuente: RD-03 esencial vs persistencia evaluable en C-01.

D-04 | Cuáles usos nativos coherentes puede elegir el equipo y cuáles requieren acuerdo del cliente; cómo evaluar si no se necesita cámara/notificaciones. Fuente: RD-04 esencial vs recursos opcionales en C-01.

D-05 | ¿Se exige ambas dependencias o solo la pertinente a los datos? No instalar ambas por interpretación. Fuente: Checklist hoja 9 B6 enumera Room y DataStore, pero RD-03/hoja 9 B12 dicen y/o.

D-06 | Dónde adjuntar lógica: anexo, sección adicional o archivo separado, sin alterar el original/fijos. Fuente: Guía §8 pide lógica paso a paso en hoja 7; Excel solo tiene entidad/campos/persistencia/relaciones.

D-07 | Cómo indicar glosario provisional y estado pendiente en el borrador. Fuente: Hoja 1 B18 pide definición acordada; guía pide primer glosario antes de entrevistar.

D-08 | Cómo registrar estado/estabilidad y fuentes sin cambiar estructura exigida. Fuente: OI-03 pide estabilidad; RF/RNF no tienen columna de estado/estabilidad.

D-09 | Cómo evaluar pendientes si cliente no aporta suficientes reglas; no llenar cuotas con restricciones técnicas ni cifras supuestas. Fuente: Mínimos posteriores >=8 RF/3 RNF/3 RN vs reglas y métricas ausentes.

D-10 | Si esos ejemplos constituyen verificación suficiente y dónde poner criterio pedido en guía. Fuente: RN usa ejemplos cumple/no cumple y no columna de aceptación titulada así.

D-11 | Formato admisible de verificación sin inventar segundos, porcentajes o límites. Fuente: VF-02 exige métrica/umbral para todo RNF; seguridad/plataforma pueden comprobarse por condiciones categóricas.

D-12 | Cómo registrar cobertura si cliente decide no incluirlo; no declarar cubierto un perfil excluido. Fuente: Disponibilidad pública RE-06 pero público condicional.

D-13 | Excepciones de formato permitidas y dónde entregar anexos. Fuente: Instrucción de amarillas, módulo RF azul claro editable; sin campo para pauta/acta.

D-14 | Qué orden/plazos de evaluación aplicar; mantener foco actual en análisis sin cancelar trabajo previo. Fuente: Docente pide preparación antes de Android Studio; guías anteriores tienen entregables técnicos.

Situación adicional ya registrada: Git está inicializado y conectado al repositorio autorizado; el repositorio es público y la guía 07 pide privado. Confirmar cómo cumplir esa indicación sin alterar visibilidad por cuenta propia. Git no demuestra participación de todo el equipo ni existencia de Trello.

Respuesta docente y responsable: _____________________________________________________

Indicación / fecha / entrega afectada: __________________________________________________

## Acta de entrevista para completar

Plantilla del equipo. Completar el mismo día de la entrevista (N-02 §7-8). No registra ninguna reunión realizada. Repetir el bloque de respuesta por cada pregunta tratada, usando hojas adicionales o la versión editable.

Fecha y horario: _______________________  Modalidad o lugar: __________________________

Equipo y grupo: __________________________________________________________________

Asistentes y responsabilidad: _________________________________________________________

Conducción / registro / tiempo: ________________________________________________________

Objetivo: comprender el proceso, aclarar el alcance y registrar respuestas y pendientes del caso Kaiju.

### Registro por respuesta

ID de pregunta y fuente: _____________________  Persona que responde: ____________________

Respuesta recibida y razón: __________________________________________________________

Continuación de respuesta:

Respuesta:

Persona:

Condiciones o pendientes:

Ejemplo ficticio autorizado / material y versión: ___________________________________________

Condiciones y excepciones: __________________________________________________________

Prioridad y criterio acordados si se definieron: ____________________________________________

IDs RF / RNF / RN / pendiente afectados: _________________________________________________

Estado: pendiente / respuesta recibida requiere validación / validada con evidencia identificable.

### Cierre del acta

Resumen corregido y decisiones del cliente: ______________________________________________

Indicaciones del docente separadas: ___________________________________________________

Propuestas del equipo sin aprobación: __________________________________________________

Pendientes con motivo / responsable / fecha por acordar: ____________________________________

Medio y evidencia de validación: ________________________________________________________

Escribir el acta no acredita aprobación. No inventar firma, asistentes, consentimiento de grabación, respuestas ni fechas de seguimiento.

## Después de la reunión y revisión del borrador

### Qué pide la guía después de entrevistar

- Acta con fecha, asistentes, preguntas y respuestas, redactada el mismo día.

- Al menos ocho RF, tres RNF y tres RN, con identificador, prioridad, origen y criterio de aceptación.

- Hoja 5: dentro y fuera del MVP, cada decisión justificada, más pendientes.

- Hoja 7: desglose de información y lógica de reglas principales. El lugar para la lógica paso a paso se consulta en D-06.

- Hoja 8: relación de cada señal de éxito con requisitos que la cubren. Revisar el borrador con el Cuestionario IEEE 830. Fuente: N-02 §8.

Los mínimos no autorizan inventar reglas o métricas. Si una respuesta falta, dejar el pendiente y pedir al docente cómo se evalúa (D-09). Los quince RF candidatos y tres ejes RNF del Excel todavía no equivalen a requisitos completos y aceptados.

### Secuencia de actualización

- Registrar evidencia y conectar respuesta con pregunta y requisito; mantener IDs e historial de cambios.

- Descomponer verbos generales solo cuando se conozcan acciones; revisar posibles duplicidades entre gestión de indicadores y análisis.

- Redactar RF con actor/condición/acción/resultado; RN con condición y consecuencia más ejemplos acordados; RNF con condición verificable, métrica y umbral cuando proceda. No introducir comportamiento nuevo para escribir la prueba.

- Actualizar alcance y modelo según evidencia; distinguir planificado de implementado en pantalla, componente y prueba. No marcar X sin comprobación.

### Fuentes para mantener la trazabilidad

C-01: Caso DSY1105 Aplicaciones Móviles KAIJU, PDF de cuatro páginas, §1-6; fuente del caso, no acta firmada. N-01: texto del mismo caso, sin cambios de alcance.

N-02: Guía extra Caso semestral Kaiju, §1 y §5-8. N-03: Cuestionario IEEE 830, §0-2; herramienta de revisión, no formulario para entregar. N-04: plantilla de requerimientos, hojas 1-9.

Fuentes originales conservadas en docs/fuentes/2026-10-04 y PDF en raíz. Auditoría completa: docs/01 y docs/07; banco P-01 a P-28: docs/02. Este kit agrupa preguntas, no sustituye ni registra respuestas.

Uso del Excel: completar entradas amarillas; los módulos RF son placeholders editables. Prioridades comerciales vacías a propósito; RN, pantallas, modelo y trazabilidad final se completan con evidencia. No cambiar datos fijos, RD ni pesos de rúbrica.
