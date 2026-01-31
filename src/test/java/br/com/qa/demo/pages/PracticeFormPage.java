package br.com.qa.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PracticeFormPage {

    private WebDriver driver;

    public PracticeFormPage(WebDriver driver) {
        this.driver = driver;
    }

    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By emailField = By.id("userEmail");
    private By mobileField = By.id("userNumber");
    private By addressField = By.id("currentAddress");
    private By submitButton = By.id("submit");
    private By modalDialog = By.id("example-modal-sizes-title-lg");
    private By closeButton = By.id("closeLargeModal");

    public void criarRegistro(String firstName, String lastName, String email, String mobile, String address) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(mobileField).sendKeys(mobile);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(submitButton).click();
    }

    public boolean validarPopupExibido() {
        return driver.findElement(modalDialog).isDisplayed();
    }

    public void fecharPopup() {
        driver.findElement(closeButton).click();
    }
}
