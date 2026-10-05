package org.sndivad.selenium.steps;

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

    @When("the home page should be displayed")
    public void the_home_page_should_be_displayed() {
        //homePage.isHomeDisplayed();
        Assert.assertTrue(homePage.isHomeDisplayed(), "Home not displayed");
    }


}
