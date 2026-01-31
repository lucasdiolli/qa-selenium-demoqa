package br.com.qa.demo.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

public class SortablePage {
    WebDriver driver;
    Actions actions;

    public SortablePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void ordenarListaCrescente() {

        String[] ordemCorreta = {"One", "Two", "Three", "Four", "Five", "Six"};

        for (int i = 0; i < ordemCorreta.length; i++) {
            // Localiza o item que deveria estar na posição 'i'
            WebElement itemParaMover = driver.findElement(By.xpath("//div[@id='demo-tabpane-list']//div[text()='" + ordemCorreta[i] + "']"));

            // Localiza o item que está atualmente na posição 'i'
            List<WebElement> itensAtuais = driver.findElements(By.xpath("//div[@id='demo-tabpane-list']//div[contains(@class, 'list-group-item')]"));
            WebElement alvo = itensAtuais.get(i);

            // Se o item já não estiver na posição correta, mover
            if (!itemParaMover.equals(alvo)) {
                actions.dragAndDrop(itemParaMover, alvo).perform();
            }
        }
    }

    public void ordenarListaDecrescente() {
        String[] ordemDecrescente = {"Six", "Five", "Four", "Three", "Two", "One"};

        // Localiza a lista inteira ou o primeiro item para dar o foco
        WebElement lista = driver.findElement(By.id("demo-tabpane-list"));

        // Centraliza a lista na tela
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", lista);

        // pausa para garantir que o scroll terminou e a tela estabilizou
        try { Thread.sleep(500); } catch (InterruptedException e) {}

        String[] ordem = {"One", "Two", "Three", "Four", "Five", "Six"};

        for (int i = 0; i < ordemDecrescente.length; i++) {
            // Localiza o item que devo colocar na posição atual 'i'
            WebElement itemParaMover = driver.findElement(
                    By.xpath("//div[@id='demo-tabpane-list']//div[text()='" + ordemDecrescente[i] + "']")
            );

            // Localiza quem está ocupando a posição 'i' agora
            List<WebElement> itensAtuais = driver.findElements(
                    By.xpath("//div[@id='demo-tabpane-list']//div[contains(@class, 'list-group-item')]")
            );
            WebElement alvo = itensAtuais.get(i);

            // Executa o dragAndDrop se já não estiver no lugar
            if (!itemParaMover.getText().equals(alvo.getText())) {
                actions.dragAndDrop(itemParaMover, alvo).perform();
                try { Thread.sleep(200); } catch (InterruptedException e) {}
            }
        }
    }
    public List<WebElement> obterItensDaLista() {
        return driver.findElements(By.xpath("//div[@id='demo-tabpane-list']//div[contains(@class, 'list-group-item')]"));
    }
}