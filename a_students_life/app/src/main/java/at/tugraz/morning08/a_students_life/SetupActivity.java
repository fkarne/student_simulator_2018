package at.tugraz.morning08.a_students_life;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import at.tugraz.morning08.a_students_life.data.Student;
import at.tugraz.morning08.a_students_life.handler.LoadSaveHandler;

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
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.gender_radio_group);
        RadioButton radioButton;

        EditText name = findViewById(R.id.user_name_tf);
        if(name != null && !name.getText().toString().isEmpty()) {
            student.setName(name.getText().toString());
            int selectedId = radioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(selectedId);
            student.setGender(radioButton.getText().equals("female") ? "female" : "male");
            setContentView(R.layout.activity_setup02);
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Name eingeben!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void setup02_next(View view) {
        Student student = Student.getInstance();
        student.setStudie(((Spinner)findViewById(R.id.studies_sp)).getSelectedItem().toString());
        LoadSaveHandler.saveCharacter(view);
        student.getStats().initializeStudent();
        startActivity(new Intent(SetupActivity.this, MainPageActivity.class));
        SetupActivity.this.finish();
    }

    public void hideVirtualKeyboard(View view) {
        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
