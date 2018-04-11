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
public class EventTest {
    Event event;

    @Before
    public void beforeTest() throws Exception {
        event = new Event("SA-Prüfung", 1, 32, "Prüfung");
    }

    @Test
    public void getNameTest() throws Exception {
        assertEquals("SA-Prüfung", event.getName());
    }

    @Test
    public void getDayTest() throws Exception {
        assertEquals(1, event.getDay());
    }

    @Test
    public void getTimeUnitTest() throws Exception {
        assertEquals(32, event.getTimeUnit());
    }

    @Test
    public void getTypeTest() throws Exception {
        assertEquals("Prüfung", event.getType());
    }

    @Test
    public void setNameTest() throws Exception {
        event.setName("SA-Vorlesung");
        assertEquals("SA-Vorlesung", event.getName());
    }

    @Test
    public void setDayTest() throws Exception {
        event.setDay(2);
        assertEquals(2, event.getDay());
    }

    @Test
    public void setTimeUnitTest() throws Exception {
        event.setTimeUnit(64);
        assertEquals(64, event.getTimeUnit());
    }

    @Test
    public void setTypeTest() throws Exception {
        event.setType("Vorlesung");
        assertEquals("Vorlesung", event.getType());
    }
}