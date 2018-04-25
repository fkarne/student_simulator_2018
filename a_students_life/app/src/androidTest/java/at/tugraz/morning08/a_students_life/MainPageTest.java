package at.tugraz.morning08.a_students_life;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.espresso.Espresso;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

import at.tugraz.morning08.a_students_life.data.Student;



@RunWith(AndroidJUnit4.class)

public class MainPageTest {
    @Rule
    public ActivityTestRule<MainPageActivity> mRule = new ActivityTestRule<>(MainPageActivity.class);

    @Test
    public void goToStatsOverviewTest() throws Exception {
        Espresso.onView(withId(R.id.stats)).perform(click());
        Espresso.onView(withId(R.id.statsPage)).check(matches(isDisplayed()));
    }

    @Test
    public void goBackToMainPageTest() throws Exception {
        Espresso.onView(withId(R.id.stats)).perform(click());
        Espresso.onView(withId(R.id.backButton)).perform(click());
        Espresso.onView(withId(R.id.mainPage)).check(matches(isDisplayed()));
    }

    @Test
    public void openLoseConditionDialogTest()
    {
        Student.getInstance().getStats().setHunger(5);
        Espresso.onView(withId(R.id.energy)).perform(click());
        Espresso.onView(withId(R.id.sleep_button)).perform(click());
        Espresso.onView(withId(R.id.popUp_eneregy_ll)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.mainPage)).check(matches(isDisplayed()));
        assertEquals(0, Student.getInstance().getStats().getHunger());
        Espresso.onView(withText("Game over!")).check(matches(isDisplayed()));
    }

    @Test
    public void calendarBackButtonTest() throws Exception {
        Espresso.onView(withId(R.id.btnCalendar)).perform(click());
        Espresso.pressBack();
        Espresso.onView(withId(R.id.mainPage)).check(matches(isDisplayed()));
    }

}
