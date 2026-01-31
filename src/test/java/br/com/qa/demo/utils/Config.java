package br.com.qa.demo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Config {
    public static final int STEP_DELAY = 1000; // Tempo em milissegundos
    public static WebDriver getDriver() { ChromeOptions options = new ChromeOptions(); options.addArguments("--start-maximized"); WebDriver driver = new ChromeDriver(options); return driver; }
}
