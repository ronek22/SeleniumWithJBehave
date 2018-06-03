package Steps;

import PageObject.PageObjectBook;
import PageObject.PageObjectLogin;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookSteps extends Steps {

    private static WebDriver driver;


    @Given("Zalogowany jako admin2")
    public void loginAsAdmin() throws Exception{
        login("admin@admin.com", "admin1");
    }

    @When("Dodam ksiazke: <title> : <pages> : <date> : <price> : <author>")
    public void addBookTest(@Named("title") String title, @Named("pages") String pages, @Named("date") String date,
                            @Named("price") String price, @Named("author") String author){
        PageObjectBook books = PageFactory.initElements(driver, PageObjectBook.class);
        books.toCreate();
        books.createBooks(title, pages, date, price, author);
    }

    @Then("Zobaczę błąd o tresci : <message>")
    public void receiveErrorWhenCreatingBook(@Named("message") String message){
        PageObjectBook books = PageFactory.initElements(driver, PageObjectBook.class);
        assertTrue(books.assertValidationError(message));
    }

    private void login(String login, String pass) throws Exception{
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        PageObjectLogin loginPage = PageFactory.initElements(driver, PageObjectLogin.class);
        loginPage.login(login, pass);
        loginPage.bookPage();
    }
}
