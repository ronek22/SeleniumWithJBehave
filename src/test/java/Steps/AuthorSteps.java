package Steps;

import PageObject.PageObjectAuthor;
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
    private int sizeBefore, sizeAfter;

    // Scenario: Bledne dodawanie autora
    @Given("Zalogowany jako admin")
    public void loginAsAdmin() throws Exception{
        login("admin@admin.com", "admin1");
    }

    @Given("Zalogowany jako uzytkownik")
    public void loginAsUser() throws Exception{
        login("user@user.com", "user12");
    }

    @When("Dodaje autora z imieniem z malych liter")
    public void addWrongFirstNameAuthor(){
        PageObjectAuthor authorCreate = PageFactory.initElements(driver, PageObjectAuthor.class);
        authorCreate.toCreate();
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
        PageObjectAuthor authorCreate = PageFactory.initElements(driver, PageObjectAuthor.class);
        authorCreate.toCreate();
        authorCreate.createAuthor("Jakub", "nowy");
    }

    @Then("Zobacze informacje o blednym nazwisku")
    public void getInfoAboutWrongLastName(){
        PageObjectAuthor authorCreate = PageFactory.initElements(driver, PageObjectAuthor.class);
        authorCreate.assertValidationError("Last Name must start with capital letter");
        driver.quit();
    }

    @When("Dodaje autora z imieniem i nazwiskiem z malych liter")
    public void addWrongFirstAndLastNameAuthor(){
        PageObjectAuthor authorCreate = PageFactory.initElements(driver, PageObjectAuthor.class);
        authorCreate.toCreate();
        authorCreate.createAuthor("jakub", "nowy");
    }

    @Then("Zobacze informacje o problemach z walidacja")
    public void getInfoAboutValidationsAuthorProblems(){
        assertEquals(2, driver.findElements(By.className("text-danger")).size());
        driver.quit();
    }

    @When("Dodaje autora z prawidlowymi danymi")
    public void addAuthorWithCorrectData(){
        PageObjectAuthor authorCreate = PageFactory.initElements(driver, PageObjectAuthor.class);
        authorCreate.toCreate();
        authorCreate.createAuthor("Jo", "Nesbo");
    }

    @Then("Zobacze dodanego autora")
    public void seeAddedAuthor(){
        PageObjectAuthor authorCreate = PageFactory.initElements(driver, PageObjectAuthor.class);
        assertTrue(authorCreate.assertAddedRecord().contains("Jo Nesbo"));
        authorCreate.deleteLastItem();
        driver.quit();
    }

    // Scenario Wyszukiwanie
    @When("Wpisuje pusty napis do wyszukiwarki")
    public void searchEmptyString(){
        PageObjectAuthor author = PageFactory.initElements(driver, PageObjectAuthor.class);
        author.toTable();
        sizeBefore = author.sizeOfTable();
        author.searchAuthor("");
        sizeAfter = author.sizeOfTable();
    }

    @Then("Wyswietla wszystkie dane")
    public void searchChangesNothing(){
        assertEquals(sizeBefore, sizeAfter);
        driver.quit();
    }

    @When("Wpisuje Mickiewicz do wyszukiwarki")
    public void searchMickiewiczString(){
        PageObjectAuthor author = PageFactory.initElements(driver, PageObjectAuthor.class);
        author.toTable();
        author.searchAuthor("Mickiewicz");
        sizeAfter = author.sizeOfTable();
    }

    @Then("Otrzymam 2 wyniki")
    public void returnTwoResultsForMickiewicz(){
        assertEquals(2, sizeAfter);
        driver.quit();
    }


    private void login(String login, String pass) throws Exception{
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        PageObjectLogin loginPage = PageFactory.initElements(driver, PageObjectLogin.class);
        loginPage.login(login, pass);
    }
}
