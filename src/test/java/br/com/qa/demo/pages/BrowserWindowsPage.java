package br.com.qa.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class BrowserWindowsPage {

    private WebDriver driver;
    private String originalWindow;

    public BrowserWindowsPage(WebDriver driver) {
        this.driver = driver;
    }

    private By newWindowButton = By.id("windowButton");
    private By sampleHeading = By.id("sampleHeading");

    public void clickNewWindowButton() {
        originalWindow = driver.getWindowHandle();
        driver.findElement(newWindowButton).click();
    }

    public boolean isNewWindowOpenedWithMessage(String message) {
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        windowHandles.remove(originalWindow);
        if (windowHandles.isEmpty()) {
            return false;
        }
        driver.switchTo().window(windowHandles.get(0));
        return driver.findElement(sampleHeading).getText().equals(message);
    }

    public void closeNewWindow() {
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        if (windowHandles.size() > 1) {
            windowHandles.remove(originalWindow);
            driver.switchTo().window(windowHandles.get(0));
            driver.close();
        }
        driver.switchTo().window(originalWindow);
    }
}
