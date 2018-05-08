package at.tugraz.morning08.a_students_life.data;

import org.junit.Before;
import org.junit.Test;


import at.tugraz.morning08.a_students_life.data.Event;
import at.tugraz.morning08.a_students_life.data.Time;

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
        event = new Event("SA-Prüfung", new Time(1, 32), "Prüfung");
    }

    @Test
    public void getNameTest() throws Exception {
        assertEquals("SA-Prüfung", event.getName());
    }

    @Test
    public void getTimeTest() throws Exception {
        Time time2 = new Time();
        Event event2 = new Event("3. Prüfung", time2, "Prüfung");
        assertEquals(time2, event2.getTime());

        Time time3 = new Time(1, 1);
        Event event3 = new Event("3. Prüfung", time3, "Prüfung");
        assertEquals(time3, event3.getTime());
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
    public void setTypeTest() throws Exception {
        event.setType("Vorlesung");
        assertEquals("Vorlesung", event.getType());
    }
}