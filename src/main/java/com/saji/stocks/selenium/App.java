package com.saji.stocks.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.time.Duration;

public class App {
    public static void main(String[] args){
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", File.separator+"Users"+File.separator+"sajethperli"+File.separator+"Downloads"+File.separator+"softwares"+File.separator+"chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

//Deleting all the cookies
        driver.manage().deleteAllCookies();

//Specifiying pageLoadTimeout and Implicit wait
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40L));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30L));

//launching the specified URL
        driver.get("https://www.google.com/");

//Locating the elements using name locator for the text box
        driver.findElement(By.name("q")).sendKeys("YouTube");

//name locator for google search button
        WebElement searchIcon = driver.findElement(By.name("btnK"));
        searchIcon.click();
    }
}
