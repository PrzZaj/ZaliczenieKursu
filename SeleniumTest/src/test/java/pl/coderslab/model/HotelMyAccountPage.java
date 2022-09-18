package pl.coderslab.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelMyAccountPage {

    private WebDriver driver;

    @FindBy(className = "alert-success")
    private WebElement successAlertField;

    @FindBy(css = ".footer_links span")
    private WebElement homeButton;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div/div/ul/li[3]/a/span")
    private WebElement adressButton;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div[2]/a/span")
    private WebElement addNewAdress;

    public HotelMyAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getAlertText() {
        return successAlertField.getText();
    }
    public void changeAdress() {adressButton.click();}
    public void newAddress() {addNewAdress.click();}

    public String getPageTitle() {
        return this.driver.getTitle();
    }

    public void goToHomePage() {
        homeButton.click();
    }
}
