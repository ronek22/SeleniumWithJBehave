package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageObjectNavigation {
    public WebDriver driver;

    @FindBy(id = "AuthorsLink")
    private WebElement authorsLink;

    @FindBy(id = "BooksLink")
    private WebElement booksLink;

    @FindBy(id = "logoutForm")
    private WebElement logoutForm;


    public PageObjectNavigation(WebDriver driver){
        this.driver = driver;
    }

    public void goToAuthors(){
        authorsLink.click();
    }

    public void goToBooks(){
        booksLink.click();
    }

    public void SignOut(){
        logoutForm.submit();
    }

}
