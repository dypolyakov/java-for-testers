package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

public class ContactCreationTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        login("admin", "secret");
    }

    private void login(String username, String password) {
        driver.get("http://localhost/addressbook/index.php");
        driver.findElement(By.name("user")).clear();
        driver.findElement(By.name("user")).sendKeys(username);
        driver.findElement(By.name("pass")).click();
        driver.findElement(By.name("pass")).clear();
        driver.findElement(By.name("pass")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @Test
    public void testContactCreation() {
        initContactCreation();
        fillContactForm(new ContactData(
                "Dmitry",
                "Polyakov",
                "DimQA",
                "+71234567890",
                "dimqa@dimqa.com"
        ));
        submitContactCreation();
        returnToHomePage();
    }

    private void returnToHomePage() {
        driver.findElement(By.linkText("home")).click();
    }

    private void submitContactCreation() {
        driver.findElement(By.name("submit")).click();
    }

    private void fillContactForm(ContactData contactData) {
        driver.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
        driver.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
        driver.findElement(By.name("nickname")).sendKeys(contactData.getNickName());
        driver.findElement(By.name("mobile")).sendKeys(contactData.getPhoneNumber());
        driver.findElement(By.name("email")).sendKeys(contactData.getEmail());
    }

    private void initContactCreation() {
        driver.findElement(By.linkText("add new")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.findElement(By.linkText("Logout")).click();
        driver.quit();
    }
}
