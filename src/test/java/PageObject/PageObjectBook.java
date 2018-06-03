package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PageObjectBook {
    public WebDriver driver;

    @FindBy(name = "Title")
    private WebElement title;

    @FindBy(id = "Length")
    private WebElement length;

    @FindBy(id = "ReleaseDate")
    private WebElement releaseDate;

    @FindBy(name = "Price")
    private WebElement price;

    @FindBy(id = "AuthorId")
    private WebElement authorID;

    @FindBy(id = "BookId")
    private WebElement formDelete;

    @FindBy(name = "SearchString")
    private WebElement searchBar;


    @FindBy(xpath = "//table[@class='table']/tbody/tr")
    private List<WebElement> tableContent;


    public PageObjectBook(WebDriver driver){
        this.driver = driver;
    }

    public void toTable(){
        driver.get("http://bookcatalog.azurewebsites.net/Books");
    }

    public void toCreate(){
        driver.get("http://bookcatalog.azurewebsites.net/Books/Create");

    }

    public void createBooks(String title, String length, String date, String price, String authorID){
        this.title.sendKeys(title);
        this.length.sendKeys(length);
        this.releaseDate.sendKeys(date);
        this.price.sendKeys(price);
        this.authorID.sendKeys(authorID);
        this.authorID.submit();
    }

    public void searchBooks(String searchPhrase){
        searchBar.sendKeys(searchPhrase);
        searchBar.submit();
    }

    public String getBook(int id){
        WebElement tr = driver.findElement(By.xpath("(//table[@class='table']/tbody/tr)[" + id + "]"));
        return tr.getText();
    }

    public void editBook(int id, String title, String length, String date, String price, String authorID){
        driver.get("http://bookcatalog.azurewebsites.net/Authors/Edit/" + id);
        this.title.clear();
        this.title.sendKeys(title);

        this.length.clear();
        this.length.sendKeys(length);

        this.releaseDate.clear();
        this.releaseDate.sendKeys(date);

        this.price.clear();
        this.price.sendKeys(price);

        this.authorID.clear();
        this.authorID.sendKeys(authorID);

        this.authorID.submit();
    }

    public int sizeOfTable(){
        return tableContent.size();
    }

    public List<WebElement> getTableContent(){
        return tableContent;
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
        formDelete.submit();
    }
}

