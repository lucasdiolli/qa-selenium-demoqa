package br.com.qa.demo.steps;

import br.com.qa.demo.context.ScenarioContext;
import br.com.qa.demo.pages.BrowserWindowsPage;
import br.com.qa.demo.utils.Config;
import io.cucumber.java.AfterStep;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrowserWindowsSteps {

    private ScenarioContext context;
    private BrowserWindowsPage browserWindowsPage;

    public BrowserWindowsSteps(ScenarioContext context) {
        this.context = context;
    }

    @AfterStep
    public void delay() throws InterruptedException {
        Thread.sleep(Config.STEP_DELAY);
    }

    @E("clico no submenu Browser Windows")
    public void clicoNoSubmenuBrowserWindows() {
        ((JavascriptExecutor) context.getDriver()).executeScript(
                "document.querySelectorAll('iframe').forEach(el => el.remove());"
        );

        // garante que o card foi clicado
        WebDriverWait wait = new WebDriverWait(context.getDriver(), Duration.ofSeconds(10));
        WebElement card = context.getDriver().findElement(By.xpath("//h5[text()='Alerts, Frame & Windows']"));
        ((JavascriptExecutor) context.getDriver()).executeScript("arguments[0].scrollIntoView(true);", card);
        ((JavascriptExecutor) context.getDriver()).executeScript("arguments[0].click();", card);

        // espera o submenu aparecer

        WebElement browserWindowsMenu = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Browser Windows']"))
        );
        browserWindowsMenu.click();

        browserWindowsPage = new BrowserWindowsPage(context.getDriver());
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
