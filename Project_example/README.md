# 📝 ToDo List Application & Selenium Waits Demo

¡Bienvenido al repositorio del proyecto **Taller Semana 4**! 
Este proyecto es una aplicación Fullstack sencilla de lista de tareas diseñada para demostrar integraciones entre Frontend y Backend, así como también una demostración clave sobre **Esperas Implícitas (Implicit Waits) en testing Automatizado E2E con Selenium**.

---

## 🏗️ Estructura del Proyecto

El proyecto se divide en 3 componentes principales:

1. **`backend/` (Express.js)**
   - API REST en Node.js ligera.
   - Sirve endpoints `GET`, `POST`, `PUT`, `DELETE` para administrar tareas.
   - Usa un archivo `tasks.json` interno como persistencia local (base de datos en memoria para propósitos del taller).
   - **Nota de Testing:** El endpoint `GET /api/tasks` tiene un **retraso artificial simulado de 3 segundos** necesario para demostrar el comportamiento asíncrono y los waits de Selenium en las pruebas de UI.

2. **`frontend/` (React + Vite)**
   - Aplicación de interfaz de usuario con un diseño minimalista "Glassmorphism" y modo oscuro.
   - Construida con React 18 y Vite, consume directamente la API del backend mediante peticiones con Axios (en el puerto 3000).
   - Puedes crear, editar, marcar como completadas y eliminar tareas dinámicamente.

3. **`e2e-tests/` (Java + Gradle Selenium Testing)**
   - Scripts de automatización en Java que interactúan con el Frontend para validar la carga retrasada debido a la latencia del Backend.
   - Usa Gradle para la gestión de dependencias WebDriver.
   - Dos demostraciones didácticas incluidas: un intento que falla (sin esperas intencionalmente) y un éxito usando "Implicit Waits".

---

## 🛠️ Requisitos Previos

- **Node.js** v18+ (para el Frontend y Backend).
- **Java JDK** 11+ o superior (para ejecutar los scripts de Selenium con Java).
- **Gradle** (para la resolución de dependencias y ejecución de tareas).
- **Navegador Google Chrome** (requerido para el WebDriver de Selenium).

---

## 🚀 Instalación y Ejecución

Sigue las instrucciones a continuación en **3 pestañas de terminal diferentes**.

### 1️⃣ Levantar el Backend (Terminal 1)
Navega a la carpeta del backend, instala dependencias y ejecuta el servidor de desarrollo:
```bash
cd backend
npm install
npm run dev
```
> El backend quedará ejecutándose en `http://localhost:3000`.

### 2️⃣ Levantar el Frontend (Terminal 2)
Navega a la carpeta del frontend, instala los paquetes e inicializa Vite:
```bash
cd frontend
npm install
npm run dev
```
> El frontend ahora será accesible en `http://localhost:5173`. Visítalo para usar la App. Notarás que la tabla Inicial demorará 3 segundos en cargarse a propósito.

### 3️⃣ Ejecutar Pruebas Automatizadas con Serenity BDD (Terminal 3)
La demostración de Selenium se ha evolucionado a un framework de grado profesional utilizando **Serenity BDD** con los patrones **Page Object Model (POM)** y **Screenplay**.

Navega a la carpeta `Project_example/e2e-tests`:
```bash
cd Project_example/e2e-tests
```

**Demostración A: Patrón POM (Page Object Model) 📄**
Ejecuta los tests basados en el patrón clásico de Page Objects:
```bash
gradle test --tests com.demo.TaskPomTest
```

**Demostración B: Patrón SCREENPLAY 🎭**
Ejecuta los tests basados en el patrón de actores, tareas e interacciones:
```bash
gradle test --tests com.demo.TaskScreenplayTest
```

**Generar Reporte de Serenity 📊**
Después de correr los tests, genera el reporte visual detallado:
```bash
gradle aggregate
```
> El reporte se encontrará en: `target/site/serenity/index.html`.

---

## 🏗️ Patrones de Automatización Implementados

- **Page Object Model (POM):** Encapsula la estructura de la página en `TaskPage.java`.
- **Screenplay Pattern:** Un enfoque más modular y centrado en el usuario, utilizando Actores (`Toby`), Tareas (`NavigateTo`) y Targets (`TaskListUI`).
- **Serenity BDD:** Proporciona logs detallados, capturas de pantalla automáticas y reportes de alta calidad.

