# Test Cases — Casos de Prueba Generados por IA

---

## HU-18: Consultar disponibilidad de un espacio

> Casos de prueba generados en lenguaje Gherkin (español), siguiendo el proceso ISTQB y considerando todos los flujos, reglas de negocio, límites, combinaciones, casos alternos y excepciones.

### Técnicas ISTQB aplicadas

- Partición de equivalencia
- Análisis de valores límite
- Pruebas de condiciones de negocio
- Pruebas de transición de estado
- Pruebas de combinaciones
- Pruebas de excepción

---

### CP-18-01: Consulta de disponibilidad en rango permitido

```gherkin
Given el usuario está autenticado en la plataforma
  And existe un espacio con ID válido
  And el horario de reserva solicitado es entre las 8:00 y 18:00 UTC
When el usuario consulta la disponibilidad del espacio para ese horario
Then el sistema muestra la disponibilidad correcta considerando solo reservas activas
```

### CP-18-02: Consulta de disponibilidad fuera de rango horario

```gherkin
Given el usuario está autenticado en la plataforma
  And existe un espacio con ID válido
  And el horario de reserva solicitado es antes de las 8:00 o después de las 18:00 UTC
When el usuario consulta la disponibilidad del espacio para ese horario
Then el sistema rechaza la consulta y muestra un mensaje de error por horario fuera del rango permitido
```

### CP-18-03: Consulta con ID de espacio inválido

```gherkin
Given el usuario está autenticado en la plataforma
  And el ID del espacio ingresado es inválido o inexistente
When el usuario consulta la disponibilidad del espacio para cualquier horario
Then el sistema muestra un mensaje de error indicando que el ID del espacio es inválido
```

### CP-18-04: Consulta de disponibilidad con solapamiento de reservas activas

```gherkin
Given el usuario está autenticado en la plataforma
  And existe un espacio con ID válido
  And hay una reserva activa en el horario solicitado
When el usuario consulta la disponibilidad del espacio para ese horario
Then el sistema indica que el espacio no está disponible debido al solapamiento con una reserva activa
```

### CP-18-05: Consulta de disponibilidad sin solapamiento de reservas activas

```gherkin
Given el usuario está autenticado en la plataforma
  And existe un espacio con ID válido
  And no hay reservas activas en el horario solicitado
When el usuario consulta la disponibilidad del espacio para ese horario
Then el sistema indica que el espacio está disponible
```

### CP-18-06: Consulta de disponibilidad con solapamiento de reservas no activas

```gherkin
Given el usuario está autenticado en la plataforma
  And existe un espacio con ID válido
  And hay una reserva no activa (cancelada o expirada) en el horario solicitado
When el usuario consulta la disponibilidad del espacio para ese horario
Then el sistema ignora el solapamiento y muestra el espacio como disponible
```

### CP-18-07: Consulta de disponibilidad con token de sesión inválido o expirado

```gherkin
Given el usuario tiene un token de sesión inválido o expirado
When el usuario intenta consultar la disponibilidad de un espacio
Then el sistema solicita al usuario que inicie sesión nuevamente
```

### CP-18-08: Consulta con ID de espacio en longitud máxima permitida

```gherkin
Given el usuario está autenticado en la plataforma
  And el ID del espacio ingresado cumple la longitud máxima permitida por el sistema
When el usuario consulta la disponibilidad del espacio para un horario permitido
Then el sistema procesa la consulta correctamente y muestra la disponibilidad
```

### CP-18-09: Consulta con ID de espacio que excede la longitud máxima permitida

```gherkin
Given el usuario está autenticado en la plataforma
  And el ID del espacio ingresado excede la longitud máxima permitida por el sistema
When el usuario consulta la disponibilidad del espacio para un horario permitido
Then el sistema rechaza la consulta y muestra un mensaje de error por ID no válido
```

### CP-18-10: Consulta de disponibilidad para varias combinaciones de espacio y horario

