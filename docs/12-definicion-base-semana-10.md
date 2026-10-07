# Definición de la base para semana 10

Fecha: 07-10-2026. Complementa la auditoría `11-auditoria-guia-10.md` con los diagramas enviados después. Petición del usuario: definir de forma ordenada y usar la plantilla del profesor, sin añadir funciones. Esta entrega actualiza documentación y una copia Excel; no implementa Android ni marca controles como cumplidos.

## 1. Fuentes y clasificación

- C-01: caso original. Conserva necesidades comerciales y preguntas todavía no validadas.
- A-10: Guía 10 pegada por el usuario. Define base académica, RF-01 a RF-10, RN-01 a RN-06 y controles B-01 a B-09.
- A-10-D1: `fuentes/2026-10-07/capas-proyecto.png`, copia de «capas-del-proyecto-y-c-mo-viajan-los-datos-diagrama.png».
- A-10-D2: `fuentes/2026-10-07/mapa-navegacion.png`, copia de «mapa-de-navegaci-n-de-la-app-kaiju-diagrama.png».
- N-04: plantilla original conservada. Copia actualizada: `entregables/kaiju-requerimientos-guia-10.xlsx`. El archivo anterior del 04-10 se conserva como historial.

Lo explícito de A-10 y sus diagramas es exigencia académica identificable. No simula validación comercial. Umbral y permisos son supuestos revisables declarados por el profesor; no se inventan respuestas para el resto. Las ideas de recursos y módulos siguen siendo opciones, no cuatro módulos obligatorios.

## 2. Qué resuelven los diagramas

### Navegación

A-10-D2 confirma:

1. Login permite ingresar a Catálogo. El dibujo dice «elige perfil», mientras §2/§5 precisa elegir un usuario de prueba. Mantener los tres usuarios ficticios con rol asociado; no añadir un segundo mecanismo de acceso por la etiqueta abreviada del dibujo.
2. Catálogo, Movimiento y Alertas son los tres destinos del menú inferior. No incluye Login ni Formulario en ese menú.
3. Tocar un producto del catálogo abre Detalle producto.
4. Botón + del catálogo abre Formulario producto, solo administrador.
5. Registrar desde detalle abre Movimiento.
6. Tocar una alerta abre Detalle producto.

No se deduce de las flechas un diseño de botones de volver, un cierre de sesión, credenciales o permisos adicionales. La edición se exige en RF-05 pero el dibujo solo muestra botón +: entrada a edición por precisar. Variación del menú por perfil y permisos parciales de RF-07 siguen abiertos.

### Capas

A-10-D1 confirma el recorrido:

```text
Interacción -> Pantalla -> ViewModel -> Repositorio -> listas en memoria
                   <- estado nuevo mediante StateFlow
```

La pantalla muestra y avisa; el ViewModel valida y revisa RN antes de guardar; el repositorio guarda y entrega. La persistencia local reemplaza después el almacenamiento, según clase. La flecha del diagrama no obliga a crear servicios remotos, capas de casos de uso, inyección de dependencias o base de datos ahora.

El ViewModel comparte estado actualizado, pero no debe convertirse en otro inventario independiente. Las pantallas no guardan directamente en listas ni mantienen copias que diverjan del repositorio. La actualización de stock y movimiento debe ser una operación consistente conforme A-10 §4. Las comprobaciones de presentación son distintas de las validaciones de negocio.

## 3. Qué contiene la nueva plantilla

Se utiliza el original como base, sin renombrar hojas, cambiar IDs fijos, RD, pesos o fórmula de rúbrica. Solo entradas previstas, títulos de módulos RF que son placeholders editables y alturas de filas rellenadas. No hay nuevas hojas o columnas.

| Hoja | Contenido actualizado | Lo que deliberadamente sigue pendiente |
|---|---|---|
| 1 | Contexto, propósito, alcance de la base, referencias y glosario con origen. | Integrantes/grupo y validación comercial. |
| 2 | Tres perfiles de prueba y tareas docentes; público conserva carácter condicional del caso. | Algunos permisos, frecuencia y experiencia de uso. |
| 3 | RF-01 a RF-10 con prioridad esencial para base académica, origen, pantallas y criterios derivados. | Detalles indicados G10; RF propios desde 11 sin asignar hasta elegir módulo. |
| 4 | RN-01 a RN-06 con ejemplos de cumplimiento/no cumplimiento; RD fijas intactas. | Tres ejes RNF anteriores siguen incompletos, no se inventan métricas. |
| 5 | Capacidades dentro de base y cinco grupos de pendientes con responsables/fechas por acordar. | No convertir automáticamente “no confirmado” en “fuera del MVP”. |
| 6 | Seis pantallas y conexiones de A-10-D2, campos/validaciones y RF. | Cambios por perfil y entrada a edición. |
| 7 | Producto, Movimiento, Usuario y enums con campos exigidos. | Tipos no fijados, política de código y almacenamiento definitivo. No inventar tablas. |
| 8 y 9 | Conservan estructura y datos fijos. | Matriz final, pruebas, cobertura y checklist de implementación sin marcar como realizados. |

