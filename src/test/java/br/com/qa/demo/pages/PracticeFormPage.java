package br.com.qa.demo.pages;

import org.openqa.selenium.*;

public class PracticeFormPage {
    private WebDriver driver;
    String path = System.getProperty("user.dir") + "/src/test/resources/qa_test.txt";

    public PracticeFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public void criarRegistro(String nome, String sobrenome, String email, String telefone, String endereco) {
        driver.findElement(By.id("firstName")).sendKeys(nome);
        driver.findElement(By.id("lastName")).sendKeys(sobrenome);
        driver.findElement(By.id("userEmail")).sendKeys(email);
        driver.findElement(By.xpath("//label[@for='gender-radio-1']")).click();
        driver.findElement(By.id("userNumber")).sendKeys(telefone);
        driver.findElement(By.id("subjectsInput")).sendKeys("Computer Science");
        driver.findElement(By.id("subjectsInput")).sendKeys(Keys.ENTER);

        WebElement hobbiesCheckbox = driver.findElement(By.xpath("//label[@for='hobbies-checkbox-1']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", hobbiesCheckbox);
        hobbiesCheckbox.click();
        driver.findElement(By.id("uploadPicture")).sendKeys(path);
        driver.findElement(By.id("currentAddress")).sendKeys(endereco);
        driver.findElement(By.id("react-select-3-input")).sendKeys("NCR");
        driver.findElement(By.id("react-select-3-input")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("react-select-4-input")).sendKeys("Delhi");
        driver.findElement(By.id("react-select-4-input")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("submit")).click();
    }

    public boolean validarPopupExibido() {
        return driver.findElement(By.id("example-modal-sizes-title-lg")).isDisplayed();
    }

    public void fecharPopup() {
        driver.findElement(By.id("closeLargeModal")).click();
    }
}
