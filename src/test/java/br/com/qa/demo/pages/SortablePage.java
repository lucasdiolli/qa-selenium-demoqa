package br.com.qa.demo.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class SortablePage {
    WebDriver driver;
    Actions actions;

    public SortablePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void ordenarListaCrescente() {
        // Nomes dos itens na ordem que queremos: One, Two, Three, Four, Five, Six
        String[] ordemCorreta = {"One", "Two", "Three", "Four", "Five", "Six"};

        for (int i = 0; i < ordemCorreta.length; i++) {
            // Localiza o item que deveria estar na posição 'i'
            WebElement itemParaMover = driver.findElement(By.xpath("//div[@id='demo-tabpane-list']//div[text()='" + ordemCorreta[i] + "']"));

            // Localiza o item que está atualmente na posição 'i' para servir de alvo
            List<WebElement> itensAtuais = driver.findElements(By.xpath("//div[@id='demo-tabpane-list']//div[contains(@class, 'list-group-item')]"));
            WebElement alvo = itensAtuais.get(i);

            // Se o item já não estiver na posição correta, movemos
            if (!itemParaMover.equals(alvo)) {
                actions.dragAndDrop(itemParaMover, alvo).perform();
            }
        }
    }

    public void ordenarListaDecrescente() {
        // Ordem desejada: Six, Five, Four, Three, Two, One
        String[] ordemDecrescente = {"Six", "Five", "Four", "Three", "Two", "One"};

        for (int i = 0; i < ordemDecrescente.length; i++) {
            // Localiza o item que queremos colocar na posição atual 'i'
            WebElement itemParaMover = driver.findElement(
                    By.xpath("//div[@id='demo-tabpane-list']//div[text()='" + ordemDecrescente[i] + "']")
            );

            // Localiza quem está ocupando a posição 'i' agora
            List<WebElement> itensAtuais = driver.findElements(
                    By.xpath("//div[@id='demo-tabpane-list']//div[contains(@class, 'list-group-item')]")
            );
            WebElement alvo = itensAtuais.get(i);

            // Executa o arrastar e soltar se já não estiver no lugar
            if (!itemParaMover.getText().equals(alvo.getText())) {
                actions.dragAndDrop(itemParaMover, alvo).perform();
                // Pequena pausa para o DOM processar a troca de lugar (opcional, mas evita erros no Mac)
                try { Thread.sleep(200); } catch (InterruptedException e) {}
            }
        }
    }
    public List<WebElement> obterItensDaLista() {
        return driver.findElements(By.xpath("//div[@id='demo-tabpane-list']//div[contains(@class, 'list-group-item')]"));
    }
}