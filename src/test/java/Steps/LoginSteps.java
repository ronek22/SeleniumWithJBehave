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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginSteps extends Steps {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Given("Mam webdrivera")
    public void givenIHaveWebdriverWithDefaultSite(){
        System.setProperty("webdriver.geckordriver.driver", "resources/geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 30);
    }

    @When("Zaloguje się na konto: <login> z hasłem: <password>")
    public void SignInDataTest(@Named("login") String login, @Named("password") String password) throws Exception{
        PageObjectLogin loginPage = PageFactory.initElements(driver, PageObjectLogin.class);
        loginPage.login(login, password);
    }

    @Then("Zobaczę <message> na stronie głównej")
    public void checkLogin(@Named("message") String message){
        wait.until(ExpectedConditions.elementToBeClickable(By.id("UserName")));
        assertTrue(driver.findElement(By.id("UserName")).getText().equals(message));
        driver.close();
    }

    @When("Logowanie na nieistniejace konto")
    public void SignInWithIncorrectData() throws Exception{
        PageObjectLogin loginPage = PageFactory.initElements(driver, PageObjectLogin.class);
        loginPage.login("notuser@mail.com", "sasa");
    }

    @Then("Zostane na stronie logowania")
    public void StayOnLoginSiteAfterFailedLogin(){
        wait.until(ExpectedConditions.urlToBe("http://bookcatalog.azurewebsites.net/Account/Login"));
        assertEquals("http://bookcatalog.azurewebsites.net/Account/Login", driver.getCurrentUrl());
        driver.close();
    }


}
