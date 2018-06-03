package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PageObjectAuthor {
    public WebDriver driver;

    @FindBy(name = "FirstName")
    private WebElement firstName;

    @FindBy(id = "LastName")
    private WebElement lastName;

    @FindBy(id = "CreateButton")
    private WebElement createButton;

    @FindBy(className = "text-danger")
    private WebElement validationError;

    @FindBy(name = "SearchString")
    private WebElement searchBar;

    @FindBy(id = "AuthorId")
    private WebElement authorDeleteForm;

    @FindBy(xpath = "//table[@class='table']/tbody/tr")
    private List<WebElement> tableContent;


    public PageObjectAuthor(WebDriver driver){
        this.driver = driver;
    }

    public void toTable(){
        driver.get("http://bookcatalog.azurewebsites.net/Authors");
    }

    public void toCreate(){
        driver.get("http://bookcatalog.azurewebsites.net/Authors/Create");

    }

    public void createAuthor(String firstname, String lastname){
        firstName.sendKeys(firstname);
        lastName.sendKeys(lastname);
        createButton.submit();
    }

    public void searchAuthor(String searchPhrase){
        searchBar.sendKeys(searchPhrase);
        searchBar.submit();
    }

    public String getAuthor(int id){
        WebElement tr = driver.findElement(By.xpath("(//table[@class='table']/tbody/tr)[" + id + "]"));
        return tr.getText();
    }

    public void editAuthor(int id, String firstname, String lastname){
        driver.get("http://bookcatalog.azurewebsites.net/Authors/Edit/" + id);
        firstName.clear();
        firstName.sendKeys(firstname);

        lastName.clear();
        lastName.sendKeys(lastname);

        lastName.submit();
    }

    public int sizeOfTable(){
        return tableContent.size();
    }

    public Boolean assertValidationError(String message){
        return validationError.getText().contains(message);
    }

    public String assertAddedRecord() {
        WebElement tr = driver.findElement(By.xpath("(//table[@class='table']/tbody/tr)[last()]"));
        return tr.getText();
    }

    public String assertRecord(int id) {
        WebElement tr = driver.findElement(By.xpath("(//table[@class='table']/tbody/tr)[" + id + "]"));
        return tr.getText();
    }

    public void deleteLastItem(){
        WebElement tr = driver.findElement(By.xpath("(//table[@class='table']/tbody/tr[last()]/td[last()]/a[last()])"));
        driver.navigate().to(tr.getAttribute("href"));
        authorDeleteForm.submit();
    }
}
