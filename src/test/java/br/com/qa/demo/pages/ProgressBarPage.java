package br.com.qa.demo.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProgressBarPage {
    WebDriver driver;
    WebDriverWait wait;

    private By startStopButton = By.id("startStopButton");
    private By progressBar = By.cssSelector(".progress-bar");
    private By resetButton = By.id("resetButton");

    public ProgressBarPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void clicarStartStop() {
        driver.findElement(startStopButton).click();
    }

    public int getValorAtual() {
        String value = driver.findElement(progressBar).getAttribute("aria-valuenow");
        return Integer.parseInt(value);
    }

    public void pararEmPercentual(int percentualAlvo) {
        clicarStartStop();
        // Monitora até atingir o valor
        while (getValorAtual() < percentualAlvo) {
            // Loop vazio apenas para monitoramento rápido
        }
        clicarStartStop(); // Para
    }

    public void esperarCemPorCentoEResetar() {
        // Espera a barra atingir a classe que indica conclusão (bg-success)
        wait.until(ExpectedConditions.attributeToBe(progressBar, "aria-valuenow", "100"));
        wait.until(ExpectedConditions.elementToBeClickable(resetButton)).click();
    }
}