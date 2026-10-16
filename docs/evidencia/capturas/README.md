# Capturas de evidencia · Kaiju

Recorrido completo de la app en el emulador Android 16 (API 36, AVD `DSY1105_API_36`), con la versión final del diseño (commit `eac9a7a` en adelante). Las tomó el 15 y 16-10-2026 Claude Code desde `adb`, por encargo de Vicente Hueichapan. No hay ediciones: son las imágenes tal como las entrega el emulador. Todos los datos son ficticios; DEMO-011 y DEMO-012 se crearon durante el recorrido.

El emulador usa la hora UTC: un movimiento que la app muestra a las 20:07 se registró a las 17:07 de Chile.

Vista general: [hoja-resumen.jpg](hoja-resumen.jpg). Contraste con la rúbrica: [docs/19-cumplimiento-entrega.md](../../19-cumplimiento-entrega.md).

| Captura | Qué muestra | Requisito |
|---|---|---|
| [01-login](01-login.png) | Elección del usuario de prueba, con una tarjeta por perfil | RF-01, IE 2.1.1 |
| [02-login-error](02-login-error.png) | "Ingresar" sin elegir: error con ícono | RF-01, IE 2.1.2 |
| [03-catalogo-admin](03-catalogo-admin.png) | Catálogo: resumen de productos y alertas, marca "Stock bajo" y borde rojo | RF-02, RF-09, B-06 |
| [04-busqueda-y-filtro](04-busqueda-y-filtro.png) | Búsqueda "caja" combinada con el filtro "Embalaje de prueba" | RF-03 |
| [05-formulario-errores-por-campo](05-formulario-errores-por-campo.png) | Formulario vacío: cada campo muestra su error con ícono | RF-05, IE 2.1.2 |
| [06-formulario-valores-invalidos](06-formulario-valores-invalidos.png) | Código repetido, nombre corto y precio 0 rechazados | RN-02, RN-03 |
| [07-producto-creado-con-0](07-producto-creado-con-0.png) | Producto creado con 0 unidades; el aviso ofrece "Registrar entrada" | RF-05, B-07, [decisión](../../18-decisiones-diseno.md) |
| [07b-producto-nuevo-en-catalogo](07b-producto-nuevo-en-catalogo.png) | El producto nuevo (DEMO-012) aparece en el catálogo | B-07 |
| [08-entrada-formulario](08-entrada-formulario.png) | Entrada por reposición del Lápiz (3 disponibles) | RF-06 |
| [09-entrada-registrada](09-entrada-registrada.png) | Confirmación: "Movimiento registrado. Disponible: 13" | RF-06, IE 2.2.2 |
| [09b-detalle-sale-de-alerta](09b-detalle-sale-de-alerta.png) | El Lápiz sale de alerta: desaparece la marca y el número cambia a verde | RN-06, IE 2.2.2 |
| [10-ajuste-validaciones](10-ajuste-validaciones.png) | Ajuste: conteo igual a lo disponible y motivo vacío, ambos rechazados | RF-08, RN-04 |
| [11-ajuste-confirmacion](11-ajuste-confirmacion.png) | Antes de guardar se confirma el cambio: de 10 a 8 (−2) | RF-08 |
| [12-detalle-historial](12-detalle-historial.png) | Historial del más reciente al más antiguo, con fecha, usuario y motivo | RF-04, RN-05 |
| [13-editar-codigo-bloqueado](13-editar-codigo-bloqueado.png) | Edición: el código queda bloqueado y se explica por qué | RF-05 |
| [14-edicion-guardada](14-edicion-guardada.png) | Precio cambiado; stock e historial intactos | RF-05 |
| [15-salida-supera-disponible](15-salida-supera-disponible.png) | Salida de 50 con 8 disponibles: rechazada sin guardar nada | RF-07, RN-01 |
| [16-lector-en-emulador](16-lector-en-emulador.png) | Sin Play Store el lector no abre: la app ofrece elegir el producto de la lista | Guía 11, recurso 2 |
| [17-alertas](17-alertas.png) | Pantalla Alertas con el resumen y la tarjeta de avisos | RF-09 |
| [18-permiso-dialogo-sistema](18-permiso-dialogo-sistema.png) | Diálogo de Android para `POST_NOTIFICATIONS`, pedido al tocar "Activar avisos" | Guía 11 sección 4 |
| [19-permiso-rechazado-alternativa](19-permiso-rechazado-alternativa.png) | Tras rechazar, la lista sigue y aparecen "Activar avisos" y "Activar en Ajustes" | Guía 11 sección 9 |
| [20-explicacion-antes-de-pedir](20-explicacion-antes-de-pedir.png) | Segundo intento: explicación propia con la opción "Ahora no" | Guía 11 sección 4 |
| [21-permiso-concedido](21-permiso-concedido.png) | Permiso concedido: la tarjeta pasa a "Activados" | Guía 11 sección 9 |
| [22-salida-deja-stock-bajo](22-salida-deja-stock-bajo.png) | Una venta deja la Mochila en su mínimo (3) | RF-07, RN-06 |
| [23-notificacion-stock-bajo](23-notificacion-stock-bajo.png) | Notificación real: "Stock bajo: Mochila de muestra" | Recurso nativo 1, RF-09 |
| [24-vendedor-catalogo](24-vendedor-catalogo.png) | Vendedor: sin pestaña Alertas ni botón "Nuevo producto" | Perfiles (Guía 10 sección 1) |
| [25-vendedor-solo-venta](25-vendedor-solo-venta.png) | Vendedor: solo puede registrar "Salida por venta" | RF-07 |
| [26-persistencia-tras-cerrar-app](26-persistencia-tras-cerrar-app.png) | Tras cerrar el proceso (`am force-stop`) y reabrir, siguen el precio, el stock y los 3 movimientos | RF-10, Guía de Room sección 9 |
| [27-rotacion-horizontal](27-rotacion-horizontal.png) | En horizontal se conservan la pantalla y la sesión, y el contenido se desplaza | IE 2.1.1 (diseño responsivo) |
