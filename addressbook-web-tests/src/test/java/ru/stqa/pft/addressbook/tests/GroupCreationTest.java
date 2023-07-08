package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;

public class GroupCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        List<Object[]> groups = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.csv"));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            groups.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
            line = reader.readLine();
        }
        return groups.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create(group);
        assertThat(before.size() + 1, equalTo(app.group().count()));
        Groups after = app.group().all();
        assertThat(before.withAdded(group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt())),
                equalTo(after));
    }

    @Test
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test'");
        app.group().create(group);
        assertThat(before.size(), equalTo(app.group().count()));
        Groups after = app.group().all();
        assertThat(before, equalTo(after));
    }

}
