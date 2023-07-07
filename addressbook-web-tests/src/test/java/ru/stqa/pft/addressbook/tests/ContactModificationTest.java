package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (!app.contact().isThereAContact()) {
            app.contact().create(new ContactData()
                    .withFirstName("Dmitry")
                    .withLastName("Polyakov")
                    .withAddress("Пушкина 24")
                    .withPhoneNumber("+71234567890")
                    .withEmail("dimqa@dimqa.com")
            );
        }
    }
    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();

        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName("Vasya")
                .withLastName("Pupkin")
                .withAddress("Ленина 1")
                .withPhoneNumber("+79876543210")
                .withEmail("vasya@pupkin.ru");

        app.contact().modify(contact);
        Contacts after = app.contact().all();
        assertEquals(before.size(), after.size());
        assertThat(before.without(modifiedContact).withAdded(contact), equalTo(after));
    }
}
