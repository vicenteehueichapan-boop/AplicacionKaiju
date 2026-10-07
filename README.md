# Kaiju · DSY1105 · Base Android

Integrante: **Vicente Hueichapan**. Trabajo individual; número de grupo y aceptación docente de esta modalidad pendientes. Nombre provisional: Kaiju.

App académica de inventario en Kotlin y Jetpack Compose, organizada según Guía 10. Utiliza diez productos y tres usuarios ficticios, con listas en memoria. Las exigencias académicas se distinguen de los acuerdos comerciales; no hay respuestas de entrevista registradas.

## Abrir y revisar

1. En Android Studio, abrir **`android/`**, que contiene `settings.gradle.kts`.
2. Completar la sincronización con JDK 21 y Android SDK 36. La ubicación del SDK se configura en el IDE o `local.properties`, excluido de Git.
3. Ejecutar `app` cuando decidas iniciar el dispositivo. **No iniciar emuladores automáticamente.**
4. Seguir la [pauta de revisión](docs/13-entrega-base-android.md).

Desde `android/`, para compilar y revisar sin emulador:

```powershell
.\gradlew.bat --no-daemon assembleDebug testDebugUnitTest lintDebug
```

APK generado: `android/app/build/outputs/apk/debug/app-debug.apk`. Los archivos generados no se versionan. Compilación y pruebas de lógica no sustituyen la revisión visual.

Verificación del 07-10-2026: compilación correcta y 15 pruebas unitarias aprobadas. Lint no concluyó por atasco del analizador; limitación documentada en la pauta. Ejecución visual pendiente a cargo de Vicente.

## Contenido y límites

- Selección validada de usuario ficticio con perfil asociado.
- Seis rutas: login, catálogo, detalle, formulario de producto, movimiento y alertas.
- Menú inferior Catálogo/Movimiento/Alertas, con acceso a alertas y creación según perfiles académicos.
- Catálogo desde ViewModel; stock bajo identificado por texto y color según RN-06; detalle y alertas derivados del mismo estado.
- Formulario con errores por campo en ViewModel, iconos, conversión segura de enteros y código duplicado literal.
- Movimiento provisional, permitido por Guía 10 §7 para esta primera revisión.

**El alta no guarda todavía:** falta definir stock inicial (G10-02). El botón valida y explica el pendiente; no supone cero ni inventa movimientos iniciales. B-07 está parcial. Búsqueda, edición, registro de movimientos, persistencia, recursos nativos, animaciones y módulo propio quedan pendientes.

## Responsabilidades

```text
Pantalla: muestra estado y envía eventos
  -> ViewModel: valida campos, permisos y reglas
     -> Repositorio: guarda y entrega datos en memoria
  <- StateFlow: estado observable
```

MainActivity llama a AppNavHost. Navegación comparte dos ViewModels y un repositorio; pantallas reciben datos y acciones. Modelos y semillas están separados. Los ViewModels conservan estado durante cambios de configuración; cerrar el proceso puede perderlo porque RF-10 aún no está implementado.

## Decisiones de Guía 10 §6

| Tema | Estado y justificación |
|---|---|
| Identidad visual | Implementada: nombre provisional Kaiju, paleta clara verde petróleo, tipografía Material 3 y alertas también por texto. Busca legibilidad del inventario; revisión visual pendiente. |
| Dos recursos nativos | Propuestas: notificaciones de stock bajo (RF-09) y vibración de confirmación de registro (RF-06/07/08). Definir disparo, permisos y comportamiento antes de integrar. No son acuerdos del cliente ni están implementadas. |
| Animaciones | Propuesta: transición de la marca de stock bajo para comunicar un cambio de RN-06. Pendiente de definir e implementar. |
| Módulo propio | Pendiente de seleccionar y justificar con el caso. No agregar funciones por llenar la plantilla. Los RF propios seguirán desde RF-11. |

B-08 sigue parcial hasta cerrar estas decisiones. Los ejemplos opcionales del profesor no se convierten en requisitos comerciales.

## Fuentes y seguimiento

- [Entrega Android: evidencia, demostración y pendientes](docs/13-entrega-base-android.md).
- [Auditoría Guía 10](docs/11-auditoria-guia-10.md) y [definición de base](docs/12-definicion-base-semana-10.md).
- [Plantilla del profesor actualizada](docs/entregables/kaiju-requerimientos-guia-10.xlsx): mantiene estructura, RD y rúbrica; no acredita implementación completa ni validación del cliente.
- [Auditoría del caso](docs/01-auditoria-caso.md), [preguntas](docs/02-preguntas-cliente.md), [reglas](docs/03-reglas-proyecto.md), [capacidades](docs/04-capacidades-y-construccion.md) y [responsabilidades](docs/05-responsabilidades-construccion.md).
- [Auditoría complementaria](docs/07-auditoria-material-nuevo.md), [preparación entrevista](docs/08-preparacion-entrevista.md), [borrador SRS](docs/09-borrador-previo-srs.md) y [kit editable](docs/10-kit-reunion-kaiju.md).
- [Kit imprimible](output/pdf/kit-reunion-kaiju.pdf) y [Excel anterior](docs/entregables/kaiju-requerimientos-previos-entrevista.xlsx): versiones históricas anteriores a Guía 10.
- [Exploración visual anterior](docs/06-exploracion-visual.md): propuestas; no es la app Android.

Fuentes preservadas en `docs/fuentes/`, PDF original en raíz; ejemplo docente local excluido de Git.

Repositorio autorizado: [AplicacionKaiju](https://github.com/vicenteehueichapan-boop/AplicacionKaiju). Visibilidad pública actual distinta de la privada solicitada en Guía 07: pendiente de aclarar. Trello y acceso del profesor pendientes; no se simulan participantes ni commits ajenos.

## Configuración técnica

Paquete provisional `cl.academico.kaiju`; SDK mínimo 26, objetivo 36; bytecode Java 17. AGP 9.3.3, Gradle 9.5.0, Kotlin integrado en AGP y plugin Compose 2.3.20; BOM Compose 2026.02.01. Compatibilidad revisada en [AGP oficial](https://developer.android.com/build/releases/agp-9-3-0-release-notes) y [Kotlin integrado](https://developer.android.com/build/migrate-to-built-in-kotlin). Son decisiones técnicas; no se copió la configuración del ejemplo antiguo.

No incluir credenciales, datos productivos ni firmas. Mantener cambios pequeños, trazables y verificables; no prometer ausencia absoluta de deuda técnica.
