package at.tugraz.morning08.a_students_life.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Singelton
public class Student {
    static private Student student;

    private String name;
    private String gender;
    private String studie;

    private Stats stats = new Stats();
    private Time time = new Time();

    private List<Event> eventList = new ArrayList<>();

    private int cash = 0;
    private int ects = 0;

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

    public int getCash() { return cash; }

    public int getEcts() {return ects; }

    public void setEcts(int ects) { this.ects = ects; }


    public void addCash(int cashToAdd) {
        this.cash += cashToAdd;
    }

    public void spendCash(int cashToSpend) {
        this.cash -= cashToSpend;
    }

    public void addEcts(int ectsToAdd) {
        this.ects += ectsToAdd;
    }

    public void addTimeUnits(int timeUnitsToAdd) {
        time.addTimeUnits(timeUnitsToAdd);

        stats.decreaseStress(timeUnitsToAdd);
        stats.decreaseEnergy(timeUnitsToAdd);
        stats.decreaseSocial(timeUnitsToAdd);
        stats.decreaseHunger(timeUnitsToAdd);
    }

    public void addEvent(Event event) {
        eventList.add(event);
        Collections.sort(eventList);
    }

    public void deleteEvent(Event event) {
        if(eventList.contains(event)) {
            eventList.remove(event);
        }
    }

    public void clearEventList() {
        eventList.clear();
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public Event getNextExam() {
        Event event = null;
        for(Event e : eventList) {
         if(e.getType() == Event.Type.Exam) {
             event = e;
             break;
         }
        }
        return event;
    }
}
