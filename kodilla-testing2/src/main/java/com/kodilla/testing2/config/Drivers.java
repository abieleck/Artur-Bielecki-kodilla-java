package com.kodilla.testing2.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.function.Supplier;

public enum Drivers {
    CHROME("chrome", "chromedriver", ChromeDriver::new),
    FIREFOX("gecko", "geckodriver", FirefoxDriver::new),
    SAFARI("safari", "SafariDriver.safariextz", SafariDriver::new);

    private static final String WIN_FILE_EXTENSION = ".exe";

    private String name;
    private String fileName;
    private Supplier<WebDriver> creator;

    private static String getSystemInfo() throws SystemNotSupportedException {
        String system = System.getProperty("os.name").toLowerCase();
        if (system.contains("windows")) {
            system = "windows";
        } else if (system.contains("linux")) {
            system = "linux";
        } else if (system.contains("mac")) {
            system = "macos";
        } else {
            throw new SystemNotSupportedException(system);
        }
        String architecture = System.getProperty("os.arch");
        if(architecture.contains("64") && !system.equals("macos")) {
            system += "64";
        } else {
            system += "32";
        }
        return system;
    }

    public WebDriver getDriver() throws SystemNotSupportedException {
        String system = getSystemInfo();
        String driverPath = "webdriver/" + system + "/" + fileName;
        if (system.contains("windows")) {
            driverPath += WIN_FILE_EXTENSION;
        }
        String absolutePath = null;
        try {
            absolutePath = this.getClass().getClassLoader().getResource(driverPath).getPath();
        } catch (NullPointerException e) {
            throw new SystemNotSupportedException(system);
        }
        System.setProperty("webdriver." + name + ".driver", absolutePath);
        return creator.get();

    }

    Drivers(String name, String fileName, Supplier<WebDriver> creator) {
        this.name = name;
        this.fileName = fileName;
        this.creator = creator;
    }
}
