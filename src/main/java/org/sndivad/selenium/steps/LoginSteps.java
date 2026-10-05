package org.sndivad.selenium.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.sndivad.selenium.config.DriverManager;
import org.sndivad.selenium.pages.HomePage;
import org.sndivad.selenium.pages.LoginPage;
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

    @Given("The User is on the login page")
    public void the_user_is_on_the_login_page() {
        loginPage.open();
        System.out.println("Abrimos la pagina: " + loginPage.getTitle());
    }

    @When("the user press the burger button")
    public void the_user_press_the_burger_button() {
        loginPage.tapBurgerButton();
        System.out.println("Hacemos click en burger button");
    }

    @And("the user press log in button")
    public void the_user_press_log_in_button() {
        loginPage.tapLoginButton();
        System.out.println("Hacemos click en login button");
    }
    @Then("The form login is displayed")
    public void the_form_login_is_displayed() {
        //loginPage.isLoginFormDisplayed();
        Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Login panel is not displayed");
    }
    @When("the user enters a valid email")
    public void the_user_enters_a_valid_email() {
        loginPage.typeEmail("sndivad@gmail.com");
        //loginPage.typeEmail(System.getProperty("user.email"));
        System.out.println("Introducimos el usuario");
    }
    @When("the user enters a valid password")
    public void the_user_enters_a_valid_password() {
        loginPage.typePassword("David@123");
        //loginPage.typePassword(System.getProperty("user.password"));
        System.out.println("Introducimos la contraseña");
    }
    @When("the user tap log in button")
    public void the_user_tap_log_in_button() {
        loginPage.submit();
    }

    @When("the user enters an invalid password {string}")
    public void the_user_enters_an_invalid_password(String invalidPass) {
        loginPage.typePassword(invalidPass);
    }
    @When("invalid password or user should be displayed")
    public void invalid_password_or_user_should_be_displayed() {
        //loginPage.isErrorDisplayed();
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error login is not displayed");
    }

}
