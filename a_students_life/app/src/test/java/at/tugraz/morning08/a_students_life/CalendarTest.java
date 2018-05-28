package at.tugraz.morning08.a_students_life;

import org.junit.Before;
import org.junit.Test;

import at.tugraz.morning08.a_students_life.data.Calendar;
import at.tugraz.morning08.a_students_life.data.Event;
import at.tugraz.morning08.a_students_life.data.Time;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CalendarTest {
    Calendar calendar;

    @Before
    public void beforeTest() throws Exception {
        calendar = Calendar.getInstance();
        calendar.clear();
    }

    @Test
    public void getNextEventTest() throws Exception {

        assertEquals(null, calendar.getNextEvent());

        Event event = new Event(1, new Time(1, 32), Event.Type.Exam, 20, 3);
        calendar.addEvent(event);
        assertEquals(event, calendar.getNextEvent());

        Event event2 = new Event(2, new Time(5, 16), Event.Type.Exam, 20, 4);
        calendar.addEvent(event2);
        assertEquals(event, calendar.getNextEvent());
    }

    @Test
    public void getEventAtTest() throws Exception {

        assertEquals(null, calendar.getEventAt(0));

        Event event = new Event(1, new Time(1, 32), Event.Type.Exam, 20, 3);
        calendar.addEvent(event);

        assertEquals(event, calendar.getEventAt(0));

        Event event2 = new Event(2, new Time(1, 32), Event.Type.Exam, 20, 4);
        calendar.addEvent(event2);

        assertEquals(event2, calendar.getEventAt(1));
        assertEquals(event, calendar.getEventAt(0));
    }

}