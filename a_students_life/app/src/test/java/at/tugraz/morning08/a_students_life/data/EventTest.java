package at.tugraz.morning08.a_students_life.data;

import org.junit.Before;
import org.junit.Test;

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
        event = new Event(1, new Time(1, 32), Event.Type.Exam, 20, 3);
    }

    @Test
    public void getNameTest() throws Exception {
        assertEquals(1, event.getNameKey());
    }

    @Test
    public void getTimeTest() throws Exception {
        Time time2 = new Time();
        Event event2 = new Event(2, time2, Event.Type.Exam, 20, 4);
        assertEquals(time2, event2.getTime());

        Time time3 = new Time(1, 1);
        Event event3 = new Event(3, time3, Event.Type.Exam, 20, 5);
        assertEquals(time3, event3.getTime());
    }

    @Test
    public void getTypeTest() throws Exception {
        assertEquals(Event.Type.Exam, event.getType());
    }

    @Test
    public void setNameTest() throws Exception {
        event.setNameKey(4);
        assertEquals(4, event.getNameKey());
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
    public void getEctsTest() throws Exception {
        assertEquals(3, event.getEcts());
    }

    @Test
    public void setEctsTest() throws Exception {
        event.setEcts(123);
        assertEquals(123, event.getEcts());
    }

    @Test
    public void getExamTest() throws Exception {
        assertEquals(null, event.getExam());

        Event exam = new Event(5, new Time(2, 32), Event.Type.Exam, 50, 3);
        Event lecture = new Event(5, new Time(2, 10), Event.Type.Lecture, exam);
        assertEquals(exam, lecture.getExam());
    }

    @Test
    public void setExamTest() throws  Exception {
        Event exam = new Event(1, new Time(2, 32), Event.Type.Exam, 50, 3);
        event.setExam(exam);
        assertEquals(exam, event.getExam());
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