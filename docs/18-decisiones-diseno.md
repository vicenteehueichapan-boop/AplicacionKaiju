# Decisiones de diseño · Kaiju

Fecha: 15-10-2026. Autor: Vicente Hueichapan. La entrevista con el cliente aún no se agenda; por indicación del docente se avanza con el material entregado. Estas decisiones completan lo que ninguna fuente del curso define, siguiendo la práctica de sistemas de inventario profesionales. Son **revisables**: si el cliente o el docente indican otra cosa, se cambia en un solo lugar (`InventarioViewModel`) y se registra el acta como origen.

## 1. Stock con que nace un producto nuevo (pendiente G10-02)

**Problema.** El formulario de la Guía 10 (sección 5) no pide stock, pero el producto necesita `stockActual` para guardarse y aparecer en el catálogo (B-07).

**Práctica profesional.**
- SAP: la ficha del producto no guarda cantidad; las existencias iniciales entran con un movimiento propio (tipo 561, "carga inicial de existencias"), que queda registrado y se puede anular (562).
- Odoo: un producto recién creado tiene 0 disponibles; las unidades entran con una recepción o un ajuste de inventario, ambos registrados en el historial.

**Decisión.** El producto se crea con **0 unidades**. Sus existencias entran con una **Entrada** (RF-06). Al guardar, la app ofrece "Registrar entrada", que abre Movimiento con el producto y el tipo ya elegidos.

**Por qué.** Cada unidad queda explicada por un movimiento con fecha y autor, que es la trazabilidad que pide el caso (sección 2.3) y la Guía 10 (RN-05 y sección 4: "los movimientos permiten explicar cómo se llegó a esa cantidad"). No agrega campos que la Guía 10 no pide.

**Dónde.** `InventarioViewModel.guardarProducto` y la acción del Snackbar en `AppNavHost`. Pruebas: `altaValidaApareceEnElCatalogoConCeroUnidades`, `altaOfreceRegistrarLaEntradaInicial`.

## 2. Qué hace un ajuste (pendiente G10-01)

**Problema.** RF-08 y RN-04 piden registrar un ajuste con motivo, pero no dicen si suma, resta o fija una cantidad.

**Práctica profesional.**
- Odoo: en un ajuste se ingresa la **cantidad contada**; el sistema calcula la diferencia (contado − disponible), pide **confirmación** antes de aplicarla y guarda el historial. Las diferencias se deben a robo, daño o error humano.
- Conteo cíclico: se cuenta una parte del inventario, se corrigen los registros según lo contado y se investiga la causa de la diferencia.

**Decisión.** El ajuste es un **conteo físico**:
1. Se ingresa la cantidad contada (entero, 0 o más; distinta del disponible) y el motivo (obligatorio, RN-04).
2. La app muestra: "el disponible pasa de 12 a 9 (diferencia −3)" y pide confirmar.
3. Al confirmar, el disponible pasa a ser lo contado y el movimiento guarda la **diferencia con signo** y el motivo.

**Por qué.** El caso dice que Kaiju "realiza conteos para determinar qué productos deben reponerse" (sección 2.2) y que su problema son las "diferencias entre el stock registrado y el stock disponible" (sección 2.3). La Guía 10 indica que los errores se corrigen con un ajuste (RN-05).

**Interpretación que se informa al docente.** La Guía 10 sección 5 pide "cantidad: entero mayor que cero". En el ajuste se acepta **0**, porque contar 0 unidades (producto agotado o perdido) es un caso real. En entradas y salidas se mantiene "mayor que cero".

**Dónde.** `InventarioViewModel.validarMovimiento`, `registrarMovimiento`, `confirmarAjuste`, `cancelarAjuste`; diálogo en `PantallaMovimiento`. Pruebas: siete casos en `MovimientosViewModelTest` (sin motivo, conteo negativo o igual, espera confirmación, confirmado, conteo en 0, cancelado, vendedor sin permiso).

## 3. Prácticas profesionales que ya seguía la app

| Práctica | En Kaiju |
|---|---|
| Historial que no se edita ni se borra | RN-05: no existe borrar ni editar movimientos |
| Separación de funciones | El vendedor solo registra ventas; no ajusta ni crea productos |
| Stock y movimiento se guardan juntos o ninguno | Transacción en `InventarioDao.registrarMovimiento` |
| Punto de reposición | RN-06: alerta cuando disponible ≤ mínimo |

## Fuentes

- SAP Help, *Initial entry of stock balances*: https://help.sap.com/saphelp_470/helpdata/en/4d/2b921e43ad11d189410000e829fbbd/content.htm
- SAP Learning, *Posting a Goods Receipt Without Reference*: https://learning.sap.com/courses/inventory-management-and-physical-inventory-in-sap-s-4hana/posting-a-goods-receipt-without-reference
- Odoo, *Inventory Adjustments*: https://hibou.io/docs/inventory-24/inventory-adjustments-990/ver/14-0-21
- Odoo 15, *Inventory adjustments*: https://www.cybrosys.com/odoo/odoo-books/community-edition-v15/inventory/inventory-adjustments/
- AccountingTools, *Cycle counting*: https://www.accountingtools.com/articles/2017/5/10/cycle-counting
