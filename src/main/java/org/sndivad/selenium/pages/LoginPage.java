package org.sndivad.selenium.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
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
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    //@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Iniciar sesión\"]")
    //@iOSXCUITFindBy(accessibility = "aqui iria el id del elemento de ios") ejemplo que añado en caso de que fuera app nativa, lo haria de esta manera
    //private WebElement ejemploButtonNativo;


    @FindBy(id = "hea-lin-menu-mob")
    private WebElement burgerButton;


    @FindBy(id = "hea-lin-login-mob")
    private WebElement loginButton;


    @FindBy(id = "log-for-user")
    private WebElement emailInput;


    @FindBy(id = "log-for-password")
    private WebElement passwordInput;


    @FindBy(id = "log-but-login")
    private WebElement loginSubmitButton;

    @FindBy(css = "[data-testid='login-error-message']")
    private WebElement loginErrorMessage;


    public void open() {
        driver.get(PropertyReader.get("base.url"));
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void tapBurgerButton() {
        wait.until(ExpectedConditions.elementToBeClickable(burgerButton)).click();
    }

    public void tapLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isLoginFormDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(emailInput)).isDisplayed();
    }

    public void typeEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);
    }

    public void typePassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
    }

    public void submit() {
        wait.until(ExpectedConditions.elementToBeClickable(loginSubmitButton)).click();
    }

    public boolean isErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(loginErrorMessage)).isDisplayed();
    }

}
