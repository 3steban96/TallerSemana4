Feature: Gestión de Tareas

  Como usuario de la plataforma
  Quiero ver las tareas cargadas en la tabla
  Para poder gestionar mis actividades pendientes

  Background:
    Given el usuario navega a la página de tareas

  Scenario: El usuario ve las tareas cargadas en la tabla (Implicit Wait via Serenity)
    Then debería ver al menos una tarea en la tabla

  Scenario: El usuario ve múltiples tareas en la tabla
    Then debería ver más de una tarea en la tabla
