package at.tugraz.morning08.a_students_life;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SetupActivity extends AppCompatActivity {
    // TODO: save input!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup01);
    }


    /**
     * When Next button is clicked -> shows second "setup-Site"
     * @param view  current View
     */
    public void setup01_next(View view) {
        setContentView(R.layout.activity_setup02);
    }

    // TODO: Main / Hub
    public void setup02_next(View view) {

    }
}
