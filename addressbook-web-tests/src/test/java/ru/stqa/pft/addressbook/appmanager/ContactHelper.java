package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("email"), contactData.getFirstEmail());
        attach(By.name("photo"), contactData.getPhoto());
        if (creation) {
//            if (contactData.getGroup() != null) {
//                new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
//            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact(int id) {
        click(By.cssSelector("input[value='" + id + "']"));
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void initContactModification(int id) {
        click(By.xpath("//a[@href='edit.php?id=" + id + "']/img"));
    }


    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData data) {
        initContactCreation();
        fillContactForm(data, true);
        submitContactCreation();
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initContactModification(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContact(contact.getId());
        deleteSelectedContacts();
        acceptAlert();
        returnToHomePage();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = driver.findElements(By.name("entry"));

        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            String address = element.findElement(By.xpath(".//td[4]")).getText();
            String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
            String allPhones = element.findElement(By.xpath(".//td[6]")).getText();

            ContactData contact = new ContactData()
                    .withId(id)
                    .withLastName(lastName)
                    .withFirstName(firstName)
                    .withAddress(address)
                    .withAllEmails(allEmails)
                    .withAllPhones(allPhones);

            contacts.add(contact);
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModification(contact.getId());

        String firstName = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastName = driver.findElement(By.name("lastname")).getAttribute("value");
        String address = driver.findElement(By.name("address")).getAttribute("value");
        String homePhone = driver.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = driver.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = driver.findElement(By.name("work")).getAttribute("value");
        String firstEmail = driver.findElement(By.name("email")).getAttribute("value");
        String secondEmail = driver.findElement(By.name("email2")).getAttribute("value");
        String thirdEmail = driver.findElement(By.name("email3")).getAttribute("value");

        driver.navigate().back();

        return new ContactData()
                .withId(contact.getId())
                .withFirstName(firstName)
                .withLastName(lastName)
                .withAddress(address)
                .withHomePhone(homePhone)
                .withMobilePhone(mobilePhone)
                .withWorkPhone(workPhone)
                .withFirstEmail(firstEmail)
                .withSecondEmail(secondEmail)
                .withThirdEmail(thirdEmail);
    }
}
