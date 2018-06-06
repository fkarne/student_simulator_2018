package at.tugraz.morning08.a_students_life;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import at.tugraz.morning08.a_students_life.data.Calendar;
import at.tugraz.morning08.a_students_life.data.Event;
import at.tugraz.morning08.a_students_life.data.Student;
import at.tugraz.morning08.a_students_life.data.Time;
import at.tugraz.morning08.a_students_life.handler.EventHandler;

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
        Espresso.pressBack();
        Student.getInstance().clearEventList();
        Calendar.getInstance().clear();
        EventHandler.updateCalendarList();
        Espresso.onView(withId(R.id.calender_img_btn)).perform(click());

        Event exam = new Event(R.string.lv_analysis_t1, new Time(5, 20), Event.Type.Exam, 20, 4);
        Event lecture = new Event(R.string.lv_analysis_t1, new Time(1, 16), Event.Type.Lecture, exam, 0, 3);

        Student.getInstance().addEvent(exam);
        Student.getInstance().addEvent(lecture);

        Espresso.pressBack();
        EventHandler.updateCalendarList();
        Calendar.getInstance().sortEvents();
        Espresso.onView(withId(R.id.calender_img_btn)).perform(click());

        Time system_time = new Time(Student.getInstance().getTime().getDay(), Student.getInstance().getTime().getTimeUnit());
        Espresso.onView(withId(0)).perform(click());

        assertNotEquals(system_time.getTimeUnit(), Student.getInstance().getTime().getTimeUnit());
    }

    // calls invalid lecture
    @Test
    public void visitLectureNotTest()
    {
        Espresso.pressBack();
        Student.getInstance().clearEventList();
        Calendar.getInstance().clear();
        EventHandler.updateCalendarList();
        Espresso.onView(withId(R.id.calender_img_btn)).perform(click());

        Event exam = new Event(R.string.lv_analysis_t1, new Time(5, 20), Event.Type.Exam, 20, 4);
        Event lecture = new Event(R.string.lv_analysis_t1, new Time(1, 20), Event.Type.Lecture, exam, 0, 3);

        Student.getInstance().addEvent(exam);
        Student.getInstance().addEvent(lecture);

        Espresso.pressBack();
        EventHandler.updateCalendarList();
        Calendar.getInstance().sortEvents();
        Espresso.onView(withId(R.id.calender_img_btn)).perform(click());

        Time system_time = new Time(Student.getInstance().getTime().getDay(), Student.getInstance().getTime().getTimeUnit());
        Espresso.onView(withId(0)).perform(click());

        assertEquals(system_time.getDay(), Student.getInstance().getTime().getDay());
        assertEquals(system_time.getTimeUnit(), Student.getInstance().getTime().getTimeUnit());
    }

    @Test
    public void testButton(){
        Espresso.pressBack();
        Espresso.onView(withId(R.id.mainPage)).check(matches(isDisplayed()));
    }
}
