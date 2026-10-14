# Prototipo Kaiju · Guías 10 y 11

Fecha: 14-10-2026. Desarrollo autorizado por Vicente después de la [auditoría del 14-10](16-auditoria-claude-2026-10-14.md). Criterio: implementar lo que las fuentes dicen textualmente; donde hay ambigüedad, dejar el punto aislado y explicado, sin inventar reglas. No se inició emulador.

## 1. Qué se construyó y con qué fuente

| Parte | Fuente textual | Implementación |
|---|---|---|
| Búsqueda y filtro | A-10 §1 RF-03, §2 | `InventarioViewModel.catalogo`, `PantallaCatalogo` |
| Edición de producto | A-10 RF-05; diagrama A-10-D2 | Botón "Editar" en el detalle (solo administrador); el código queda bloqueado |
| Entrada y salidas | A-10 RF-06/07, RN-01, §5 | `registrarMovimiento`, `PantallaMovimiento` |
| Historial | A-10 RF-04, RN-05, §2 | `historial(codigo)`, lista en el detalle |
| Stock y movimiento juntos | A-10 §4 | `InventarioDao.registrarMovimiento` con `@Transaction` |
| Persistencia | A-10 RF-10, plantilla RD-03 | Room: `BaseDeDatosKaiju`, `InventarioRepositoryRoom` |
| Permisos por perfil | Tabla A-10 §1 | `tiposPermitidos(rol)`, `puedeGestionarProductos`, `puedeVerAlertas` |
| Recursos nativos | C-01 §3.3; A-11 §4, §6, §7, §9, §10 | `ui/recursos/` |
| Animaciones | A-10 §6, IE 2.2.2 | `IndicadorStock`, color del stock en el detalle, Snackbar |
| Identidad visual | A-10 §6 | `ui/theme/Theme.kt`, ícono adaptable |
| Correcciones de la auditoría | doc 16 H-06, H-07, H-11, H-12, H-13, H-15, H-16 | Vuelta al login sin sesión, íconos por campo, precio único, ícono de app, perfil visible, repositorio sin exponer, error que se limpia al corregir |

### Decisiones técnicas reversibles (no son reglas del cliente)

- **Búsqueda:** coincidencia parcial en nombre o código, sin distinguir mayúsculas; se combina con la categoría. La pregunta G10-06 sigue abierta para el cliente.
- **Código no editable al editar:** es la clave del historial (A-10 §4: `Movimiento.codigoProducto`). G10-03 sigue abierta.
- **Espacios al inicio y al final** se recortan al guardar, como hace el ejemplo docente (`trim()` en la Guía 09). Las mayúsculas no se normalizan: eso sí es una regla de negocio.
- **Guardado seguro:** si el stock cambió entre la validación y el guardado (por ejemplo, dos toques seguidos), no se guarda nada y se pide revisar. Así RN-01 no se rompe por un estado desactualizado.
- **Aviso del teléfono:** solo cuando un movimiento hace **entrar** un producto en alerta, para no repetirlo en cada salida.
- **Tras registrar** se conservan el producto y el tipo, para que registrar varios movimientos seguidos sea más rápido (señal de éxito "mayor rapidez", C-01 §4.1).

### Lo que sigue pendiente a propósito

| Pendiente | Por qué no se resolvió | Cómo se ve en la app |
|---|---|---|
| G10-02 stock inicial del alta | Ninguna fuente lo define | "Crear producto" valida y muestra que falta el dato; no guarda |
| G10-01 cálculo del ajuste | Ninguna fuente lo define; con cantidad > 0 (A-10 §5) no se sabe si suma, resta o fija | "Ajuste" valida el motivo y no cambia el stock |
| Módulo propio | Necesita métricas del cliente (P-13, P-14) | No implementado; A-10 §9 permite recortarlo |
| Trello y capturas | Trabajo individual sin Trello | Consultar al docente |

## 2. Verificación ejecutada (14-10-2026)

Copia temporal de `android/` sin cachés ni el archivo local de JDK 25; JDK 21.0.12; Gradle 9.5.0.

| Tarea | Resultado |
|---|---|
| `testDebugUnitTest` | **38 pruebas, 0 fallos**: InventarioViewModelTest 19, MovimientosViewModelTest 15, SesionViewModelTest 4 |
| `assembleDebug` | APK debug generado |
| `assembleDebugAndroidTest` | Compila `InventarioDaoTest` (3 pruebas de Room). **No ejecutada**: necesita teléfono |
| `lintDebug` | 0 errores. 10 avisos, todos de versiones más nuevas disponibles; no se actualizan sin necesidad |

