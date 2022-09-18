package pl.coderslab;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.coderslab.model.*;

import java.time.Duration;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdressChangeTest {
    private final static String PERMANENT_LOGIN = "mytest@cl-test.com";
    private final static String PERMANENT_PASSWORD = "mytestPassword";
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.driver.get("https://hotel-testlab.coderslab.pl/en/");


    }

    @Test
    public void logInUser() {
        String email = new Random().nextInt(100000000) + "TEA26@test.com";

        HotelMainPage hotelMainPage = new HotelMainPage(this.driver);
        hotelMainPage.clickSignIn();
        HotelLoginPage hotelLoginPage = new HotelLoginPage(this.driver);
        hotelLoginPage.signInWithCredentials(PERMANENT_LOGIN, PERMANENT_PASSWORD);
        HotelMyAccountPage hotelMyAccountPage = new HotelMyAccountPage(this.driver);
        hotelMyAccountPage.changeAdress();
        hotelMyAccountPage.newAddress();
        DubelUseraNaTest dubelUseraNaTest = new DubelUseraNaTest(this.driver);
        dubelUseraNaTest.newName("Artur");
        dubelUseraNaTest.newLastName("Kowal");
        dubelUseraNaTest.newAdress("Tuwima");
        dubelUseraNaTest.newZip("10100");
        dubelUseraNaTest.newCity("Warszawa");
        dubelUseraNaTest.insertHomePhone("11111111");
        dubelUseraNaTest.addMobilePhone("123455678");
        dubelUseraNaTest.addressAssign("costammm");
       // dubelUseraNaTestza.saveButton();
      //  HotelUserForm hotelUserForm1 = new HotelUserForm(this.driver);
      //  assertTrue(hotelUserForm1.lastItem() > 0);


    }
}