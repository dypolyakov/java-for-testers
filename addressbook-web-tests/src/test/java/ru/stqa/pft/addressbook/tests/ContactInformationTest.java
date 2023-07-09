package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactInformationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
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
    public void testContactPhone() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Stream.of(contact.getFirstEmail(), contact.getSecondEmail(), contact.getThirdEmail())
                .filter((s) -> !s.equals(""))
                .map(ContactInformationTest::cleanedEmail)
                .collect(Collectors.joining("\n"));
    }

    private String mergePhones(ContactData contact) {
        return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .filter((s) -> !s.equals(""))
                .map(ContactInformationTest::cleanedPhone)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedPhone(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

    public static String cleanedEmail(String email) {
        return email.replaceAll("\\n", "");
    }
}
