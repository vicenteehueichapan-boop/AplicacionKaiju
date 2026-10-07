# Primera base Android · Guía 10

Fecha: 07-10-2026. Desarrollo autorizado después de la auditoría. Fuentes: A-10 §1–8 y diagramas A-10-D1/D2. No hay entrevista validada. Este documento actualiza el desarrollo; las auditorías anteriores conservan su fecha histórica.

## 1. Respuestas y límites

- El usuario confirma que ya entregó toda la información docente disponible; stock inicial G10-02 continúa sin respuesta. No se guarda con stock supuesto.
- Único integrante: Vicente Hueichapan. Grupo no informado. Consultar al docente cómo acreditar el trabajo individual frente al requisito de tres integrantes.
- La revisión visual la hará Vicente para evitar sobrecargar su PC. No iniciar emuladores. Uno se inició antes de recibir esta instrucción y se cerró inmediatamente después; no se instaló ni probó esta app allí.

## 2. Estado de B-01 a B-09

| Control | Estado | Evidencia / pendiente |
|---|---|---|
| B-01 Corre en emulador | Pendiente manual | Compilación se verifica por separado; ejecución en dispositivo corresponde al usuario. |
| B-02 Estructura §3 | Creada | navigation, model, repository, viewmodel, ui/screen, ui/components y ui/theme en `android/app/src/main/java/cl/academico/kaiju/`. |
| B-03 Modelos/enums | Creados | Producto, Movimiento, Usuario, Rol y TipoMovimiento; campos de §4. |
| B-04 Datos ficticios | Creados | Diez productos, tres usuarios; stock menor, igual y mayor al mínimo. |
| B-05 Rutas/menú | Implementados; revisión visual pendiente | Seis destinos, menú Catálogo/Movimiento/Alertas; permisos de base académica. |
| B-06 Catálogo/ViewModel | Implementado; revisión visual pendiente | StateFlow desde repositorio; datos RF-02 y marca RN-06. |
| B-07 Formulario/alta | Parcial | Validación por campo implementada; guardado detenido por G10-02. |
| B-08 README/decisiones | Parcial | Integrante, descripción, arquitectura e identidad visual registrados. Recursos/animación propuestos; módulo propio y grupo pendientes. |
| B-09 GitHub/Trello | Parcial | Código versionado; Trello no aportado y modalidad individual por aclarar. No fabricar autores. |

No significa RF-01 a RF-10 completos. RF-03, edición RF-05, RF-06/07/08, RF-10 y §6 siguen pendientes. Movimiento provisional está permitido por §7 para esta base. No se añaden servidor, contraseñas, pagos ni operaciones adicionales.

## 3. Pauta de revisión en Android Studio

Abrir `android/`, sincronizar con JDK 21/SDK 36 y ejecutar `app` cuando el PC tenga recursos. El ejemplo docente es otro proyecto.

1. Ingresar sin usuario: error «Elige un usuario de prueba», sin salir de login.
2. Elegir Administrador de prueba: catálogo de diez productos y botón Crear producto.
3. Stock bajo en DEMO-002 (igual al mínimo), DEMO-004 (cero), DEMO-006 (menor) y DEMO-009 (igual). DEMO-001 sin marca.
4. Tocar producto: detalle con sus datos e historial vacío. No se inventaron movimientos de las semillas. Volver.
5. Crear producto y Validar producto vacío: errores de código, nombre, categoría, precio y stock mínimo.
6. Probar código DEMO-001, nombre de dos caracteres, precio 0/decimal y mínimo -1: rechazo en su campo, sin cierre de app.
7. Completar código NUEVO, nombre Muestra, categoría ficticia, precio 100 y mínimo 0: mensaje de datos válidos con stock inicial pendiente; al volver siguen diez productos. Es el pendiente de B-07, no un alta exitosa.
8. Alertas: tocar producto abre detalle. Movimiento: aviso de pantalla preparada, sin operaciones simuladas.
9. Para otro perfil, cerrar el proceso y ejecutar de nuevo. Vendedor sin Crear producto ni Alertas. Encargado con Alertas, sin Crear producto.
10. Rotar dispositivo: conservar usuario y catálogo durante cambio de configuración. Cerrar el proceso puede perder estado: no hay persistencia local aún.

Registrar commit, dispositivo, resultado y captura de errores. No marcar ejecución visual como aprobada antes de hacer esta pauta.

## 4. Qué debes poder explicar

- Diferencia entre exigencia académica y validación del cliente; motivo del stock inicial pendiente.
- Pantalla envía eventos; ViewModel valida; repositorio entrega/guarda; StateFlow actualiza presentación.
- RN-06 compara `stockActual <= stockMinimo`; igualdad también es alerta. Una función compartida del ViewModel evita reglas distintas entre pantallas.
- Campos numéricos se escriben como texto; `toIntOrNull` rechaza texto, decimales y desbordamientos sin provocar cierre. Errores viven en el estado del formulario.
- Usuario ficticio no equivale a autenticación real; memoria no equivale a persistencia; ruta provisional no equivale a funcionalidad terminada.

