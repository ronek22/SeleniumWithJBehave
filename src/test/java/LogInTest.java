import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogInTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // Implicity wait -> max czas na znalezienie elementu na stronie
        wait = new WebDriverWait(driver, 10);
        driver.get("http://bookcatalog.azurewebsites.net/Account/Login");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void logInTest(){
        WebElement element = driver.findElement(By.id("Email"));
        element.sendKeys("admin@admin.com");

        element = driver.findElement(By.id("Password"));
        element.sendKeys("admin1");

        element.submit();

        wait.until(ExpectedConditions.urlToBe("http://bookcatalog.azurewebsites.net/"));

        WebElement userName = driver.findElement(By.id("UserName"));
        assertEquals("Hello admin@admin.com!", userName.getText());

    }

}