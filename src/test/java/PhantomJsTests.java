import PageObject.PageObjectAuthor;
import PageObject.PageObjectLogin;
import static org.junit.Assert.*;
import io.github.bonigarcia.SeleniumExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import static java.util.concurrent.TimeUnit.SECONDS;

@ExtendWith(SeleniumExtension.class)
public class PhantomJsTests {
    private PhantomJSDriver driver;
    private Wait wait;


    public PhantomJsTests(PhantomJSDriver driver) {
        this.driver = driver;
        driver.manage().window().setSize(new Dimension(1920, 1080));
        wait = new FluentWait(driver)
                .withTimeout(10, SECONDS)
                .pollingEvery(1, SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    @BeforeEach
    public void setDefaultPage() throws Exception {
        PageObjectLogin loginPage = PageFactory.initElements(driver, PageObjectLogin.class);
        loginPage.login("admin@admin.com", "admin1");
        loginPage.authorPage();
    }

    @Test
    public void testTitlePage()
    {
        wait.until(ExpectedConditions.titleIs("Authors - BookCatalog"));
        assertEquals("Authors - BookCatalog", driver.getTitle());
    }

    @Test
    public void testEditAuthor()
    {
        PageObjectAuthor author = PageFactory.initElements(driver, PageObjectAuthor.class);
        String[] oldAuthor = author.getAuthor(1).split(" ");
        author.editAuthor(1, "Jan", "Edytowany");

        assertTrue(author.assertRecord(1).contains("Jan Edytowany"));
        author.editAuthor(1, oldAuthor[0], oldAuthor[1]);
    }

}