## 5. Pendientes en orden

1. Revisión manual por Vicente; corregir lo observado.
2. G10-02 al profesor: «Para B-07, ¿cómo se define stockActual al crear un producto si el formulario no incluye ese campo? ¿Debe existir un movimiento inicial?» No proponer un valor por defecto como si estuviera aprobado.
3. Aclarar modalidad individual, grupo, acceso al repositorio y Trello. Preparar tarjetas reales por pantalla/formulario; no declarar tablero existente sin evidencia.
4. Resolver edición de código, semántica de búsqueda y cálculo de ajustes antes de completar esas operaciones. Conservar banco G10 y preguntas al cliente.
5. Cerrar decisiones §6 justificadas con el caso; ejemplos opcionales no son acuerdos del cliente.
6. Después de revisión: movimientos, persistencia según clases, dos recursos y animaciones. Cambios pequeños, con pruebas de reglas, sin capas innecesarias.

## 6. Decisiones técnicas

- Proyecto propio en `android/`; nombres de capas y clases de §3/§4.
- `Int` para precio CLP, stock y cantidades de prueba. `Long` para representación de fecha: no fija zona horaria ni política comercial.
- Categorías y tipos ficticios no son clasificación comercial aprobada. Código duplicado se compara literalmente, sin normalización inventada.
- Repositorio retenido por InventarioViewModel, compartido con SesionViewModel desde navegación; no se recrean listas al cambiar de pantalla.
- Gradle limitado a dos trabajadores y 2 GB de memoria; sin inicio automático de emulador.
- Parámetro de stock inicial sin valor en producción. Un stock explícito de prueba unitaria es solo dato de ensayo, no regla aprobada.

## 7. Verificación técnica

Ejecución final, después de corregir el icono obsoleto y hacer desplazable el login: `assembleDebug testDebugUnitTest` terminó con **BUILD SUCCESSFUL**, 42 tareas (7 ejecutadas, 35 actualizadas), en 23 segundos. APK generado en `android/app/build/outputs/apk/debug/app-debug.apk`. Las 15 pruebas (12 de inventario, 3 de sesión) pasaron sin fallos ni omisiones. La ejecución anterior también había generado APK y aprobado las mismas pruebas.

`lintDebug` no concluyó: tras más de diez minutos, el diagnóstico de hilos mostró al analizador recorriendo comentarios en `BidirectionalTextDetector` / `KotlinUFile.getAllCommentsInFile`. Se detuvo el proceso para liberar recursos. La alternativa `android.lint.useK2Uast=false` fue rechazada porque AGP 9.3 eliminó esa opción; no se añadió al proyecto ni se desactivaron reglas. No declarar lint aprobado. Queda pendiente diagnosticar o resolver la incompatibilidad del analizador antes de la revisión final del Parcial 2.

La terminal de esta sesión necesitó una opción temporal de Java para usar su alternativa TCP ante un fallo de sockets locales de Windows. Solo se aplicó al proceso de compilación; no se guardó en Gradle ni altera el proyecto portátil. Android Studio no requiere copiar esa opción salvo que reproduzca el mismo problema.

No se ejecutó la app en dispositivo, por indicación del usuario. Informes de pruebas en `android/app/build/reports/tests/testDebugUnitTest/`, excluidos de Git. Ni compilación ni pruebas acreditan persistencia o revisión visual.

## 8. Tarjetas preparadas para Trello

Responsable informado para todas: Vicente Hueichapan. Esta lista está preparada para trasladarla al tablero cuando se aporte su enlace/acceso. No equivale a un tablero creado. Fecha exacta de revisión no informada; no se inventan vencimientos.

| Tarjeta | Fuente | Estado para trasladar / criterio |
|---|---|---|
| Pantalla Login | RF-01, A-10 §2 | Implementada; revisar manualmente selección e ingreso. |
| Pantalla Catálogo | RF-02/03/09 | Catálogo y marca implementados; búsqueda/filtro pendientes de definición. |
| Pantalla Detalle | RF-04 | Datos e historial vacío implementados; revisar recorrido, historial real depende de movimientos. |
| Pantalla Formulario Producto | RF-05 | UI implementada; edición pendiente y alta bloqueada por G10-02. |
| Pantalla Movimiento | RF-06/07/08 | Provisional permitida en §7; lógica pendiente, ajuste depende de G10-01. |
| Pantalla Alertas | RF-09, RN-06 | Lista y acceso por perfil implementados; revisar abrir detalle. |
| Formulario Login: validación | A-10 §5 | Lógica probada; revisar error visual sin selección. |
| Formulario Producto: validación y alta | A-10 §5, B-07 | Lógica probada; revisar errores visuales; alta pendiente de respuesta identificable sobre stock inicial. |
| Formulario Movimiento: validación | A-10 §5 | Estado creado; implementación posterior a revisión de base y aclaración de ajustes. |

Agregar después tarjetas de persistencia, recursos, animaciones y módulo cuando se definan. Conservar evidencia real de cambios; no mover tarjetas a terminado solo por tener un archivo creado.
