package br.com.qa.demo.steps;

import br.com.qa.demo.pages.PracticeFormPage;
import br.com.qa.demo.utils.Config;
import io.cucumber.java.AfterStep;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.E;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PracticeFormSteps {

    private PracticeFormPage practiceFormPage;

    @AfterStep
    public void delay() throws InterruptedException {
        Thread.sleep(Config.STEP_DELAY);
    }
    @Quando("escolho a opcao Forms na pagina inicial")
    public void escolho_a_opcao_forms_na_pagina_inicial() {
        // Remove todos os iframes de anúncios que atrapalham o clique
        ((JavascriptExecutor) Hooks.driver).executeScript(
                "document.querySelectorAll('iframe').forEach(el => el.remove());"
        );

        // Localiza o card Forms
        WebElement formsCard = Hooks.driver.findElement(By.xpath("//h5[text()='Forms']"));

        // Rola até o elemento
        ((JavascriptExecutor) Hooks.driver).executeScript("arguments[0].scrollIntoView(true);", formsCard);

        // Força o clique via JavaScript
        ((JavascriptExecutor) Hooks.driver).executeScript("arguments[0].click();", formsCard);
    }

    @Quando("clico no submenu Practice Form")
    public void clico_no_submenu_practice_form() {
        // Primeiro entra no card Forms
        WebElement formsCard = Hooks.driver.findElement(By.xpath("//h5[text()='Forms']"));
        ((JavascriptExecutor) Hooks.driver).executeScript("arguments[0].scrollIntoView(true);", formsCard);
        ((JavascriptExecutor) Hooks.driver).executeScript("arguments[0].click();", formsCard);

        // Espera o submenu aparecer
        WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(10));
        WebElement practiceFormMenu = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Practice Form']"))
        );
        practiceFormMenu.click();

        // Inicializa a Page Object
        practiceFormPage = new PracticeFormPage(Hooks.driver);
    }

    @Quando("crio um novo registro")
    public void criarNovoRegistro() {
        practiceFormPage.criarRegistro("Lucas", "Oliveira", "lucas@teste.com", "1234567890", "Paulista Av");
    }

    @Entao("valido que foi exbido popup com dados do registro")
    public void valido_que_foi_exibido_popup_com_dados_do_registro() {
        assertTrue(practiceFormPage.validarPopupExibido());
    }

    @E("fecho o popup")
    public void fechoOPopup() {
        practiceFormPage.fecharPopup();
    }
}
