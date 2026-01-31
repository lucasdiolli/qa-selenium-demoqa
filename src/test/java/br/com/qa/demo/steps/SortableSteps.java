package br.com.qa.demo.steps;

import br.com.qa.demo.pages.SortablePage;
import io.cucumber.java.pt.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortableSteps {
    SortablePage sortPage = new SortablePage(Hooks.driver);

    @Quando("escolho a opcao Interactions na pagina inicial")
    public void escolho_a_opcao_interactions() {
        WebElement card = Hooks.driver.findElement(By.xpath("//h5[text()='Interactions']"));
        ((JavascriptExecutor) Hooks.driver).executeScript("arguments[0].scrollIntoView(true);", card);
        card.click();
    }

    @Quando("clico no submenu Sortable")
    public void clico_no_submenu_sortable() {
        WebElement menu = Hooks.driver.findElement(By.xpath("//span[text()='Sortable']"));
        ((JavascriptExecutor) Hooks.driver).executeScript("arguments[0].click();", menu);
        WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(2));
    }

    @Quando("ordeno os elementos da lista na ordem crescente")
    public void ordeno_os_elementos() {
        sortPage.ordenarListaCrescente();
    }

    @Então("valido que os elementos estão na ordem correta")
    public void valido_a_ordem() {
        List<WebElement> itens = sortPage.obterItensDaLista();
        String[] ordemEsperada = {"One", "Two", "Three", "Four", "Five", "Six"};

        for (int i = 0; i < ordemEsperada.length; i++) {
            assertEquals(ordemEsperada[i], itens.get(i).getText(), "O item na posição " + i + " está incorreto!");
        }
    }
    @Quando("ordeno os elementos da lista na ordem decrescente")
    public void ordeno_os_elementos_decrescente() {
        sortPage.ordenarListaDecrescente();
    }

    @Então("valido que os elementos estão na ordem {string} para {string}")
    public void valido_a_ordem_decrescente(String primeiro, String ultimo) {
        List<WebElement> itens = sortPage.obterItensDaLista();
        String[] ordemEsperada = {"Six", "Five", "Four", "Three", "Two", "One"};

        for (int i = 0; i < ordemEsperada.length; i++) {
            assertEquals(ordemEsperada[i], itens.get(i).getText(),
                    "Erro na posição " + i + ". Esperado: " + ordemEsperada[i] + " mas veio: " + itens.get(i).getText());
        }
    }
}