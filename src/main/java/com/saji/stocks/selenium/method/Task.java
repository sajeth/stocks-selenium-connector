package com.saji.stocks.selenium.method;

import com.saji.stocks.pojo.selenium.Action;
import com.saji.stocks.pojo.selenium.ParameterType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Task {
    private static WebDriver driver = null;

    private static final Logger logger = Logger.getLogger("Method.class");

    private final List<MethodObject> executions = Collections.synchronizedList(new ArrayList<>());

    private static Action action;

    public Task(final WebDriver driver, final Action action) {
        Task.driver = driver;
        driver.get(action.url());
        new WebDriverWait(driver, Duration.ZERO);

        Task.action = action;
        Method[] methods = this.getClass().getMethods();
        Task.action.steps().forEach(
                val -> {
                    try {
                        Arrays.stream(methods).filter(method -> method.getName().equals(val.actionType().name())).findAny().ifPresent(
                                m ->
                                        executions.add(new MethodObject(val.data(), m, findElement(val.identifier(), val.parameterType())))
                        );
                    } catch (Exception e) {
                        e.printStackTrace();
                        driver.quit();

                    }

                }
        );
    }


    public void execute() {

        try {
            executions.forEach(methodObject -> {
                try {
                    methodObject.method().invoke(this, methodObject.element, methodObject.data);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    driver.quit();
                    throw new RuntimeException(e);
                }
            });
        } finally {
            driver.quit();
        }
    }

    public WebElement findElement(final String parameterName, final ParameterType parameterType) {
        By typeOfFunction = null;
        switch (parameterType) {
            case id -> typeOfFunction = By.id(parameterName);
            case name -> typeOfFunction = By.name(parameterName);
            case link -> typeOfFunction = By.linkText(parameterName);
            case partiallink -> typeOfFunction = By.partialLinkText(parameterName);
            case xpath -> typeOfFunction = By.xpath(parameterName);
            case classnm -> typeOfFunction = By.className(parameterName);
            case tag -> typeOfFunction = By.tagName(parameterName);
            case css -> typeOfFunction = By.cssSelector(parameterName);
            default -> {
                String err = action.name() + "*" + parameterName + "*" + parameterType;
                logger.log(Level.WARNING, err);
            }
        }

        return driver.findElement(typeOfFunction);
    }

    public void click(WebElement element, String ignoredData) {
        element.click();
    }

    public void submit(WebElement element, String ignoredData) {
        element.submit();
    }

    public void input(WebElement element, String data) {
        element.sendKeys(data);
    }

    public void wait(WebElement ignoredElement, long data) throws InterruptedException {
        Thread.sleep(Math.min(Math.max(data, 30), 60L));
    }

    public record MethodObject(Object data, Method method, WebElement element) {
    }
}