```gherkin
Given el usuario está autenticado en la plataforma
  And existen varios espacios disponibles con IDs válidos
  And el usuario realiza consultas para diferentes horarios dentro del rango permitido
When el usuario consulta la disponibilidad de cada espacio para cada horario
Then el sistema muestra la disponibilidad correcta para cada combinación considerando reservas activas
```

---

## HU-19: Crear reserva

> Casos de prueba en lenguaje Gherkin (español), siguiendo los lineamientos ISTQB. Técnicas aplicadas: partición de clases de equivalencia, análisis de valores límite, prueba de combinaciones, prueba de reglas de negocio y manejo de excepciones.

---

### CP-19-01: Reserva exitosa dentro del horario permitido

```gherkin
Given que soy un usuario autenticado
  And selecciono un espacio disponible
  And selecciono una fecha con hora de inicio "08:00" y hora de fin "09:00" dentro del rango permitido
When intento crear la reserva
Then la reserva se crea exitosamente
  And recibo una confirmación de la reserva
```

### CP-19-02: Reserva fuera del horario permitido (antes de las 8:00)

```gherkin
Given que soy un usuario autenticado
  And selecciono un espacio disponible
  And selecciono una fecha con hora de inicio "07:59" y hora de fin "09:00"
When intento crear la reserva
Then la reserva es rechazada
  And recibo un mensaje indicando que el horario está fuera del rango permitido
```

### CP-19-03: Reserva fuera del horario permitido (después de las 18:00)

```gherkin
Given que soy un usuario autenticado
  And selecciono un espacio disponible
  And selecciono una fecha con hora de inicio "17:00" y hora de fin "18:01"
When intento crear la reserva
Then la reserva es rechazada
  And recibo un mensaje indicando que el horario está fuera del rango permitido
```

### CP-19-04: Reserva en el límite inferior del horario permitido

```gherkin
Given que soy un usuario autenticado
  And selecciono un espacio disponible
  And selecciono una fecha con hora de inicio "08:00" y hora de fin "08:30"
When intento crear la reserva
Then la reserva se crea exitosamente
  And recibo una confirmación de la reserva
```

### CP-19-05: Reserva en el límite superior del horario permitido

```gherkin
Given que soy un usuario autenticado
  And selecciono un espacio disponible
  And selecciono una fecha con hora de inicio "17:30" y hora de fin "18:00"
When intento crear la reserva
Then la reserva se crea exitosamente
  And recibo una confirmación de la reserva
```

### CP-19-06: Reserva sin equipos adicionales

```gherkin
Given que soy un usuario autenticado
  And selecciono un espacio disponible
  And selecciono una fecha y horario válidos
  And no selecciono ningún equipo
When intento crear la reserva
Then la reserva se crea exitosamente
  And recibo una confirmación de la reserva
```

### CP-19-07: Reserva con equipos disponibles en la misma ciudad

```gherkin
Given que soy un usuario autenticado
  And selecciono un espacio disponible
  And selecciono una fecha y horario válidos
  And selecciono equipos disponibles en la misma ciudad
When intento crear la reserva
Then la reserva se crea exitosamente
  And los equipos seleccionados quedan asociados a la reserva
```

### CP-19-08: Reserva con equipos no disponibles

```gherkin
Given que soy un usuario autenticado
  And selecciono un espacio disponible
  And selecciono una fecha y horario válidos
  And selecciono equipos que no están disponibles
When intento crear la reserva
Then la reserva es rechazada
  And recibo un mensaje indicando que los equipos no están disponibles
```

### CP-19-09: Reserva con equipos en otra ciudad

```gherkin
Given que soy un usuario autenticado
  And selecciono un espacio disponible
  And selecciono una fecha y horario válidos
  And selecciono equipos que no están en la misma ciudad del espacio
When intento crear la reserva
Then la reserva es rechazada
  And recibo un mensaje indicando que los equipos deben pertenecer a la misma ciudad del espacio reservado
```

### CP-19-10: Reserva con campos opcionales vacíos

