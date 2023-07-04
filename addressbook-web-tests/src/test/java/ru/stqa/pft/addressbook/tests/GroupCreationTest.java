package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData("group_test", null, null);
        app.group().create(group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(before.size() + 1, after.size());
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);

        group.setId(after.stream().max(byId).get().getId());
        before.add(group);

        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }

}
