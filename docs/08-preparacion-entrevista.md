# Preparación de la entrevista Kaiju

Fecha de preparación: 04-10-2026. No se proporcionó convocatoria ni acta. Esta pauta prepara la conversación; no registra respuestas del cliente.

Fuente principal del procedimiento: N-02 §7-8. Preguntas de apoyo: N-03 §1. Fuentes/IDs en `07-auditoria-material-nuevo.md`; P-01 a P-28 en `02-preguntas-cliente.md`.

## 1. Datos que faltan completar

| Dato | Estado |
|---|---|
| Fecha, hora, duración, lugar o enlace | Por confirmar con docente/coordinación. |
| Integrantes y número de grupo | No aportados. |
| Interlocutor(es) del cliente y responsabilidad | Por confirmar. |
| Conducción | Integrante por asignar. |
| Registro de respuestas | Integrante por asignar. |
| Control de tiempo | Integrante por asignar. |
| Plazo/canal de entrega de borrador, pauta y acta | Por confirmar con docente. |
| Medio para validar acta y resolver preguntas posteriores | Por acordar. |

Las tres responsabilidades de conversación no prueban que el grupo tenga tres personas. Repartirlas según equipo real. No inventar nombres o fechas para cerrar pendientes de la hoja 5.

## 2. Presentación breve propuesta

“Somos el equipo de DSY1105 que trabaja el caso de gestión y trazabilidad de inventario de Kaiju. Leímos el documento recibido. Queremos comprender su proceso y aclarar los puntos que todavía no define, para preparar un MVP académico con datos ficticios. Al terminar resumiremos lo entendido para que pueda corregirnos.”

Agregar nombres/grupo solo cuando se conozcan. No prometer funcionalidades, plazos ni continuidad laboral.

## 3. Pauta principal priorizada

El orden es propuesta del equipo para conducir la reunión; no prioridad de los requisitos del cliente. Hay doce preguntas principales, superando el mínimo académico de ocho. Adaptar lenguaje y no repetir lo que el cliente ya respondió espontáneamente.

| Orden | Pregunta para conversar | Qué la motiva / fuente | Preguntas de seguimiento vinculadas |
|---|---|---|---|
| 1 | ¿Nos puede contar cómo manejan un producto desde que llega hasta que se utiliza o vende y revisan lo que queda? ¿Puede explicarlo con un ejemplo ficticio de cómo lo anotan hoy? | C-01 §2.1-2.2; N-02 §7; conocer proceso sin copiar datos reales. | P-21. |
| 2 | De las tareas descritas en el caso, ¿cuáles necesita cubrir este MVP para considerarlo útil y cuáles quedarían para después? | C-01 §3.1, §4.1, §5.1; frontera y prioridades sin darlas por supuestas. | P-01. |
| 3 | ¿Qué hace cada perfil en ese proceso? ¿“Administrador o encargado” es un perfil único? ¿Qué significa gestionar productos, categorías y usuarios? | C-01 §3.1; verbos y responsabilidades abiertos. | P-02, P-03, P-04; ampliar por objeto, sin suponer CRUD. |
| 4 | ¿Cómo identifican los productos y qué datos deben registrarse? ¿Qué diferencia hay entre categoría y tipo? ¿Cuáles atributos de la lista aplican efectivamente? | C-01 §3.2; datos y atributos condicionales. | P-06, P-07. |
| 5 | ¿En qué unidad registran cantidades y qué valores admiten? ¿Cómo determinan la existencia inicial y qué distingue un ingreso, una salida y un ajuste? | C-01 §2.2, §3.1-3.2; movimientos y cantidades sin reglas. | P-08, P-09. |
| 6 | ¿Qué información constituye una venta y cómo se relaciona con el registro de existencias y movimientos? | C-01 §2.2, §3.1-3.2; riesgo de asumir descuento o doble registro. | P-10. |
| 7 | ¿Qué necesitan conservar y consultar para seguir la historia de un producto? La guía menciona saber quién hizo los movimientos: ¿qué información necesitan para ello? ¿Cómo corrigen errores y manejan excepciones? | C-01 §2.4, §3.1, §4.1; N-02 §1; N-03 §1 reglas. | P-11, P-22, P-24. |
| 8 | ¿Cómo determinan stock bajo o necesidad de reposición, cómo usan el mínimo y qué esperan que ocurra al alcanzarlo? | C-01 §2.3-2.4, §3.2, §6; reglas anunciadas aún no entregadas. | P-12, P-19. |
| 9 | ¿Qué indicadores necesitan consultar? ¿Cómo entienden más/menos vendidos y tendencias, con qué períodos y cómo evaluarían que la información les resulta útil? | C-01 §3.1-3.2, §4.1; fórmulas, períodos y aceptación no definidos. | P-13, P-14, P-18. |
| 10 | ¿Quiénes necesitan compartir información, cuándo debe verse actualizada y qué debe ocurrir cuando no hay conexión? ¿En qué teléfonos/condiciones se usará y cuánto pueden esperar al consultar o registrar? | C-01 §3.3; N-03 §1 calidad; actualización y condiciones abiertas. | P-15, P-16, P-23, P-25, P-26. |
| 11 | ¿Este MVP incluirá consulta del público general? Si corresponde, ¿qué disponibilidad se publica? Respecto de las opciones de cámara o notificaciones del caso, ¿qué uso necesitan realmente? | C-01 §3.1, §3.3, §4.1; opciones condicionadas; RD-04 es requisito académico distinto. | P-05, P-17. Confirmar con docente cómo satisfacer RD-04 de forma coherente. |
| 12 | Resumimos lo que entendimos y lo que sigue pendiente: ¿qué corregiría? ¿Falta algo importante? ¿Por qué medio podemos validar esta minuta y aclarar lo que falte? | N-02 §7 cierre; N-03 §1 cierre; evitar acuerdos falsos. | P-28. |

