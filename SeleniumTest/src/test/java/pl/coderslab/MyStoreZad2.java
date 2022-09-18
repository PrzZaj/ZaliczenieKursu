package pl.coderslab;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.coderslab.model.*;

import java.io.File;
import java.security.Key;
import java.time.Duration;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyStoreZad2 {
    private WebDriver driver;
    private final static String PERMANENT_LOGIN = "zfnjveuznndedqmxej@kvhrr.com";
    private final static String PERMANENT_PASSWORD = "asdf1234";
    private static String NUMBER_OF_UNITS = "5";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.driver.get("https://mystore-testlab.coderslab.pl");
    }
//    @After
 //   public void tearDown() {
 //       driver.quit();
 //   }

    @Test
    public void checkShopping(){
        String expectedAlertDiscount = "SAVE 20%";
        //Logowanie na założonego użytkownika
        WebElement signIn = this.driver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div"));
        signIn.click();
        WebElement signEmail = this.driver.findElement(By.xpath("//*[@id=\"login-form\"]/section/div[1]/div[1]/input"));
        signEmail.sendKeys(PERMANENT_LOGIN);
        WebElement signPassword = this.driver.findElement(By.xpath("//*[@id=\"login-form\"]/section/div[2]/div[1]/div/input"));
        signPassword.sendKeys(PERMANENT_PASSWORD);
        WebElement confirmUser = this.driver.findElement(By.xpath("//*[@id=\"submit-login\"]"));
        confirmUser.click();
        //wybierze do zakupu Hummingbird Printed Sweater (opcja dodatkowa: sprawdzi czy rabat na niego wynosi 20%)
        WebElement chooseClothes = this.driver.findElement(By.xpath("//*[@id=\"category-3\"]/a"));
        chooseClothes.click();
        WebElement chooseSweater = this.driver.findElement(By.xpath("//*[@id=\"js-product-list\"]/div[1]/article[2]/div/div[1]/h2/a"));
        chooseSweater.click();
        WebElement checkDiscount = this.driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div[2]/div[1]/div[2]/div/span[2]"));
        String discountText = checkDiscount.getText();
        assertEquals(expectedAlertDiscount,discountText);
        //wybierze rozmiar M
        WebElement changeSize = this.driver.findElement(By.xpath("//*[@id=\"group_1\"]"));
        changeSize.click();
        WebElement chooseSize = this.driver.findElement(By.xpath("//*[@id=\"group_1\"]/option[2]"));
        chooseSize.click();
        //wybierze 5 sztuk według parametru podanego w teście
        WebElement numberOfUnits = this.driver.findElement(By.id("quantity_wanted"));
        numberOfUnits.click();
        numberOfUnits.sendKeys(Keys.BACK_SPACE);
        numberOfUnits.sendKeys(NUMBER_OF_UNITS);
        //dodaj produkt do koszyka,
        WebElement addToCart = this.driver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button"));
        addToCart.click();
        //przejdzie do opcji - checkout
        WebElement proceedToCheckout = this.driver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a"));
        proceedToCheckout.click();
        WebElement proceedToCheckoutFinal = this.driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/a"));
        proceedToCheckoutFinal.click();
        //Potwierdzi adres
        WebElement addressConfirm = this.driver.findElement(By.xpath("//*[@id=\"checkout-addresses-step\"]/div/div/form/div[2]/button"));
        addressConfirm.click();
        //wybierze metodę odbioru - PrestaShop "pick up in store"
        WebElement chooseShipping = this.driver.findElement(By.xpath("//*[@id=\"js-delivery\"]/div/div[1]/div[1]/div/span/span"));
        chooseShipping.click();
        WebElement continueToPayment = this.driver.findElement(By.xpath("//*[@id=\"js-delivery\"]/button"));
        continueToPayment.click();
        //wybierze opcję płatności - Pay by Check
        WebElement payByCheck = this.driver.findElement(By.xpath("//*[@id=\"payment-option-1-container\"]/label/span"));
        payByCheck.click();
        //kliknie na "order with an obligation to pay"(Wymaga zaznaczenia terms of service)
        WebElement termsOfService = this.driver.findElement(By.xpath("//*[@id=\"conditions_to_approve[terms-and-conditions]\"]"));
        termsOfService.click();
        WebElement obligationToPayButton = this.driver.findElement(By.xpath("//*[@id=\"payment-confirmation\"]/div[1]/button"));
        obligationToPayButton.click();
        //zrobi screenshot z potwierdzeniem zamówienia i kwotą.
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
    }
}
