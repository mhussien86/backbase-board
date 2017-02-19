package com.backbase.boardmembers.ui;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import com.backbase.boardmembers.R;
import com.backbase.boardmembers.ui.memberslist.MembersListActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by mohamed on 19/02/17.
 */
public class MembersListFragmentTest {

    MembersListActivity membersListActivity ;
    @Rule
    public ActivityTestRule<MembersListActivity> mActivityRule =
            new ActivityTestRule<>(MembersListActivity.class);

    @Before
    public void setActivity() {
        membersListActivity = mActivityRule.getActivity();
    }

    @Test
    public void testBottomSheetDisplayedTest() throws Exception{
        onView(withId(R.id.members_recycle_view)).perform(RecyclerViewActions.actionOnItemAtPosition(5, click()));
        onView(withId(R.id.container_relative)).check(matches(isDisplayed()));
    }

}
