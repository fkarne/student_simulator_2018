package at.tugraz.morning08.a_students_life;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import at.tugraz.morning08.a_students_life.data.Calendar;
import at.tugraz.morning08.a_students_life.data.Student;
import at.tugraz.morning08.a_students_life.data.Time;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.doubleClick;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)

public class CalendarTest {
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
        Espresso.onView(withId(R.id.calender_img_btn)).perform(click());
    }


    // calls valid lecture (student time == event time)
    @Test
    public void visitLectureTest()
    {
        Time system_time = new Time(Student.getInstance().getTime().getDay(), Student.getInstance().getTime().getTimeUnit());
        Espresso.onView(withText(R.string.lv_analysis_t1)).perform(click());
        assertNotEquals(system_time.getTimeUnit(), Student.getInstance().getTime().getTimeUnit());
    }

    // calls invalid lecture
    @Test
    public void visitLectureNotTest()
    {
        Time system_time = new Time(Student.getInstance().getTime().getDay(), Student.getInstance().getTime().getTimeUnit());
        Espresso.onView(withText(R.string.lv_foundations_computer_science)).perform(click());
        assertEquals(system_time.getDay(), Student.getInstance().getTime().getDay());
        assertEquals(system_time.getTimeUnit(), Student.getInstance().getTime().getTimeUnit());
    }

    @Test
    public void testButton(){
        Espresso.pressBack();
        Espresso.onView(withId(R.id.mainPage)).check(matches(isDisplayed()));
    }

    // calls valid lecture (student time == event time with 2h buffer)
    @Test
    public void visitLectureEarlyTest()
    {
        Student.getInstance().setTime(new Time(2, 16));
        Time system_time = new Time(Student.getInstance().getTime().getDay(), Student.getInstance().getTime().getTimeUnit());
        Espresso.onView(withText(R.string.lv_foundations_computer_science)).perform(click());
        assertNotEquals(system_time.getTimeUnit(), Student.getInstance().getTime().getTimeUnit());

        Student.getInstance().setTime(new Time(2, 17));
        system_time.setDay(Student.getInstance().getTime().getDay());
        system_time.setTimeUnit(Student.getInstance().getTime().getTimeUnit());
        Espresso.onView(withText(R.string.lv_foundations_computer_science)).perform(click());
        assertNotEquals(system_time.getTimeUnit(), Student.getInstance().getTime().getTimeUnit());
    }

    // calls invalid lecture
    @Test
    public void visitLectureEarlyNotTest()
    {
        Student.getInstance().setTime(new Time(2, 14));
        Time system_time = new Time(Student.getInstance().getTime().getDay(), Student.getInstance().getTime().getTimeUnit());
        Espresso.onView(withText(R.string.lv_foundations_computer_science)).perform(click());
        assertEquals(system_time.getDay(), Student.getInstance().getTime().getDay());
        assertEquals(system_time.getTimeUnit(), Student.getInstance().getTime().getTimeUnit());
    }
}
