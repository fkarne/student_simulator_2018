package at.tugraz.morning08.a_students_life;

import org.junit.Before;
import org.junit.Test;

import at.tugraz.morning08.a_students_life.data.Time;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TimeTest {
    Time time;

    @Before
    public void beforeTest() throws Exception {
        time = new Time();
        time.setDay(1);
        time.setTimeUnit(16);
    }

    @Test
    public void getDayTest() throws Exception {
        assertEquals(1, time.getDay());
    }

    @Test
    public void getTimeUnitTest() throws Exception {
        assertEquals(16, time.getTimeUnit());
    }

    @Test
    public void setDayTest() throws Exception {
        time.setDay(20);
        assertEquals(20, time.getDay());
    }

    @Test
    public void setTimeUnitTest() throws Exception {
        time.setTimeUnit(25);
        assertEquals(25, time.getTimeUnit());
    }

    @Test
    public void addTimeUnitsTest() throws Exception {
        time.addTimeUnits(16);
        assertEquals(32, time.getTimeUnit());
        assertEquals(1, time.getDay());

        time.addTimeUnits(20);
        assertEquals(4, time.getTimeUnit());
        assertEquals(2, time.getDay());

        time.addTimeUnits(16);
        assertEquals(20, time.getTimeUnit());
        assertEquals(2, time.getDay());

        time.addTimeUnits(100);
        assertEquals(24, time.getTimeUnit());
        assertEquals(4, time.getDay());
    }
}