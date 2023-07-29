package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFirstName("Гарри")
                    .withLastName("Поттер")
                    .withAddress("Тисовая, 4")
                    .withHomePhone("+42779 768837")
                    .withFirstEmail("harry@potter.com")
            );
        }
    }
    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        Groups modifiedContactGroups = modifiedContact.getGroups();

        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName("Уолтер")
                .withLastName("Уайт")
                .withAddress("Негра Арройо Лэйн, 308")
                .withHomePhone("+925837 94483")
                .withFirstEmail("walter@white.com")
                .withGroups(modifiedContactGroups);
        app.goTo().homePage();
        app.contact().modify(contact);
        Contacts after = app.db().contacts();
        assertEquals(before.size(), after.size());
        assertThat(before.without(modifiedContact).withAdded(contact), equalTo(after));
        verifyContactListInUI();
    }
}
