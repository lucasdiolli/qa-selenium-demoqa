package br.com.qa.demo.steps;

import br.com.qa.demo.pages.WebTablesPage;
import io.cucumber.java.pt.*;
import org.openqa.selenium.*;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebTablesSteps {
    WebDriver driver = Hooks.driver;
    WebTablesPage webPage = new WebTablesPage(driver);

    @Quando("escolho a opcao Elements na pagina inicial")
    public void escolho_a_opcao_elements() {
        WebElement card = driver.findElement(By.xpath("//h5[text()='Elements']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", card);
        card.click();
    }

    @Quando("clico no submenu Web Tables")
    public void clico_no_submenu_web_tables() {
        WebElement menu = driver.findElement(By.xpath("//span[text()='Web Tables']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", menu);
    }

    @Quando("crio {int} novos registros dinâmicos")
    public void crio_registros_dinamicos(Integer quantidade) {
        for (int i = 1; i <= quantidade; i++) {
            webPage.clicarBotaoAdd();
            webPage.preencherRegistro("User"+i, "Teste", "user"+i+"@teste.com", "25", "5000", "TI");
        }
    }
    @Quando("edito o último registro criado para o nome {string}")
    public void edito_o_ultimo_registro(String novoNome) {
        webPage.editarUltimoRegistro(novoNome);
    }

    @Quando("deleto todos os registros criados")
    public void deleto_todos_os_registros() {
        webPage.deletarTodosRegistros();
    }

    @Entao("valido que a tabela está vazia ou sem os registros criados")
    public void valido_que_a_tabela_esta_vazia() {
        assertTrue(webPage.tabelaEstaVazia(), "A tabela deveria estar vazia, mas ainda contém registros!");
    }
}