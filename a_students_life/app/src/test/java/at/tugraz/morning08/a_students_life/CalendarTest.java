package at.tugraz.morning08.a_students_life;

import org.junit.Before;
import org.junit.Test;

import at.tugraz.morning08.a_students_life.data.Calendar;

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
        calendar.createEvent("SA-Prüfung", 1, 32, "Prüfung");
        calendar.createEvent("SW-Deadline", 5, 16, "Deadline");
    }

    @Test
    public void getFirstEventNameTest() throws Exception {
        assertEquals("SA-Prüfung", calendar.getNextEvent().getName());
    }

    @Test
    public void getFirstEventDayTest() throws Exception {
        assertEquals(1, calendar.getNextEvent().getDay());
    }

    @Test
    public void getFirstEventTimeUnitTest() throws Exception {
        assertEquals(32, calendar.getNextEvent().getTimeUnit());
    }

    @Test
    public void getFirstEventTypeTest() throws Exception {
        assertEquals("Prüfung", calendar.getNextEvent().getType());
    }

    @Test
    public void getSecondEventNameTest() throws Exception {
        assertEquals("SW-Deadline", calendar.getEventAt(1).getName());
    }

    @Test
    public void getSecondEventDayTest() throws Exception {
        assertEquals(5, calendar.getEventAt(1).getDay());
    }

    @Test
    public void getSecondEventTimeUnitTest() throws Exception {
        assertEquals(16, calendar.getEventAt(1).getTimeUnit());
    }

    @Test
    public void getSecondEventTypeTest() throws Exception {
        assertEquals("Deadline", calendar.getEventAt(1).getType());
    }
}