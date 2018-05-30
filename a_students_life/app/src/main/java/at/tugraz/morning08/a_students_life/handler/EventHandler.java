package at.tugraz.morning08.a_students_life.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import at.tugraz.morning08.a_students_life.data.Activities;
import at.tugraz.morning08.a_students_life.data.Calendar;
import at.tugraz.morning08.a_students_life.data.Event;
import at.tugraz.morning08.a_students_life.data.Student;
import at.tugraz.morning08.a_students_life.data.Time;

/**
 * Created by aless on 30.05.2018.
 */

public final class EventHandler {
    private static List<Event> lecture_list = new ArrayList<>();
    private static List<Event> exam_list = new ArrayList<>();


    public List<Event> getLecture_list() {
        return lecture_list;
    }

    public static void addLecture(Event lecture) {
        lecture_list.add(lecture);
    }

    public List<Event> getExam_list() {
        return exam_list;
    }

    public static void addExam(Event exam) {
        exam_list.add(exam);
    }



    public static boolean visitLecture(Event lecture){
        if(lecture.getTime().getDay() == Student.getInstance().getTime().getDay() &&
                lecture.getTime().getTimeUnit() == Student.getInstance().getTime().getTimeUnit() &&
                lecture.getType() == Event.Type.Lecture) {
            Activities.visitLecture(Student.getInstance(), lecture);
            return true;
        }
        return false;
    }

    public static void updateCalendarList(){

        Calendar.getInstance().clear();

        for (Event e: lecture_list) {
            if(e.getTime().getDay() == Student.getInstance().getTime().getDay() &&
                    e.getTime().getTimeUnit() >= Student.getInstance().getTime().getTimeUnit() &&
                    e.getLv_visited_count() < e.getLv_max_count()){
                Calendar.getInstance().addEvent(e);
            }
            else if(e.getTime().getTimeUnit() >= Student.getInstance().getTime().getTimeUnit() &&
                    e.getLv_visited_count() < e.getLv_max_count() &&
                    e.getExam().getTime().getDay() > Student.getInstance().getTime().getDay()){
                Random rm = new Random();
                int visiblity_of_lecture = rm.nextInt(2);

                System.out.println("visibility: " + visiblity_of_lecture);
                if(visiblity_of_lecture == 1){
                    e.setTime(new Time(Student.getInstance().getTime().getDay(), e.getTime().getTimeUnit()));
                    Calendar.getInstance().addEvent(e);
                    System.out.println("blalbalalblafmaldfgknaslkdfharsmk");
                }
            }
        }

        for (Event e: exam_list) {
            if(e.getTime().getDay() >= Student.getInstance().getTime().getDay() &&
                    //e.getTime().getDay() <= Student.getInstance().getTime().getDay() &&
                    e.getTime().getTimeUnit() >= Student.getInstance().getTime().getTimeUnit()){
                Calendar.getInstance().addEvent(e);
            }
        }
        Calendar.getInstance().sortEvents();
    }
}
