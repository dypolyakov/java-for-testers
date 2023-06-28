package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData(
                            "Dmitry",
                            "Polyakov",
                            "DimQA",
                            "+71234567890",
                            "dimqa@dimqa.com"
                    ));
        }

        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().initContactModification();
        ContactData contact = new ContactData(
                "Vasya",
                "Pupkin",
                "Vassya",
                "+70987654321",
                "vasya@pupkin.ru",
                null
        );
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(before.size(), after.size());

        before.remove(0);
        before.add(contact);

        Comparator<? super ContactData> byLastName = Comparator.comparing(ContactData::getLastName);
        before.sort(byLastName);
        after.sort(byLastName);

        Assert.assertEquals(before, after);
    }
}
