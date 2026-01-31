package br.com.qa.demo.steps;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterStep
    public void limparTela() {
        // Limpa anúncios após cada passo para garantir que o próximo não seja bloqueado
        try {
            ((JavascriptExecutor) Hooks.driver).executeScript(
                    "var nodes = document.querySelectorAll('iframe, #fixedban');" +
                            "for(var i=0; i<nodes.length; i++) { nodes[i].remove(); }"
            );
        } catch (Exception e) {
            // Ignora erros caso o driver já tenha fechado
        }
    }
}
