# Capacidades: qué construir y qué mantener pendiente

Fecha: 30-09-2026. Decisión de trabajo del equipo, autorizada por el usuario: avanzar por partes que no requieren resolver reglas ambiguas. No se modifica el alcance documental del cliente ni se registran respuestas nuevas a P-01 a P-20.

## 1. Tres estados distintos

- **Construible como base técnica:** organización del proyecto, compilación y ejecución; no determina negocio.
- **Construible como propuesta visual:** representación de información explícita con ejemplos ficticios. Diseño y datos de muestra no constituyen contrato de datos, permisos ni criterios de aceptación del cliente.
- **Pendiente de reglas o alcance:** acciones, cálculos y efectos sobre datos que necesitan una respuesta. Se bloquea esa parte, no toda la aplicación.

En esta entrega se completa la separación de capacidades y se generan tres propuestas visuales en Lovable. Todavía no se crea el proyecto Android ni se implementan reglas de negocio. La corrección acotada del visor está pendiente de aprobación del plan en Lovable.

## 2. Matriz por capacidad

Las fuentes detalladas de RC, DT, RE y CO se conservan en `01-auditoria-caso.md`; las preguntas están en `02-preguntas-cliente.md`. “Propuesta” significa decisión reversible del equipo, no exigencia de pantalla ni aprobación del cliente.

| Capacidad | Evidencia | Parte que puede avanzarse ahora | Parte que sigue pendiente | Dependencias para implementar esa parte |
|---|---|---|---|---|
| Productos: registro, consulta y gestión | RC-01; DT-01; p. 2, §3.1-3.2 | Proponer una consulta visual con nombre, código de muestra y cantidad disponible. Primera candidata de construcción, elegida por el equipo. | Formulario, operaciones específicas, identificación real, campos obligatorios y validaciones. | P-01, P-03, P-06, P-07; MA-01. |
| Categorías | RC-02; DT-02; p. 2, §3.1-3.2 | Documentar que categoría es información explícita y diferente término de tipo de producto. No necesita una pantalla propia todavía. | Gestión, clasificación y relación con tipos/productos. | P-03, P-06; MA-01. |
| Existencias y disponibilidad | RC-03; DT-04; RE-01; p. 2, §3.1-3.2; p. 3, §4.1 | Mostrar el texto de cantidad disponible en la misma consulta, con valores de ejemplo. Sin semáforo ni cálculo. | Unidad, modelo numérico, stock inicial, actualización y validaciones. | P-08, P-15. |
| Ingresos, salidas y ajustes | RC-04; DT-05; p. 2, §3.1-3.2 | Mantener descripción de capacidad y preguntas. No generar formularios o algoritmos para aparentar completitud. | Tipos, fecha, información necesaria, efectos y correcciones. | P-08, P-09, P-11; MA-02, MA-03. |
| Registro y gestión de ventas | RC-05; DT-06; p. 2, §3.1-3.2 | Mantener trazabilidad de la necesidad; ninguna operación de venta en la primera propuesta. | Composición de una venta, acciones y relación con stock/movimientos. | P-03, P-10. |
| Revisión de movimientos y trazabilidad | RC-06; RE-01; p. 2, §3.1; p. 3, §4.1 | Documentar la información mínima aún desconocida y solicitar movimientos ficticios. | Presentación e información del historial, conservación y correcciones. | P-09, P-11; MA-02. |
| Niveles de stock y reposición | RC-07; DT-04; p. 1, §2.4; p. 2, §3.1-3.2 | Conservar necesidad y pregunta sobre umbral; no destacar valores de muestra como stock bajo. | Comparación, comportamiento y cualquier alerta. | P-12, P-17; MA-03. |
| Gestión de usuarios | RC-08; CO-01; p. 2, §3.1, §3.3 | Identificar perfiles mencionados sin convertirlos en permisos o login. | Acciones de gestión, autorización, acceso y roles. | P-02, P-03, P-04, P-20. |
| Indicadores, más/menos vendidos y tendencias | RC-09; RE-03, RE-04; p. 2, §3.1-3.2; p. 3, §4.1 | Conservar resultados explícitos y sus preguntas. | Fórmulas, períodos, presentación y criterios de evaluación. | P-03, P-13, P-14, P-18. |

