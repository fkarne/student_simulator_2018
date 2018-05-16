package at.tugraz.morning08.a_students_life;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.Espresso;
import android.widget.ProgressBar;
import at.tugraz.morning08.a_students_life.data.Student;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)

public class MainPageTest {
    @Rule
    public ActivityTestRule<MainPageActivity> mRule = new ActivityTestRule<>(MainPageActivity.class);

    /*@Test
    public void goToStatsOverviewTest() throws Exception {
        Espresso.onView(withId(R.id.stats)).perform(click());
        Espresso.onView(withId(R.id.statsPage)).check(matches(isDisplayed()));
    }
    */

    @Test
    public void openLoseConditionDialogTest()
    {
        Student.getInstance().getStats().setHunger(5);
        Espresso.onView(withId(R.id.energy_img_btn)).perform(click());
        Espresso.onView(withId(R.id.sleep_btn)).perform(click());
        //Espresso.onView(withId(R.id.popUp_eneregy_ll)).check(matches(isDisplayed()));
        //Espresso.onView(withId(R.id.mainPage)).check(matches(isDisplayed()));
        assertEquals(0, Student.getInstance().getStats().getHunger());
        Espresso.onView(withText(R.string.lose_gameOver)).check(matches(isDisplayed()));
    }

    /*@Test
    public void openWinConditionDialogTest()
    {
        Espresso.onView(withId(R.id.energy_img_btn)).perform(click());
        Student.getInstance().setEcts(180);
        Student.getInstance().setStudie("Informatics");
        Espresso.onView(withId(R.id.sleep_btn)).perform(click());
        //assertEquals(180, Student.getInstance().getEcts());
        Espresso.onView(withText(R.string.win_congrats)).check(matches(isDisplayed()));
    }
    */
    @Test
    public void activityHungerButtonTest() throws Exception {
        Student.getInstance().getStats().setHunger(50);
        Espresso.onView(withId(R.id.hunger_img_btn)).perform(click());
        Espresso.onView(withId(R.id.eat_btn)).perform(click());
        Espresso.onView(withId(R.id.popUp_hunger_ll)).check(matches(isDisplayed()));
        assertEquals(60, Student.getInstance().getStats().getHunger());
    }

    @Test
    public void activityEnergyButtonTest() throws Exception {
        Student.getInstance().getStats().setEnergy(50);
        Espresso.onView(withId(R.id.energy_img_btn)).perform(click());
        Espresso.onView(withId(R.id.sleep_btn)).perform(click());
        Espresso.onView(withId(R.id.popUp_eneregy_ll)).check(matches(isDisplayed()));
        assertEquals(100, Student.getInstance().getStats().getEnergy());
    }

    @Test
    public void activityStressButtonTest() throws Exception {
        Student.getInstance().getStats().setStress(50);
        Espresso.onView(withId(R.id.stress_img_btn)).perform(click());
        Espresso.onView(withId(R.id.watchTV_btn)).perform(click());
        Espresso.onView(withId(R.id.popUp_stress_ll)).check(matches(isDisplayed()));
        assertEquals(60, Student.getInstance().getStats().getStress());
    }

    @Test
    public void activitySocialButtonTest() throws Exception {
        Student.getInstance().getStats().setSocial(50);
        Espresso.onView(withId(R.id.social_img_btn)).perform(click());
        Espresso.onView(withId(R.id.phoneCall_btn)).perform(click());
        Espresso.onView(withId(R.id.popUp_social_ll)).check(matches(isDisplayed()));
        assertEquals(60, Student.getInstance().getStats().getSocial());
    }

    @Test
    public void activityMoneyButtonTest() throws Exception {
        Student.getInstance().setCash(200);
        Espresso.onView(withId(R.id.money_img_btn)).perform(click());
        Espresso.onView(withId(R.id.askForMoney_btn)).perform(click());
        Espresso.onView(withId(R.id.popUp_money_ll)).check(matches(isDisplayed()));
        assertEquals(300, Student.getInstance().getCash());
    }

    @Test
    public void activityStudyButtonTest() throws Exception {
        //TODO set probability for event

        Espresso.onView(withId(R.id.study_img_btn)).perform(click());
        Espresso.onView(withId(R.id.learning_btn)).perform(click());
        Espresso.onView(withId(R.id.popUp_study_ll)).check(matches(isDisplayed()));

        //TODO check if probability increased
    }

    @Test
    public void calendarButtonTest() throws Exception {
        Espresso.onView(withId(R.id.calender_img_btn)).perform(click());
        Espresso.onView(withId(R.id.calendarPage)).check(matches(isDisplayed()));
    }

    @Test
    public void calendarBackButtonTest() throws Exception {
        Espresso.onView(withId(R.id.calender_img_btn)).perform(click());
        Espresso.pressBack();
        Espresso.onView(withId(R.id.mainPage)).check(matches(isDisplayed()));
    }

    //TODO TEST?!
    /*
    @Test
    public void myProgressBarTest() throws Exception {
        Espresso.onView(withId(R.id.social)).perform(click());
        Espresso.onView(withId(R.id.partying_button)).perform(click());
        Espresso.onView(withId(R.id.partying_button)).perform(click());
        Espresso.onView(withId(R.id.partying_button)).perform(click());
        Espresso.onView(((ProgressBar)withId(R.id.progressBarStressMainPage))
                .getSecondaryProgressTintList()..check(matches(Color.RED);
    }
    */
}
