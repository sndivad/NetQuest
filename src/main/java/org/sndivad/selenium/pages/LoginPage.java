package org.sndivad.selenium.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sndivad.selenium.utils.PropertyReader;

import java.time.Duration;

public class LoginPage {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    }


    //@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Iniciar sesión\"]")
    //@iOSXCUITFindBy(accessibility = "aqui iria el id del elemento de ios") ejemplo que añado en caso de que fuera app nativa, lo haria de esta manera
    //private WebElement ejemploButtonNativo;

    //SPLASH SCREEN ELEMENTS

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"onboarding-skip-button\")")   // content-desc
    @iOSXCUITFindBy(accessibility = "TODO_login_entry")  // accessibilityIdentifier / label
    private WebElement skipButton;


    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"already-account-button\")")
    @iOSXCUITFindBy(accessibility = "TODO_login_entry")
    private WebElement alreadyHaveAccountButton;


    //LOGIN SCREEN ELEMENTS

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"sign-in-email-input\")")
    @iOSXCUITFindBy(accessibility = "TODO_login_entry")
    private WebElement emailInput;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"sign-in-password-input\")")
    @iOSXCUITFindBy(accessibility = "TODO_login_entry")
    private WebElement passwordInput;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"sign-in-button\")")
    @iOSXCUITFindBy(accessibility = "TODO_login_entry")
    private WebElement loginButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Nombre de usuario y / o contraseña incorrectos\")")
    @iOSXCUITFindBy(accessibility = "TODO_login_entry")
    private WebElement loginErrorMessage;


    //splash screen
    public void tapSkipButton(){
        wait.until(ExpectedConditions.elementToBeClickable(skipButton)).click();
    }

    public void tapAlreadyHaveAccountButton(){
        wait.until(ExpectedConditions.elementToBeClickable(alreadyHaveAccountButton)).click();
    }


    //login screen
    public void typeEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);
    }

    public void typePassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
    }

    public void tapLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isLoginFormDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(emailInput)).isDisplayed();
    }

    public boolean isErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(loginErrorMessage)).isDisplayed();
    }

}
