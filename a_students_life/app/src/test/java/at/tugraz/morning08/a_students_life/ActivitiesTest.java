package at.tugraz.morning08.a_students_life;

import org.junit.Before;
import org.junit.Test;

import at.tugraz.morning08.a_students_life.data.Activities;
import at.tugraz.morning08.a_students_life.data.Event;
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
        student.getStats().setEnergy(10);

        Activities.sleep(student);
        assertEquals(84, student.getStats().getSocial());
        assertEquals(84, student.getStats().getHunger());
        assertEquals(84, student.getStats().getStress());
        assertEquals(60, student.getStats().getEnergy());
        assertEquals(32, student.getTime().getTimeUnit());
        assertEquals(1, student.getTime().getDay());

        Activities.sleep(student);
        assertEquals(68, student.getStats().getSocial());
        assertEquals(68, student.getStats().getHunger());
        assertEquals(68, student.getStats().getStress());
        assertEquals(100, student.getStats().getEnergy());
        assertEquals(0, student.getTime().getTimeUnit());
        assertEquals(2, student.getTime().getDay());
    }

    @Test
    public void learnTest() throws Exception
    {
        student.addEvent(new Event("exam 01", new Time(10, 16), Event.Type.Exam, 20, null));
        student.getStats().setStress(10);

        Activities.learn(student);
        assertEquals(96, student.getStats().getSocial());
        assertEquals(96, student.getStats().getHunger());
        assertEquals(13, student.getStats().getStress());
        assertEquals(92, student.getStats().getEnergy());
        assertEquals(20, student.getTime().getTimeUnit());
        assertEquals(1, student.getTime().getDay());

        Activities.learn(student);
        assertEquals(92, student.getStats().getSocial());
        assertEquals(92, student.getStats().getHunger());
        assertEquals(16, student.getStats().getStress());
        assertEquals(84, student.getStats().getEnergy());
        assertEquals(24, student.getTime().getTimeUnit());
        assertEquals(1, student.getTime().getDay());
    }

    @Test
    public void napTest() throws Exception {
        student.getStats().setEnergy(50);

        Activities.nap(student);
        assertEquals(98, student.getStats().getSocial());
        assertEquals(98, student.getStats().getHunger());
        assertEquals(98, student.getStats().getStress());
        assertEquals(60, student.getStats().getEnergy());
        assertEquals(18, student.getTime().getTimeUnit());

        Activities.nap(student);
        assertEquals(96, student.getStats().getSocial());
        assertEquals(96, student.getStats().getHunger());
        assertEquals(96, student.getStats().getStress());
        assertEquals(70, student.getStats().getEnergy());
        assertEquals(20, student.getTime().getTimeUnit());
    }

    @Test
    public void goingOutToEatTest() throws Exception {
        student.getStats().setHunger(50);
        student.getStats().setSocial(50);

        Activities.goingOutToEat(student);
        assertEquals(55, student.getStats().getSocial());
        assertEquals(65, student.getStats().getHunger());
        assertEquals(96, student.getStats().getStress());
        assertEquals(96, student.getStats().getEnergy());
        assertEquals(20, student.getTime().getTimeUnit());
        assertEquals(150, student.getCash());

        Activities.goingOutToEat(student);
        assertEquals(60, student.getStats().getSocial());
        assertEquals(80, student.getStats().getHunger());
        assertEquals(92, student.getStats().getStress());
        assertEquals(92, student.getStats().getEnergy());
        assertEquals(24, student.getTime().getTimeUnit());
        assertEquals(100, student.getCash());
    }

    @Test
    public void readingBookTest() throws Exception {
        student.getStats().setStress(50);

        Activities.readingBook(student);
        assertEquals(99, student.getStats().getSocial());
        assertEquals(99, student.getStats().getHunger());
        assertEquals(55, student.getStats().getStress());
        assertEquals(99, student.getStats().getEnergy());
        assertEquals(17, student.getTime().getTimeUnit());

        Activities.readingBook(student);
        assertEquals(98, student.getStats().getSocial());
        assertEquals(98, student.getStats().getHunger());
        assertEquals(60, student.getStats().getStress());
        assertEquals(98, student.getStats().getEnergy());
        assertEquals(18, student.getTime().getTimeUnit());
    }

    @Test
    public void partyingTest() throws Exception {
        student.getStats().setEnergy(100);
        student.getStats().setSocial(10);

        Activities.partying(student);
        assertEquals(60, student.getStats().getSocial());
        assertEquals(88, student.getStats().getHunger());
        assertEquals(88, student.getStats().getStress());
        assertEquals(70, student.getStats().getEnergy());
        assertEquals(28, student.getTime().getTimeUnit());

        Activities.partying(student);
        assertEquals(100, student.getStats().getSocial());
        assertEquals(76, student.getStats().getHunger());
        assertEquals(76, student.getStats().getStress());
        assertEquals(40, student.getStats().getEnergy());
        assertEquals(40, student.getTime().getTimeUnit());
    }

    @Test
    public void meetFriendsTest() throws Exception {
        student.getStats().setStress(50);
        student.getStats().setSocial(50);

        Activities.meetFriends(student);
        assertEquals(70, student.getStats().getSocial());
        assertEquals(96, student.getStats().getHunger());
        assertEquals(55, student.getStats().getStress());
        assertEquals(96, student.getStats().getEnergy());
        assertEquals(20, student.getTime().getTimeUnit());

        Activities.meetFriends(student);
        assertEquals(90, student.getStats().getSocial());
        assertEquals(92, student.getStats().getHunger());
        assertEquals(60, student.getStats().getStress());
        assertEquals(92, student.getStats().getEnergy());
        assertEquals(24, student.getTime().getTimeUnit());
    }

    @Test
    public void sportsTest() throws Exception {
        student.getStats().setEnergy(100);
        student.getStats().setStress(50);

        Activities.sports(student);
        assertEquals(97, student.getStats().getSocial());
        assertEquals(97, student.getStats().getHunger());
        assertEquals(65, student.getStats().getStress());
        assertEquals(90, student.getStats().getEnergy());
        assertEquals(19, student.getTime().getTimeUnit());

        Activities.sports(student);
        assertEquals(94, student.getStats().getSocial());
        assertEquals(94, student.getStats().getHunger());
        assertEquals(80, student.getStats().getStress());
        assertEquals(80, student.getStats().getEnergy());
        assertEquals(22, student.getTime().getTimeUnit());
    }

    @Test
    public void snackTest() throws Exception {
        student.getStats().setHunger(50);

        Activities.snack(student);
        assertEquals(99, student.getStats().getSocial());
        assertEquals(57, student.getStats().getHunger());
        assertEquals(99, student.getStats().getStress());
        assertEquals(99, student.getStats().getEnergy());
        assertEquals(17, student.getTime().getTimeUnit());
        assertEquals(180, student.getCash());

        Activities.snack(student);
        assertEquals(98, student.getStats().getSocial());
        assertEquals(64, student.getStats().getHunger());
        assertEquals(98, student.getStats().getStress());
        assertEquals(98, student.getStats().getEnergy());
        assertEquals(18, student.getTime().getTimeUnit());
        assertEquals(160, student.getCash());
    }

    @Test
    public void visitLectureTest() throws Exception
    {
        Event exam = new Event("exam 01", new Time(5, 20), Event.Type.Exam, 20, null);
        Event lecture = new Event("lecture 01", new Time(1, 16), Event.Type.Lecture, 100, exam);
        student.getStats().setStress(10);

        Activities.visitLecture(student, lecture);
        assertEquals(96, student.getStats().getSocial());
        assertEquals(96, student.getStats().getHunger());
        assertEquals(13, student.getStats().getStress());
        assertEquals(92, student.getStats().getEnergy());
        assertEquals(20, student.getTime().getTimeUnit());
        assertEquals(1, student.getTime().getDay());
        assertEquals(40, lecture.getExam().getProbabilityPercentage());

        Activities.visitLecture(student, lecture);
        assertEquals(96, student.getStats().getSocial());
        assertEquals(96, student.getStats().getHunger());
        assertEquals(13, student.getStats().getStress());
        assertEquals(92, student.getStats().getEnergy());
        assertEquals(20, student.getTime().getTimeUnit());
        assertEquals(1, student.getTime().getDay());
        assertEquals(40, lecture.getExam().getProbabilityPercentage());


        lecture.setTime(new Time(1, 20));
        Activities.visitLecture(student, lecture);
        assertEquals(92, student.getStats().getSocial());
        assertEquals(92, student.getStats().getHunger());
        assertEquals(16, student.getStats().getStress());
        assertEquals(84, student.getStats().getEnergy());
        assertEquals(24, student.getTime().getTimeUnit());
        assertEquals(1, student.getTime().getDay());
        assertEquals(60, lecture.getExam().getProbabilityPercentage());
    }

    @Test
    public void multiplicator1Test() throws Exception {
        student.getStats().setHunger(50);
        student.getStats().setHunger_multiplicator(2);

        Activities.snack(student);
        assertEquals(99, student.getStats().getSocial());
        assertEquals(65, student.getStats().getHunger());
        assertEquals(99, student.getStats().getStress());
        assertEquals(99, student.getStats().getEnergy());
        assertEquals(17, student.getTime().getTimeUnit());
        assertEquals(180, student.getCash());

        Activities.snack(student);
        assertEquals(98, student.getStats().getSocial());
        assertEquals(80, student.getStats().getHunger());
        assertEquals(98, student.getStats().getStress());
        assertEquals(98, student.getStats().getEnergy());
        assertEquals(18, student.getTime().getTimeUnit());
        assertEquals(160, student.getCash());
    }

    @Test
    public void multiplicator2Test() throws Exception {
        student.getStats().setEnergy(100);
        student.getStats().setSocial(10);
        student.getStats().setEnergy_multiplicator(2);
        student.getStats().setSocial_multiplicator(0.7f);

        Activities.partying(student);
        assertEquals(41, student.getStats().getSocial());
        assertEquals(88, student.getStats().getHunger());
        assertEquals(88, student.getStats().getStress());
        assertEquals(79, student.getStats().getEnergy());
        assertEquals(28, student.getTime().getTimeUnit());


        student.getStats().setEnergy_multiplicator(0.5f);

        Activities.partying(student);
        assertEquals(72, student.getStats().getSocial());
        assertEquals(76, student.getStats().getHunger());
        assertEquals(76, student.getStats().getStress());
        assertEquals(31, student.getStats().getEnergy());
        assertEquals(40, student.getTime().getTimeUnit());
    }

    @Test
    public void multiplicator3Test() throws Exception {
        student.getStats().setStress(50);
        student.getStats().setStress_multiplicator(1.5f);

        Activities.readingBook(student);
        assertEquals(99, student.getStats().getSocial());
        assertEquals(99, student.getStats().getHunger());
        assertEquals(58, student.getStats().getStress());
        assertEquals(99, student.getStats().getEnergy());
        assertEquals(17, student.getTime().getTimeUnit());

        Activities.readingBook(student);
        assertEquals(98, student.getStats().getSocial());
        assertEquals(98, student.getStats().getHunger());
        assertEquals(66, student.getStats().getStress());
        assertEquals(98, student.getStats().getEnergy());
        assertEquals(18, student.getTime().getTimeUnit());
    }
}