package ui.baseSettings;

//Установка настроек браузера с использованием паттерна Singleton

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


public class DriverSingleton {
    private static DriverSingleton driverSingleton = null;

    protected DriverSingleton() {
    }

    public static DriverSingleton getInstance() {
        if(driverSingleton == null)
            driverSingleton = new DriverSingleton();
        return driverSingleton;
    }

    @BeforeEach
    public void setUp() {
        getInstance();
        Configuration.browser = "chrome";
        Configuration.browserSize = "1980x1080";
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
