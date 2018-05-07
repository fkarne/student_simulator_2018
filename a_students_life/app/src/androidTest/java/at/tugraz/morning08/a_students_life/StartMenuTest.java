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
    public void changeLanguageToGermanTest() throws Exception {
        Espresso.onView(withId(R.id.options_btn)).perform(click());
        Espresso.onView(withId(R.id.lang_de_rb)).perform(click());
        Espresso.onView(withId(R.id.save_lang_btn)).perform(click());
        Espresso.onView(withId(R.id.options_btn)).check(matches(withText("Optionen")));
    }


    @Test
    public void changeLanguageTwiceTest() throws Exception {
        // change to german
        Espresso.onView(withId(R.id.options_btn)).perform(click());
        Espresso.onView(withId(R.id.lang_de_rb)).perform(click());
        Espresso.onView(withId(R.id.save_lang_btn)).perform(click());

        //change back to english
        Espresso.onView(withId(R.id.options_btn)).perform(click());
        Espresso.onView(withId(R.id.lang_en_rb)).perform(click());
        Espresso.onView(withId(R.id.save_lang_btn)).perform(click());
        Espresso.onView(withId(R.id.options_btn)).check(matches(withText("Options")));
    }

    @Test
    public void cancelOptionsTest() throws Exception {
        Espresso.onView(withId(R.id.options_btn)).perform(click());
        Espresso.onView(withId(R.id.lang_de_rb)).perform(click());
        Espresso.onView(withId(R.id.cancel_lang_btn)).perform(click());
        Espresso.onView(withId(R.id.options_btn)).check(matches(withText("Options")));
    }

    @Test
    public void hideVirtualKeyboardTest() throws Exception {
        Espresso.onView(withId(R.id.user_name_tf)).perform(click());
        Espresso.onView(withId(R.id.next01_btn)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.introduction_tv)).perform(click());
        Espresso.onView(withId(R.id.next01_btn)).check(matches(isDisplayed()));
    }
}
