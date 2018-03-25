package com.kodilla.testing2.facebook;

import com.kodilla.testing2.config.Drivers;
import com.kodilla.testing2.config.SystemNotSupportedException;
import com.kodilla.testing2.config.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URISyntaxException;
import java.time.LocalDate;

public class FacebookTestingApp {
    public static final LocalDate birthDate = LocalDate.of(1975, 8, 20);
    public static final String XPATH_SELECT_DAY =
            "//span[@data-name=\"birthday_wrapper\"]/span/select[@id=\"day\"]";
    public static final String XPATH_SELECT_MONTH =
            "//span[@data-name=\"birthday_wrapper\"]/span/select[@id=\"month\"]";
    public static final String XPATH_SELECT_YEAR =
            "//span[@data-name=\"birthday_wrapper\"]/span/select[@id=\"year\"]";

    public static void main(String[] args) throws SystemNotSupportedException {
        final Drivers driverBrowser = Drivers.CHROME;

        WebDriver driver = WebDriverConfig.getDriver(driverBrowser);
        driver.get("https://www.facebook.com/");

        WebElement selectDayElement = driver.findElement(By.xpath(XPATH_SELECT_DAY));
        Select selectDay = new Select(selectDayElement);
        selectDay.selectByValue(String.valueOf(birthDate.getDayOfMonth()));

        WebElement selectMonthElement = driver.findElement(By.xpath(XPATH_SELECT_MONTH));
        Select selectMonth = new Select(selectMonthElement);
        selectMonth.selectByValue(String.valueOf(birthDate.getMonthValue()));

        WebElement selectYearElement = driver.findElement(By.xpath(XPATH_SELECT_YEAR));
        Select selectYear = new Select(selectYearElement);
        selectYear.selectByValue(String.valueOf(birthDate.getYear()));

    }
}
