package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageObjectLogin {
    public WebDriver driver;
    private String loginEmail;

    @FindBy(id = "Email")
    private WebElement email;

    @FindBy(id = "Password")
    private WebElement password;

    @FindBy(id = "LoginButton")
    private WebElement loginButton;

    @FindBy(id = "UserName")
    private WebElement userNameLabel;


    public PageObjectLogin(WebDriver driver){
        this.driver = driver;
        driver.get("http://bookcatalog.azurewebsites.net/Account/Login");
    }

    public void login(String login, String pass) throws Exception{
        email.sendKeys(login); loginEmail = login;
        password.sendKeys(pass);
        loginButton.submit();
    }

    public boolean assertMessage() throws Exception{
        return userNameLabel.getText().contains(loginEmail);
    }
}
