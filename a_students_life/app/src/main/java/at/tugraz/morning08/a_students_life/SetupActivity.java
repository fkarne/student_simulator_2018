package at.tugraz.morning08.a_students_life;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        setContentView(R.layout.activity_main_page);
        startActivity(new Intent(SetupActivity.this, MainPageActivity.class));
    }
}
