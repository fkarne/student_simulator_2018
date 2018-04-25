package at.tugraz.morning08.a_students_life;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.Espresso;

import at.tugraz.morning08.a_students_life.data.Student;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

import at.tugraz.morning08.a_students_life.data.Student;

import static org.junit.Assert.assertEquals;


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
    public void activityHungerButtonTest() throws Exception {
        Student.getInstance().getStats().setHunger(50);
        Espresso.onView(withId(R.id.hunger)).perform(click());
        Espresso.onView(withId(R.id.eat_button)).perform(click());
        Espresso.onView(withId(R.id.popUp_hunger_ll)).check(matches(isDisplayed()));
        assertEquals(60, Student.getInstance().getStats().getHunger());
    }

    @Test
    public void activityEnergyButtonTest() throws Exception {
        Student.getInstance().getStats().setEnergy(50);
        Espresso.onView(withId(R.id.energy)).perform(click());
        Espresso.onView(withId(R.id.sleep_button)).perform(click());
        Espresso.onView(withId(R.id.popUp_eneregy_ll)).check(matches(isDisplayed()));
        assertEquals(100, Student.getInstance().getStats().getEnergy());
    }

    @Test
    public void activityStressButtonTest() throws Exception {
        Student.getInstance().getStats().setStress(50);
        Espresso.onView(withId(R.id.stress)).perform(click());
        Espresso.onView(withId(R.id.watchTV_button)).perform(click());
        Espresso.onView(withId(R.id.popUp_stress_ll)).check(matches(isDisplayed()));
        assertEquals(60, Student.getInstance().getStats().getStress());
    }

    @Test
    public void activitySocialButtonTest() throws Exception {
        Student.getInstance().getStats().setSocial(50);
        Espresso.onView(withId(R.id.social)).perform(click());
        Espresso.onView(withId(R.id.phoneCall_button)).perform(click());
        Espresso.onView(withId(R.id.popUp_social_ll)).check(matches(isDisplayed()));
        assertEquals(60, Student.getInstance().getStats().getSocial());
    }

    @Test
    public void activityMoneyButtonTest() throws Exception {
        Student.getInstance().setCash(200);
        Espresso.onView(withId(R.id.money)).perform(click());
        Espresso.onView(withId(R.id.askForMoney_button)).perform(click());
        Espresso.onView(withId(R.id.popUp_money_ll)).check(matches(isDisplayed()));
        assertEquals(300, Student.getInstance().getCash());
    }

    @Test
    public void activityStudyButtonTest() throws Exception {
        //TODO set probability for exam

        Espresso.onView(withId(R.id.study)).perform(click());
        Espresso.onView(withId(R.id.learning_button)).perform(click());
        Espresso.onView(withId(R.id.popUp_study_ll)).check(matches(isDisplayed()));

        //TODO check if probability increased
    }

    @Test
    public void calendarButtonTest() throws Exception {
        Espresso.onView(withId(R.id.btnCalendar)).perform(click());
        Espresso.onView(withId(R.id.calendarPage)).check(matches(isDisplayed()));
    }

    @Test
    public void calendarBackButtonTest() throws Exception {
        Espresso.onView(withId(R.id.btnCalendar)).perform(click());
        Espresso.pressBack();
        Espresso.onView(withId(R.id.mainPage)).check(matches(isDisplayed()));
    }
}
