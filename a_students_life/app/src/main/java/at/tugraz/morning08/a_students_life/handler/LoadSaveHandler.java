package at.tugraz.morning08.a_students_life.handler;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;

import at.tugraz.morning08.a_students_life.data.Student;

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
            editor.commit();
        }
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
            student.getTime().setTimeUnit(prefs.getInt("time", 16));
            student.getTime().setDay(prefs.getInt("day", 1));
            student.getStats().setEnergy(prefs.getInt("energy", 100));
            student.getStats().setStress(prefs.getInt("stress", 100));
            student.getStats().setHunger(prefs.getInt("hunger", 100));
            student.getStats().setSocial(prefs.getInt("social", 100));
        }
    }
}
