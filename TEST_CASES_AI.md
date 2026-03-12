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
Dado que el usuario está autenticado en la plataforma
  Y existe un espacio con ID válido
  Y el horario de reserva solicitado es entre las 8:00 y 18:00 UTC
Cuando el usuario consulta la disponibilidad del espacio para ese horario
Entonces el sistema muestra la disponibilidad correcta considerando solo reservas activas
```

### CP-18-02: Consulta de disponibilidad fuera de rango horario

```gherkin
Dado que el usuario está autenticado en la plataforma
  Y existe un espacio con ID válido
  Y el horario de reserva solicitado es antes de las 8:00 o después de las 18:00 UTC
Cuando el usuario consulta la disponibilidad del espacio para ese horario
Entonces el sistema rechaza la consulta y muestra un mensaje de error por horario fuera del rango permitido
```

### CP-18-03: Consulta con ID de espacio inválido

```gherkin
Dado que el usuario está autenticado en la plataforma
  Y el ID del espacio ingresado es inválido o inexistente
Cuando el usuario consulta la disponibilidad del espacio para cualquier horario
Entonces el sistema muestra un mensaje de error indicando que el ID del espacio es inválido
```

### CP-18-04: Consulta de disponibilidad con solapamiento de reservas activas

```gherkin
Dado que el usuario está autenticado en la plataforma
  Y existe un espacio con ID válido
  Y hay una reserva activa en el horario solicitado
Cuando el usuario consulta la disponibilidad del espacio para ese horario
Entonces el sistema indica que el espacio no está disponible debido al solapamiento con una reserva activa
```

### CP-18-05: Consulta de disponibilidad sin solapamiento de reservas activas

```gherkin
Dado que el usuario está autenticado en la plataforma
  Y existe un espacio con ID válido
  Y no hay reservas activas en el horario solicitado
Cuando el usuario consulta la disponibilidad del espacio para ese horario
Entonces el sistema indica que el espacio está disponible
```

### CP-18-06: Consulta de disponibilidad con solapamiento de reservas no activas

```gherkin
Dado que el usuario está autenticado en la plataforma
  Y existe un espacio con ID válido
  Y hay una reserva no activa (cancelada o expirada) en el horario solicitado
Cuando el usuario consulta la disponibilidad del espacio para ese horario
Entonces el sistema ignora el solapamiento y muestra el espacio como disponible
```

### CP-18-07: Consulta de disponibilidad con token de sesión inválido o expirado

```gherkin
Dado que el usuario tiene un token de sesión inválido o expirado
Cuando el usuario intenta consultar la disponibilidad de un espacio
Entonces el sistema solicita al usuario que inicie sesión nuevamente
```

### CP-18-08: Consulta con ID de espacio en longitud máxima permitida

```gherkin
Dado que el usuario está autenticado en la plataforma
  Y el ID del espacio ingresado cumple la longitud máxima permitida por el sistema
Cuando el usuario consulta la disponibilidad del espacio para un horario permitido
Entonces el sistema procesa la consulta correctamente y muestra la disponibilidad
```

### CP-18-09: Consulta con ID de espacio que excede la longitud máxima permitida

```gherkin
Dado que el usuario está autenticado en la plataforma
  Y el ID del espacio ingresado excede la longitud máxima permitida por el sistema
Cuando el usuario consulta la disponibilidad del espacio para un horario permitido
Entonces el sistema rechaza la consulta y muestra un mensaje de error por ID no válido
```

### CP-18-10: Consulta de disponibilidad para varias combinaciones de espacio y horario

```gherkin
Dado que el usuario está autenticado en la plataforma
  Y existen varios espacios disponibles con IDs válidos
  Y el usuario realiza consultas para diferentes horarios dentro del rango permitido
