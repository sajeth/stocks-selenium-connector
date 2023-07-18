package com.saji.stocks.selenium.config;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@Configuration
//@EnableAspectJAutoProxy
@ComponentScan({"com.saji.stocks.selenium.logging"})
@PropertySource("classpath:selenium.properties")
public class SeleniumConfig {
    @Autowired
    public Environment env;

    @Bean
    public WebDriver driver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        //  chromeOptions.setCapability("browserVersion", "100");
        chromeOptions.setCapability("platformName", Platform.LINUX);
        //  chromeOptions.addArguments("--remote-allow-origins=*");
// Showing a test name instead of the session id in the Grid UI
        //  chromeOptions.setCapability("se:name", "My simple test");
// Other type of metadata can be seen in the Grid UI by clicking on the
// session info or via GraphQL
        //   chromeOptions.setCapability("se:sampleMetadata", "Sample metadata value");
        WebDriver driver;
        try {
            driver = new RemoteWebDriver(new URI(Objects.requireNonNull(env.getProperty("browser.url"))).toURL(), chromeOptions);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }

    public SeleniumConfig() {
    }
}
