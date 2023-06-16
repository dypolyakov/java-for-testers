package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

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
                            "dimqa@dimqa.com",
                            "group_test"
                    ),
                    true);
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData(
                "Vasya",
                "Pupkin",
                "Vassya",
                "+70987654321",
                "vasya@pupkin.ru",
                null
        ),
                false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }
}
