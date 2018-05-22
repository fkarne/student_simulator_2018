package at.tugraz.morning08.a_students_life.data;

import android.support.annotation.NonNull;

public class Event implements Comparable
{
    public enum Type {Exam, Lecture, Other}

    public Event(String name, Time time, Type type, int probability_percentage, Event exam) {
        this.name = name;
        this.time = time;
        this.type = type;
        this.probability_percentage = probability_percentage;
        this.exam = exam;
    }

    private String name;
    private Time time;
    private Type type;
    private int probability_percentage; // [0; 100]
    private Event exam;

    public String getName() { return name; }

    public Time getTime() { return time; }

    public Type getType() { return type; }

    public int getProbabilityPercentage() { return probability_percentage; }

    public Event getExam() { return exam; }

    public void setName(String name) { this.name = name; }

    public void setTime(Time time) { this.time = time; }

    public void setType(Type type) { this.type = type; }

    public void setProbabilityPercentage(int probability_percentage) { this.probability_percentage = probability_percentage; }

    public void setExam(Event exam) { this.exam = exam; }

    public void increaseProbability(int probabilityChange){
        probability_percentage += probabilityChange;
    }

    public void checkBorderProbability() {
        if (probability_percentage > 100)
            probability_percentage = 100;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return this.getTime().compareTo(((Event)o).getTime());
    }
}
