package pl.coderslab.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HotelUserForm {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"firstname\"]")
    private WebElement changeName;
    @FindBy(xpath = "//*[@id=\"lastname\"]")
    private WebElement changeLastName;
    @FindBy(xpath = "//*[@id=\"address1\"]")
    private WebElement changeAdress;
    @FindBy(xpath = "//*[@id=\"postcode\"]")
    private WebElement zipCode;
    @FindBy(xpath = "//*[@id=\"city\"]")
    private WebElement addCity;
    @FindBy(xpath = "//*[@id=\"phone\"]")
    private WebElement homePhone;
    @FindBy(xpath = "//*[@id=\"phone_mobile\"]")
    private WebElement mobilePhone;
    @FindBy(xpath = "//*[@id=\"alias\"]")
    private WebElement assignAdress;
    @FindBy(xpath = "//*[@id=\"submitAddress\"]/span")
    private WebElement saveChanges;
    @FindBy(className = "last_item alternate_item box")
    private List<WebElement> successAlert;


    public HotelUserForm (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public void newName(String name ){changeName.sendKeys(name);}
    public void newLastName(String lastname){changeLastName.sendKeys(lastname);}
    public void newAdress(String address){changeAdress.sendKeys(address);}
    public void newZip(String zipcode){zipCode.sendKeys(zipcode);}
    public void newCity(String city){addCity.sendKeys(city);}
    public void insertHomePhone(String number){homePhone.sendKeys(number);}
    public void addMobilePhone(String phone){mobilePhone.sendKeys(phone);}
    public void addressAssign(String assign){assignAdress.sendKeys(assign);}
    public void saveButton(){saveChanges.click();}
   // public String getAlertText() {return successAlert.getText();}
    public String getPageTitle() {return this.driver.getTitle();}
    public Integer lastItem() {return successAlert.size();}

}
