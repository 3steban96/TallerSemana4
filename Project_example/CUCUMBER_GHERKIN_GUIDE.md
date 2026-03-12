# Guía: Gherkin + Cucumber con Serenity BDD

> Esta guía explica cómo se integra Gherkin/Cucumber al proyecto `e2e-tests` y su relación con el concepto de **pruebas implícitas (implicit waits)** de Selenium.

---

## ¿Qué es Gherkin y para qué se usa?

**Gherkin** es un lenguaje de especificación legible por humanos que permite describir el comportamiento esperado del sistema usando palabras clave estructuradas:

| Keyword    | Propósito                                               |
|------------|---------------------------------------------------------|
| `Feature`  | Describe la funcionalidad a probar                      |
| `Scenario` | Un caso de prueba concreto                              |
| `Background` | Pasos comunes a todos los escenarios del feature     |
| `Given`    | Precondición o estado inicial del sistema               |
| `When`     | Acción que realiza el usuario                           |
| `Then`     | Resultado esperado o verificación                       |
| `And`      | Continuación de un paso anterior                        |

**Cucumber** es el framework que ejecuta los archivos Gherkin (`.feature`) y los conecta con código Java a través de los **Step Definitions**.

---

## Esperas Implícitas (Implicit Waits) vs Serenity BDD

### El problema sin esperas
Cuando la aplicación carga datos del backend (ej: 3 segundos de delay), Selenium puede lanzar un `NoSuchElementException` si busca el elemento antes de que aparezca en el DOM. Esta es exactamente la demostración del archivo `NoWaitFailDemo.java`.

### Esperas implícitas puras (Selenium)
Selenium permite configurar un tiempo de espera global:
```java
// Ejemplo en WaitSuccessDemo.java
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
```
Esto le dice a Selenium que espere **hasta 10 segundos** antes de lanzar excepción cuando busca un elemento.

### Serenity BDD abstrae las esperas implícitas
En los tests de Cucumber/Screenplay de este proyecto **no escribimos esperas explícitas**. Serenity lo maneja automáticamente:

- Los **`Target`** (como `TaskListUI.TASK_TITLES`) usan `WebElementFacade` internamente.
- `WebElementFacade` aplica las esperas configuradas en `serenity.properties` de manera transparente.
- El resultado es que nuestros Step Definitions están **libres de `Thread.sleep()` o `WebDriverWait`**.

---

## Estructura del Proyecto con Cucumber

```
e2e-tests/
├── build.gradle                          ← Dependencia serenity-cucumber agregada
├── serenity.properties                   ← Configuración de timeouts de Serenity
└── src/
    ├── main/java/com/demo/
    │   ├── screenplay/
    │   │   ├── tasks/NavigateTo.java     ← Tarea Screenplay: navegar a la app
    │   │   └── ui/TaskListUI.java        ← Localizadores/Targets de la tabla
    │   └── pom/TaskPage.java             ← Page Object (patrón POM)
    └── test/
        ├── java/com/demo/
        │   ├── CucumberTestSuite.java    ← Runner de Cucumber (JUnit Platform Suite)
        │   ├── TaskPomTest.java          ← Test con patrón POM
        │   ├── TaskScreenplayTest.java   ← Test con patrón Screenplay (directo)
        │   └── steps/
        │       └── TaskStepDefinitions.java  ← Step Definitions que conectan Gherkin con Screenplay
        └── resources/
            └── features/
                └── tasks.feature         ← Escenarios en lenguaje Gherkin
```

---

## Relación: TEST_CASES_AI.md → .feature files

Los escenarios del archivo `TEST_CASES_AI.md` se definen en Gherkin como **documentación estructurada**. El archivo `tasks.feature` convierte esa documentación en **tests ejecutables**:

| En TEST_CASES_AI.md | En tasks.feature |
|---------------------|-----------------|
| `Given el usuario está autenticado...` | `Given el usuario navega a la página de tareas` |
| `When el usuario consulta la disponibilidad...` | (simplificado al contexto de la tabla de tareas) |
| `Then el sistema muestra...` | `Then debería ver al menos una tarea en la tabla` |

---

## Cómo Ejecutar los Tests de Cucumber

> [!IMPORTANT]
> La aplicación frontend debe estar corriendo en `http://localhost:5173` antes de ejecutar.

### Ejecutar todos los tests (POM + Screenplay + Cucumber)
```bash
cd Project_example/e2e-tests
./gradlew clean test aggregate
```

### Ver el reporte de Serenity
Después de ejecutar, abrir en el navegador:
```
Project_example/e2e-tests/target/site/serenity/index.html
```

---

## Flujo de Ejecución de un Escenario Cucumber

```
tasks.feature
    │  Scenario: El usuario ve las tareas cargadas
    │  Given el usuario navega a la página de tareas
    │  Then debería ver al menos una tarea en la tabla
    │
    ▼
CucumberTestSuite.java
    │  (Runner JUnit Platform Suite)
    │  @SelectClasspathResource("features")
    │  @ConfigurationParameter(GLUE → "com.demo.steps")
    │
    ▼
TaskStepDefinitions.java
    │  @Given → actor.attemptsTo(NavigateTo.theTaskPage())
    │  @Then  → actor.should(seeThat(Text.ofEach(TASK_TITLES), size > 0))
    │
    ▼
Serenity BDD
    │  Aplica implicit wait automático via WebElementFacade
    │  Genera reporte HTML con cada paso del escenario
    │
    ▼
Reporte: target/site/serenity/index.html
```

---

## Comparación de los 3 patrones de prueba del proyecto

| Característica | `NoWaitFailDemo` / `WaitSuccessDemo` | `TaskPomTest` (POM) | `TaskScreenplayTest` (Screenplay) | `CucumberTestSuite` (Gherkin) |
|---|---|---|---|---|
| Formato | Script `main()` | JUnit 5 | JUnit 5 | Gherkin `.feature` |
| Legibilidad negocio | Baja | Media | Media-Alta | **Alta** |
| Reutilización | Ninguna | Con Page Objects | Con Tasks/Targets | Reutiliza Screenplay |
| Reporte Serenity | No | Sí | Sí | **Sí (con escenarios BDD)** |
| Esperas | Manual (`implicitlyWait`) | Serenity automático | Serenity automático | Serenity automático |