Cuando el usuario consulta la disponibilidad de cada espacio para cada horario
Entonces el sistema muestra la disponibilidad correcta para cada combinación considerando reservas activas
```

---

## HU-19: Crear reserva

> Casos de prueba en lenguaje Gherkin (español), siguiendo los lineamientos ISTQB. Técnicas aplicadas: partición de clases de equivalencia, análisis de valores límite, prueba de combinaciones, prueba de reglas de negocio y manejo de excepciones.

---

### CP-19-01: Reserva exitosa dentro del horario permitido

```gherkin
Dado que soy un usuario autenticado
  Y selecciono un espacio disponible
  Y selecciono una fecha con hora de inicio "08:00" y hora de fin "09:00" dentro del rango permitido
Cuando intento crear la reserva
Entonces la reserva se crea exitosamente
  Y recibo una confirmación de la reserva
```

### CP-19-02: Reserva fuera del horario permitido (antes de las 8:00)

```gherkin
Dado que soy un usuario autenticado
  Y selecciono un espacio disponible
  Y selecciono una fecha con hora de inicio "07:59" y hora de fin "09:00"
Cuando intento crear la reserva
Entonces la reserva es rechazada
  Y recibo un mensaje indicando que el horario está fuera del rango permitido
```

### CP-19-03: Reserva fuera del horario permitido (después de las 18:00)

```gherkin
Dado que soy un usuario autenticado
  Y selecciono un espacio disponible
  Y selecciono una fecha con hora de inicio "17:00" y hora de fin "18:01"
Cuando intento crear la reserva
Entonces la reserva es rechazada
  Y recibo un mensaje indicando que el horario está fuera del rango permitido
```

### CP-19-04: Reserva en el límite inferior del horario permitido

```gherkin
Dado que soy un usuario autenticado
  Y selecciono un espacio disponible
  Y selecciono una fecha con hora de inicio "08:00" y hora de fin "08:30"
Cuando intento crear la reserva
Entonces la reserva se crea exitosamente
  Y recibo una confirmación de la reserva
```

### CP-19-05: Reserva en el límite superior del horario permitido

```gherkin
Dado que soy un usuario autenticado
  Y selecciono un espacio disponible
  Y selecciono una fecha con hora de inicio "17:30" y hora de fin "18:00"
Cuando intento crear la reserva
Entonces la reserva se crea exitosamente
  Y recibo una confirmación de la reserva
```

### CP-19-06: Reserva sin equipos adicionales

```gherkin
Dado que soy un usuario autenticado
  Y selecciono un espacio disponible
  Y selecciono una fecha y horario válidos
  Y no selecciono ningún equipo
Cuando intento crear la reserva
Entonces la reserva se crea exitosamente
  Y recibo una confirmación de la reserva
```

### CP-19-07: Reserva con equipos disponibles en la misma ciudad

```gherkin
Dado que soy un usuario autenticado
  Y selecciono un espacio disponible
  Y selecciono una fecha y horario válidos
  Y selecciono equipos disponibles en la misma ciudad
Cuando intento crear la reserva
Entonces la reserva se crea exitosamente
  Y los equipos seleccionados quedan asociados a la reserva
```

### CP-19-08: Reserva con equipos no disponibles

```gherkin
Dado que soy un usuario autenticado
  Y selecciono un espacio disponible
  Y selecciono una fecha y horario válidos
  Y selecciono equipos que no están disponibles
Cuando intento crear la reserva
Entonces la reserva es rechazada
  Y recibo un mensaje indicando que los equipos no están disponibles
```

### CP-19-09: Reserva con equipos en otra ciudad

```gherkin
Dado que soy un usuario autenticado
  Y selecciono un espacio disponible
  Y selecciono una fecha y horario válidos
  Y selecciono equipos que no están en la misma ciudad del espacio
Cuando intento crear la reserva
Entonces la reserva es rechazada
  Y recibo un mensaje indicando que los equipos deben pertenecer a la misma ciudad del espacio reservado
```

### CP-19-10: Reserva con campos opcionales vacíos

```gherkin
Dado que soy un usuario autenticado
  Y selecciono un espacio disponible
  Y selecciono una fecha y horario válidos
  Y dejo los campos opcionales vacíos (title, attendeesCount, notes, equipmentIds)
