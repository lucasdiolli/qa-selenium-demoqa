package br.com.qa.demo.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class WebTablesPage {
    WebDriver driver;

    public WebTablesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clicarBotaoAdd() {
        driver.findElement(By.id("addNewRecordButton")).click();
    }

    public void preencherRegistro(String nome, String sobrenome, String email, String idade, String salario, String departamento) {
        driver.findElement(By.id("firstName")).sendKeys(nome);
        driver.findElement(By.id("lastName")).sendKeys(sobrenome);
        driver.findElement(By.id("userEmail")).sendKeys(email);
        driver.findElement(By.id("age")).sendKeys(idade);
        driver.findElement(By.id("salary")).sendKeys(salario);
        driver.findElement(By.id("department")).sendKeys(departamento);
        driver.findElement(By.id("submit")).click();
    }

    public void mostrar20Linhas() {
        // Garante que os 12 registros apareçam na mesma página para o delete funcionar
        WebElement selectElement = driver.findElement(By.xpath("//select[@aria-label='rows per page']"));
        Select select = new Select(selectElement);
        select.selectByValue("20");
    }

    public void deletarTodosRegistros() {
        mostrar20Linhas();
        while (true) {
            try {
                var buttons = driver.findElements(By.xpath("//span[@title='Delete']"));
                if (buttons.isEmpty()) break;

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", buttons.get(0));
            } catch (Exception e) {
                break;
            }
        }
    }

    public void editarUltimoRegistro(String novoNome) {
        // Clica no ícone de edição do primeiro registro da lista
        driver.findElement(By.xpath("(//span[@title='Edit'])[1]")).click();
        WebElement campoNome = driver.findElement(By.id("firstName"));
        campoNome.clear();
        campoNome.sendKeys(novoNome);
        driver.findElement(By.id("submit")).click();
    }

    public boolean tabelaEstaVazia() {
        try {
            return driver.findElement(By.className("rt-noData")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}