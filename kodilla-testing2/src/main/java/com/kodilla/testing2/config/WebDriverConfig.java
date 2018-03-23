package com.kodilla.testing2.config;

import org.openqa.selenium.WebDriver;

public class WebDriverConfig {

    public static WebDriver getDriver() throws SystemNotSupportedException {
        WebDriver result = null;
        boolean isDriverFound = false;
        boolean isSystemSupported = false;
        SystemNotSupportedException systemNotSupportedException = null;
        Drivers[] drivers = Drivers.values();
        for(int i=0; i<drivers.length && !isDriverFound; i++) {
            try {
                result = drivers[i].getDriver();
                isSystemSupported = true;
                isDriverFound = result != null;
            } catch (SystemNotSupportedException e) {
                systemNotSupportedException = e;
            }
        }
        if (!isSystemSupported) {
            throw systemNotSupportedException;
        }
        return result;
    }

    public static WebDriver getDriver(Drivers driver) throws SystemNotSupportedException {
        return driver.getDriver();
    }

}
