package br.com.qa.demo.steps;

import br.com.qa.demo.pages.BrowserWindowsPage;
import br.com.qa.demo.utils.Config;
import io.cucumber.java.AfterStep;
import io.cucumber.java.pt.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrowserWindowsSteps {

    private WebDriver driver;
    private BrowserWindowsPage browserWindowsPage;
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
        //originalWindow = driver.getWindowHandle();
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

    @Quando("clico no submenu Browser Windows")
    public void clicoNoSubmenuBrowserWindows() {
        driver.findElement(By.xpath("//span[text()='Browser Windows']")).click();
        browserWindowsPage = new BrowserWindowsPage(driver);
    }

    @Quando("clico no botão New Window")
    public void clicoNoBotaoNewWindow() {
        browserWindowsPage.clickNewWindowButton();
    }

    @Então("uma nova janela deve ser aberta com a mensagem {string}")
    public void validarMensagemNovaJanela(String mensagemEsperada) {
        // troca para a nova janela
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        String textoPagina = driver.findElement(By.id("sampleHeading")).getText();
        assertEquals(mensagemEsperada, textoPagina);
    }

    @E("fecho a nova janela aberta")
    public void fecharNovaJanela() {
        driver.close(); // fecha a nova janela
        driver.switchTo().window(originalWindow); // volta para a janela original
        driver.quit();
    }
}
