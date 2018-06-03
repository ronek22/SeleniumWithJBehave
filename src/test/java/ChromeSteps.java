import PageObject.PageObjectAuthorCreate;
import PageObject.PageObjectLogin;
import static org.junit.Assert.*;

import PageObject.PageObjectNavigation;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class ChromeSteps extends Steps {
    private static WebDriver driver;
    private ExamplesTable table;

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

    // Scenario: Bledne dodawanie autora
    @Given("Zalogowany jako admin")
    public void loginAsAdmin() throws Exception{
        login("admin@admin.com", "admin1");
    }

    @When("Dodaje autora z imieniem z malych liter")
    public void addWrongFirstNameAuthor(){
        driver.get("http://bookcatalog.azurewebsites.net/Authors/Create");
        PageObjectAuthorCreate authorCreate = PageFactory.initElements(driver, PageObjectAuthorCreate.class);
        authorCreate.createAuthor("jakub", "Nowy");
    }

    @Then("Zobacze informacje o blednym imieniu")
    public void getInfoAboutWrongFirstName(){
        WebElement validation = driver.findElement(By.className("text-danger"));
        assertTrue(validation.getText().contains("First Name must start with capital letter"));
    }


    public void login(String login, String pass) throws Exception{
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        PageObjectLogin loginPage = PageFactory.initElements(driver, PageObjectLogin.class);
        loginPage.login(login, pass);
    }
}
