import PageObject.PageObjectBook;
import PageObject.PageObjectLogin;
import io.github.bonigarcia.SeleniumExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumExtension.class)
public class HtmlUnitTest
{
    private WebDriver driver;
    private WebDriverWait wait;

    public HtmlUnitTest() {
        this.driver = new HtmlUnitDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @BeforeEach
    public void setDefaultPage() throws Exception {
        PageObjectLogin loginPage = PageFactory.initElements(driver, PageObjectLogin.class);
        loginPage.login("admin@admin.com", "admin1");
        loginPage.bookPage();
    }

    @Test
    public void searchTestForJanPhrase(){
        PageObjectBook books = PageFactory.initElements(driver, PageObjectBook.class);
        books.searchBooks("Jan");
        assertEquals(3, books.sizeOfTable());
    }

    @Test
    public void searchTestFor1900Phrase(){
        PageObjectBook books = PageFactory.initElements(driver, PageObjectBook.class);
        books.searchBooks("1900");
        assertAll(
                () -> assertEquals(2, books.sizeOfTable()),
                () -> assertTrue(books.getTableContent().get(0).getText().contains("1900")),
                () -> assertTrue(books.getTableContent().get(1).getText().contains("1900"))
        );
    }

}