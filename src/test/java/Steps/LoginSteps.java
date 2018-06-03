package Steps;

import PageObject.PageObjectLogin;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginSteps extends Steps {
    private static WebDriver driver;

    @Given("Mam webdrivera")
    public void givenIHaveWebdriverWithDefaultSite(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @When("Zaloguje się na konto: <login> z hasłem: <password>")
    public void SignInDataTest(@Named("login") String login, @Named("password") String password) throws Exception{
        PageObjectLogin loginPage = PageFactory.initElements(driver, PageObjectLogin.class);
        loginPage.login(login, password);
    }

    @Then("Zobaczę <message> na stronie głównej")
    public void checkLogin(@Named("message") String message){
        assertTrue(driver.findElement(By.id("UserName")).getText().equals(message));
        driver.quit();
    }

    @When("Logowanie na nieistniejace konto")
    public void SignInWithIncorrectData() throws Exception{
        PageObjectLogin loginPage = PageFactory.initElements(driver, PageObjectLogin.class);
        loginPage.login("notuser@mail.com", "sasa");
    }

    @Then("Zostane na stronie logowania")
    public void StayOnLoginSiteAfterFailedLogin(){
        assertEquals("http://bookcatalog.azurewebsites.net/Account/Login", driver.getCurrentUrl());
        driver.quit();
    }


}
