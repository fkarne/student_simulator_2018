package at.tugraz.morning08.a_students_life.data;

import android.support.annotation.NonNull;
import at.tugraz.morning08.a_students_life.R;

public class Event implements Comparable
{
    public enum Type {Exam, Lecture}
    private int name_key;
    private Time time;
    private Type type;
    private int probability_percentage; // [0; 100]
    private Event exam;
    private int ects;
    private boolean completed = false;
    private static Boolean init = false;
    private int lv_visited_count;
    private int lv_max_count;


    //LV Contructor
    public Event(int name_key, Time time, Type type, Event exam, int lv_visited_count, int lv_max_count) {
        this.name_key = name_key;
        this.time = time;
        this.type = type;
        this.exam = exam;
        this.lv_visited_count = lv_visited_count;
        this.lv_max_count = lv_max_count;
    }

    //Exam Contructor
    public Event(int name_key, Time time, Type type, int probability_percentage, int ects) {
        this.name_key = name_key;
        this.time = time;
        this.type = type;
        this.probability_percentage = probability_percentage;
        this.ects = ects;
    }

    public int getNameKey() { return name_key; }

    public Time getTime() { return time; }

    public Type getType() { return type; }

    public int getProbabilityPercentage() { return probability_percentage; }

    public Event getExam() { return exam; }

    public void setNameKey(int name_key) { this.name_key = name_key; }

    public void setTime(Time time) { this.time = time; }

    public void setType(Type type) { this.type = type; }

    public void setProbabilityPercentage(int probability_percentage) { this.probability_percentage = probability_percentage; }

    public void setExam(Event exam) { this.exam = exam; }

    public int getLv_visited_count() { return lv_visited_count;}

    public void setLv_visited_count(int lv_visited_count) { this.lv_visited_count = lv_visited_count;   }

    public int getLv_max_count() { return lv_max_count; }

    public void setLv_max_count(int lv_max_count) { this.lv_max_count = lv_max_count; }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void increaseProbability(int probabilityChange){
        probability_percentage += probabilityChange;
    }

    public void checkBorderProbability() {
        if (probability_percentage > 100)
            probability_percentage = 100;
    }

    public int getEcts() {
        return ects;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return this.getTime().compareTo(((Event)o).getTime());
    }
}
