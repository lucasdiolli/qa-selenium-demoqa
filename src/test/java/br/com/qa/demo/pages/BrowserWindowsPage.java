package br.com.qa.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Set;

public class BrowserWindowsPage {
    private WebDriver driver;
    private String originalWindow;

    public BrowserWindowsPage(WebDriver driver) {
        this.driver = driver;
        this.originalWindow = driver.getWindowHandle();
    }

    public void clickNewWindowButton() {
        driver.findElement(By.id("windowButton")).click();
    }

    public boolean isNewWindowOpenedWithMessage(String expectedMessage) {
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                String bodyText = driver.findElement(By.tagName("body")).getText();
                return bodyText.contains(expectedMessage);
            }
        }
        return false;
    }

    public void closeNewWindow() {
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                driver.close();
                driver.switchTo().window(originalWindow);
                break;
            }
        }
    }
}
