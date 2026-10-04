# AplicacionKaiju · Proyecto semestral DSY1105

Estado al 04-10-2026: auditoría del material nuevo y preparación de entrevista/SRS preliminar. No se aportaron convocatoria, acta ni reglas de stock del cliente. Hay propuestas visuales previas en Lovable; todavía no se creó la aplicación Android propia ni se implementaron reglas de negocio.

El caso describe gestión y trazabilidad de inventario para Kaiju. Esta carpeta contiene el caso original, documentación del equipo y un ejemplo docente de Agenda de Eventos que sirve como referencia.

## Lectura en orden

1. [Auditoría del caso](docs/01-auditoria-caso.md): evidencia explícita, capacidades, datos, restricciones y pendientes.
2. [Preguntas para la reunión](docs/02-preguntas-cliente.md): preguntas trazables, sin respuestas supuestas.
3. [Reglas y contexto académico](docs/03-reglas-proyecto.md): trabajo con Git, referencias y pasos siguientes.
4. [Capacidades y construcción](docs/04-capacidades-y-construccion.md): partes que podemos avanzar y partes que dependen de respuestas.
5. [Responsabilidades de construcción](docs/05-responsabilidades-construccion.md): UI, estado, muestra y reglas futuras.
6. [Exploración visual en Lovable](docs/06-exploracion-visual.md): tres propuestas de apariencia y límites de su uso.

## Material nuevo y trabajo actual

7. [Auditoría del 04-10-2026](docs/07-auditoria-material-nuevo.md): qué cambió, qué exige el curso y cómo usar las diez pestañas de la plantilla.
8. [Preparación de entrevista](docs/08-preparacion-entrevista.md): doce preguntas principales con fuente, roles por asignar y estructura de acta sin respuestas.
9. [Borrador previo SRS](docs/09-borrador-previo-srs.md): contexto, glosario provisional, roles y candidatos RF/RNF con pendientes explícitos.
10. [Kit editable para la reunión](docs/10-kit-reunion-kaiju.md): preparación, preguntas con fuente y motivo, registro de respuestas, dudas docentes y acta vacía.

## Documentos para llevar a la reunión

- [Kit imprimible de entrevista](output/pdf/kit-reunion-kaiju.pdf): diez páginas de apoyo; pauta principal en páginas 4-7, aclaraciones docentes en página 8 y acta en página 9.
- [Excel previo a la entrevista](docs/entregables/kaiju-requerimientos-previos-entrevista.xlsx): copia de trabajo con hojas 1 y 2, quince RF candidatos, tres ejes RNF sin completar y pendientes de hoja 5. RN, alcance acordado, pantallas, modelo y trazabilidad final quedan para completar con evidencia. No se marcaron verificaciones técnicas como realizadas.

Completar integrantes/grupo, convocatoria y responsables de conducción, registro y tiempo; ensayar presentación y cierre. La documentación no reemplaza la entrevista ni acredita aprobación del cliente. Se preservan valores fijos, formato de celdas, listas de validación, RD y fórmula/pesos de rúbrica de la plantilla; solo se ajustaron alturas de filas rellenadas para que el texto sea legible.

Las fuentes nuevas están preservadas en `docs/fuentes/2026-10-04/`. La plantilla Excel es una copia exacta sin completar ni modificar. Las restricciones fijas del curso ahora explicitan persistencia local y al menos dos recursos nativos; eso no aporta reglas de negocio ni respuestas del cliente.

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
