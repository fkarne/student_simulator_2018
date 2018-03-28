package at.tugraz.morning08.a_students_life.data;

//Singelton
public class Student {
    private String name;
    private String gender;
    private String studie;

    static private Student student;

    private Student() {}

    static public Student getInstance() {
        if(student == null) {
            student = new Student();
        }
        return student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStudie() {
        return studie;
    }

    public void setStudie(String studie) {
        this.studie = studie;
    }
}
