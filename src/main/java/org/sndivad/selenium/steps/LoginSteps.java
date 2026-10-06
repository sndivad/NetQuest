package org.sndivad.selenium.steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.sndivad.selenium.config.DriverManager;
import org.sndivad.selenium.pages.HomePage;
import org.sndivad.selenium.pages.LoginPage;
import org.sndivad.selenium.utils.PropertyReader;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URISyntaxException;


public class LoginSteps {

    private LoginPage loginPage;
    private HomePage homePage;

    public LoginSteps() throws MalformedURLException, URISyntaxException, InterruptedException {
        loginPage = new LoginPage(DriverManager.getDriver());
        homePage = new HomePage(DriverManager.getDriver());
    }


    @Given("the app is launched")
    public void the_app_is_launched() {
        System.out.println("App lanzada");
        AppiumDriver driver = DriverManager.getDriver();
        Assert.assertEquals(((AndroidDriver) driver).getCurrentPackage(), PropertyReader.get("android.appPackage"));
    }
    @And("the user skips the splash screen")
    public void the_user_skips_the_splash_screen() {
        loginPage.tapSkipButton();
    }
    @And("the user selects \"Ya tengo una cuenta\"")
    public void the_user_selects_Ya_tengo_una_cuenta() {
        loginPage.tapAlreadyHaveAccountButton();
    }

    @And("the form login is displayed")
    public void the_form_login_is_displayed() {
        Assert.assertTrue(loginPage.isLoginFormDisplayed());
    }

    @When("the user enters a valid email")
    public void the_user_enters_a_valid_email() {
        //loginPage.typeEmail("tuemail");
        loginPage.typeEmail(System.getProperty("user.email"));
    }
    @When("the user enters a valid password")
    public void the_user_enters_a_valid_password() {
        //loginPage.typePassword("tupassword");
        loginPage.typePassword(System.getProperty("user.password"));
    }

    @When("the user taps on \"Iniciar sesión\"")
    public void the_user_taps_on_Iniciar_sesión() {
        loginPage.tapLoginButton();
    }

    @When("the user enters an incorrect password {string}")
    public void the_user_enters_an_incorrect_password(String pass) {
        loginPage.typePassword(pass);
    }
    @Then("the user should see an incorrect password error message")
    public void the_user_should_see_an_incorrect_password_error_message() {
        Assert.assertTrue(loginPage.isErrorDisplayed());

    }



}
