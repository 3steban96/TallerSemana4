package com.demo.steps;

import com.demo.screenplay.tasks.NavigateTo;
import com.demo.screenplay.ui.TaskListUI;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.questions.Text;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

/**
 * Step Definitions de Cucumber para los escenarios de Gestión de Tareas.
 *
 * <p>Cada método anotado con @Given, @When, @Then corresponde a un paso del archivo
 * src/test/resources/features/tasks.feature.
 *
 * <p>Los pasos reutilizan las clases de Screenplay existentes:
 * - {@link NavigateTo}: acción de abrir la página de tareas.
 * - {@link TaskListUI}: localizadores del target de la tabla.
 *
 * <p>Serenity BDD maneja internamente los "implicit waits" a través de su
 * mecanismo de espera automática con WebElementFacade y Targets, de manera que
 * los tests no necesitan {@code Thread.sleep()} ni {@code WebDriverWait} explícitos.
 */
public class TaskStepDefinitions {

    private Actor actor;

    @Before
    public void setStage() {
        // Se inicializa el "escenario" de Screenplay con un actor llamado "Usuario"
        OnStage.setTheStage(new OnlineCast());
        actor = OnStage.theActorCalled("Usuario");
    }

    @Given("el usuario navega a la página de tareas")
    public void elUsuarioNavegaALaPaginaDeTareas() {
        // NavigateTo.theTaskPage() abre http://localhost:5173 usando Open.url()
        // Serenity ya tiene implicit wait configurado vía serenity.properties
        actor.attemptsTo(
                NavigateTo.theTaskPage()
        );
    }

    @Then("debería ver al menos una tarea en la tabla")
    public void deberiaVerAlMenosUnaTareaEnLaTabla() {
        // seeThat verifica que la lista de títulos tiene tamaño > 0
        // El "implicit wait" de Serenity espera a que los elementos aparezcan en el DOM
        actor.should(
                seeThat("las tareas mostradas",
                        Text.ofEach(TaskListUI.TASK_TITLES),
                        tasks -> tasks.size() > 0)
        );
    }

    @Then("debería ver más de una tarea en la tabla")
    public void deberiaVerMasDeUnaTareaEnLaTabla() {
        actor.should(
                seeThat("la lista de tareas",
                        Text.ofEach(TaskListUI.TASK_TITLES),
                        hasSize(greaterThan(1)))
        );
    }
}
