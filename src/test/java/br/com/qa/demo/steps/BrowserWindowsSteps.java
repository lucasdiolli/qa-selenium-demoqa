package br.com.qa.demo.steps;

import br.com.qa.demo.pages.BrowserWindowsPage;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrowserWindowsSteps {

    private BrowserWindowsPage browserWindowsPage;

    @Quando("escolho a opcao Alerts, Frame & Windows na pagina inicial")
    public void escolhoAlertsFrameWindows() {
        WebElement card = Hooks.driver.findElement(By.xpath("//h5[text()='Alerts, Frame & Windows']"));
        ((JavascriptExecutor) Hooks.driver).executeScript("arguments[0].scrollIntoView(true);", card);
        ((JavascriptExecutor) Hooks.driver).executeScript("arguments[0].click();", card);
    }

    @E("clico no submenu Browser Windows")
    public void clicoNoSubmenuBrowserWindows() {
        // Primeiro entra no card Alerts, Frame & Windows
        WebElement card = Hooks.driver.findElement(By.xpath("//h5[text()='Alerts, Frame & Windows']"));
        ((JavascriptExecutor) Hooks.driver).executeScript("arguments[0].scrollIntoView(true);", card);
        ((JavascriptExecutor) Hooks.driver).executeScript("arguments[0].click();", card);

        // Espera o submenu aparecer
        WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(10));
        WebElement browserWindowsMenu = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Browser Windows']"))
        );
        browserWindowsMenu.click();

        // Inicializa a Page Object
        browserWindowsPage = new BrowserWindowsPage(Hooks.driver);
    }

    @E("clico no botão New Window")
    public void clicoNoBotaoNewWindow() {
        browserWindowsPage.clickNewWindowButton();
    }

    @Entao("uma nova janela deve ser aberta com a mensagem {string}")
    public void umaNovaJanelaDeveSerAbertaComAMensagem(String message) {
        assertTrue(browserWindowsPage.isNewWindowOpenedWithMessage(message));
    }

    @E("fecho a nova janela aberta")
    public void fechoANovaJanelaAberta() {
        browserWindowsPage.closeNewWindow();
    }
}
