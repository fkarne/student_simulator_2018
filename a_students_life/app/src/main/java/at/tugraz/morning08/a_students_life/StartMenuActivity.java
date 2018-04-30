package at.tugraz.morning08.a_students_life;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.app.Activity;
import android.widget.RadioButton;

import java.util.Locale;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class StartMenuActivity extends AppCompatActivity {
    //TODO: options-Button & load-button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, StartMenuActivity.class);
        startActivity(refresh);
        finish();
    }

    // call options file
    public void show_options(View view) {
        setContentView(R.layout.activity_start_menu_options);
    }


    public void save_options_language(View view) {
        RadioButton rdb_en = findViewById(R.id.lang_en_rb);
        RadioButton rdb_de = findViewById(R.id.lang_de_rb);

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
        RadioButton rb_en = findViewById(R.id.lang_en_rb);
        RadioButton rb_de = findViewById(R.id.lang_de_rb);

        boolean  checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.lang_en_rb:
                if (checked)
                    rb_en.setTypeface(null, Typeface.BOLD_ITALIC);
                    //set the other two radio buttons text style to default
                    rb_de.setTypeface(null, Typeface.NORMAL);
                break;

            case R.id.lang_de_rb:
                if (checked)
                    rb_de.setTypeface(null, Typeface.BOLD_ITALIC);
                    //set the other two radio buttons text style to default
                    rb_en.setTypeface(null, Typeface.NORMAL);
                break;
        }
    }
}
