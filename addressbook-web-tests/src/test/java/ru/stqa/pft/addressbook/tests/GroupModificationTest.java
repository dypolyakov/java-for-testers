package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("group for modification").withHeader("header").withFooter("footer"));
        }
    }
    @Test
    public void testGroupModification() {
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test1");
        app.goTo().groupPage();
        app.group().modify(group);
        assertThat(before.size(), equalTo(app.group().count()));
        Groups after = app.db().groups();
        assertThat(before.without(modifiedGroup).withAdded(group), equalTo(after));
        verifyGroupListInUI();
    }
}
