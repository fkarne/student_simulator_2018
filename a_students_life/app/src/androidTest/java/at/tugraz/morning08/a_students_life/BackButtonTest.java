package at.tugraz.morning08.a_students_life;

import android.app.AlertDialog;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class BackButtonTest {
    @Rule
    public ActivityTestRule<MainPageActivity> mRule = new ActivityTestRule<>(MainPageActivity.class);


    @Test
    public void goBackInProgramm() throws Exception
    {
        Espresso.onView(withId(R.id.stats)).perform(click());
        Espresso.pressBack();
        Espresso.onView(withId(R.id.mainPage)).check(matches(isDisplayed()));
    }

    @Test
    public void goBackInMainPagePressNo() throws Exception
    {
        Espresso.pressBack();
        Espresso.onView(withText("Spiel beenden")).check(matches(isDisplayed()));
        mRule.getActivity().getBackPressedAlert().getButton(AlertDialog.BUTTON_NEGATIVE).callOnClick();
        Espresso.onView(withId(R.id.mainPage)).check(matches(isDisplayed()));
    }

    @Test
    public void goBackInAlert()
    {
        Espresso.pressBack();
        Espresso.onView(withText("Spiel beenden")).check(matches(isDisplayed()));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.mainPage)).check(matches(isDisplayed()));
    }
}
