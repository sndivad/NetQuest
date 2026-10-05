package org.sndivad.selenium.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    public HomePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(xpath = "//div[@data-testid='nav-bar-profile-item']")
    private WebElement profileButton;


    public boolean isHomeDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(profileButton)).isDisplayed();
    }
}
