package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        String group = "group_test";

        app.goTo().groupPage();
        if (!app.group().isThereAGroup(group)) {
            app.group().create(new GroupData().withName(group).withHeader("header").withFooter("footer"));
        }
        app.goTo().goToHomePage();

        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().initContactCreation();
        ContactData contact = new ContactData(
                "Dmitry",
                "Polyakov",
                "DimQA",
                "+71234567890",
                "dimqa@dimqa.com",
                group
        );
        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(before.size() + 1, after.size());

        before.add(contact);
        Comparator<? super ContactData> byLastName = (o1, o2) -> String.CASE_INSENSITIVE_ORDER.compare(o1.getLastName(), o2.getLastName());
        before.sort(byLastName);
        after.sort(byLastName);

        Assert.assertEquals(before, after);
    }

}
