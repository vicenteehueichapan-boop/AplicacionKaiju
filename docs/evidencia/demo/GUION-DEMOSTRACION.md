# Guion de demostración · Kaiju (7 minutos)

Pauta completa y fundamentos en [docs/17](../../17-prototipo-guia-11.md). Datos ficticios.

## Antes de mostrar

1. Cerrar Chrome, WhatsApp, Discord y Mem Reduct (clic derecho en su ícono → Salir).
2. En el teléfono o emulador: Ajustes → Apps → Kaiju → Almacenamiento → **Borrar datos**. Vuelven los 10 productos y el diálogo de permiso.
3. Abrir `codigos-demo.html` (esta carpeta) en el PC para el lector de códigos.
4. Dejar abiertos el repositorio en GitHub, el README y el Word de evidencia.
5. Si usas el emulador: abrirlo primero desde Device Manager, esperar la pantalla de inicio y recién entonces ▶ Run (no el bicho). En el emulador el lector puede no estar disponible; muestra la alternativa de elegir de la lista.

## Recorrido

| # | Qué hacer | Qué se demuestra |
|---|---|---|
| 1 | Ingresar sin elegir usuario → error. Entrar como **Administrador** | RF-01, validación de login |
| 2 | Catálogo: 10 productos, marca "Stock bajo"; buscar "lápiz"; filtrar "Embalaje de prueba" | B-04, B-06, RF-02, RF-03 |
| 3 | + → Crear producto vacío → errores con ícono. Código DEMO-001 → "ya existe" | B-07, RN-02, RN-03 |
| 4 | Detalle de DEMO-001 → Editar → cambiar nombre → Guardar | RF-05 |
| 5 | Salir (arriba) → **Encargado de inventario** → Movimiento: entrada de 5 a DEMO-001 → ver historial en el detalle | RF-06, RF-04, RN-05 |
| 6 | Salida por consumo mayor a lo disponible → error | RN-01 |
| 7 | Alertas → **Activar avisos** → Permitir. Vender o consumir hasta dejar un producto en su mínimo → notificación y marca animada | Guía 11, RF-09, animaciones |
| 8 | Movimiento → **Escanear código** → QR DEMO-005 | Guía 11, segundo recurso |
| 9 | Cerrar la app desde recientes y abrirla → los datos siguen | RF-10 (Room) |
| 10 | GitHub: README, Excel y Word de evidencia | B-08, B-09 |

## Si preguntan

- **¿Por qué crear producto no guarda?** La Guía 10 no dice con qué stock nace un producto nuevo; está preguntado al docente (G10-02). Se cambia en un solo punto del ViewModel.
- **¿Y el ajuste?** No está definido si suma, resta o fija lo contado (G10-01). Valida el motivo y no cambia el stock.
- **¿Módulo propio?** Más/menos vendidos o tendencias necesitan que el cliente defina cómo medir. La Guía 10 sección 9 permite recortarlo antes que la base.
- **¿Por qué no hay Trello?** Trabajo individual; todo el registro está en GitHub.
- **¿Dónde están las reglas?** En `InventarioViewModel`: la pantalla solo muestra el estado, el repositorio solo guarda.
