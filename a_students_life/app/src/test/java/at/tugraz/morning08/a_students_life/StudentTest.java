package at.tugraz.morning08.a_students_life;

import org.junit.Before;
import org.junit.Test;

import at.tugraz.morning08.a_students_life.data.Student;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class StudentTest {
    Student student;

    @Before
    public void beforeTest() throws Exception {
        student = Student.getInstance();
        student.setName("Florian Karner");
        student.setGender("male");
        student.setStudie("Informatik");
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
}