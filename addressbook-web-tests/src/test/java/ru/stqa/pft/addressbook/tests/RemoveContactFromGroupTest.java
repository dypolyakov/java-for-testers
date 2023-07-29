package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTest extends TestBase {

    ContactData contactWithAddedGroup;
    @BeforeMethod
    public void ensurePreconditions() {
        // Создать группу, если нет ни одной группы
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("Хогвартс")
                    .withHeader("Школа Чародейства и волшебства Хоогвартс")
                    .withFooter("Гриффиндор, Пуффендуй, Когтевран, Слизерин"));
            app.goTo().homePage();
        }

        // Создать контакт, если нет ни одного контакта
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().createWithoutGroup(new ContactData()
                    .withFirstName("Гарри")
                    .withLastName("Поттер")
                    .withAddress("Тисовая, 4")
                    .withHomePhone("+42779 768837")
                    .withFirstEmail("harry@potter.com")
            );
        }

        contactWithAddedGroup = app.contact().contactWithAddedGroup();
        if (contactWithAddedGroup == null) {

            app.contact().create(contactWithAddedGroup = new ContactData()
                    .withFirstName("Гарри")
                    .withLastName("Поттер")
                    .withAddress("Тисовая, 4")
                    .withHomePhone("+42779 768837")
                    .withFirstEmail("harry@potter.com"));

            Contacts contactsAfterAddition = app.db().contacts();
            int maxId = contactsAfterAddition.stream().mapToInt(ContactData::getId).max().getAsInt();
            contactWithAddedGroup.withId(maxId);
        }

    }

    @Test
    public void testRemoveContactFromGroup() {
        Groups before = contactWithAddedGroup.getGroups();
        GroupData group = before.iterator().next();
        app.goTo().homePage();
        app.contact().removeContactFromGroup(contactWithAddedGroup, group);
        Groups after = app.db().getContactById(contactWithAddedGroup.getId()).iterator().next().getGroups();
        assertThat(before.without(group), equalTo(after));
    }
}
