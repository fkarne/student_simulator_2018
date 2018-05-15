package at.tugraz.morning08.a_students_life;

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
        event = new Event("SA-Prüfung", new Time(1, 32), Event.Type.Exam, 20);
    }

    @Test
    public void getNameTest() throws Exception {
        assertEquals("SA-Prüfung", event.getName());
    }

    @Test
    public void getTimeTest() throws Exception {
        Time time2 = new Time();
        Event event2 = new Event("3. Prüfung", time2, Event.Type.Exam, 20);
        assertEquals(time2, event2.getTime());

        Time time3 = new Time(1, 1);
        Event event3 = new Event("3. Prüfung", time3, Event.Type.Exam, 20);
        assertEquals(time3, event3.getTime());
    }

    @Test
    public void getTypeTest() throws Exception {
        assertEquals(Event.Type.Exam, event.getType());
    }

    @Test
    public void setNameTest() throws Exception {
        event.setName("SA-Vorlesung");
        assertEquals("SA-Vorlesung", event.getName());
    }

    @Test
    public void setTypeTest() throws Exception {
        event.setType(Event.Type.Lecture);
        assertEquals(Event.Type.Lecture, event.getType());
    }

    @Test
    public void getProbabilityPercentageTest() throws Exception {
        assertEquals(20, event.getProbabilityPercentage());
    }

    @Test
    public void setProbabilityPercentageTest() throws Exception {
        event.setProbabilityPercentage(50);
        assertEquals(50, event.getProbabilityPercentage());
    }

    @Test
    public void increaseProbabilityTest() throws Exception {
        event.increaseProbability(10);
        assertEquals(30, event.getProbabilityPercentage());
    }

    @Test
    public void checkBorderProbabilityTest() throws Exception {
        event.setProbabilityPercentage(150);
        event.checkBorderProbability();
        assertEquals(100, event.getProbabilityPercentage());

        event.increaseProbability(10);
        event.checkBorderProbability();
        assertEquals(100, event.getProbabilityPercentage());
    }
}