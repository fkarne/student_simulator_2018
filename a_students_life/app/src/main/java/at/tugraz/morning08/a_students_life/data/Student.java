package at.tugraz.morning08.a_students_life.data;

//Singelton
public class Student {
    static private Student student;

    private String name;
    private String gender;
    private String studie;

    private Stats stats = new Stats();
    private Time time = new Time();

    private int cash = 0;

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

    public Stats getStats() {
        return stats;
    }

    public Time getTime() {
        return time;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getCash() {
        return cash;
    }

    public void addCash(int cashToAdd) {
        this.cash += cashToAdd;
    }

    public void spendCash(int cashToSpend) {
        this.cash -= cashToSpend;
    }
}
