package org.sndivad.selenium.steps;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.sndivad.selenium.config.DriverManager;

public class Hooks {

    @Before
    public void setUp() {
        DriverManager.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                scenario.attach(takeScreenshot(), "image/png", "Fallo");
            }
        } catch (Exception e) {
            System.err.println("No se pudo capturar la pantalla: " + e.getMessage());
        } finally {
            DriverManager.quitDriver();
        }
    }

    private byte[] takeScreenshot() {
        AndroidDriver driver = (AndroidDriver) DriverManager.getDriver();
        String previousContext = driver.getContext();
        try {
            driver.context("NATIVE_APP");
            return driver.getScreenshotAs(OutputType.BYTES);
        } finally {
            driver.context(previousContext);
        }
    }
}
