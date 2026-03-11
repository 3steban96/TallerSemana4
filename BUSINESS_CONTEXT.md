# Plataforma de Reservas Casa Sofka

## 1. Descripción del Proyecto
La **Plataforma de Reservas de Espacios Casa Sofka** es una herramienta diseñada para optimizar la gestión de espacios comunes. Su objetivo principal es facilitar a los colaboradores la visualización y reserva de horarios disponibles en cada espacio de la oficina.

## 2. Flujos Críticos del Negocio
| Flujo | Descripción |
| :--- | :--- |
| **Autenticación** | Registro e inicio de sesión seguro de usuarios. |
| **Exploración** | Listado de espacios disponibles y consulta de disponibilidad. |
| **Reserva** | Selección de fecha y horario para asegurar un espacio. |

**Funcionalidades Clave:**
- Módulo de espacios.
- Módulo de reservas.
- Módulo de espacios reservados.

## 3. Reglas de Negocio y Restricciones
> [!IMPORTANT]
> **Horario de Reserva:** Las reservas solo están permitidas en la franja horaria de **8:00 AM a 6:00 PM**.

* **Normativa:** El uso de los espacios está sujeto a la normativa interna de la empresa.

## 4. Perfiles de Usuario y Roles
| Rol | Descripción | Permisos / Limitaciones |
| :--- | :--- | :--- |
| **Colaborador** | Usuario estándar de la plataforma. | Control completo sobre su propio usuario. No puede cancelar reservas de otros usuarios. |

## 5. Condiciones del Entorno Técnico
* **Plataforma:** Aplicación disponible exclusivamente para web.
* **Stack Tecnológico:** React, ExpressJS, MariaDB, Podman.

## 6. Casos Especiales y Excepciones
* **Sesión:** Si el token es inválido o expira, el usuario debe volver a iniciar sesión.
* **Disponibilidad:** Si no hay disponibilidad, no se crea la reserva.
* **Reglas de Equipos:** Si los equipos no cumplen las reglas establecidas, la reserva se rechaza.
* **Cancelación:** Si el estado actual no permite la cancelación, la operación es rechazada.
