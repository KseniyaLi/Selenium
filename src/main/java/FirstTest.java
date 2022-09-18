import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FirstTest {

    String MOBILE_SERVICE_URL = "https://next.privat24.ua/mobile";

    @Test
    void checkMinSum() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","src\\main/resources/chromedriver_win32/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        By cardFrom = By.xpath("//input[@data-qa-node='numberdebitSource']");
        By expDate = By.xpath("//input[@data-qa-node='expiredebitSource']");
        By cvv = By.xpath("//input[@data-qa-node='cvvdebitSource']");
        By phoneNumber = By.xpath("//input[@data-qa-node='phone-number']");
        By amount = By.xpath("//input[@data-qa-node='amount']");
        By submitPayment = By.xpath("//button[@data-qa-node='submit']");
        By termsAndConditionsLink = By.xpath("//a[@href='https://privatbank.ua/terms']");

        By expectedTermsAndConditions = By.xpath("//a[@href='https://privatbank.ua/terms']");
        By expectedSum = By.xpath("//div[@data-qa-node='amount']");
        By recipientNameInTheCart = By.xpath("//span[@data-qa-node='nameB']");
        By commissionInTheCart = By.xpath("//span[@data-qa-node='commission']");




        driver.get(MOBILE_SERVICE_URL);
        driver.findElement(cardFrom).sendKeys("4149629353645682");
        driver.findElement(expDate).sendKeys("1225");
        driver.findElement(cvv).sendKeys("111");
        driver.findElement(phoneNumber).sendKeys("502672115");
        driver.findElement(amount).sendKeys("\b\b\b\b\b\b", "33");
        driver.findElement(submitPayment).submit();
        driver.findElement(termsAndConditionsLink).click();

        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);

        Assertions.assertEquals("Умови та правила", driver.findElement(expectedTermsAndConditions).getText());
        Assertions.assertEquals("33 UAH", driver.findElement(expectedSum).getText());
        Assertions.assertEquals("Vodafone", driver.findElement(recipientNameInTheCart).getText());
        Assertions.assertEquals("2 UAH", driver.findElement(commissionInTheCart).getText());


    }

}
