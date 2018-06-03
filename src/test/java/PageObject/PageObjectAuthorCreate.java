package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageObjectAuthorCreate {
    public WebDriver driver;

    @FindBy(name = "FirstName")
    private WebElement firstName;

    @FindBy(id = "LastName")
    private WebElement lastName;

    @FindBy(id = "CreateButton")
    private WebElement createButton;

    @FindBy(className = "text-danger")
    private WebElement validationError;


    public PageObjectAuthorCreate(WebDriver driver){
        this.driver = driver;
    }

    public void createAuthor(String firstname, String lastname){
        firstName.sendKeys(firstname);
        lastName.sendKeys(lastname);
        createButton.submit();
    }

    public Boolean assertValidationError(String message){
        return validationError.getText().contains(message);
    }
}
