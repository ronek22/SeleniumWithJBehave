import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import PageObject.PageObjectLogin;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectLoginTest {
    private static WebDriver driver;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Test
    public void loginPassed() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        PageObjectLogin loginPage = PageFactory.initElements(driver, PageObjectLogin.class);
        loginPage.login("admin@admin.com", "admin1");
        wait.until(ExpectedConditions.urlToBe("http://bookcatalog.azurewebsites.net/"));
        assertTrue(loginPage.assertMessage());
    }

    @Test
    public void loginFailed() throws Exception {
        PageObjectLogin loginPage = PageFactory.initElements(driver, PageObjectLogin.class);
        loginPage.login("sdasdad@admin.com", "admicza");
        assertEquals("http://bookcatalog.azurewebsites.net/Account/Login", driver.getCurrentUrl());
    }


    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        driver.quit();
    }
}