Preguntas adicionales según tiempo y contexto: P-27 (restricciones/cambios futuros), P-19 (material autorizado), dudas específicas de datos y excepciones. D-01 a D-14 de la auditoría nueva son para el docente, no para cargar al cliente decisiones del curso.

## 4. Durante la conversación

- Separar situación actual de comportamiento esperado del MVP.
- Registrar lo solicitado, la razón que explica el cliente y las condiciones. Preguntar “para qué” no autoriza al equipo a reemplazar su solicitud por una interpretación propia.
- Ante una cifra, umbral, excepción o prioridad, pedir un ejemplo ficticio que lo precise y registrar quién lo afirma.
- Ante falta de respuesta, marcar pendiente y acordar cómo resolverlo. No sugerir una regla como si ya estuviera confirmada.
- Si se muestran registros actuales, usar demostración autorizada ficticia/depurada; no guardar información productiva, datos personales o accesos prohibidos.
- Al cierre, leer acuerdos y pendientes separados. No marcar una decisión como validada por haberla escrito.

## 5. Plantilla de acta para completar después

Esta estructura es propuesta del equipo, no formato oficial suministrado. Debe completarse el mismo día de la entrevista según N-02 §7. Actualmente está vacía de respuestas.

```text
ACTA DE ENTREVISTA KAIJU
Fecha y modalidad:
Asistentes y responsabilidad:
Equipo/grupo:
Responsables de conducción, registro y tiempo:
Objetivo de la reunión:

Para cada pregunta:
ID / fuente que motivó la pregunta:
Respuesta y persona que la entregó:
Ejemplo autorizado, si se proporcionó:
Condiciones, excepciones y alcance:
Estado: respuesta recibida / requiere validación / validada
RF/RNF/RN o pendiente afectado:

Resumen corregido por el interlocutor:
Decisiones del cliente:
Indicaciones del docente (separadas):
Propuestas del equipo (separadas):
Pendientes: motivo / responsable por acordar / fecha por acordar
Material prometido o recibido y versión:
Medio y procedimiento para validar esta acta:
```

Conservar respuestas y cambios identificables, sin inventar firma, aprobación o consentimiento de grabación. Prioridad y criterio de aceptación deben provenir del acuerdo pertinente, no del orden de preguntas.

## 6. Después

1. Redactar el acta ese mismo día y registrar cómo se validará.
2. Relacionar cada respuesta con P-xx y los requisitos candidatos; mantener IDs, sin reutilizarlos al eliminar requisitos.
3. Completar reglas/criterios solo con evidencia. Mantener pendientes de prioridad, datos y métricas.
4. Actualizar alcance, modelo lógico y trazabilidad.
5. Revisar con los 35 controles N-03; no afirmar completitud cuando hay decisiones abiertas.
6. Implementar únicamente partes con comportamiento suficientemente definido, respetando RD académicas.
