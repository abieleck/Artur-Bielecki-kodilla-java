package com.kodilla.testing2.ebay;

import com.kodilla.testing2.config.Drivers;
import com.kodilla.testing2.config.SystemNotSupportedException;
import com.kodilla.testing2.config.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EBayTestingApp {
    public static final String SEARCHFIELD = "gh-ac";
    public static void main(String[] args) throws SystemNotSupportedException {

        WebDriver driver = WebDriverConfig.getDriver(Drivers.SAFARI);
        driver.get("https://www.ebay.com");
        WebElement searchField = driver.findElement(By.id(SEARCHFIELD));
        searchField.sendKeys("Laptop");
        searchField.submit();
    }
}
