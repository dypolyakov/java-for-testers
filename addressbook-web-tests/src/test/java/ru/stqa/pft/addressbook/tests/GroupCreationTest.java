package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("group_test");
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
