# Historias de Usuario Originales

---

## 🛡️ HU-05: Protección de Rutas

**Como** sistema,  
**quiero** restringir el acceso a las páginas del dashboard y reservas solo a usuarios autenticados,  
**para** garantizar la seguridad de la información.

### ✅ Criterios de Aceptación
- Las rutas `/dashboard` y `/my-reservations` solo son accesibles con un token JWT válido.
- Si un usuario no autenticado intenta acceder, es redirigido al login.
- Cada servicio backend valida el token JWT de forma independiente en cada petición.

### ⚙️ Datos Técnicos
- **Componente:** `ProtectedRoute`
- **Rutas protegidas:** `/dashboard`, `/my-reservations`

---

## 📦 HU-15: Listar Equipos

**Como** usuario autenticado,  
**quiero** ver la lista de equipos disponibles,  
**para** seleccionar los que necesito al crear una reserva.

### ✅ Criterios de Aceptación
- Se muestra una lista de equipos con nombre, modelo, estado, serial y ciudad.
- Los equipos se presentan en formato de tarjetas en el dashboard.
- Se puede filtrar por ciudad.

### ⚙️ Datos Técnicos
- **Endpoint:** `GET /inventory/equipments`
- **Servicio:** `inventory-service`

---

## 📅 HU-18: Consultar Disponibilidad de un Espacio

**Como** usuario autenticado,  
**quiero** verificar la disponibilidad de un espacio en un rango de fecha y hora específico,  
**para** saber si puedo crear una reserva en ese horario.

### ✅ Criterios de Aceptación
- Se indica el ID del espacio, fecha/hora de inicio y fecha/hora de fin (formato ISO-8601 UTC).
- El sistema responde indicando si el espacio está disponible (`available: true/false`) y la cantidad de reservas que se solapan.
- La fecha de inicio debe ser anterior a la fecha de fin.

### ⚙️ Datos Técnicos
- **Endpoint:** `GET /bookings/spaces/{spaceId}/availability?startAt=...&endAt=...`
- **Servicio:** `bookings-service`
- **Respuesta:** `{ "available": boolean, "overlappingReservations": number }`
