package at.tugraz.morning08.a_students_life;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import at.tugraz.morning08.a_students_life.data.Student;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.doubleClick;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)

public class MainPageTest {
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
    public void openLoseConditionDialogTest()
    {
        Student.getInstance().getStats().setHunger(5);
        Espresso.onView(withId(R.id.energy_img_btn)).perform(click());
        Espresso.onView(withId(0)).perform(click());
        assertEquals(0, Student.getInstance().getStats().getHunger());
        Espresso.onView(withText(R.string.lose_gameOver)).check(matches(isDisplayed()));
        Espresso.onView(withText(R.string.lose_btnOk)).perform(click());
    }

    @Test
    public void openWinConditionDialogTest()
    {
        Espresso.onView(withId(R.id.energy_img_btn)).perform(click());
        Student.getInstance().setEcts(180);
        Student.getInstance().setStudie("Informatics");
        Espresso.onView(withId(0)).perform(click());
        Espresso.onView(withText(R.string.win_congrats)).check(matches(isDisplayed()));
        Espresso.onView(withText(R.string.win_btnOk)).perform(click());
    }

    @Test
    public void activityHungerButtonTest() {
        Student.getInstance().getStats().setHunger(50);
        Espresso.onView(withId(R.id.hunger_img_btn)).perform(click());
        Espresso.onView(withId(0)).perform(click());
        Espresso.onView(withId(R.id.popUp_activity_ll)).check(matches(isDisplayed()));
        assertEquals(60, Student.getInstance().getStats().getHunger());
    }

    @Test
    public void activityEnergyButtonTest() {
        Student.getInstance().getStats().setEnergy(50);
        Espresso.onView(withId(R.id.energy_img_btn)).perform(click());
        Espresso.onView(withId(0)).perform(click());
        Espresso.onView(withId(R.id.popUp_activity_ll)).check(matches(isDisplayed()));
        assertEquals(100, Student.getInstance().getStats().getEnergy());
    }

    @Test
    public void activityStressButtonTest() {
        Student.getInstance().getStats().setStress(50);
        Espresso.onView(withId(R.id.stress_img_btn)).perform(click());
        Espresso.onView(withId(0)).perform(click());
        Espresso.onView(withId(R.id.popUp_activity_ll)).check(matches(isDisplayed()));
        assertEquals(60, Student.getInstance().getStats().getStress());
    }

    @Test
    public void activitySocialButtonTest() {
        Student.getInstance().getStats().setSocial(50);
        Espresso.onView(withId(R.id.social_img_btn)).perform(click());
        Espresso.onView(withId(0)).perform(click());
        Espresso.onView(withId(R.id.popUp_activity_ll)).check(matches(isDisplayed()));
        assertEquals(60, Student.getInstance().getStats().getSocial());
    }

    @Test
    public void activityMoneyButtonTest() {
        Student.getInstance().setCash(200);
        Espresso.onView(withId(R.id.money_img_btn)).perform(click());
        Espresso.onView(withId(0)).perform(click());
        Espresso.onView(withId(R.id.popUp_activity_ll)).check(matches(isDisplayed()));
        assertEquals(300, Student.getInstance().getCash());
    }

    @Test
    public void activityStudyButtonTest() {
        //TODO set probability for event

        Espresso.onView(withId(R.id.study_img_btn)).perform(click());
        Espresso.onView(withId(0)).perform(click());
        Espresso.onView(withId(R.id.popUp_activity_ll)).check(matches(isDisplayed()));

        //TODO check if probability increased
    }

    @Test
    public void calendarButtonTest(){
        Espresso.onView(withId(R.id.calender_img_btn)).perform(click());
        Espresso.onView(withId(R.id.calendarPage)).check(matches(isDisplayed()));
    }

    @Test
    public void calendarBackButtonTest(){
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
