# Test Cases

| HU | ID | Caso de prueba | ajuste realizado por el probador | ¿Porque se ajusto? |
| --- | --- | --- | --- | --- |
| HU-05 | TC-01 | Acceso a dashboard sin autenticación dado que el usuario no está autenticado cuando intenta acceder a la página del dashboard entonces es redirigido a la página de inicio de sesión. | | |
| HU-05 | TC-02 | Acceso a reservas sin autenticación dado que el usuario no está autenticado cuando intenta acceder a la página de reservas entonces es redirigido a la página de inicio de sesión. | | |
| HU-05 | TC-03 | Acceso a dashboard con autenticación válida dado que el usuario está autenticado correctamente cuando accede a la página del dashboard entonces puede visualizar el contenido del dashboard. | | |
| HU-15 | TC-04 | Dado que el usuario ha iniciado sesión correctamente y la lista de equipos tiene al menos un equipo registrado cuando el usuario accede a la pantalla de inventario de equipos entonces el sistema muestra una tarjeta por cada equipo con su estado respectivo y cada tarjeta permite seleccionar el equipo para la reserva | | |
| HU-15 | TC-05 | Dado que el usuario ha iniciado sesión correctamente y la selección de equipos está configurada como única cuando el usuario selecciona un equipo para la reserva entonces solo un equipo puede estar seleccionado a la vez y la selección previa se desmarca automáticamente al seleccionar un nuevo equipo | | |
| HU-15 | TC-06 | Dado que el usuario ha iniciado sesión correctamente y la selección de equipos está configurada como múltiple cuando el usuario selecciona más de un equipo para la reserva entonces el sistema permite seleccionar varios equipos simultáneamente | | |
| HU-18 | TC-07 | Dado que soy un colaborador autenticado y me encuentro en la plataforma web Cuando consulto los espacios disponibles para reservar a las 10:00 AM entonces visualizo la lista de espacios disponibles en ese horario y la información de cada espacio incluye nombre, capacidad y ubicación | | |
| HU-18 | TC-08 | Dado que soy un colaborador autenticado y deseo reservar un espacio cuando consulto los espacios disponibles para las 7:30 AM entonces la plataforma indica que no hay disponibilidad en ese horario y no permite realizar la reserva | | |
| HU-18 | TC-09 | Dado que soy un colaborador autenticado y deseo reservar un espacio Cuando consulto los espacios disponibles para las 6:30 PM Entonces la plataforma indica que no hay disponibilidad en ese horario y no permite realizar la reserva | | |
