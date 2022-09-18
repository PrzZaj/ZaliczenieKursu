package pl.coderslab.cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyStoreSteps {

    private WebDriver driver;
    private final static String PERMANENT_LOGIN = "zfnjveuznndedqmxej@kvhrr.com";
    private final static String PERMANENT_PASSWORD = "asdf1234";

    @Given("an open browser with {}")
    public void openBrowser(String url){
        //        Ustaw gdzie jest chromedriver -> STEROWNIK
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");
        //        Otworz przegladarke
        this.driver = new ChromeDriver();
        //        Jesli test nie przechodzi poprawnie, to pewnie za wolno laduje sie strona -> Dodaj czekanie.
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.driver.get(url);
    }

    @And("user is registered with {} and {}")
    public void loggedUser(String mail, String password){
        WebElement signIn = this.driver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div"));
        signIn.click();
        WebElement signEmail = this.driver.findElement(By.xpath("//*[@id=\"login-form\"]/section/div[1]/div[1]/input"));
        signEmail.sendKeys(PERMANENT_LOGIN);
        WebElement signPassword = this.driver.findElement(By.xpath("//*[@id=\"login-form\"]/section/div[2]/div[1]/div/input"));
        signPassword.sendKeys(PERMANENT_PASSWORD);
        WebElement confirmUser = this.driver.findElement(By.xpath("//*[@id=\"submit-login\"]"));
        confirmUser.click();
    }

    @When("new address form is filled with {} and {} and {} and {} and {}")
    public void fillNewAddress(String alias, String address, String city, String zipcode, String phone){
        WebElement addAddress = this.driver.findElement(By.xpath("//*[@id=\"addresses-link\"]/span/i"));
        addAddress.click();
        WebElement createNewAddress = this.driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/a/span"));
        createNewAddress.click();
        WebElement fillAllias = this.driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/form/section/div[1]/div[1]/input"));
        fillAllias.sendKeys(alias);
        WebElement fillAddress = this.driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/form/section/div[6]/div[1]/input"));
        fillAddress.sendKeys(address);
        WebElement fillCity = this.driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/form/section/div[8]/div[1]/input"));
        fillCity.sendKeys(city);
        WebElement fillZip = this.driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/form/section/div[9]/div[1]/input"));
        fillZip.sendKeys(zipcode);
        WebElement fillPhone = this.driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/form/section/div[11]/div[1]/input"));
        fillPhone.sendKeys(phone);
        WebElement saveButton = this.driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/form/footer/button"));
        saveButton.click();
    }
    @Then("new address is created")
    public void addressCreated(){
        String expectedAlertText = "Address successfully added!";
        WebElement isItSuccess = this.driver.findElement(By.xpath("//*[@id=\"notifications\"]/div/article/ul/li"));
        String alertText = isItSuccess.getText();
        assertEquals(expectedAlertText, alertText);
    }
  //  And new address is deleted
 //   @And("new address is deleted")
 //   public void deleteAddress(){
 //       WebElement deleteAddress = this.driver.findElement(By.className("col-lg-4 col-md-6 col-sm-6"));
 //       deleteAddress.click();
 //       String expectedAlertText = "Address successfully deleted!";
 //       WebElement successTest = this.driver.findElement(By.xpath("//*[@id=\"notifications\"]/div/article/ul/li"));
 //       String alertText = successTest.getText();
 //       assertEquals(expectedAlertText, alertText);
 //   }

}

//#address-29198 > div.address-footer > a:nth-child(2) > span
//*[@id="address-29200"]/div[2]/a[2]/span
//*[@id="address-29200"]/div[2]/a[2]
//*[@id="content"]/div[2]
