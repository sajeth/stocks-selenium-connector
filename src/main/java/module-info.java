module stocks.selenium.connector {
    requires org.seleniumhq.selenium.api;
    requires org.seleniumhq.selenium.chrome_driver;
    requires transitive stocks.commons;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires com.google.common;
    requires org.aspectj.tools;
    requires org.seleniumhq.selenium.support;
    exports com.saji.stocks.selenium.config to spring.context, spring.beans;
    opens com.saji.stocks.selenium.config to spring.core;
}