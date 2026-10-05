package org.sndivad.selenium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class AppiumTest {

    @Test
    public void AppiumTest2() throws URISyntaxException, MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
       // options.setDeviceName("988d91315a594e545130");
        //options.setAppPackage("com.android.chrome");
        //options.setAppActivity("com.google.android.apps.chrome.Main");

        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        //options.setUdid("988d91315a594e545130");
        options.setUdid("emulator-5554");
        options.setCapability("browserName", "Chrome");
        //options.setCapability("appium:chromedriverAutodownload", true);

        AndroidDriver driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
        driver.get("https://www.marca.com");
        System.out.println(driver.getTitle());
        driver.quit();
    }
}
