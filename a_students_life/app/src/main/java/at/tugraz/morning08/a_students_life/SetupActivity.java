package at.tugraz.morning08.a_students_life;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import at.tugraz.morning08.a_students_life.data.Student;

public class SetupActivity extends AppCompatActivity {
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
        Student student = Student.getInstance();

        EditText name = findViewById(R.id.user_name_tf);
        if(name != null && !name.getText().toString().isEmpty()) {
            student.setName(name.getText().toString());
            student.setGender(findViewById(R.id.gender_male_radio).isSelected() ? "male" : "female");
            setContentView(R.layout.activity_setup02);
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Name eingeben!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    // TODO: Main / Hub
    public void setup02_next(View view) {
        Student student = Student.getInstance();
        student.setStudie(((Spinner)findViewById(R.id.studies_sp)).getSelectedItem().toString());
        saveCharacter(student.getName(),student.getGender(),student.getStudie());
        student.getStats().initializeStudent();
        startActivity(new Intent(SetupActivity.this, MainPageActivity.class));
        SetupActivity.this.finish();
    }

    public void saveCharacter(String name, String gender, String study) {
        String name_name = "name";
        String gender_name = "gender";
        String study_name = "study";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(name_name, name);
        editor.putString(gender_name, gender);
        editor.putString(study_name, study);
        editor.commit();
    }

    public void hideVirtualKeyboard(View view) {
        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
