package br.com.qa.demo.steps;

import br.com.qa.demo.context.ScenarioContext;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

    private ScenarioContext context;

    public Hooks(ScenarioContext context) {
        this.context = context;
    }

    @Before
    public void setUp() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        context.setDriver(driver); // aqui o driver é setado
    }

    @After
    public void tearDown() {
        if (context.getDriver() != null) {
            context.getDriver().quit();
        }
    }
}
