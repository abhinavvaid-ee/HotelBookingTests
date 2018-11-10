package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.Utils;

public class HotelBookingTests {
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private Utils utils;
    private String firstName;
    private String lastName;

    private By firstNameElement = By.id("firstname");
    private By lastNameElement = By.id("lastname");
    private By totalPriceElement = By.id("totalprice");
    private By depositPaidElement = By.id("depositpaid");
    private By checkInElement = By.id("checkin");
    private By checkout = By.id("checkout");
    private By saveButton = By.xpath("//input[@value=' Save ']");

    private By bookingRow;
    private By deleteButtonForBooking;

    @BeforeTest
    public void setUp() {
        driver = new FirefoxDriver();
        String url = "http://hotel-test.equalexperts.io";
        driver.get(url);
        utils = new Utils();
        firstName = utils.getFirstName();
        lastName = utils.getLastName();

        webDriverWait = new WebDriverWait(driver, 10);

        bookingRow = By.xpath(String.format("//div[@class='row']/div/p[text() = '%s']", firstName));
        deleteButtonForBooking = By.xpath(String.format("//div[div[p[text()='%s']]]/div/input", firstName));
    }

    @Test
    public void  createAndDeleteBooking() {
        driver.findElement(firstNameElement).sendKeys(firstName);
        driver.findElement(lastNameElement).sendKeys(lastName);
        driver.findElement(totalPriceElement).sendKeys(utils.getRandomNumber());
        WebElement element = driver.findElement(depositPaidElement);
        Select select = new Select(element);
        select.selectByVisibleText("true");
        driver.findElement(checkInElement).sendKeys(utils.getFutureDate(10));
        driver.findElement(checkout).sendKeys(utils.getFutureDate(20));
        driver.findElement(saveButton).click();
        webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(bookingRow));

        driver.findElement(deleteButtonForBooking).click();
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(bookingRow));
    }

    @AfterTest
    public void closeWebDriver() {
        driver.close();
    }
}
