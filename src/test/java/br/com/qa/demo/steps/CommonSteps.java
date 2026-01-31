package br.com.qa.demo.steps;

import br.com.qa.demo.context.ScenarioContext;
import io.cucumber.java.pt.Dado;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonSteps {

   // private ScenarioContext context;

   // public CommonSteps(ScenarioContext context) {
   //     this.context = context;
   // }

    @Dado("que acesso o site DemoQA")
    public void acessarSiteDemoQA() {
      //  context.getDriver().get("https://demoqa.com/");
        WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://demoqa.com/"));
        assertEquals("https://demoqa.com/", Hooks.driver.getCurrentUrl());
    }
}
