package at.tugraz.morning08.a_students_life.handler;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;

import at.tugraz.morning08.a_students_life.data.Event;
import at.tugraz.morning08.a_students_life.data.Student;
import at.tugraz.morning08.a_students_life.data.Time;

/**
 * Created by aless on 16.05.2018.
 */

public final class LoadSaveHandler {
    public static void saveGame(View view){
        if(view != null) {
            Student student = Student.getInstance();
            SharedPreferences prefs = view.getContext().getSharedPreferences("CommonPrefs",
                    Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("energy", student.getStats().getEnergy());
            editor.putFloat("energy_mul", student.getStats().getEnergy_multiplicator());
            editor.putInt("stress", student.getStats().getStress());
            editor.putFloat("stress_mul", student.getStats().getStress_multiplicator());
            editor.putInt("hunger", student.getStats().getHunger());
            editor.putFloat("hunger_mul", student.getStats().getHunger_multiplicator());
            editor.putInt("social", student.getStats().getSocial());
            editor.putFloat("social_mul", student.getStats().getSocial_multiplicator());
            editor.putInt("money", student.getCash());
            editor.putInt("ects", student.getEcts());
            editor.putInt("time", student.getTime().getTimeUnit());
            editor.putInt("day", student.getTime().getDay());

            saveEvents(editor);

            editor.commit();
        }
    }

    private static void saveEvents(SharedPreferences.Editor editor) {
        Student student = Student.getInstance();
        int eventcount = 0;
        for(; eventcount < student.getEventList().size(); eventcount++) {
            Event event = student.getEventList().get(eventcount);
            editor.putInt("nameE" + eventcount, event.getNameKey());
            editor.putInt("timeE" + eventcount, event.getTime().getTimeUnit());
            editor.putInt("dayE" + eventcount, event.getTime().getDay());
            editor.putString("typeE" + eventcount, event.getType().toString());
            editor.putInt("prob_perE" + eventcount, event.getProbabilityPercentage());
            if(event.getExam() != null) {
                editor.putInt("examKeyE" + eventcount, event.getExam().getNameKey());
                editor.putInt("lv_visitedE" + eventcount, event.getLv_visited_count());
                editor.putInt("lv_maxE" + eventcount, event.getLv_max_count());
            } else {
                editor.putBoolean("completeE" + eventcount, event.isCompleted());
            }
            editor.putInt("ectsE" + eventcount, event.getEcts());
        }
        editor.putInt("eventcount", eventcount);
    }

    public static void saveCharacter(View view) {
        SharedPreferences prefs = view.getContext().getSharedPreferences("CommonPrefs",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name", Student.getInstance().getName());
        editor.putString("gender", Student.getInstance().getGender());
        editor.putString("study", Student.getInstance().getStudie());
        editor.commit();
    }

    public static void loadGame(View view) {
        if(view != null) {
            SharedPreferences prefs = view.getContext().getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
            Student student = Student.getInstance();
            student.setName(prefs.getString("name", ""));
            student.setGender(prefs.getString("gender", ""));
            student.setStudie(prefs.getString("study", ""));
            student.setEcts(prefs.getInt("ects", 0));
            student.setCash(prefs.getInt("money", 0));
            student.getTime().setTimeUnit(prefs.getInt("time",16));
            student.getTime().setDay(prefs.getInt("day", 1));
            student.getStats().setEnergy(prefs.getInt("energy", 100));
            student.getStats().setStress(prefs.getInt("stress", 100));
            student.getStats().setHunger(prefs.getInt("hunger", 100));
            student.getStats().setSocial(prefs.getInt("social", 100));

            loadEvent(prefs);
        }
    }

    private static void loadEvent(SharedPreferences prefs) {
        Student student = Student.getInstance();
        for(int eventcount = 0; eventcount < prefs.getInt("eventcount", 7); eventcount++) {
            int name = prefs.getInt("nameE" + eventcount, -1);
            int time = prefs.getInt("timeE" + eventcount, -1);
            int day = prefs.getInt("dayE" + eventcount, -1);

            Event.Type type = Event.Type.valueOf(prefs.getString("typeE" + eventcount, "Default"));

            if(type == Event.Type.Lecture) {
                int examKey = prefs.getInt("examKeyE" + eventcount, -1);
                int lv_visited = prefs.getInt("lv_visitedE" + eventcount, -1);
                int lv_max = prefs.getInt("lv_maxE" + eventcount, -1);

                for(Event event : student.getEventList()) {
                    if(event.getNameKey() == examKey) {
                        student.addEvent(new Event(name, new Time(day, time), type, event, lv_visited, lv_max));
                        break;
                    }
                }
            } else {
                int prob_per = prefs.getInt("prob_perE" + eventcount, -1);
                int ects = prefs.getInt("ectsE" + eventcount, -1);
                boolean completed = prefs.getBoolean("completeE" + eventcount, false);
                Event event = new Event(name, new Time(day, time), type, prob_per, ects);
                event.setCompleted(completed);
                student.addEvent(event);
            }
        }
    }

    public static void resetSave(View view) {
        SharedPreferences prefs = view.getContext().getSharedPreferences("CommonPrefs",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name", "");
        editor.putString("gender", "");
        editor.putString("study", "");
        editor.putInt("energy", 0);
        editor.putFloat("energy_mul", 0);
        editor.putInt("stress", 0);
        editor.putInt("stress_mul", 0);
        editor.putInt("hunger", 0);
        editor.putInt("hunger_mul", 0);
        editor.putInt("social", 0);
        editor.putInt("social_mul", 0);
        editor.putInt("money", 0);
        editor.putInt("ects", 0);
        editor.putInt("time", 0);
        editor.putInt("day", 0);
        editor.commit();
    }
}
