package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;

public class ContactDeletionTest extends TestBase {

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
    public void testContactDeletion() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.db().contacts();
        assertEquals(before.size() - 1, after.size());
        assertThat(before.without(deletedContact), equalTo(after));
        verifyContactListInUI();
    }

}
