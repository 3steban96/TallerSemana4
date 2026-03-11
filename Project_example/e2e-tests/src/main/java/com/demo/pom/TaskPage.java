package com.demo.pom;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class TaskPage extends PageObject {

    @FindBy(css = "tbody tr td strong")
    private List<WebElementFacade> taskTitles;

    public List<String> getTaskTitles() {
        // Serenity's WebElementFacade automatically handles waits based on configuration
        return taskTitles.stream()
                .map(WebElementFacade::getText)
                .collect(Collectors.toList());
    }
}
