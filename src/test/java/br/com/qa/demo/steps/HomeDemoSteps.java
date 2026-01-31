package br.com.qa.demo.steps;

import br.com.qa.demo.pages.HomeDemoPage;
import br.com.qa.demo.utils.Config;
import io.cucumber.java.AfterStep;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomeDemoSteps {

    private WebDriver driver;
    private HomeDemoPage homeDemoPage;
    private String originalWindow;

    @AfterStep
    public void delay() throws InterruptedException {
        Thread.sleep(Config.STEP_DELAY);
    }

    @Dado("que acesso o site DemoQA")
    public void acessarSiteDemoQA() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");
        originalWindow = driver.getWindowHandle();
    }

    @Quando("escolho a opcao Forms na pagina inicial")
    public void escolho_a_opcao_forms_na_pagina_inicial() {
        // Remove todos os iframes de anúncios que atrapalham o clique
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll('iframe').forEach(el => el.remove());"
        );

        // Localiza o card Forms
        WebElement formsCard = driver.findElement(By.xpath("//h5[text()='Forms']"));

        // Rola até o elemento
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", formsCard);

        // Força o clique via JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", formsCard);
    }


    @Quando("escolho a opcao Alerts, Frame & Windows na pagina inicial")
    public void escolhoAlertsFrameWindows() {
        WebElement card = driver.findElement(By.xpath("//h5[text()='Alerts, Frame & Windows']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", card);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", card);
    }
}
