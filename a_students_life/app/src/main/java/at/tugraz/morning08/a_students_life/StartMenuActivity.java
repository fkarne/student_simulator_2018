package at.tugraz.morning08.a_students_life;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.app.Activity;
import android.widget.RadioButton;

import java.util.Locale;

import at.tugraz.morning08.a_students_life.data.Student;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class StartMenuActivity extends AppCompatActivity {
    //TODO: load-button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // load local or last saved language
        loadLocale();

        setContentView(R.layout.activity_start_menu);
    }

    /**
     * When start button is clicked ->Start game
     * changes to SetupActivity
     * @param view  current View
     */
    public void start_game(View view) {
        startActivity(new Intent(StartMenuActivity.this, SetupActivity.class));
        StartMenuActivity.this.finish();
    }

    /*
     * Sets chosen language
     * needs String -> 'en', 'de'
     */
    public void setLocale(String lang) {
        // change Prefs to new language
        changeLang(lang);

        Intent refresh = new Intent(this, StartMenuActivity.class);
        startActivity(refresh);
        finish();
    }

    // call options file
    public void show_options(View view) {
        setContentView(R.layout.activity_start_menu_options);
    }


    public void save_options_language(View view) {
        RadioButton rdb_en = findViewById(R.id.lang_en_radio);
        RadioButton rdb_de = findViewById(R.id.lang_de_radio);

        if(rdb_en.isChecked()){
            setLocale((String) getText(R.string.lang_en_short));
        }
        else if(rdb_de.isChecked()){
            setLocale((String) getText(R.string.lang_de_short));
        }
    }

    //reset xml
    public void cancel_options_language(View view) {
        setContentView(R.layout.activity_start_menu);
    }

    public void onRadioButtonClicked(View view) {
        RadioButton rb_en = findViewById(R.id.lang_en_radio);
        RadioButton rb_de = findViewById(R.id.lang_de_radio);

        boolean  checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.lang_en_radio:
                if (checked)
                    rb_en.setTypeface(null, Typeface.BOLD_ITALIC);
                    //set the other two radio buttons text style to default
                    rb_de.setTypeface(null, Typeface.NORMAL);
                break;

            case R.id.lang_de_radio:
                if (checked)
                    rb_de.setTypeface(null, Typeface.BOLD_ITALIC);
                    //set the other two radio buttons text style to default
                    rb_en.setTypeface(null, Typeface.NORMAL);
                break;
        }
    }

    
    public void loadLocale() {
        String lang = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String language = prefs.getString(lang, "");
        changeLang(language);
    }

    public void changeLang(String language) {
        if (language.equalsIgnoreCase(""))
            return;

        Locale myLocale = new Locale(language);
        saveLocale(language);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
    }

    public void saveLocale(String language) {
        String lang = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(lang, language);
        editor.commit();
    }

    public void load_game(View view) {
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String name = prefs.getString("name","");
        if(name.length() > 0) {
            Student student = Student.getInstance();
            student.setName(prefs.getString("name", ""));
            student.setGender(prefs.getString("gender", ""));
            student.setStudie(prefs.getString("study", ""));
            student.setEcts(prefs.getInt("ects", 0));
            student.setCash(prefs.getInt("money", 0));
            student.getTime().setTimeUnit(prefs.getInt("time", 16));
            student.getTime().setDay(prefs.getInt("day", 1));
            student.getStats().setEnergy(prefs.getInt("energy", 100));
            student.getStats().setEnergy_multiplicator(prefs.getFloat("energy_mul", 1));
            student.getStats().setStress(prefs.getInt("stress", 100));
            student.getStats().setStress_multiplicator(prefs.getFloat("stress_mul", 1));
            student.getStats().setHunger(prefs.getInt("hunger", 100));
            student.getStats().setHunger_multiplicator(prefs.getFloat("hunger_mul", 1));
            student.getStats().setSocial(prefs.getInt("social", 100));
            student.getStats().setSocial_multiplicator(prefs.getFloat("social_mul", 1));


            startActivity(new Intent(StartMenuActivity.this, MainPageActivity.class));
        }
        else
        {
            startActivity(new Intent(StartMenuActivity.this, SetupActivity.class));

        }
        StartMenuActivity.this.finish();

    }
}
