# AplicacionKaiju · Proyecto semestral DSY1105

Estado al 30-09-2026: toma de requerimientos documental, previa a la reunión con el cliente. No se han desarrollado funcionalidades ni creado la aplicación Android propia.

El caso describe gestión y trazabilidad de inventario para Kaiju. Esta carpeta contiene el caso original, documentación del equipo y un ejemplo docente de Agenda de Eventos que sirve como referencia.

## Lectura en orden

1. [Auditoría del caso](docs/01-auditoria-caso.md): evidencia explícita, capacidades, datos, restricciones y pendientes.
2. [Preguntas para la reunión](docs/02-preguntas-cliente.md): preguntas trazables, sin respuestas supuestas.
3. [Reglas y contexto académico](docs/03-reglas-proyecto.md): trabajo con Git, referencias y pasos siguientes.

"Confirmado documentalmente" significa que aparece explícitamente en el caso; no significa que ya se acordó su detalle con el cliente ni que está listo para programarse.

## Datos de equipo pendientes

Nombre académico de la aplicación, número de grupo, integrantes, docente colaborador, enlaces de Drive y Trello: pendientes de aportar. No se inventan ni se crean servicios externos en esta etapa.

Repositorio de trabajo: [vicenteehueichapan-boop/AplicacionKaiju](https://github.com/vicenteehueichapan-boop/AplicacionKaiju). Su visibilidad actual es pública; la guía 07 solicita un repositorio privado. Esta diferencia queda pendiente de resolver, sin cambiar la visibilidad automáticamente.

Contexto académico previsto: Kotlin, Jetpack Compose y MVVM según las guías. No es una exigencia tecnológica del cliente. Versiones, paquete, SDK y dependencias de la aplicación propia quedan por decidir y verificar cuando corresponda crearla.

## Organización

- `docs/`: documentación propia versionada.
- PDF en la raíz: fuente del caso, conservada sin modificaciones.
- `dsy1105-ejemplo-agenda-eventos-main/`: ejemplo docente local, excluido del repositorio propio; no moverlo ni copiarlo como aplicación Kaiju.
- `tmp/`: archivos temporales de revisión, excluidos de Git.

La raíz es el repositorio de trabajo propio. El proyecto Android se creará en una etapa posterior; el ejemplo del profesor no es la aplicación del equipo.

Git local inicializado en `main`, con identidad local proporcionada por el usuario y `origin` conectado al repositorio de trabajo. Se conserva el commit inicial de GitHub junto con el historial local. La primera entrega versionada contiene la auditoría, las preguntas, las reglas y el caso fuente; no incluye una aplicación Android propia.
