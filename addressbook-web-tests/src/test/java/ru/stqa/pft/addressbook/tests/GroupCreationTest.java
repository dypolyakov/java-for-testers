package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Set<GroupData> before = app.group().all();
        GroupData group = new GroupData().withName("group_test");
        app.group().create(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(before.size() + 1, after.size());

        group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt());
        before.add(group);

        Assert.assertEquals(before, after);
    }

}
