# Separación de responsabilidades de construcción

Fecha: 30-09-2026. Propuesta técnica del equipo para una aplicación Android propia, en el contexto académico Kotlin + Compose + MVVM. No es arquitectura exigida por el cliente ni código ya creado.

## 1. Responsabilidades por parte

| Parte | Responsabilidad | No debe decidir | Aplicación al primer incremento |
|---|---|---|---|
| Actividad de entrada y composición | Conectar tema, pantalla y dependencias necesarias. | Reglas de inventario, ventas o autorización. | Una entrada mínima; no navegación multipantalla sin necesidad actual. |
| Tema y recursos | Colores, tipografía, espaciado y textos de presentación. | Identidad de marca confirmada o significado de stock bajo. | Traducir la opción visual elegida a recursos/tema Compose. |
| Pantalla de consulta | Mostrar estado y organizar contenido. | Cálculo de stock, permisos, validaciones o acceso a datos productivos. | Presentar los productos de muestra. |
| Componente de fila/tarjeta | Dibujar un producto recibido por parámetros; legibilidad y accesibilidad. | Obtener datos o establecer reglas según cantidad. | Componente reutilizable, sin botones de negocio. |
| ViewModel de consulta | Exponer estado de presentación de solo lectura y coordinar lo que la pantalla necesita. | Dibujar UI, inventar reglas o ser almacén definitivo de inventario. | Estado pequeño con lista de presentación; sin acciones de modificación del stock. |
| Modelo de presentación | Representar únicamente lo necesario para dibujar esta consulta. | Contrato definitivo del producto, entidades de base de datos, unidades o precisión. | Nombre, código mostrado y cantidad mostrada; la representación no fija tipos del negocio. |
| Fuente de muestra | Proporcionar fixtures ficticios identificables para revisar la consulta. | Representar catálogo autorizado por el cliente ni simular sincronización real. | Lista en memoria separada del componente; fácil de sustituir cuando se acuerde el origen. |
| Lógica de negocio / datos definitivos | Más adelante, aplicar reglas y obtener/conservar datos acordados. | Resolver P-xx por cuenta propia. | No crear motor de stock, modelos persistentes ni backend ahora. |

El ViewModel organiza presentación; las reglas reales, cuando existan, tendrán una responsabilidad explícita y comprobable. No se obliga a concentrar toda la lógica futura en un único ViewModel compartido, ni a crear una capa de casos de uso o framework de inyección antes de necesitarlo.

## 2. Flujo de la primera consulta

```text
Fixtures de muestra -> estado del ViewModel -> pantalla -> componente visual
                                           -> tema y recursos de presentación
```

No hay acciones que cambien inventario en este incremento. No se crean métodos `vender`, `ajustarStock` o `eliminarProducto` vacíos para aparentar una arquitectura completa.

Cuando una regla se acuerde, documentar sus entradas, resultado y condiciones, asignar la responsabilidad y comprobarla. Evitar duplicar la misma regla en UI, ViewModel y fuente de datos.

## 3. Organización mínima propuesta

La siguiente distribución es orientativa; no se han creado estas carpetas ni fijado el paquete Android:

```text
paquete de la aplicación propia/
  MainActivity.kt
  ui/
    theme/                 apariencia elegida
    products/              pantalla y componentes de consulta
  viewmodel/               estado de presentación
  model/                   modelo de presentación, no contrato definitivo
  preview/                 datos ficticios de demostración
```

Añadir navegación, repositorios, almacenamiento o módulos solo al aparecer una necesidad concreta. No copiar `com.agenda.app`, ni el catálogo de versiones del docente, ni reglas de eventos. El repositorio del equipo contendrá su propia app junto con `docs/`; el ejemplo seguirá excluido.

## 4. Revisión de cada cambio

Antes de aceptar un incremento, verificar cuatro aspectos:

1. **Evidencia:** qué ID del caso sustenta la capacidad y qué decisiones siguen abiertas.
2. **Responsabilidad:** qué parte presenta, coordina u obtiene información y por qué.
3. **Verificación:** comprobación pertinente al cambio; en la base Android, compilación/ejecución; en apariencia, revisión de pantalla; en reglas futuras, pruebas de casos acordados.
4. **Límite:** no nuevos campos, reglas o acciones introducidos por plantilla, generación automática o datos de muestra.

La generación de Lovable será revisada con estos límites antes de utilizarla como referencia. HTML/TypeScript y dependencias web no se incorporan como implementación Android.

## 5. Responsabilidades humanas pendientes

Los integrantes y número de grupo todavía no fueron aportados. No se inventa asignación nominal de tareas. El equipo deberá asignar preparación de preguntas, revisión de decisiones, implementación y comprobación según integrantes reales. Cliente valida negocio/alcance; docente valida condiciones académicas; equipo decide implementación sin cambiar negocio.
