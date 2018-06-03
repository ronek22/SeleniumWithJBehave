import PageObject.PageObjectAuthorCreate;
import PageObject.PageObjectLogin;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.jbehave.core.steps.Steps;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AuthorSteps extends Steps {
    private static WebDriver driver;
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
        driver.quit();
    }

    @When("Dodaje autora z nazwiskiem z malych liter")
    public void addWrongLastNameAuthor(){
        driver.get("http://bookcatalog.azurewebsites.net/Authors/Create");
        PageObjectAuthorCreate authorCreate = PageFactory.initElements(driver, PageObjectAuthorCreate.class);
        authorCreate.createAuthor("Jakub", "nowy");
    }

    @Then("Zobacze informacje o blednym nazwisku")
    public void getInfoAboutWrongLastName(){
        PageObjectAuthorCreate authorCreate = PageFactory.initElements(driver, PageObjectAuthorCreate.class);
        authorCreate.assertValidationError("Last Name must start with capital letter");
        driver.quit();
    }

    @When("Dodaje autora z imieniem i nazwiskiem z malych liter")
    public void addWrongFirstAndLastNameAuthor(){
        driver.get("http://bookcatalog.azurewebsites.net/Authors/Create");
        PageObjectAuthorCreate authorCreate = PageFactory.initElements(driver, PageObjectAuthorCreate.class);
        authorCreate.createAuthor("jakub", "nowy");
    }

    @Then("Zobacze informacje o problemach z walidacja")
    public void getInfoAboutValidationsAuthorProblems(){
        assertEquals(2, driver.findElements(By.className("text-danger")).size());
        driver.quit();
    }


    private void login(String login, String pass) throws Exception{
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        PageObjectLogin loginPage = PageFactory.initElements(driver, PageObjectLogin.class);
        loginPage.login(login, pass);
    }
}
