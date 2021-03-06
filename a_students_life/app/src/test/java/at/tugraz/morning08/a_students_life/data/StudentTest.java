package at.tugraz.morning08.a_students_life.data;

import org.junit.Before;
import org.junit.Test;

import at.tugraz.morning08.a_students_life.data.Event;
import at.tugraz.morning08.a_students_life.data.Stats;
import at.tugraz.morning08.a_students_life.data.Student;
import at.tugraz.morning08.a_students_life.data.Time;
import at.tugraz.morning08.a_students_life.handler.EventHandler;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class StudentTest {
    Student student;
    Stats stats;
    Time time;
    Event event;

    @Before
    public void beforeTest() throws Exception {
        student = Student.getInstance();
        student.setName("Florian Karner");
        student.setGender("male");
        student.setStudie("Informatik");
        student.setCash(200);
        student.setEcts(50);

        stats = new Stats();
        stats.setEnergy(100);
        stats.setStress(100);
        stats.setHunger(100);
        stats.setSocial(100);
        student.setStats(stats);

        time = new Time();
        time.setTimeUnit(16);
        time.setDay(1);
        student.setTime(time);

        event = new Event(1, new Time(1, 32), Event.Type.Exam, 20, 3);
        student.clearEventList();
    }

    @Test
    public void getNameTest() throws Exception {
        assertEquals("Florian Karner", student.getName());
    }

    @Test
    public void getGenderTest() throws Exception {
        assertEquals("male", student.getGender());
    }

    @Test
    public void getStudieTest() throws Exception {
        assertEquals("Informatik", student.getStudie());
    }

    @Test
    public void setNameTest() throws Exception {
        student.setName("Chris Seidlinger");
        assertEquals("Chris Seidlinger", student.getName());
    }

    @Test
    public void setGenderTest() throws Exception {
        student.setGender("female");
        assertEquals("female", student.getGender());
    }

    @Test
    public void setStudieTest() throws Exception {
        student.setStudie("BWL");
        assertEquals("BWL", student.getStudie());
    }

    @Test
    public void getStatsTest() throws Exception {
        assertEquals(stats, student.getStats());
    }

    @Test
    public void getTimeTest() throws Exception {
        assertEquals(time, student.getTime());
    }

    @Test
    public void setStatsTest() throws Exception {
        Stats newStats = new Stats();
        student.setStats(newStats);
        assertEquals(newStats, student.getStats());
    }

    @Test
    public void setTimeTest() throws Exception {
        Time newTime = new Time();
        student.setTime(newTime);
        assertEquals(newTime, student.getTime());
    }

    @Test
    public void getCashTest() throws Exception {
        assertEquals(200, student.getCash());
    }

    @Test
    public void setCashTest() throws Exception {
        student.setCash(5000);
        assertEquals(5000, student.getCash());
    }

    @Test
    public void getEctsTest() throws Exception {
        assertEquals(50, student.getEcts());
    }

    @Test
    public void setEctsTest() throws Exception {
        student.setEcts(160);
        assertEquals(160, student.getEcts());
    }

    @Test
    public void addCashTest() throws Exception {
        student.addCash(150);
        assertEquals(350, student.getCash());
    }

    @Test
    public void spendCashTest() throws Exception {
        student.spendCash(150);
        assertEquals(50, student.getCash());
    }

    @Test
    public void addEctsTest() throws Exception {

        student.addEcts(5);
        assertEquals(55, student.getEcts());

        student.addEcts(190);
        assertEquals(245, student.getEcts());
    }

    @Test
    public void addTimeUnitsTest() {
        student.addTimeUnits(2);
        assertEquals(98, student.getStats().getSocial());
        assertEquals(98, student.getStats().getHunger());
        assertEquals(98, student.getStats().getStress());
        assertEquals(98, student.getStats().getEnergy());
        assertEquals(18, student.getTime().getTimeUnit());
    }

    @Test
    public void addEventTest() {
        student.addEvent(event);
        assertEquals(1, student.getEventList().size());
    }

    @Test
    public void deleteEventTest() {
        student.addEvent(event);
        assertEquals(1, student.getEventList().size());
        student.deleteEvent(event);
        assert(student.getEventList().size() == 0);
    }

    @Test
    public void getNextExamTest() {
        Event exam1 = new Event(2, new Time(1, 32), Event.Type.Exam, 20, 3);
        Event lecture = new Event(2, new Time(1, 32), Event.Type.Lecture, exam1, 0, 3);
        Event exam2 = new Event(3, new Time(1, 40), Event.Type.Exam, 20, 4);

        student.addEvent(exam1);
        student.addEvent(lecture);
        student.addEvent(exam2);

        assertEquals(exam1, student.getNextExam());

        student.clearEventList();

        student.addEvent(lecture);
        student.addEvent(exam2);
        student.addEvent(exam1);

        assertEquals(exam1, student.getNextExam());
    }
}