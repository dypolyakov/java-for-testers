package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() {
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

        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().acceptAlert();
        app.getContactHelper().returnToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(before.size() - 1, after.size());

        before.remove(0);

        Assert.assertEquals(before, after);


    }
}
