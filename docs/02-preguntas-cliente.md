# Preguntas para la reunión de requerimientos

Fecha de preparación: 30-09-2026. Estado de P-01 a P-20: pendiente, sin respuesta registrada. La reunión aún no se ha realizado.

Estas preguntas aclaran elementos presentes en el caso. No son nuevos requerimientos ni propuestas de funcionalidades. La fuente C-01 y los IDs relacionados están en `01-auditoria-caso.md`.

## 1. Alcance, perfiles y datos

Comenzar por estas preguntas: sus respuestas determinan las tareas y la información que pertenecen al MVP.

| ID | Pregunta neutral | Fuente / trazabilidad | Decisión que depende de la respuesta |
|---|---|---|---|
| P-01 | De las tareas y resultados mencionados en el caso, ¿cuáles deben estar en el MVP semestral y cuáles corresponden a una etapa posterior? ¿Quién validará este alcance? | p. 2, §3.1; p. 3, §4.1 y §5.1; RC-01 a RC-09, RE-01 a RE-06 | Límite y validación del alcance. No borrar expectativas sin acuerdo. |
| P-02 | ¿“Administrador o encargado” es un único perfil o representa perfiles distintos? ¿Cómo se relaciona con encargado de inventario y personal de venta? ¿Una persona puede realizar tareas de varios perfiles? | p. 2, §3.1; RC-08, AM-02 | Perfiles y responsabilidades. |
| P-03 | ¿Qué acciones concretas comprende “gestionar” productos, categorías, stock, ventas, usuarios e indicadores en este MVP? ¿Puede describir un ejemplo ficticio del proceso de cada uno? | p. 2, §3.1; RC-01, RC-02, RC-05, RC-08, RC-09 | Operaciones autorizadas, sin presumir CRUD completo. |
| P-04 | ¿Cómo se determina que una persona está autorizada y qué acciones puede realizar cada perfil? ¿Cómo debe representarse esa autorización en el MVP académico? | p. 2, §3.1 y §3.3; CO-01, RC-08 | Acceso y permisos, sin proponer mecanismo de login. |
| P-05 | §3.1 condiciona el acceso del público general con “cuando corresponda” y §4.1 lo menciona entre los resultados: ¿se incluye en este MVP? Si se incluye, ¿qué puede consultar y qué significa “disponibilidad publicada”, quién la define y cuándo se actualiza? | p. 2, §3.1; p. 3, §4.1; RE-06, AM-01 | Participación del público e información visible. |
| P-06 | ¿Qué productos, materiales e insumos debe representar el MVP? De la información enumerada en §3.2, ¿cuál debe registrarse siempre y cuál depende del caso? ¿Qué significan categoría, tipo de producto y precio, y qué formatos y reglas aplican a esos datos? | p. 1, §2.1; p. 2, §3.2; DT-01 a DT-04, AM-03, AM-04 | Conceptos y contrato de datos, sin presumir tienda de ropa ni entidades separadas. |
| P-07 | ¿Cómo se identifica cada producto: qué significa “código o identificador” y qué reglas debe cumplir? De talla, color, modelo, lote y fecha de vencimiento, ¿cuáles aplican realmente y a qué tipos de producto? ¿Cómo deben relacionarse con el producto? | p. 2, §3.2; DT-01, DT-07 | Identificación y atributos aplicables, sin asumir variantes. |
| P-08 | ¿En qué unidad se expresa la cantidad disponible, la cantidad de movimiento y el stock mínimo? ¿Cómo se determina la existencia inicial y qué valores se admiten en estas cantidades? | p. 2, §3.1 y §3.2; RC-03, RC-04, DT-04, DT-05 | Representación y reglas de cantidades; no decidir enteros, decimales ni stock negativo. |

## 2. Movimientos, ventas y resultados