## 3. Condiciones que no se incorporan automáticamente

| Elemento | Tratamiento actual | Dependencia |
|---|---|---|
| Consulta del público general | Inclusión y disponibilidad publicada pendientes. La propuesta visual no asigna usuario ni permisos. | P-05. |
| Talla, color, modelo, lote y vencimiento | Atributos condicionales, fuera de la primera propuesta. | P-07. |
| Internet para sincronizar | Contexto explícito de uso, sin elección de backend ni actualización automática. | P-15, P-20. |
| Persistencia local | Evaluable; usar datos de muestra en memoria no significa que el producto final pierda sus datos. | P-16. |
| Notificaciones y cámara QR/barras | Opciones condicionadas; no añadir botones, permisos ni librerías. | P-17. |
| Integraciones existentes / futuras | No exigidas para el MVP; no habilitar servicios como consecuencia de usar Lovable. | RS-05, FU-01. |

## 4. Primer incremento propuesto: consulta visual de productos

### Límite

Una pantalla de consulta es propuesta del equipo para materializar RC-01 y RC-03. No se afirma que sea la pantalla inicial definitiva. En la exploración solo se presentan nombre, identificador de muestra y cantidad disponible, todos conceptos del caso. No se seleccionan aún obligatoriedad, unicidad, unidades, precisión ni reglas de precio.

Los valores `DEMO-001`, `Producto de muestra A`, `12`, etc., son fixtures de diseño creados por el equipo; no sustituyen MA-01 ni representan datos reales de Kaiju. Presentarlos como texto permite explorar legibilidad sin establecer que las cantidades de negocio sean cadenas, enteros o decimales. La cantidad `0` no activa aviso, color especial ni impedimento de acciones.

No incluir búsqueda, filtros, menú de funciones, alta, edición, borrado, venta, escáner, alertas, estadísticas, selección de rol o conexión simulada. No poner acciones deshabilitadas de funciones aún no acordadas, porque también introducirían expectativas de alcance.

### Comprobaciones del equipo para esta propuesta

- Las tres alternativas visuales muestran los mismos datos, etiquetas e información; solo cambia apariencia.
- No hay controles de negocio ni campos ajenos a esta propuesta.
- El aviso de propuesta y datos ficticios está en el visor de revisión, fuera de las pantallas del producto.
- El contenido debe seguir legible a ancho móvil, sin depender del color para comunicar información.
- No hay reglas de stock, red, almacenamiento ni servicios externos habilitados por la exploración.

Estas son comprobaciones de calidad del prototipo, no criterios comerciales acordados. La selección estética del usuario tampoco equivale a validación de alcance del cliente.

## 5. Orden de construcción

| Paso | Entregable | Estado en esta etapa | Qué permite pasar al siguiente |
|---|---|---|---|
| 1 | Matriz de capacidades y responsabilidades | Documentado en esta entrega | Alcance de la primera propuesta separado de las reglas pendientes. |
| 2 | Tres propuestas de apariencia en Lovable | Generadas; revisión de código realizada, inspección visual directa y ajuste del visor pendientes. Ver `06-exploracion-visual.md` | Elegir una dirección visual y revisar el resultado. La elección no resuelve preguntas del cliente. |
| 3 | Configuración de aplicación Android propia | Pendiente; el ejemplo docente no se utiliza como base copiada | Completar nombre académico/grupo y decidir paquete/SDK/dependencias con verificación de compatibilidad. |
| 4 | Compilación y ejecución de una base mínima | Pendiente | Evidencia de compilación y emulador/dispositivo disponible. |
| 5 | Implementación Compose de la consulta elegida | Pendiente de dirección visual y base técnica | Comprobar el incremento sin reglas de negocio supuestas. |
| 6 | Capacidades funcionales por partes | Pendiente para cada regla dependiente | Respuestas registradas y validadas, material aplicable y criterios derivados de evidencia. |

No hay que esperar a responder las veinte preguntas para construir la base o revisar apariencia. Sí hay que responder las preguntas que cambian el comportamiento de la capacidad que se va a programar.
