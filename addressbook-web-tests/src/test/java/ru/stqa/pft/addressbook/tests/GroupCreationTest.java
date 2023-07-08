package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;

public class GroupCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() {
        List<Object[]> groups = new ArrayList<>();
        groups.add(new Object[] {new GroupData().withName("test1").withHeader("header1").withFooter("footer1")});
        groups.add(new Object[] {new GroupData().withName("test2").withHeader("header2").withFooter("footer2")});
        groups.add(new Object[] {new GroupData().withName("test3").withHeader("header3").withFooter("footer3")});
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