Los criterios son especificación para comprobar en el futuro, no resultados de pruebas ejecutadas. Cuando un RF está incompleto, su celda lo dice; no se presenta como listo para implementar sin sus dependencias. Los ejemplos numéricos de RN ilustran una regla ya escrita, no añaden límites ni valores predeterminados.

El cambio de IDs respecto al 04-10 se explica en `11-auditoria-guia-10.md` §6. No se perdió la necesidad de indicadores, gestión de categorías o usuarios del caso por no estar toda en la base académica. Tampoco se les asignan nuevas funciones CRUD por costumbre.

## 4. Orden concreto de desarrollo posterior

| Incremento | Qué construir | Evidencia para terminar | Dependencias |
|---|---|---|---|
| 1 Base técnica | Proyecto Android propio y estructura §3, con configuración compatible verificada. | Abre y corre en emulador; estructura presente. B-01/B-02. | Equipo/grupo/nombre académico y entorno por revisar. |
| 2 Modelo y datos | Tres modelos, enums, repositorio en memoria y datos de prueba separados. | Diez productos, tres usuarios ficticios, ejemplos con stock bajo. B-03/B-04. | Tipos no definidos son decisiones técnicas a registrar; no inventar unidades de negocio. |
| 3 Navegación y estado | Seis rutas, menú Catálogo/Movimiento/Alertas; usuario seleccionado; ViewModels compartidos según alcance. | Recorrido base sin reiniciar inventario; pantallas pendientes con título donde §7 lo permite. B-05. | Variación por perfil por aclarar; no conceder acciones ausentes. |
| 4 Catálogo | Datos desde ViewModel, marca derivada de RN-06. | Muestra cuatro datos de RF-02 y distingue igualdad con mínimo. B-06. | Búsqueda final depende de semántica G10-06. |
| 5 Formulario | Estado y errores por campo; validación en ViewModel, iconos y alta visible en catálogo. | B-07 con los criterios explícitos de §5. | Stock inicial G10-02 antes de guardar definitivamente el nuevo producto; edición de código G10-03. |
| 6 Evidencia de revisión | README y decisiones §6, commits propios de todos, tablero repartido. | B-08/B-09 y demostración real de los otros siete controles. | Integrantes reales, tablero y decisiones aún faltan. |
| Posteriores | Movimientos e historial completos, alertas, persistencia, dos recursos y animaciones; módulo acotado. | Criterios de RF/RN y Parcial 2; no confundir lista en memoria con RF-10 cumplido. | Ajuste G10-01, guías de persistencia/recursos y detalles restantes. |

Crear estructura no acredita lógica; que una ruta abra un título no acredita su funcionalidad final. El objetivo próximo es B-01 a B-09 y sus dependencias, no integrar todas las ideas de §6 a la vez. La guía exige documentar decisiones §6 para B-08 aunque su integración venga después.

## 5. Preguntas que deben resolverse primero

Para el docente, manteniendo vínculo con la base que entregó:

1. «Para crear un producto en B-07, ¿cómo se establece stockActual si no aparece en los campos del formulario? ¿Se registra un movimiento inicial?» G10-02.
2. «El diagrama confirma los tres destinos. ¿Cómo se adapta el menú por rol y qué variantes de salida permite cada perfil, especialmente administrador e inventario?» G10-04, parcialmente resuelta.
3. «¿Qué lista ficticia de categorías usamos y qué datos del modelo además de §5 deben capturarse? ¿Puede el equipo proponer esa lista para la prueba?» G10-05.
4. «¿Búsqueda, edición y detalle deben estar completos en la próxima revisión, o basta el grado indicado en B-05/B-07 y las pantallas provisionales de §7?» G10-09.

Antes del incremento de movimientos, preguntar el cálculo de ajustes G10-01. Antes de editar código, aclarar G10-03. El resto del banco G10 y de preguntas del cliente se conserva. Una respuesta docente aclara evaluación; no se registra como respuesta comercial de Kaiju.

## 6. Límites y buenas prácticas

No añadir borrado de productos, CRUD de categorías/usuarios, contraseñas, nube, pagos, descuentos, compras o devoluciones. No convertir la opción de fotografía en un nuevo campo obligatorio ni el texto detalle en un sistema de lotes o vencimientos. El módulo propio requerido debe justificarse con el caso y definirse antes de construirlo.

Reutilizar una única regla para alertas; mantener códigos y relaciones consistentes; no reiniciar semillas sobre datos modificados; errores de conversión en campo, no cierres de app; estado compartido sin mezclar formulario y almacenamiento. Comprobar escenarios de reglas, navegación y reapertura cuando corresponda, sin pruebas que solo reproduzcan nombres de archivos.

Reparto humano orientativo: modelo/repositorio/datos; navegación/sesión/catálogo; formularios/errores/validación. Nombres por asignar, con revisión cruzada de contratos. Ninguna persona puede fabricar commits de los demás.

Sí hay base suficiente para comenzar los incrementos independientes cuando pasemos a desarrollo. La definición no autoriza resolver stock inicial o ajuste por suposición. Esta entrega deja la plantilla preparada y delimita lo implementable; no finge una app ya funcionando.
