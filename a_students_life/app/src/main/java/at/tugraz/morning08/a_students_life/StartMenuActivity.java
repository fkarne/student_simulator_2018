package at.tugraz.morning08.a_students_life;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import java.util.Locale;

import at.tugraz.morning08.a_students_life.handler.LoadSaveHandler;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class StartMenuActivity extends AppCompatActivity {

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
    public void startGame(View view) {
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
    public void showOptions(View view) {
        Locale locale;
        String lang = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String language = prefs.getString(lang, "");

        setContentView(R.layout.activity_start_menu_options);

       if(language.length() < 1) {
            locale = Locale.getDefault();
            language = getResources().getConfiguration().locale.getLanguage();
        }

        RadioButton lang_de_radio = findViewById(R.id.lang_de_radio);
        RadioButton lang_en_radio = findViewById(R.id.lang_en_radio);

        if (language.equals("en")) {
            lang_en_radio.setChecked(true);
        }
        else if (language.equals("de")) {
            lang_de_radio.setChecked(true);
        }
    }


    public void saveOptionsLanguage(View view) {
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
    public void cancelOptionsLanguage(View view) {
        setContentView(R.layout.activity_start_menu);
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

    public void loadGame(View view) {
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String name = prefs.getString("name","");
        Class goTo = SetupActivity.class;

        if(name.length() > 0) {
            LoadSaveHandler.loadGame(view);
            goTo = MainPageActivity.class;
        }

        startActivity(new Intent(StartMenuActivity.this, goTo));
        StartMenuActivity.this.finish();
    }

    @Override
    public void onBackPressed() {
        cancelOptionsLanguage(null);
    }
}
