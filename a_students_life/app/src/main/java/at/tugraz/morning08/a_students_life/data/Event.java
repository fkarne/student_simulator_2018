package at.tugraz.morning08.a_students_life.data;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import at.tugraz.morning08.a_students_life.R;
import at.tugraz.morning08.a_students_life.handler.EventHandler;

public class Event implements Comparable
{
    public enum Type {Exam, Lecture}
    private int name_key;
    private Time time;
    private Type type;
    private int probability_percentage; // [0; 100]
    private Event exam;
    private int ects;
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

    public static void createLectureList() {
        if(!init) {
            Event analysis_exam = new Event(R.string.lv_analysis_t1, new Time(10, 24), Type.Exam, 20, 7);
            Event analysis_lecture = new Event(R.string.lv_analysis_t1, new Time(1, 16), Type.Lecture,analysis_exam, 0, 3);
            EventHandler.addExam(analysis_exam);
            EventHandler.addLecture(analysis_lecture);

            Event programming_0_exam = new Event(R.string.lv_programming_0, new Time(3, 24), Type.Exam, 60, 2);
            Event programming_0_lecture = new Event(R.string.lv_programming_0, new Time(1, 16), Type.Lecture, programming_0_exam, 0, 1);
            EventHandler.addExam(programming_0_exam);
            EventHandler.addLecture(programming_0_lecture);

            Event foundations_computer_science_exam = new Event(R.string.lv_foundations_computer_science, new Time(7, 32), Type.Exam, 30, 7);
            Event foundations_computer_science_lecture = new Event(R.string.lv_foundations_computer_science, new Time(2, 20), Type.Lecture,foundations_computer_science_exam,0, 2 );
            EventHandler.addExam(foundations_computer_science_exam);
            EventHandler.addLecture(foundations_computer_science_lecture);

            Event computer_mathematics_1_exam = new Event(R.string.lv_computer_mathematics_1, new Time(6, 30), Type.Exam, 50, 2);
            Event computer_mathematics_1_lecture = new Event(R.string.lv_computer_mathematics_1, new Time(3, 10), Type.Lecture, programming_0_exam, 0, 2);
            EventHandler.addExam(computer_mathematics_1_exam);
            EventHandler.addLecture(computer_mathematics_1_lecture);

            Event numerical_computing_linear_algebra_exam = new Event(R.string.lv_numerical_computing_linear_algebra, new Time(12, 26), Type.Exam, 30, 5);
            Event numerical_computing_linear_algebra_lecture = new Event(R.string.lv_numerical_computing_linear_algebra, new Time(2, 40), Type.Lecture, numerical_computing_linear_algebra_exam,0, 3);
            EventHandler.addExam(numerical_computing_linear_algebra_exam);
            EventHandler.addLecture(numerical_computing_linear_algebra_lecture);

            Event introduction_structured_programming_exam = new Event(R.string.lv_introduction_structured_programming, new Time(12, 24), Type.Exam, 40, 3);
            Event introduction_structured_programming_lecture = new Event(R.string.lv_introduction_structured_programming, new Time(2, 30), Type.Lecture,introduction_structured_programming_exam, 0,3);
            EventHandler.addExam(introduction_structured_programming_exam);
            EventHandler.addLecture(introduction_structured_programming_lecture);

            Event introduction_computer_science_exam = new Event(R.string.lv_introduction_computer_science, new Time(14, 36), Type.Exam, 65, 1);
            Event introduction_computer_science_lecture = new Event(R.string.lv_introduction_computer_science, new Time(5, 30), Type.Lecture, introduction_computer_science_exam, 0, 4);
            EventHandler.addExam(introduction_computer_science_exam);
            EventHandler.addLecture(introduction_computer_science_lecture);


            init = true;
        }
    }
}
