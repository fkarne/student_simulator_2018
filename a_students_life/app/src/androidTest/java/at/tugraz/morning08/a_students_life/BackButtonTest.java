package at.tugraz.morning08.a_students_life;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.doubleClick;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class BackButtonTest {
    @Rule
    public ActivityTestRule<StartMenuActivity> mRule = new ActivityTestRule<>(StartMenuActivity.class);

    @Before
    public void beforeTest()
    {
        Espresso.onView(withId(R.id.start_btn)).perform(click());
        Espresso.onView(withId(R.id.user_name_tf)).perform(replaceText("Test Student"));
        Espresso.onView(withText(R.string.introduction_tv)).perform(doubleClick());
        Espresso.onView(withId(R.id.next01_btn)).perform(click());
        Espresso.onView(withId(R.id.next02_btn)).perform(click());
    }

    @Test
    public void goBackInMainPagePressNo() throws Exception
    {
        Espresso.pressBack();
        Espresso.onView(withText(R.string.exit_game)).check(matches(isDisplayed()));
        Espresso.onView(withText(R.string.no_btn)).perform(click());
        Espresso.onView(withId(R.id.mainPage)).check(matches(isDisplayed()));
    }

    @Test
    public void goBackInAlert()
    {
        Espresso.pressBack();
        Espresso.onView(withText(R.string.exit_game)).check(matches(isDisplayed()));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.mainPage)).check(matches(isDisplayed()));
    }

    /*@Test
    public void goBackStartMenu()
    {
        Espresso.pressBack();
        mRule.getActivity().getBackPressedAlert().getButton(AlertDialog.BUTTON_POSITIVE).callOnClick();
        Espresso.onView(withId(R.id.startPage_fl)).check(matches(isDisplayed()));
    }
    */
}
