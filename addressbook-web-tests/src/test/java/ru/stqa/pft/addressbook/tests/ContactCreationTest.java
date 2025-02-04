package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.serializers.FileDeserializer;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;

public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.json"))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(File.class, new FileDeserializer())
                    .create();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("group").withHeader("header").withFooter("footer"));
            app.goTo().homePage();
        }
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) {
        Contacts before = app.db().contacts();
        Groups groups = app.db().groups();
        contact.inGroup(groups.iterator().next());
        app.contact().create(contact);
        Contacts after = app.db().contacts();
        assertEquals(before.size() + 1, after.size());
        assertThat(before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt())),
                equalTo(after));
        verifyContactListInUI();
    }

}
