package com.demo.screenplay.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateTo {
    public static Performable theTaskPage() {
        return Task.where("{0} opens the task page",
                Open.url("http://localhost:5173")
        );
    }
}
