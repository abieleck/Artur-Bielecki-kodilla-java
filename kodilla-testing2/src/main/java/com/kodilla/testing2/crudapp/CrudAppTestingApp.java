package com.kodilla.testing2.crudapp;

import com.kodilla.testing2.config.SystemNotSupportedException;
import com.kodilla.testing2.config.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URISyntaxException;

public class CrudAppTestingApp {
    public static final String XPATH_INPUT =
            "//html/body/app-root/mat-card/mat-card-content/mat-form-field/div/div/div/input";
    public static final String XPATH_TEXTAREA =
            "//html/body/app-root/mat-card/mat-card-content/mat-form-field/div/div/div/textarea";
    public static final String XPATH_SELECT =
            "//div[contains(@class,\"task-row\")]/mat-grid-tile/figure/mat-select";
    //"//html/body/app-root/mat-card/mat-card-content/mat-grid-list/div/div/mat-grid-tile/figure/mat-select";
    public static final String XPATH_SELECTION =
            "//mat-option[2]";
    public static void main(String[] args) throws SystemNotSupportedException {
        WebDriver driver = WebDriverConfig.getDriver();
        driver.get("https://abieleck.github.io/");

        WebElement inputField = driver.findElement(By.xpath(XPATH_INPUT));
        inputField.sendKeys("New robotic task");

        WebElement textareaField = driver.findElement(By.xpath(XPATH_TEXTAREA));
        textareaField.sendKeys("The robotic content");

        WebDriverWait wait = new WebDriverWait(driver, 120);

        WebElement selectCombo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_SELECT)));
        selectCombo.click();

        WebElement selection = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_SELECTION)));
        selection.click();
        /*Select selectBoard = new Select(selectCombo);
        selectBoard.selectByIndex(1);*/
    }
}
