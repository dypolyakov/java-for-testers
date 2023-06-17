package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        String group = "group_test";

        if (!app.getGroupHelper().isThereAGroup(group)) {
            app.getNavigationHelper().goToGroupPage();
            app.getGroupHelper().createGroup(new GroupData(group, "header", "footer"));
        }

        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData(
                "Dmitry",
                "Polyakov",
                "DimQA",
                "+71234567890",
                "dimqa@dimqa.com",
                group
        ),
                true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
    }

}
