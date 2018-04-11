package at.tugraz.morning08.a_students_life;

import org.junit.Before;
import org.junit.Test;

import at.tugraz.morning08.a_students_life.data.Calendar;
import at.tugraz.morning08.a_students_life.data.Event;

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

        Event event = new Event("SA-Prüfung", 1, 32, "Prüfung");
        calendar.addEvent(event);
        assertEquals(event, calendar.getNextEvent());

        Event event2 = new Event("SW-Deadline", 5, 16, "Deadline");
        calendar.addEvent(event2);
        assertEquals(event, calendar.getNextEvent());
    }

    @Test
    public void getEventAtTest() throws Exception {

        assertEquals(null, calendar.getEventAt(0));

        Event event = new Event("SA-Prüfung", 1, 32, "Prüfung");
        calendar.addEvent(event);

        assertEquals(event, calendar.getEventAt(0));

        Event event2 = new Event("SA-Vorlesung", 1, 32, "Vorlesung");
        calendar.addEvent(event2);

        assertEquals(event2, calendar.getEventAt(1));
        assertEquals(event, calendar.getEventAt(0));
    }

}