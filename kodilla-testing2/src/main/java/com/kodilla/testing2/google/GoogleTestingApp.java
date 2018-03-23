package com.kodilla.testing2.google;

import com.kodilla.testing2.config.SystemNotSupportedException;
import com.kodilla.testing2.config.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.URISyntaxException;

public class GoogleTestingApp {
    public static final String SEARCHFIELD = "lst-ib";
    public static void main(String[] args) throws SystemNotSupportedException {

        WebDriver driver = WebDriverConfig.getDriver();
        driver.get("https://www.google.com");
        WebElement searchField = driver.findElement(By.id(SEARCHFIELD));
        searchField.sendKeys("Kodilla");
        searchField.submit();
    }
}
