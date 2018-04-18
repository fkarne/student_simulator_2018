package at.tugraz.morning08.a_students_life.data;


public class Event
{
    public Event(String name, int day, int timeUnit, String type) {
        this.name = name;
        this.day = day;
        this.timeUnit = timeUnit;
        this.type = type;
    }

    private String name;
    private int day;
    private int timeUnit;
    private String type;

    public String getName() {
        return name;
    }

    public int getDay() {
        return day;
    }

    public int getTimeUnit() {
        return timeUnit;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setTimeUnit(int timeUnit) {
        this.timeUnit = timeUnit;
    }

    public void setType(String type) {
        this.type = type;
    }
}