```gherkin
Given que soy un usuario autenticado
  And selecciono un espacio disponible
  And selecciono una fecha y horario válidos
  And dejo los campos opcionales vacíos (title, attendeesCount, notes, equipmentIds)
When intento crear la reserva
Then la reserva se crea exitosamente
  And recibo una confirmación de la reserva
```

---

## HU-20: Listar reservas

> Casos de prueba en lenguaje Gherkin (español). Técnicas ISTQB aplicadas: partición de equivalencia, análisis de valores límite, prueba de reglas de negocio, prueba de combinaciones (tablas de decisión), pruebas de errores y pruebas de roles/permisos.

---

### CP-20-01: Consulta de reservas propias sin filtros

```gherkin
Given que soy un usuario autenticado con rol colaborador
  And tengo reservas previas registradas en el sistema
When accedo a la sección de reservas sin aplicar ningún filtro
Then se muestra la lista completa de mis reservas activas
  And la información de cada reserva es clara y estructurada
```

### CP-20-02: Consulta de reservas generales (todas) sin filtros

```gherkin
Given que soy un usuario autenticado con rol colaborador
  And existen reservas de diferentes usuarios en el sistema
When accedo a la sección de reservas generales sin aplicar filtros
Then se muestra la lista de reservas accesibles según mis permisos
  And la información presentada es clara y estructurada
```

### CP-20-03: Combinación de filtros por fecha y espacio

```gherkin
Given que soy un usuario autenticado con rol colaborador
  And existen reservas en diferentes fechas y espacios
When aplico un filtro por fecha específica y por ID de espacio
Then solo se muestran las reservas que cumplen ambos criterios
```

### CP-20-04: Filtro por estado de reserva

```gherkin
Given que soy un usuario autenticado con rol colaborador
  And tengo reservas con diferentes estados (activa, cancelada, finalizada)
When filtro las reservas por estado "activa"
Then solo se muestran las reservas con estado "activa"
  And no se muestran reservas de otros estados
```

### CP-20-05: Ordenamiento ascendente y descendente por fecha de reserva

```gherkin
Given que soy un usuario autenticado con rol colaborador
  And tengo varias reservas en diferentes fechas
When ordeno las reservas por fecha de forma ascendente
Then la primera reserva en la lista tiene la fecha más antigua
When ordeno las reservas por fecha de forma descendente
Then la primera reserva en la lista tiene la fecha más reciente
```

### CP-20-06: Paginación de la lista de reservas

```gherkin
Given que soy un usuario autenticado con rol colaborador
  And tengo más reservas que el número de registros permitidos por página
When navego a las siguientes páginas de la lista de reservas
Then los resultados se actualizan correctamente para cada página
  And no se repiten reservas entre páginas
```

### CP-20-07: Validación de límites de paginación

```gherkin
Given que soy un usuario autenticado con rol colaborador
  And tengo suficientes reservas para cubrir múltiples páginas
When intento navegar a una página menor que 1 o mayor que el total de páginas
Then el sistema muestra un mensaje de error o me mantiene en la página válida más cercana
```

### CP-20-08: Consulta sin resultados

```gherkin
Given que soy un usuario autenticado con rol colaborador
  And aplico filtros que no coinciden con ninguna reserva existente
When visualizo la lista de reservas
Then el sistema muestra un mensaje indicando que no hay reservas para los criterios seleccionados
```

### CP-20-09: Manejo de sesión expirada o token inválido

```gherkin
Given que mi sesión ha expirado o el token es inválido
When intento consultar la lista de reservas
Then el sistema me redirige a la pantalla de inicio de sesión
  And se muestra un mensaje indicando que la sesión ha expirado
```

### CP-20-10: Validación de longitud máxima de filtros de entrada

