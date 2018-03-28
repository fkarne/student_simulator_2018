package at.tugraz.morning08.a_students_life;

import org.junit.Before;
import org.junit.Test;

import at.tugraz.morning08.a_students_life.data.Stats;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class StatsTest {
    Stats stats;

    @Before
    public void beforeTest() throws Exception {
        stats = new Stats();
        stats.setEnergy(70);
        stats.setHunger(70);
        stats.setSocial(70);
        stats.setStress(70);
    }

    @Test
    public void getEnergyTest() throws Exception {
        assertEquals(70, stats.getEnergy());
    }

    @Test
    public void setEnergyTest() throws Exception {
        stats.setEnergy(50);
        assertEquals(50, stats.getEnergy());
    }

    @Test
    public void increaseEnergyTest() throws Exception {
        stats.increaseEnergy(10);
        assertEquals(80, stats.getEnergy());
        stats.increaseEnergy(30);
        assertEquals(100, stats.getEnergy());
    }

    @Test
    public void decreaseEnergyTest() throws Exception {
        stats.decreaseEnergy(10);
        assertEquals(60, stats.getEnergy());
        stats.decreaseEnergy(80);
        assertEquals(0, stats.getEnergy());
    }

    @Test
    public void getStressTest() throws Exception {
        assertEquals(70, stats.getStress());
    }

    @Test
    public void setStressTest() throws Exception {
        stats.setStress(50);
        assertEquals(50, stats.getStress());
    }

    @Test
    public void increaseStressTest() throws Exception {
        stats.increaseStress(10);
        assertEquals(80, stats.getStress());
        stats.increaseStress(30);
        assertEquals(100, stats.getStress());
    }

    @Test
    public void decreaseStressTest() throws Exception {
        stats.decreaseStress(10);
        assertEquals(60, stats.getStress());
        stats.decreaseStress(80);
        assertEquals(0, stats.getStress());
    }

    @Test
    public void getHungerTest() throws Exception {
        assertEquals(70, stats.getHunger());
    }

    @Test
    public void setHungerTest() throws Exception {
        stats.setHunger(50);
        assertEquals(50, stats.getHunger());
    }

    @Test
    public void increaseHungerTest() throws Exception {
        stats.increaseHunger(10);
        assertEquals(80, stats.getHunger());
        stats.increaseHunger(30);
        assertEquals(100, stats.getHunger());
    }

    @Test
    public void decreaseHungerTest() throws Exception {
        stats.decreaseHunger(10);
        assertEquals(60, stats.getHunger());
        stats.decreaseHunger(80);
        assertEquals(0, stats.getHunger());
    }

    @Test
    public void getSocialTest() throws Exception {
        assertEquals(70, stats.getSocial());
    }

    @Test
    public void setSocialTest() throws Exception {
        stats.setSocial(50);
        assertEquals(50, stats.getSocial());
    }

    @Test
    public void increaseSocialTest() throws Exception {
        stats.increaseSocial(10);
        assertEquals(80, stats.getSocial());
        stats.increaseSocial(30);
        assertEquals(100, stats.getSocial());
    }

    @Test
    public void decreaseSocialTest() throws Exception {
        stats.decreaseSocial(10);
        assertEquals(60, stats.getSocial());
        stats.decreaseSocial(80);
        assertEquals(0, stats.getSocial());
    }
}