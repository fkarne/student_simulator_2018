package at.tugraz.morning08.a_students_life.handler;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
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

    public static void addExam(Event exam) {
        exam_list.add(exam);
    }

    public static int getMaxDay() {
        return MAX_DAY;
    }

    public static void createNewExams(Context context) {
        List<Event> current_exams = new ArrayList<>();
        TypedArray lectures;
        int probability = 0;

        // set studies & difficulty
        if (Student.getInstance().getStudie().equals(context.getString(R.string.studies_inf_li))) {
            lectures = context.getResources().obtainTypedArray(R.array.lectures_informatics);
            probability = 20;
            MAX_EXAMS = 3;
        } else if (Student.getInstance().getStudie().equals(context.getString(R.string.studies_bwl_li))) {
            lectures = context.getResources().obtainTypedArray(R.array.lectures_bwl);
            probability = 35;
            MAX_EXAMS = 2;
        } else {
            lectures = context.getResources().obtainTypedArray(R.array.lectures_philosophy);
            probability = 50;
            MAX_EXAMS = 1;
        }

        int min = Student.getInstance().getTime().getDay() + 2;
        MAX_DAY = Student.getInstance().getTime().getDay() + 9;
        Random randi = new Random();
        int day = randi.nextInt((MAX_DAY - min) + 1) + min;
        int index = randi.nextInt(lectures.length());
        int ects = randi.nextInt(13 - 7 + 1) + 7;

        boolean found_element = false;
        boolean added_element = false;
        if (exam_list.size() > 0) {
            do {

                for (Event exam : exam_list) {
                    if (Resources.getSystem().getText(exam.getNameKey()).equals(lectures.getString(index))) {
                        if (!exam.isCompleted()) {
                            current_exams.add(exam);
                            added_element = true;
                        }
                        found_element = true;
                    }
                }

                if (!found_element && !added_element) {
                    // create new exam
                    Event exam = new Event(lectures.getResourceId(index, 0), new Time(day, 24), Event.Type.Exam, probability, ects);
                    exam_list.add(exam);
                    current_exams.add(exam);
                }

                found_element = false;
                added_element = false;
                index = randi.nextInt(lectures.length());
            }while(current_exams.size() < MAX_EXAMS);


        } else {

            for (int i = 0; i < MAX_EXAMS; i++) {
                Event exam = new Event(lectures.getResourceId(index, 0), new Time(day, 24), Event.Type.Exam, probability, ects);
                exam_list.add(exam);
                current_exams.add(exam);
                ects = randi.nextInt(13 - 7 + 1) + 7;
                day = randi.nextInt((MAX_DAY - min) + 1) + min;


                do {
                    found_element = false;
                    index = randi.nextInt(lectures.length());

                    for (Event e : exam_list) {
                        if (e.getNameKey() == lectures.getResourceId(index, 0)) {
                            found_element = true;
                        }
                    }
                } while (found_element);
            }
        }

        System.out.println("current_exams size: "+current_exams.size());

        for (Event exam : current_exams) {
            Student.getInstance().addEvent(exam);
        }
    }

    public static void createLectures() {

        List<Event> current_exams = Student.getInstance().getEventList();
        List<Event> current_lectures = new ArrayList<>();
        Random randi = new Random();
        int timeunit;
        int max_count;
        int day = Student.getInstance().getTime().getDay();


        for (Event exam : current_exams) {
            timeunit = randi.nextInt((40 - 16) + 1) + 16;
            max_count = exam.getTime().getDay() - Student.getInstance().getTime().getDay();
            max_count /= 2;
            Event lecture = new Event(exam.getNameKey(), new Time(day, timeunit), Event.Type.Lecture, exam, 0, max_count);

            current_lectures.add(lecture);
        }

        for (Event lect : current_lectures) {
            Student.getInstance().addEvent(lect);
        }
    }

    public static boolean goToUniversity(Event lecture) {
        if (lecture.getTime().getDay() == Student.getInstance().getTime().getDay() &&
                lecture.getTime().getTimeUnit() == Student.getInstance().getTime().getTimeUnit() &&
                lecture.getType() == Event.Type.Lecture) {
            Activities.visitLecture(Student.getInstance(), lecture);
            return true;
        } else if (lecture.getTime().getDay() == Student.getInstance().getTime().getDay() &&
                lecture.getTime().getTimeUnit() == Student.getInstance().getTime().getTimeUnit() &&
                lecture.getType() == Event.Type.Exam) {
            // TODO: write method for writing an exam; visit is called to set new time & change stats
            boolean completed = Activities.takeExam(Student.getInstance(), lecture);
            return completed;
        }
        return false;
    }

    public static void updateCalendarList() {
        Calendar.getInstance().clear();

        for (Event e : Student.getInstance().getEventList()) {
            if (e.getType() == Event.Type.Lecture) {
                if (e.getTime().getDay() == Student.getInstance().getTime().getDay() &&
                        e.getTime().getTimeUnit() >= Student.getInstance().getTime().getTimeUnit() &&
                        e.getLv_visited_count() < e.getLv_max_count()) {
                    Calendar.getInstance().addEvent(e);
                } else if (e.getTime().getTimeUnit() >= Student.getInstance().getTime().getTimeUnit() &&
                        e.getLv_visited_count() < e.getLv_max_count() &&
                        e.getExam().getTime().getDay() > Student.getInstance().getTime().getDay()) {
                    Random rm = new Random();
                    int visiblity_of_lecture = rm.nextInt(2);


                    if (visiblity_of_lecture == 1) {
                        e.setTime(new Time(Student.getInstance().getTime().getDay(), e.getTime().getTimeUnit()));
                        Calendar.getInstance().addEvent(e);

                    }
                }
            } else {

                if (e.getTime().getDay() >= Student.getInstance().getTime().getDay()) {
                    Calendar.getInstance().addEvent(e);
                }
            }
        }

        //System.out.println("events in student: " + Student.getInstance().getEventList().size());
        //System.out.println("events in calendar: " + Calendar.getInstance().getEventList().size());
        Calendar.getInstance().sortEvents();
    }

    // delete old Events
    public static void reloadCalendarElements() {
        List<Event> toBeRemoved = new ArrayList<>();

        for (Event e : Calendar.getInstance().getEventList()) {
            if (e.getType() == Event.Type.Lecture) {
                if (e.getTime().getDay() < Student.getInstance().getTime().getDay() ||
                        e.getTime().getTimeUnit() < Student.getInstance().getTime().getTimeUnit() ||
                        e.getLv_visited_count() >= e.getLv_max_count()) {
                    toBeRemoved.add(e);
                }
            } else {
                if (e.getTime().getDay() <= Student.getInstance().getTime().getDay() &&
                        e.getTime().getTimeUnit() < Student.getInstance().getTime().getTimeUnit()) {
                    toBeRemoved.add(e);
                }
            }
        }

        for (Event e : toBeRemoved) {
            Calendar.getInstance().removeEvent(e);
        }
        Calendar.getInstance().sortEvents();
    }

    public List<Event> getExam_list() {
        return exam_list;
    }
}
