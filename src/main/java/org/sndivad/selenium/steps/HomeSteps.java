package org.sndivad.selenium.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.sndivad.selenium.config.DriverManager;
import org.sndivad.selenium.pages.HomePage;
import org.sndivad.selenium.pages.LoginPage;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import org.testng.Assert;

public class HomeSteps {

    private HomePage homePage;
    private LoginPage loginPage;

    public HomeSteps() throws MalformedURLException, URISyntaxException, InterruptedException {
        homePage = new HomePage(DriverManager.getDriver());
        loginPage = new LoginPage(DriverManager.getDriver());
    }

    @Then("the user should be redirected to the home screen")
    public void the_user_should_be_redirected_to_the_home_screen() {
        Assert.assertTrue(homePage.isHomeDisplayed(), "Home is not displayed");
    }



}