```gherkin
Given que soy un usuario autenticado con rol colaborador
When ingreso un texto que excede la longitud máxima permitida en el campo de filtro de espacio
Then el sistema rechaza el filtro y muestra un mensaje de error
  And no ejecuta la consulta
```
| HU | ID | Caso de prueba | Ajuste realizado por el probador | ¿Por qué se ajustó? |
| --- | --- | --- | --- | --- |
| HU-18 | TC-01 | **Given** que el usuario está autenticado en la plataforma, **And** existe un espacio con ID válido, **And** el horario de reserva solicitado está antes de las 8:00 o después de las 18:00 UTC, **When** el usuario consulta la disponibilidad del espacio para ese horario, **Then** el sistema rechaza la consulta con código HTTP 400 y muestra un mensaje de error indicando que el horario está fuera del rango permitido (8:00–18:00 UTC). | Se separaron los dos escenarios límite (antes de las 8:00 y después de las 18:00) en casos independientes para mayor precisión en la validación. Además, se especificó el código de respuesta HTTP 400 y el texto del mensaje de error esperado. | La HU refinada explicitó la restricción del rango horario y el manejo de errores, lo cual obliga a verificar ambos extremos del límite y el código de respuesta exacto para asegurar la correcta implementación. |
| HU-18 | TC-02 | **Given** que soy un colaborador autenticado, **And** deseo consultar la disponibilidad de un espacio con ID válido, **When** consulto la disponibilidad para las 7:30 AM UTC, **Then** el sistema responde con código HTTP 400 y un mensaje indicando que el horario solicitado está fuera del rango permitido (mínimo 8:00 UTC). | Se agregó el código de respuesta HTTP 400 esperado y se especificó el ID del espacio como válido para aislar el error al horario y no al ID. Se añadió la verificación del mensaje específico que indica el límite inferior (8:00 UTC). | La HU original no definía el comportamiento para horarios fuera del rango; la versión refinada exige validación explícita del límite inferior, lo que requiere verificar tanto el código HTTP como el mensaje de error devuelto por la API. |
| HU-18 | TC-03 | **Given** que soy un colaborador autenticado, **And** deseo consultar la disponibilidad de un espacio con ID válido, **When** consulto la disponibilidad para las 6:30 PM (18:30 UTC), **Then** el sistema responde con código HTTP 400 y un mensaje indicando que el horario solicitado está fuera del rango permitido (máximo 18:00 UTC). | Se actualizó la hora a formato UTC (18:30 UTC) para coherencia con la especificación técnica del proyecto. Se incluyó el código HTTP 400 y la mención explícita del límite superior (18:00 UTC) en el resultado esperado. | La HU refinada unificó el uso de UTC y explicitó la restricción del horario máximo. Sin este ajuste, el caso de prueba podría interpretarse en hora local y no detectar errores de conversión de zona horaria en la API. |
| HU-19 | TC-04 | **Given** que soy un usuario autenticado, **And** selecciono un espacio disponible, **And** selecciono fecha y hora de inicio \"17:00 UTC\" y hora de fin \"18:01 UTC\", **When** intento crear la reserva, **Then** el sistema rechaza la reserva con código HTTP 400 **And** el mensaje de error indica que la hora de fin excede el límite permitido de las 18:00 UTC. | Se reemplazó el mensaje genérico "horario fuera del rango" por una validación específica sobre la hora de fin. Se añadió el código HTTP 400 y se precisó que el error corresponde exclusivamente al límite superior de la hora de fin. | La HU refinada especifica que tanto el inicio como el fin deben estar dentro del rango 8:00–18:00 UTC. Diferenciar el error de la hora de fin del de la hora de inicio permite confirmar que la API valida ambos campos de forma independiente. |
| HU-19 | TC-05 | **Given** que soy un usuario autenticado, **And** selecciono un espacio disponible en la ciudad "Bogotá", **And** selecciono una fecha y horario válidos, **And** selecciono equipos que pertenecen a la ciudad "Medellín", **When** intento crear la reserva, **Then** el sistema rechaza la reserva con código HTTP 422 **And** el mensaje de error indica que los equipos deben pertenecer a la misma ciudad del espacio reservado. | Se especificó el código HTTP 422 (entidad no procesable) para distinguir este error de negocio de un error de validación de formato (400). Se incluyeron nombres de ciudades concretos para hacer el caso reproducible y trazable. | La HU original no detallaba el comportamiento ante equipos de otra ciudad. La versión refinada establece explícitamente esta regla de negocio, requiriendo que el caso de prueba verifique tanto el rechazo como el mensaje descriptivo del error. |
| HU-19 | TC-06 | **Given** que soy un usuario autenticado, **And** selecciono un espacio disponible, **And** selecciono una fecha y horario válidos, **And** no envío los campos opcionales `title`, `attendeesCount`, `notes` y `equipmentIds`, **When** intento crear la reserva, **Then** la reserva se crea exitosamente con estado `confirmed` **And** los campos opcionales se almacenan como `null` en el sistema. | Se especificó el comportamiento esperado para cada campo opcional (almacenados como `null`) y se verificó el estado resultante de la reserva (`confirmed`). Anteriormente el caso solo validaba el éxito general sin comprobar los valores nulos. | La HU refinada aclara que los campos opcionales vacíos o ausentes deben manejarse apropiadamente. Definir el valor nulo esperado en la respuesta permite detectar regresiones donde el sistema asigne valores por defecto incorrectos o rechace la petición. |
| HU-20 | TC-07 | **Given** que soy un usuario autenticado con rol colaborador, **And** mi sesión ha expirado (token JWT inválido), **When** intento consultar la lista de reservas, **Then** el sistema responde con código HTTP 401 **And** muestra un mensaje indicando que la sesión ha expirado o el token es inválido **And** me redirige a la pantalla de inicio de sesión. | Se agregó el código de respuesta HTTP 401 esperado y se separó el comportamiento de la API (respuesta 401) del comportamiento esperado en el frontend (redirección al login). Se incluyó el token JWT inválido como precondición explícita. | La HU original no definía el comportamiento ante errores de sesión. La versión refinada los contempla explícitamente. Separar la validación de la API de la del frontend permite identificar con precisión dónde falla el sistema en caso de error. |
| HU-20 | TC-08 | **Given** que soy un usuario autenticado con rol colaborador, **And** existen reservas con diferentes estados (`pending`, `confirmed`, `in_progress`, `completed`, `cancelled`), **When** aplico el filtro por estado `confirmed` **And** también por `spaceId` válido, **Then** el sistema responde con código HTTP 200 **And** retorna únicamente las reservas con estado `confirmed` que corresponden al espacio indicado **And** no se muestran reservas de otros estados ni de otros espacios. | Se combinaron dos filtros simultáneos (estado + espacio) para validar la lógica de filtros combinados, que era una ambigüedad identificada en la refinación. Se precisó que la lista resultado no debe incluir registros de ningún otro estado o espacio. | La HU original no especificaba si los filtros eran combinables. La versión refinada establece que deben serlo; este caso verifica esa regla de negocio crítica y detecta posibles errores en la lógica de intersección de filtros en la API. |
| HU-20 | TC-09 | **Given** que soy un usuario autenticado con rol colaborador, **And** aplico filtros que no coinciden con ninguna reserva existente (por ejemplo, `spaceId` válido pero sin reservas registradas), **When** visualizo la lista de reservas, **Then** el sistema responde con código HTTP 200 **And** retorna una lista vacía `[]` con un mensaje informativo que indica que no se encontraron reservas para los criterios seleccionados. | Se especificó que la respuesta debe ser HTTP 200 (no 404) con un array vacío `[]`, diferenciando el "sin resultados" de un "recurso no encontrado". Se añadió la verificación del mensaje informativo para mejorar la experiencia de usuario. | La HU refinada indica que el sistema debe manejar correctamente las consultas sin resultados. Confirmar el código 200 con array vacío evita malinterpretar este escenario como un error, alineándose con las buenas prácticas REST y los criterios de aceptación refinados. |
