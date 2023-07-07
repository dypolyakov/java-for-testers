package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;

public class ContactCreationTest extends TestBase {

    private final String group = "group_test";

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (!app.group().isThereAGroup(group)) {
            app.group().create(new GroupData().withName(group).withHeader("header").withFooter("footer"));
        }
        app.goTo().homePage();
    }

    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstName("Гарри")
                .withLastName("Поттер")
                .withAddress("Тисовая, 4")
                .withHomePhone("+42779 768837")
                .withFirstEmail("harry@potter.com")
                .withGroup(group);

        app.contact().create(contact);

        Contacts after = app.contact().all();

        assertEquals(before.size() + 1, after.size());
        assertThat(before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt())),
                equalTo(after));
    }

}
