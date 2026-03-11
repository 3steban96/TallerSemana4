package com.demo;

import com.demo.pom.TaskPage;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.annotations.Managed;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
public class TaskPomTest {

    @Managed
    WebDriver driver;

    TaskPage taskPage;

    @Test
    void shouldSeeTasksLoadedInTableUsingPOM() {
        taskPage.open();
        
        System.out.println(" Esperando a que las tareas carguen (POM)...");
        
        // Serenity maneja la espera automáticamente gracias a WebElementFacade en TaskPage
        assertThat(taskPage.getTaskTitles())
                .as("La lista de tareas no debería estar vacía")
                .isNotEmpty();
                
        System.out.println("Tareas encontradas: " + taskPage.getTaskTitles());
    }
}
