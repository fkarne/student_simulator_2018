package at.tugraz.morning08.a_students_life.handler;

import android.content.res.Resources;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import at.tugraz.morning08.a_students_life.R;
import at.tugraz.morning08.a_students_life.data.Activities;
import at.tugraz.morning08.a_students_life.data.Calendar;
import at.tugraz.morning08.a_students_life.data.Event;
import at.tugraz.morning08.a_students_life.data.Student;
import at.tugraz.morning08.a_students_life.data.Time;

/**
 * Created by aless on 30.05.2018.
 */

public final class EventHandler {
    private static List<Event> exam_list = new ArrayList<>();
    private static int MAX_DAY = 0;

    public List<Event> getExam_list() {
        return exam_list;
    }
    public static void addExam(Event exam) {
        exam_list.add(exam);
    }
    public static int getMaxDay() { return MAX_DAY; }

    public static void createNewExams(){
        List<Event> current_exams = new ArrayList<>();
        List<Integer> lectures = Arrays.asList(R.array.lecures_informatics);
        int min = Student.getInstance().getTime().getDay() +2;
        MAX_DAY = Student.getInstance().getTime().getDay() +9;
        Random randi = new Random();
        int day = randi.nextInt((MAX_DAY-min)+1)+min;
        int index = randi.nextInt(lectures.size());
        int ects = randi.nextInt(20-10+1)+10;
        boolean found_element = false;

        if(exam_list.size() > 0){
            found_element = false;
            for (Event exam: exam_list) {
                    if(exam.getNameKey() == lectures.get(index)){
                        if(!exam.isCompleted()) {
                            current_exams.add(exam);
                        }
                        found_element = true;
                    }
                }
                if(!found_element) {
                    // create new exam
                    Event exam = new Event(lectures.get(index), new Time(day, 24), Event.Type.Exam, 20, ects);
                    exam_list.add(exam);
                    current_exams.add(exam);
                }
        }
        else {
            for(int i = 0; i < 3; i++){
                Event exam = new Event(lectures.get(index), new Time(day, 24), Event.Type.Exam, 20, ects);
                exam_list.add(exam);
                current_exams.add(exam);
                ects = randi.nextInt(20-10+1)+10;
                day = randi.nextInt((MAX_DAY-min)+1)+min;


                do {
                    found_element = false;
                    index = randi.nextInt(lectures.size());

                    for (Event e : exam_list) {
                        if (e.getNameKey() == lectures.get(index)) {
                            found_element = true;
                        }
                    }
                }while(found_element);
            }

        }

        for(Event exam: current_exams) {
//            System.out.println("exam: " + Resources.getSystem().getResourceEntryName(exam.getNameKey()));
            System.out.println("for each..."+exam.getNameKey());
            Student.getInstance().addEvent(exam);
        }
    }


    public static void createLectures(){
        List<Event> current_exams = new ArrayList<>();
        current_exams = Student.getInstance().getEventList();
        Random randi = new Random();
        int timeunit;
        int max_count;
        int day = Student.getInstance().getTime().getDay();
        //System.out.println("list size: "+current_exams.size());
        for(Event exam : current_exams){
            timeunit = randi.nextInt((40-16)+1)+16;
            max_count = exam.getTime().getDay() - Student.getInstance().getTime().getDay();
            max_count /= 2;
            Event lecture = new Event(exam.getNameKey(), new Time(day, timeunit), Event.Type.Lecture, exam, 0, max_count);
            Student.getInstance().addEvent(lecture);
        }
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

        for (Event e: Student.getInstance().getEventList()) {
            if(e.getType() == Event.Type.Lecture) {
                if (e.getTime().getDay() == Student.getInstance().getTime().getDay() &&
                        e.getTime().getTimeUnit() >= Student.getInstance().getTime().getTimeUnit() &&
                        e.getLv_visited_count() < e.getLv_max_count()) {
                    Calendar.getInstance().addEvent(e);
                } else if (e.getTime().getTimeUnit() >= Student.getInstance().getTime().getTimeUnit() &&
                        e.getLv_visited_count() < e.getLv_max_count() &&
                        e.getExam().getTime().getDay() > Student.getInstance().getTime().getDay()) {
                    Random rm = new Random();
                    int visiblity_of_lecture = rm.nextInt(2);

                    //System.out.println("visibility: " + visiblity_of_lecture);
                    if (visiblity_of_lecture == 1) {
                        e.setTime(new Time(Student.getInstance().getTime().getDay(), e.getTime().getTimeUnit()));
                        Calendar.getInstance().addEvent(e);
                        //   System.out.println("blalbalalblafmaldfgknaslkdfharsmk");
                    }
                }
            }
            else{
                if(e.getTime().getDay() >= Student.getInstance().getTime().getDay() &&
                        //e.getTime().getDay() <= Student.getInstance().getTime().getDay() &&
                        e.getTime().getTimeUnit() >= Student.getInstance().getTime().getTimeUnit()){
                    Calendar.getInstance().addEvent(e);
                }
            }
        }

        Calendar.getInstance().sortEvents();
    }
}