Qué demuestran las pruebas unitarias: validaciones por campo y límites numéricos; código duplicado, también con espacios; edición que conserva código y stock; permisos por perfil; entrada, venta igual a lo disponible, salida mayor rechazada sin escribir; ajuste sin motivo; historial ordenado con empates de fecha; aviso solo al entrar en alerta; resultado del lector (código existente o inexistente); búsqueda y filtro combinados; sesión y cambio de usuario.

**No verificado por mí:** la interfaz en un dispositivo, el lector real (necesita Play services y cámara), el diálogo de permiso del sistema, ni la persistencia al cerrar el proceso. Para eso está la pauta de la sección 3.

Nota de entorno: en este PC, Gradle por línea de comandos necesita `JAVA_TOOL_OPTIONS=-Djdk.net.unixdomain.tmpdir=Z:\kaiju-no-unix-sockets` (falla la conexión local de Java). Además, el `local.properties` generado debe escapar los dos puntos (`sdk.dir=C\:/Users/...`), o lint marca error. Ninguno de los dos cambios se guarda en el proyecto.

## 3. Pauta de demostración en teléfono

Instalar desde Android Studio en un teléfono por USB. Para empezar desde cero: Ajustes → Apps → Kaiju → Borrar almacenamiento.

**Sesión y perfiles (RF-01)**
1. Tocar Ingresar sin elegir: aparece el error con ícono.
2. Entrar como Administrador de prueba: la barra superior muestra nombre y perfil; el menú tiene Catálogo, Movimiento y Alertas.
3. Botón de salida (arriba a la derecha) → elegir Personal de venta: no aparecen Alertas ni el botón +.

**Catálogo y detalle (RF-02, RF-03, RF-04, RF-09)**
4. Buscar "lápiz" y luego "demo-01"; filtrar por "Embalaje de prueba" y combinar con "caja".
5. DEMO-002, 004, 006 y 009 muestran "Stock bajo".
6. Abrir un producto: datos, precio en CLP e historial.

**Formulario de producto (RF-05)**
7. Como administrador, + → Crear producto vacío: errores por campo con ícono. Código DEMO-001: "ya existe". Precio 0 o 1.5, mínimo -1: rechazados.
8. Datos válidos: aparece el mensaje del stock inicial pendiente (G10-02). Es lo esperado.
9. Detalle de DEMO-001 → Editar: cambiar nombre y precio → Guardar. Snackbar de confirmación; el stock no cambia y el código no se puede editar.

**Movimientos (RF-06, RF-07, RF-08, RN-01, RN-04, RN-05)**
10. Como inventario: entrada de 5 a DEMO-001 → disponible 17; en el historial aparecen fecha, "Entrada · +5" y el usuario.
11. Salida por consumo mayor a lo disponible: error "supera lo disponible"; no cambia nada.
12. Ajuste sin motivo: error. Con motivo: mensaje de G10-01; el stock no cambia.
13. Como vendedor: solo aparece "Salida por venta". Vender DEMO-001 hasta dejarlo en 4: la marca "Stock bajo" aparece animada.

**Recursos nativos (Guía 11 §9)**
14. Como inventario, Alertas → "Activar avisos" → **Permitir**. Registrar una salida que deje un producto en alerta: llega la notificación. *Captura 1.*
15. Borrar almacenamiento, repetir y tocar **No permitir**: la lista sigue funcionando y aparecen "Activar avisos" y "Activar en Ajustes". *Captura 2.*
16. Tocar "Activar avisos" otra vez: primero aparece la explicación propia ("¿Activar avisos?"), con opción "Ahora no". Si continúas y vuelves a rechazar, Android ya no muestra su diálogo: queda solo el camino de Ajustes (rechazo reiterado, Guía 11 §3).
17. Activarlo desde Ajustes y volver: la tarjeta cambia a "Activados" sin reiniciar.
18. Movimiento → "Escanear código": apuntar a un código QR que contenga el texto `DEMO-005` (generado en el PC, sin personas en la imagen) → queda elegido DEMO-005. Cancelar no cambia nada; un código desconocido muestra el error en el campo producto.

**Persistencia (RF-10)**
19. Registrar un movimiento, cerrar la app desde recientes y abrirla: el stock y el historial siguen ahí. Vuelve a pedir usuario (la sesión no se guarda; G10-08).

Anotar fecha, teléfono, versión de Android y resultado de cada paso.

## 4. Qué debes saber explicar

- Por qué el ViewModel valida y el repositorio solo guarda; dónde está cada RN (`InventarioViewModel`).
- Por qué stock e historial se guardan en una transacción, y qué pasa si el stock cambió entretanto.
- Diferencia entre rotar el teléfono (el ViewModel sobrevive), cerrar el proceso (Room conserva los datos y la sesión vuelve al login) y borrar datos.
- Por qué el lector no pide permiso de cámara y las notificaciones sí (desde Android 13).
- Qué quedó pendiente y por qué no lo inventaste: stock inicial, cálculo del ajuste, módulo propio.
