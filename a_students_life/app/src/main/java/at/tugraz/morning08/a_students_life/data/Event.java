package at.tugraz.morning08.a_students_life.data;


public class Event
{
    public Event(String name, Time time, String type) {
        this.name = name;
        this.time = time;
        this.type = type;
    }

    private String name;
    private Time time;
    private String type;

    public String getName() {
        return name;
    }

    public Time getTime() { return time; }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(Time time) { this.time = time; }

    public void setType(String type) {
        this.type = type;
    }
}
