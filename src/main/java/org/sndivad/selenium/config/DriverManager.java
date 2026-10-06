package org.sndivad.selenium.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.sndivad.selenium.utils.PropertyReader;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class DriverManager {

    private static AppiumDriver driver;

    public static AppiumDriver getDriver() {
        if (driver == null) {
            String platform = System.getProperty("platform", "android");
            try {
                URL appiumUrl = new URI(PropertyReader.get("appium.url")).toURL();

                switch (platform) {
                    case "android" -> {
                        UiAutomator2Options androidOptions = new UiAutomator2Options();

                        androidOptions.setUdid(PropertyReader.get("android.udid"));
                        androidOptions.setAppPackage(PropertyReader.get("android.appPackage"));
                        androidOptions.setAppActivity(PropertyReader.get("android.appActivity"));
                        androidOptions.setAutoGrantPermissions(true);
                        androidOptions.setNoReset(false);
                        androidOptions.setNewCommandTimeout(Duration.ofSeconds(120));


                        //androidOptions.setCapability("browserName", "Chrome");
                        //androidOptions.setChromeOptions(Map.of("args", List.of("--no-first-run","--disable-fre","--no-default-browser-check")));
                        driver = new AndroidDriver(appiumUrl, androidOptions);
                    }
                    case "ios" -> {
                        XCUITestOptions options = new XCUITestOptions();

                        options.setDeviceName("iPhone 15 Plus");
                        options.setApp("//Users//davidnavarro//Library//Developer//Xcode//DerivedData//Runner-datsrpryvgrnprchkjgjkqnxrlzb//Build//Products//Debug-iphonesimulator//Runner.app");
                        options.setPlatformVersion("17.5");
                        options.setPlatformName("iOS");
                        options.setAutomationName("XCUITest");
                        //options.setUdid("FFXD3DN9PLJQ");
                        options.setWdaLaunchTimeout(Duration.ofSeconds(20));
                        driver = new IOSDriver(appiumUrl, options);
                    }
                    default -> throw new IllegalArgumentException("Plataforma no soportada: " + platform);
                }
            } catch (MalformedURLException | URISyntaxException e) {
                throw new RuntimeException("URL de Appium inválida", e);
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}