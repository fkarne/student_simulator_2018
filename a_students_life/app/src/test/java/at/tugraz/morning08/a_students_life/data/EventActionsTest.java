package at.tugraz.morning08.a_students_life.data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EventActionsTest {
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
    public void lotteryWinTest() throws Exception {
        EventActions.lotteryWin(student);
        assertEquals( 10200, student.getCash());

    }

    @Test
    public void foundMoneyTest() throws Exception {
        EventActions.foundMoney(student);
        assertTrue(300 <= student.getCash() && student.getCash() <= 600);
    }

    @Test
    public void moneyFromGrandmaTest() throws Exception {
        EventActions.moneyFromGrandma(student);
        assertTrue(250 <= student.getCash() && student.getCash() <= 500);
    }

    @Test
    public void inheritTest() throws Exception {
        EventActions.inherit(student);
        assertTrue(5200 <= student.getCash() && student.getCash() <= 10200);
    }

    @Test
    public void helpedAFriendTest() throws Exception {
        EventActions.helpedAFriend(student);
        assertTrue(1700 <= student.getCash() && student.getCash() <= 2700);
    }

    @Test
    public void foundCerealBarTest() throws Exception {
        student.getStats().setHunger(50);

        EventActions.foundCerealBar(student);
        assertEquals(100, student.getStats().getSocial());
        assertEquals(55, student.getStats().getHunger());
        assertEquals(100, student.getStats().getStress());
        assertEquals(100, student.getStats().getEnergy());
        assertEquals(16, student.getTime().getTimeUnit());
    }

    @Test
    public void invitedToEat() throws Exception {
        student.getStats().setHunger(50);

        EventActions.invitedToEat(student);
        assertEquals(96, student.getStats().getSocial());
        assertEquals(75, student.getStats().getHunger());
        assertEquals(96, student.getStats().getStress());
        assertEquals(96, student.getStats().getEnergy());
        assertEquals(20, student.getTime().getTimeUnit());
    }

    @Test
    public void visitFastFoodRestaurant() throws Exception {
        student.getStats().setHunger(50);

        EventActions.visitFastFoodRestaurant(student);
        assertEquals(99, student.getStats().getSocial());
        assertEquals(65, student.getStats().getHunger());
        assertEquals(99, student.getStats().getStress());
        assertEquals(99, student.getStats().getEnergy());
        assertEquals(17, student.getTime().getTimeUnit());
    }


    @Test
    public void freeFoodDayAtUniCampus() throws Exception {
        student.getStats().setHunger(50);

        EventActions.freeFoodDayAtUniCampus(student);
        assertEquals(98, student.getStats().getSocial());
        assertEquals(65, student.getStats().getHunger());
        assertEquals(98, student.getStats().getStress());
        assertEquals(98, student.getStats().getEnergy());
        assertEquals(18, student.getTime().getTimeUnit());
    }
    
}
