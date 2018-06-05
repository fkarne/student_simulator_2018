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
    private static int MAX_EXAMS = 3;

    public List<Event> getExam_list() {
        return exam_list;
    }
    public static void addExam(Event exam) {
        exam_list.add(exam);
    }
    public static int getMaxDay() { return MAX_DAY; }

    private static int nrOfUncompletedExam()
    {
        int count = 0;
        for(Event e:exam_list)
        {
            if(!e.isCompleted())
            {
                count++; //wenn noch nicht abgeschlossene Exam vorhanden
            }
        }
        return count;
    }

    private static Event getNewExam() {
        List<Integer> lectures = Arrays.asList(R.array.lecures_informatics);

        System.out.println("************* lectures: "+lectures.size());
        //System.out.println("************* inhalt: "+lectures.get(0)+", "+lectures.get(1)+", "+lectures.get(2)+"...");


        int min = Student.getInstance().getTime().getDay() +2;
        MAX_DAY = Student.getInstance().getTime().getDay() +9;

        Random randi = new Random();
        int day = randi.nextInt((MAX_DAY-min)+1)+min;
        int ects = randi.nextInt(20-10+1)+10;

        for (int l = 0; l < lectures.size(); l++) {
            for (Event e : exam_list) {
                if(e.getNameKey() == lectures.get(l) && !e.isCompleted())
                {
                    System.out.println("found exam with same name key....");
                    Event exam = new Event(lectures.get(l), new Time(day, 24), Event.Type.Exam, 20, ects);
                    return exam;
                }
            }
        }
        return null;
    }

    public static void createNewExams(){
        List<Event> current_exams = new ArrayList<>();
        /*List<Integer> lectures = Arrays.asList(R.array.lecures_informatics);

        int min = Student.getInstance().getTime().getDay() +2;
        MAX_DAY = Student.getInstance().getTime().getDay() +9;

        Random randi = new Random();
        int day = randi.nextInt((MAX_DAY-min)+1)+min;
        int index = randi.nextInt(lectures.size());
        int ects = randi.nextInt(20-10+1)+10;

        boolean found_element = false;
        */

        if(nrOfUncompletedExam() >= MAX_EXAMS) {
            //find element with random index and check if completed --> add to curr_exams

            //finding uncompleted Element
            for (Event exam: exam_list) {
                if(exam.getType() == Event.Type.Exam && !exam.isCompleted() && current_exams.size() < MAX_EXAMS){
                    current_exams.add(exam);
                }
            }


            //create new exam to add
            //System.out.println("kein brauchbares element gefunden... exam erzeugen...");

            /*for (int l = 0; l < lectures.size(); l++) {
                for (Event e:exam_list) {
                    if(e.getNameKey()==)
                }
            }

            Event exam = new Event(lectures.get(index), new Time(day, 24), Event.Type.Exam, 20, ects);
            exam_list.add(exam);
            current_exams.add(exam);*/

            /*if(!found_element) {
                // create new exam
                System.out.println("kein brauchbares element gefunden... exam erzeugen...");
                Event exam = new Event(lectures.get(index), new Time(day, 24), Event.Type.Exam, 20, ects);
                exam_list.add(exam);
                current_exams.add(exam);
            }*/
        }
        else if(nrOfUncompletedExam()>0) {
            int remaining = MAX_EXAMS - nrOfUncompletedExam();

            //add uncompleted elements
            for (Event e:exam_list) {
                if(e.getType() == Event.Type.Exam && !e.isCompleted()){
                    current_exams.add(e);
                }
            }

            //create remaining elements
            for(int i = 0; i < remaining; i++)
            {
                Event exam = getNewExam();
                if(exam!=null) {
                    exam_list.add(exam);
                    current_exams.add(exam);
                }
                else {
                    System.out.println("HILFE KEINE EXAMEN MEHR VORHANDEN!");
                    break;
                }
            }
        }
        else {
            //no uncompleted Exams in exam_list left
            //creating 3 new exams...
            System.out.println("exam wird erzeugt...");
            for(int i = 0; i < MAX_EXAMS; i++){

                Event exam = getNewExam();
                if(exam!=null) {
                    exam_list.add(exam);
                    current_exams.add(exam);
                }
                else {
                    System.out.println("HILFE!!! KEINE EXAMEN MEHR VORHANDEN!");
                    break;
                }
            }


            /*Event exam = new Event(lectures.get(index), new Time(day, 24), Event.Type.Exam, 20, ects);
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
            }while(found_element);*/
        }

        for(Event exam: current_exams) {
//            System.out.println("exam: " + Resources.getSystem().getResourceEntryName(exam.getNameKey()));
            System.out.println("exam wird an Student übergeben..."+exam.getNameKey());
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
