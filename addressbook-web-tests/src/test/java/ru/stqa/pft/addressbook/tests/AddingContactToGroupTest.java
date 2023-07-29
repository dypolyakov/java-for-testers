package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddingContactToGroupTest extends TestBase {

    ContactData availableContact;

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

        // Найти контакт, который можно добавить в существующие группы. Если такого контакта нет - создать его
        availableContact = app.contact().findAvailableContactToAddGroup();
        if (availableContact == null) {

            app.contact().createWithoutGroup(availableContact = new ContactData()
                    .withFirstName("Гарри")
                    .withLastName("Поттер")
                    .withAddress("Тисовая, 4")
                    .withHomePhone("+42779 768837")
                    .withFirstEmail("harry@potter.com"));

            Contacts contactsAfterAddition = app.db().contacts();
            int maxId = contactsAfterAddition.stream().mapToInt(ContactData::getId).max().getAsInt();
            availableContact.withId(maxId);
        }

    }

    @Test
    public void testAddingContactToGroup() {
        // Получить список групп контакта до добавления его в группу
        Groups before = availableContact.getGroups();

        // Получить список доступных групп контакта для добавления
        Groups availableGroups = app.db().groups();
        availableGroups.removeAll(availableContact.getGroups());

        // Выбрать одну из доступных групп
        GroupData availableGroup = availableGroups.iterator().next();

        // Добавить доступный контакт в доступную для него группу
        app.goTo().homePage();
        app.contact().addToGroup(availableContact, availableGroup);

        // Получить список групп контакта после добавления его в группу
        Groups after = app.db().getContactById(availableContact.getId()).iterator().next().getGroups();

        // Сравнить список групп контакта до добавления в группу и после
        assertThat(before.withAdded(availableGroup), equalTo(after));
    }
}
