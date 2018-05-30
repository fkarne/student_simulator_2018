package at.tugraz.morning08.a_students_life;

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

@RunWith(AndroidJUnit4.class)

public class StartMenuTest {
    @Rule
    public ActivityTestRule<StartMenuActivity> mRule = new ActivityTestRule<>(StartMenuActivity.class);

    @Test
    public void changeLanguageTwiceTest() throws Exception {
        //BeforeTest
        Espresso.onView(withId(R.id.options_btn)).perform(click());
        Espresso.onView(withId(R.id.lang_de_radio)).perform(click());
        Espresso.onView(withId(R.id.save_lang_btn)).perform(click());

        //change to english
        Espresso.onView(withId(R.id.options_btn)).perform(click());
        Espresso.onView(withId(R.id.lang_en_radio)).perform(click());
        Espresso.onView(withId(R.id.save_lang_btn)).perform(click());
        Espresso.onView(withId(R.id.load_btn)).check(matches(withText("Load")));

        //change to german
        Espresso.onView(withId(R.id.options_btn)).perform(click());
        Espresso.onView(withId(R.id.lang_de_radio)).perform(click());
        Espresso.onView(withId(R.id.save_lang_btn)).perform(click());
        Espresso.onView(withId(R.id.load_btn)).check(matches(withText("Laden")));
    }

    @Test
    public void hideVirtualKeyboardTest() throws Exception {
        Espresso.onView(withId(R.id.start_btn)).perform(click());
        Espresso.onView(withId(R.id.user_name_tf)).perform(click());
        Espresso.onView(withId(R.id.introduction_tv)).perform(click());
        Espresso.onView(withId(R.id.next01_btn)).check(matches(isDisplayed()));
    }

    @Test
    public void goBackFromOptions(){
        Espresso.onView(withId(R.id.options_btn)).perform(click());
        Espresso.pressBack();
        Espresso.onView(withText(R.string.load_btn)).check(matches(isDisplayed()));
    }

    /*
    @Test
    public void SaveLoadTest() throws Exception {
        Espresso.onView(withId(R.id.start_btn)).perform(click());
        Espresso.onView(withId(R.id.user_name_tf)).perform(clearText(), typeText("Name")).perform(closeSoftKeyboard());
        Espresso.onView(withId(R.id.next01_btn)).perform(click());
        Espresso.onView(withId(R.id.next02_btn)).perform(click());
        Espresso.onView(withId(R.id.social_img_btn)).perform(click());
        Espresso.onView(withId(R.id.partying_btn)).perform(click());
        Espresso.pressBack();
        Espresso.pressBack();
        Espresso.onView(withText(R.string.yes_btn)).perform(click());
        Espresso.onView(withId(R.id.load_btn)).perform(click());
        assertEquals(28, Student.getInstance().getTime().getTimeUnit());
    }
    */
}