Cuando intento crear la reserva
Entonces la reserva se crea exitosamente
  Y recibo una confirmación de la reserva
```

---

## HU-20: Listar reservas

> Casos de prueba en lenguaje Gherkin (español). Técnicas ISTQB aplicadas: partición de equivalencia, análisis de valores límite, prueba de reglas de negocio, prueba de combinaciones (tablas de decisión), pruebas de errores y pruebas de roles/permisos.

---

### CP-20-01: Consulta de reservas propias sin filtros

```gherkin
Dado que soy un usuario autenticado con rol colaborador
  Y tengo reservas previas registradas en el sistema
Cuando accedo a la sección de reservas sin aplicar ningún filtro
Entonces se muestra la lista completa de mis reservas activas
  Y la información de cada reserva es clara y estructurada
```

### CP-20-02: Consulta de reservas generales (todas) sin filtros

```gherkin
Dado que soy un usuario autenticado con rol colaborador
  Y existen reservas de diferentes usuarios en el sistema
Cuando accedo a la sección de reservas generales sin aplicar filtros
Entonces se muestra la lista de reservas accesibles según mis permisos
  Y la información presentada es clara y estructurada
```

### CP-20-03: Combinación de filtros por fecha y espacio

```gherkin
Dado que soy un usuario autenticado con rol colaborador
  Y existen reservas en diferentes fechas y espacios
Cuando aplico un filtro por fecha específica y por ID de espacio
Entonces solo se muestran las reservas que cumplen ambos criterios
```

### CP-20-04: Filtro por estado de reserva

```gherkin
Dado que soy un usuario autenticado con rol colaborador
  Y tengo reservas con diferentes estados (activa, cancelada, finalizada)
Cuando filtro las reservas por estado "activa"
Entonces solo se muestran las reservas con estado "activa"
  Y no se muestran reservas de otros estados
```

### CP-20-05: Ordenamiento ascendente y descendente por fecha de reserva

```gherkin
Dado que soy un usuario autenticado con rol colaborador
  Y tengo varias reservas en diferentes fechas
Cuando ordeno las reservas por fecha de forma ascendente
Entonces la primera reserva en la lista tiene la fecha más antigua
Cuando ordeno las reservas por fecha de forma descendente
Entonces la primera reserva en la lista tiene la fecha más reciente
```

### CP-20-06: Paginación de la lista de reservas

```gherkin
Dado que soy un usuario autenticado con rol colaborador
  Y tengo más reservas que el número de registros permitidos por página
Cuando navego a las siguientes páginas de la lista de reservas
Entonces los resultados se actualizan correctamente para cada página
  Y no se repiten reservas entre páginas
```

### CP-20-07: Validación de límites de paginación

```gherkin
Dado que soy un usuario autenticado con rol colaborador
  Y tengo suficientes reservas para cubrir múltiples páginas
Cuando intento navegar a una página menor que 1 o mayor que el total de páginas
Entonces el sistema muestra un mensaje de error o me mantiene en la página válida más cercana
```

### CP-20-08: Consulta sin resultados

```gherkin
Dado que soy un usuario autenticado con rol colaborador
  Y aplico filtros que no coinciden con ninguna reserva existente
Cuando visualizo la lista de reservas
Entonces el sistema muestra un mensaje indicando que no hay reservas para los criterios seleccionados
```

### CP-20-09: Manejo de sesión expirada o token inválido

```gherkin
Dado que mi sesión ha expirado o el token es inválido
Cuando intento consultar la lista de reservas
Entonces el sistema me redirige a la pantalla de inicio de sesión
  Y se muestra un mensaje indicando que la sesión ha expirado
```

### CP-20-10: Validación de longitud máxima de filtros de entrada

```gherkin
Dado que soy un usuario autenticado con rol colaborador
Cuando ingreso un texto que excede la longitud máxima permitida en el campo de filtro de espacio
Entonces el sistema rechaza el filtro y muestra un mensaje de error
  Y no ejecuta la consulta
