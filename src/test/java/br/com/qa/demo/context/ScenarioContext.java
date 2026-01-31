package br.com.qa.demo.context;

import org.openqa.selenium.WebDriver;

public class ScenarioContext {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
