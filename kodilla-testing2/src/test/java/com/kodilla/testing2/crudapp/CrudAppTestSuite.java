package com.kodilla.testing2.crudapp;

import com.kodilla.testing2.config.WebDriverConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class CrudAppTestSuite {
    private static final String BASE_URL = "https://abieleck.github.io/";
    private WebDriver driver;
    private Random generator;

    @Before
    public void initTests() {
        driver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driver.get(BASE_URL);
        generator = new Random();
    }

    @After
    public void cleanUpAfterTest() {
        driver.close();
    }

    private String createCrudAppTestTask() throws InterruptedException {
        final String XPATH_TASK_NAME = "//*[@id=\"mat-input-0\"]";
        final String XPATH_TASK_CONTENT = "//*[@id=\"mat-input-1\"]";
        final String XPATH_ADD_BUTTON = "//mat-card[contains(@class, \"card-add-new\")]//button";

        String taskName = "Task number " + generator.nextInt(100000);
        String taskContent = taskName + " content";

        WebElement name = driver.findElement(By.xpath(XPATH_TASK_NAME));
        name.sendKeys(taskName);

        WebElement content = driver.findElement(By.xpath(XPATH_TASK_CONTENT));
        content.sendKeys(taskContent);

        WebElement addButton = driver.findElement(By.xpath(XPATH_ADD_BUTTON));
        addButton.click();
        Thread.sleep(2000);

        return taskName;
    }

    private void sendTestTaskToTrello(String taskName) throws InterruptedException {
        driver.navigate().refresh();

        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//mat-select[1]")));

        driver.findElements(By.xpath("//div[contains(@class, \"task-row\")]")).stream()
                .filter(anyForm ->
                    anyForm.findElement(By.xpath(".//input[contains(@class, \"input-tasks-name\")]"))
                            .getAttribute("value").equals(taskName))
                .forEach(theForm -> {
                    WebElement selectBoard = theForm.findElement(By.xpath(".//mat-select[1]"));
                    selectBoard.click();
                    WebElement boardSelection = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//mat-option[2]")));
                    boardSelection.click();
                    wait.until(ExpectedConditions.invisibilityOf(boardSelection));

                    WebElement selectList = theForm.findElement(By.xpath(".//mat-select[contains(@class,\"select-list\")]"));
                    selectList.click();
                    WebElement listSelection = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//mat-option[2]")));
                    listSelection.click();
                    wait.until(ExpectedConditions.invisibilityOf(listSelection));

                    WebElement buttonCreateCard =
                            theForm.findElement(By.xpath(".//button[contains(@class, \"trello\")]"));
                    buttonCreateCard.click();

                    WebElement buttonDeleteTask =
                            theForm.findElement(By.xpath(".//button[contains(@class,\"button-task-delete\")]"));
                    buttonDeleteTask.click();
                });
        Thread.sleep(5000);

    }

    private boolean checkTaskExistsInTrello(String taskName) throws InterruptedException {
        final String TRELLO_URL = "https://trello.com/login";
        boolean result = false;

        WebDriver driverTrello = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driverTrello.get(TRELLO_URL);

        driverTrello.findElement(By.id("user")).sendKeys("username");
        driverTrello.findElement(By.id("password")).sendKeys("password");
        driverTrello.findElement(By.id("login")).submit();

        Thread.sleep(2000);

        driverTrello.findElements(By.xpath("//a[@class=\"board-tile\"]")).stream()
                .filter(aHref -> aHref.findElements(By.xpath(".//span[@title=\"Kodilla Application\"]")).size() > 0)
                .forEach(aHref -> aHref.click());

        Thread.sleep(2000);

        result = driverTrello.findElements(By.xpath("//span")).stream()
                .filter(theSpan -> theSpan.getText().contains(taskName))
                .collect(Collectors.toList())
                .size() > 0;

        driverTrello.close();

        return result;

    }

    @Test
    public void shouldCreateTrelloCard() throws InterruptedException {
        String taskName = createCrudAppTestTask();
        sendTestTaskToTrello(taskName);
        assertTrue(checkTaskExistsInTrello(taskName));
    }
}