```


# Test Cases

| HU | ID | Caso de prueba | Ajuste realizado por el probador | ¿Por qué se ajustó? |
| --- | --- | --- | --- | --- |
| HU-18 | TC-01 | **Dado** que el usuario está autenticado en la plataforma, **Y** existe un espacio con ID válido, **Y** el horario de reserva solicitado está antes de las 8:00 o después de las 18:00 UTC, **Cuando** el usuario consulta la disponibilidad del espacio para ese horario, **Entonces** el sistema rechaza la consulta con código HTTP 400 y muestra un mensaje de error indicando que el horario está fuera del rango permitido (8:00–18:00 UTC). | Se separaron los dos escenarios límite (antes de las 8:00 y después de las 18:00) en casos independientes para mayor precisión en la validación. Además, se especificó el código de respuesta HTTP 400 y el texto del mensaje de error esperado. | La HU refinada explicitó la restricción del rango horario y el manejo de errores, lo cual obliga a verificar ambos extremos del límite y el código de respuesta exacto para asegurar la correcta implementación. |
| HU-18 | TC-02 | **Dado** que soy un colaborador autenticado, **Y** deseo consultar la disponibilidad de un espacio con ID válido, **Cuando** consulto la disponibilidad para las 7:30 AM UTC, **Entonces** el sistema responde con código HTTP 400 y un mensaje indicando que el horario solicitado está fuera del rango permitido (mínimo 8:00 UTC). | Se agregó el código de respuesta HTTP 400 esperado y se especificó el ID del espacio como válido para aislar el error al horario y no al ID. Se añadió la verificación del mensaje específico que indica el límite inferior (8:00 UTC). | La HU original no definía el comportamiento para horarios fuera del rango; la versión refinada exige validación explícita del límite inferior, lo que requiere verificar tanto el código HTTP como el mensaje de error devuelto por la API. |
| HU-18 | TC-03 | **Dado** que soy un colaborador autenticado, **Y** deseo consultar la disponibilidad de un espacio con ID válido, **Cuando** consulto la disponibilidad para las 6:30 PM (18:30 UTC), **Entonces** el sistema responde con código HTTP 400 y un mensaje indicando que el horario solicitado está fuera del rango permitido (máximo 18:00 UTC). | Se actualizó la hora a formato UTC (18:30 UTC) para coherencia con la especificación técnica del proyecto. Se incluyó el código HTTP 400 y la mención explícita del límite superior (18:00 UTC) en el resultado esperado. | La HU refinada unificó el uso de UTC y explicitó la restricción del horario máximo. Sin este ajuste, el caso de prueba podría interpretarse en hora local y no detectar errores de conversión de zona horaria en la API. |
| HU-19 | TC-04 | **Dado** que soy un usuario autenticado, **Y** selecciono un espacio disponible, **Y** selecciono fecha y hora de inicio \"17:00 UTC\" y hora de fin \"18:01 UTC\", **Cuando** intento crear la reserva, **Entonces** el sistema rechaza la reserva con código HTTP 400 **Y** el mensaje de error indica que la hora de fin excede el límite permitido de las 18:00 UTC. | Se reemplazó el mensaje genérico "horario fuera del rango" por una validación específica sobre la hora de fin. Se añadió el código HTTP 400 y se precisó que el error corresponde exclusivamente al límite superior de la hora de fin. | La HU refinada especifica que tanto el inicio como el fin deben estar dentro del rango 8:00–18:00 UTC. Diferenciar el error de la hora de fin del de la hora de inicio permite confirmar que la API valida ambos campos de forma independiente. |
| HU-19 | TC-05 | **Dado** que soy un usuario autenticado, **Y** selecciono un espacio disponible en la ciudad "Bogotá", **Y** selecciono una fecha y horario válidos, **Y** selecciono equipos que pertenecen a la ciudad "Medellín", **Cuando** intento crear la reserva, **Entonces** el sistema rechaza la reserva con código HTTP 422 **Y** el mensaje de error indica que los equipos deben pertenecer a la misma ciudad del espacio reservado. | Se especificó el código HTTP 422 (entidad no procesable) para distinguir este error de negocio de un error de validación de formato (400). Se incluyeron nombres de ciudades concretos para hacer el caso reproducible y trazable. | La HU original no detallaba el comportamiento ante equipos de otra ciudad. La versión refinada establece explícitamente esta regla de negocio, requiriendo que el caso de prueba verifique tanto el rechazo como el mensaje descriptivo del error. |
| HU-19 | TC-06 | **Dado** que soy un usuario autenticado, **Y** selecciono un espacio disponible, **Y** selecciono una fecha y horario válidos, **Y** no envío los campos opcionales `title`, `attendeesCount`, `notes` y `equipmentIds`, **Cuando** intento crear la reserva, **Entonces** la reserva se crea exitosamente con estado `confirmed` **Y** los campos opcionales se almacenan como `null` en el sistema. | Se especificó el comportamiento esperado para cada campo opcional (almacenados como `null`) y se verificó el estado resultante de la reserva (`confirmed`). Anteriormente el caso solo validaba el éxito general sin comprobar los valores nulos. | La HU refinada aclara que los campos opcionales vacíos o ausentes deben manejarse apropiadamente. Definir el valor nulo esperado en la respuesta permite detectar regresiones donde el sistema asigne valores por defecto incorrectos o rechace la petición. |
| HU-20 | TC-07 | **Dado** que soy un usuario autenticado con rol colaborador, **Y** mi sesión ha expirado (token JWT inválido), **Cuando** intento consultar la lista de reservas, **Entonces** el sistema responde con código HTTP 401 **Y** muestra un mensaje indicando que la sesión ha expirado o el token es inválido **Y** me redirige a la pantalla de inicio de sesión. | Se agregó el código de respuesta HTTP 401 esperado y se separó el comportamiento de la API (respuesta 401) del comportamiento esperado en el frontend (redirección al login). Se incluyó el token JWT inválido como precondición explícita. | La HU original no definía el comportamiento ante errores de sesión. La versión refinada los contempla explícitamente. Separar la validación de la API de la del frontend permite identificar con precisión dónde falla el sistema en caso de error. |
| HU-20 | TC-08 | **Dado** que soy un usuario autenticado con rol colaborador, **Y** existen reservas con diferentes estados (`pending`, `confirmed`, `in_progress`, `completed`, `cancelled`), **Cuando** aplico el filtro por estado `confirmed` **Y** también por `spaceId` válido, **Entonces** el sistema responde con código HTTP 200 **Y** retorna únicamente las reservas con estado `confirmed` que corresponden al espacio indicado **Y** no se muestran reservas de otros estados ni de otros espacios. | Se combinaron dos filtros simultáneos (estado + espacio) para validar la lógica de filtros combinados, que era una ambigüedad identificada en la refinación. Se precisó que la lista resultado no debe incluir registros de ningún otro estado o espacio. | La HU original no especificaba si los filtros eran combinables. La versión refinada establece que deben serlo; este caso verifica esa regla de negocio crítica y detecta posibles errores en la lógica de intersección de filtros en la API. |
| HU-20 | TC-09 | **Dado** que soy un usuario autenticado con rol colaborador, **Y** aplico filtros que no coinciden con ninguna reserva existente (por ejemplo, `spaceId` válido pero sin reservas registradas), **Cuando** visualizo la lista de reservas, **Entonces** el sistema responde con código HTTP 200 **Y** retorna una lista vacía `[]` con un mensaje informativo que indica que no se encontraron reservas para los criterios seleccionados. | Se especificó que la respuesta debe ser HTTP 200 (no 404) con un array vacío `[]`, diferenciando el "sin resultados" de un "recurso no encontrado". Se añadió la verificación del mensaje informativo para mejorar la experiencia de usuario. | La HU refinada indica que el sistema debe manejar correctamente las consultas sin resultados. Confirmar el código 200 con array vacío evita malinterpretar este escenario como un error, alineándose con las buenas prácticas REST y los criterios de aceptación refinados. |
