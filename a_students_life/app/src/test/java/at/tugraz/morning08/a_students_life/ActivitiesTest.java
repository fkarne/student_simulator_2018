package at.tugraz.morning08.a_students_life;

import org.junit.Before;
import org.junit.Test;

import at.tugraz.morning08.a_students_life.data.Activities;
import at.tugraz.morning08.a_students_life.data.Stats;
import at.tugraz.morning08.a_students_life.data.Student;
import at.tugraz.morning08.a_students_life.data.Time;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ActivitiesTest {
    Student student;

    @Before
    public void beforeTest() throws Exception {
        student = Student.getInstance();
        student.setCash(200);

        Stats stats = new Stats();
        stats.setEnergy(100);
        stats.setStress(100);
        stats.setHunger(100);
        stats.setSocial(100);
        student.setStats(stats);

        Time time = new Time();
        time.setTimeUnit(16);
        time.setDay(1);
        student.setTime(time);
    }

    @Test
    public void askForMoneyTest() throws Exception {
        assertEquals(200, student.getCash());

        Activities.askForMoney(student);
        assertEquals(300, student.getCash());
        assertEquals(98, student.getStats().getSocial());
        assertEquals(98, student.getStats().getHunger());
        assertEquals(98, student.getStats().getStress());
        assertEquals(98, student.getStats().getEnergy());
        assertEquals(18, student.getTime().getTimeUnit());

        Activities.askForMoney(student);
        assertEquals(400, student.getCash());
        assertEquals(96, student.getStats().getSocial());
        assertEquals(96, student.getStats().getHunger());
        assertEquals(96, student.getStats().getStress());
        assertEquals(96, student.getStats().getEnergy());
        assertEquals(20, student.getTime().getTimeUnit());
    }

    @Test
    public void watchTVTest() throws Exception {
        student.getStats().setStress(50);

        Activities.watchTV(student);
        assertEquals(98, student.getStats().getSocial());
        assertEquals(98, student.getStats().getHunger());
        assertEquals(60, student.getStats().getStress());
        assertEquals(98, student.getStats().getEnergy());
        assertEquals(18, student.getTime().getTimeUnit());

        Activities.watchTV(student);
        assertEquals(96, student.getStats().getSocial());
        assertEquals(96, student.getStats().getHunger());
        assertEquals(70, student.getStats().getStress());
        assertEquals(96, student.getStats().getEnergy());
        assertEquals(20, student.getTime().getTimeUnit());
    }

    @Test
    public void phoneCallTest() throws Exception {
        student.getStats().setSocial(50);

        Activities.phoneCall(student);
        assertEquals(60, student.getStats().getSocial());
        assertEquals(98, student.getStats().getHunger());
        assertEquals(98, student.getStats().getStress());
        assertEquals(98, student.getStats().getEnergy());
        assertEquals(18, student.getTime().getTimeUnit());

        Activities.phoneCall(student);
        assertEquals(70, student.getStats().getSocial());
        assertEquals(96, student.getStats().getHunger());
        assertEquals(96, student.getStats().getStress());
        assertEquals(96, student.getStats().getEnergy());
        assertEquals(20, student.getTime().getTimeUnit());
    }

    @Test
    public void eatTest() throws Exception {
        student.getStats().setHunger(50);

        Activities.eat(student);
        assertEquals(98, student.getStats().getSocial());
        assertEquals(60, student.getStats().getHunger());
        assertEquals(98, student.getStats().getStress());
        assertEquals(98, student.getStats().getEnergy());
        assertEquals(18, student.getTime().getTimeUnit());

        Activities.eat(student);
        assertEquals(96, student.getStats().getSocial());
        assertEquals(70, student.getStats().getHunger());
        assertEquals(96, student.getStats().getStress());
        assertEquals(96, student.getStats().getEnergy());
        assertEquals(20, student.getTime().getTimeUnit());
    }

    @Test
    public void sleepTest() throws Exception {
        student.getStats().setEnergy(50);

        Activities.sleep(student);
        assertEquals(98, student.getStats().getSocial());
        assertEquals(98, student.getStats().getHunger());
        assertEquals(98, student.getStats().getStress());
        assertEquals(60, student.getStats().getEnergy());
        assertEquals(18, student.getTime().getTimeUnit());

        Activities.sleep(student);
        assertEquals(96, student.getStats().getSocial());
        assertEquals(96, student.getStats().getHunger());
        assertEquals(96, student.getStats().getStress());
        assertEquals(70, student.getStats().getEnergy());
        assertEquals(20, student.getTime().getTimeUnit());
    }

}