| ID | Pregunta neutral | Fuente / trazabilidad | Decisión que depende de la respuesta |
|---|---|---|---|
| P-09 | ¿Qué distingue un ingreso, una salida y un ajuste? ¿Qué información y efecto sobre la cantidad disponible debe tener cada uno, y qué representa la fecha del movimiento? ¿Cómo se registran las salidas por consumo mencionadas en el contexto? | p. 1, §2.2; p. 2, §3.1 y §3.2; RC-04, DT-05, AM-05 | Reglas de movimientos, ajustes y fechas. |
| P-10 | ¿Qué información constituye una venta y cómo se registra? ¿Cómo se relaciona una venta registrada con las existencias y los movimientos para que el registro sea correcto? | p. 1, §2.2; p. 2, §3.1 y §3.2; RC-05, DT-06, AM-05 | Flujo de venta y efecto de inventario. No presuponer descuento automático. |
| P-11 | ¿Qué información debe poder consultarse o conservarse para considerar el inventario “trazable por producto”? ¿Qué debe ocurrir con la trazabilidad cuando se corrige un registro? | p. 1, §2.4; p. 2, §3.1; p. 3, §4.1; RC-06, RE-01 | Consulta de trazabilidad y correcciones, sin presumir historial inmutable. |
| P-12 | ¿Cómo se define stock bajo y necesidad de reposición? ¿Cómo se utiliza el stock mínimo y qué debe ocurrir cuando se alcanza ese nivel? ¿Qué reglas generales de stock nos entregarán? | p. 1, §2.3 y §2.4; p. 2, §3.2; p. 4, §6; RC-07, DT-04, MA-03 | Regla de reposición y comportamiento esperado, sin asumir comparación ni notificación. |
| P-13 | ¿Qué indicadores necesita el administrador o encargado? Para más vendidos y menos vendidos, ¿cómo se calcula el resultado, qué período abarca, qué productos se consideran y cómo se espera consultarlo? | p. 2, §3.1 y §3.2; p. 3, §4.1; RC-09, RE-03 | Indicadores y criterios de orden; no elegir cantidad, ingresos o top N por cuenta propia. |
| P-14 | ¿Qué información debe mostrar la tendencia de ventas? ¿Qué períodos, categorías o tipos deben considerarse, cómo se compara la información y qué presentación permite cumplir el objetivo? | p. 2, §3.2; p. 3, §4.1; RE-04 | Cálculo y visualización de tendencias, sin imponer gráficos ni filtros. |

## 3. Condiciones de uso, opciones y validación

| ID | Pregunta neutral | Fuente / trazabilidad | Decisión que depende de la respuesta |
|---|---|---|---|
| P-15 | ¿Qué teléfonos y condiciones de conexión se consideran para el MVP? ¿Entre quiénes y qué información debe sincronizarse? ¿Cuándo debe verse reflejado un registro para considerarlo actualizado y qué debe ocurrir si dos personas registran cambios simultáneamente? | p. 2, §3.3; p. 3, §4.1; CO-01, CO-02, RE-01 | Entorno, actualización y coordinación; no imponer backend ni tiempo real. |
| P-16 | El caso permite evaluar persistencia local: ¿se incorpora en este MVP? ¿Qué debe poder consultarse o registrarse cuando no hay conexión y cómo se tratarían esos registros temporales al recuperarla? | p. 2, §3.3; CO-03, AM-08 | Comportamiento sin conexión solo si corresponde. |
| P-17 | ¿Se incluirán notificaciones de niveles mínimos o cámara para leer QR/códigos de barras en el MVP? Si se incluyen, ¿qué comportamiento se espera y quién confirma que está dentro del alcance académico? | p. 2, §3.3; p. 4, §6; CO-04, MA-03 | Opciones autorizadas y restricciones académicas. Pregunta conjunta a cliente y docente según responsabilidad. |
| P-18 | ¿Cómo se evaluarán, con datos ficticios, la disminución de diferencias y quiebres de stock, la rapidez del registro y la claridad de la disponibilidad? ¿Qué ejemplos o evidencias permitirán aceptar cada resultado de §4.1? | p. 3, §4.1 y §5.1; RE-01 a RE-06 | Criterios verificables; no prometer impacto productivo sin medición. |
| P-19 | ¿Cuándo se entregarán el catálogo ficticio, los ejemplos de movimientos y las reglas generales de stock? ¿Quién confirma su revisión y qué versión debemos usar para validar la propuesta? | p. 4, §6; MA-01 a MA-03 | Datos y reglas autorizados; evitar inventarlos. |
| P-20 | **Para el docente:** ¿cómo aplicar las actividades de las guías 07-09 a Kaiju sin ampliar el alcance del cliente, especialmente la referencia a “tienda de ropa”, los tamaños de equipo distintos y los recursos nativos opcionales? Si la solución necesitara autenticación técnica de prueba, ¿qué entorno es admisible ante la restricción de credenciales, llaves y tokens del caso? | Guías 07-09; p. 2, §3.3 y §3.4; p. 3, §5.1; AM-10, RS-03 | Condiciones académicas y técnicas. No atribuir al cliente decisiones del docente. |

La mención de casos simultáneos, correcciones o datos de movimiento busca precisar capacidades existentes; no confirma por sí misma nuevas funciones. Si el interlocutor amplía el alcance, registrar esa ampliación como tal, con su fuente y responsable.

## 4. Registro de respuestas

Usar una entrada por pregunta respondida. Mantener lo no respondido como pendiente; no llenar este registro con respuestas del equipo ni con inferencias.

```text
Pregunta: P-xx
Fecha de reunión:
Interlocutor y responsabilidad:
Respuesta recibida:
Evidencia o minuta asociada:
Alcance de la respuesta / condiciones:
IDs documentales afectados:
Reglas, datos y ejemplos confirmados:
Criterios de aceptación acordados, si se definieron:
Aspectos que siguen pendientes:
Responsable de validación:
Estado: pendiente / respondida, pendiente de validación / validada
```

Después de la reunión, actualizar la auditoría enlazando cada decisión a su respuesta. Distinguir la validación comercial del cliente de la validación académica del docente. No marcar una pregunta como validada por haber preparado la minuta.
