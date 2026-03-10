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

3. **`e2e-tests/` (Python Selenium Testing)**
   - Scripts de automatización en Python que interactúan con el Frontend para validar la carga retrasada debido a la latencia del Backend.
   - Dos demostraciones didácticas incluidas: un intento que falla (sin esperas intencionalmente) y un éxito usando "Implicit Waits".

---

## 🛠️ Requisitos Previos

- **Node.js** v18+ (para el Frontend y Backend).
- **Python** 3.8+ y pip (para ejecutar los scripts de Selenium).
- **Navegador Google Chrome** (requerido para el WebDriver de Selenium de los tests Python).

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

### 3️⃣ Ejecutar Pruebas Automatizadas con Selenium (Terminal 3)
La demostración de Selenium busca específicamente ejemplificar cómo manejar un elemento DOM que tarda en aparecer en pantalla debido a las asincronizaciones con el servidor.

Navega a la carpeta `e2e-tests` e instala el entorno Python:
```bash
cd e2e-tests
python -m pip install -r requirements.txt
npm install
```

**Demostración A: Prueba SIN esperas (Debe fallar intencionalmente) ❌**
```bash
npm run test:fail
```
> _**Comportamiento:** Como el WebDriver consulta el DOM inmediatamente apenas carga el sitio en el puerto 5173, no encuentra las tareas renderizadas (que demorarán 3 segundos). Arrojará una excepción de tipo `NoSuchElementException`._

**Demostración B: Prueba CON Esperas Implícitas (Ejecución exitosa) ✅**
```bash
npm run test:success
```
> _**Comportamiento:** El script configura un `driver.implicitly_wait(10)` previo a la búsqueda. Cuando busca la información en la tabla, aunque no esté inicialmente, no falla hasta que agoten los 10 segundos buscando internamente en iteraciones. El elemento se rinde a los 3 segundos, lo asume de forma silenciosa y declara la prueba local exitosa imprimiendo el título de la tarea por consola._
