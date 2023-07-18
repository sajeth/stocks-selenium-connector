package com.saji.stocks.selenium;

import com.saji.stocks.pojo.selenium.Action;
import com.saji.stocks.pojo.selenium.ActionType;
import com.saji.stocks.pojo.selenium.ParameterType;
import com.saji.stocks.pojo.selenium.Step;
import com.saji.stocks.selenium.config.SeleniumConfig;
import com.saji.stocks.selenium.method.Task;
import org.openqa.selenium.WebDriver;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;


public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(SeleniumConfig.class);
        WebDriver driver = context.getBean("driver",WebDriver.class);
        List<Step> steps = new ArrayList<>();
        steps.add(new Step(ActionType.input, "APjFqb", ParameterType.id, "test"));
        //       steps.add(new Step(ActionType.input, "password", ParameterType.id, "123"));
//        steps.add(new Step(ActionType.submit, "password", ParameterType.id, ""));
//        steps.add(new Step(ActionType.wait, "INPUT", ParameterType.tag, 10L));
//        steps.add(new Step(ActionType.submit, "INPUT", ParameterType.tag, ""));
        Action action = new Action("Kite", "https://www.google.com/webhp?hl=en&sa=X&ved=0ahUKEwicqdH3mpeAAxWXlIkEHTV0COAQPAgI", steps);
        Task task = new Task(driver, action);
        task.execute();
        context.close();

    }
}
