package at.tugraz.morning08.a_students_life;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

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
    }
}
