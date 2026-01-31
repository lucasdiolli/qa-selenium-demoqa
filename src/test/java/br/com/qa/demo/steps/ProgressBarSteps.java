package br.com.qa.demo.steps;

import br.com.qa.demo.pages.ProgressBarPage;
import io.cucumber.java.pt.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProgressBarSteps {
    ProgressBarPage pbPage = new ProgressBarPage(Hooks.driver);

    @Quando("escolho a opcao Widgets na pagina inicial")
    public void escolho_a_opcao_widgets() {
        WebElement card = Hooks.driver.findElement(By.xpath("//h5[text()='Widgets']"));
        ((JavascriptExecutor) Hooks.driver).executeScript("arguments[0].scrollIntoView(true);", card);
        card.click();
    }

    @Quando("clico no submenu Progress Bar")
    public void clico_no_submenu_progress_bar() {
        WebElement menu = Hooks.driver.findElement(By.xpath("//span[text()='Progress Bar']"));
        ((JavascriptExecutor) Hooks.driver).executeScript("arguments[0].click();", menu);
    }

    @Quando("inicio a barra e paro antes dos 25%")
    public void inicio_a_barra_e_paro_antes_dos_25() {
        pbPage.pararEmPercentual(23);
    }

    @Então("valido que o valor da barra é menor ou igual a 25%")
    public void valido_o_percentual() {
        int valor = pbPage.getValorAtual();
        assertTrue(valor <= 25, "O valor era " + valor + ", mas deveria ser <= 25");
    }

    @Quando("inicio a barra novamente e reseto ao chegar em 100%")
    public void inicio_novamente_e_reseto() {
        pbPage.clicarStartStop();
        pbPage.esperarCemPorCentoEResetar();
    }
}