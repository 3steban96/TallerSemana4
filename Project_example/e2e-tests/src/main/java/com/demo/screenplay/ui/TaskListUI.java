package com.demo.screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class TaskListUI {
    public static final Target TASK_TITLES = Target.the("task titles")
            .locatedBy("tbody tr td strong");
}
