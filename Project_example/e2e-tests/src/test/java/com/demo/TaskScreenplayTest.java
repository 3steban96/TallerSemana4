package com.demo;

import com.demo.screenplay.tasks.NavigateTo;
import com.demo.screenplay.ui.TaskListUI;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.questions.Text;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SerenityJUnit5Extension.class)
public class TaskScreenplayTest {

    @CastMember(name = "Toby")
    Actor toby;

    @Test
    void shouldSeeTasksLoadedInTableUsingScreenplay() {
        toby.attemptsTo(
                NavigateTo.theTaskPage()
        );

        System.out.println(" Esperando a que las tareas carguen (Screenplay)...");

        toby.should(
                seeThat("the displayed tasks",
                        Text.ofEach(TaskListUI.TASK_TITLES),
                        tasks -> tasks.size() > 0)
        );
        
        System.out.println("Tareas encontradas con éxito usando Screenplay.");
    }
}
