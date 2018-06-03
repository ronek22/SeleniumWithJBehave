package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageObjectLogin {
    public WebDriver driver;

    @FindBy(id = "Email")
    private WebElement email;

    @FindBy(id = "Password")
    private WebElement password;

    @FindBy(id = "LoginButton")
    private WebElement loginButton;


    public PageObjectLogin(WebDriver driver){
        this.driver = driver;
        driver.get("http://bookcatalog.azurewebsites.net/Account/Login");
    }

    public void login(String login, String pass) throws Exception{
        email.sendKeys(login);
        password.sendKeys(pass);
        loginButton.submit();
    }
    
}
