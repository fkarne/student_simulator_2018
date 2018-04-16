package at.tugraz.morning08.a_students_life;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.nfc.Tag;
import android.os.Build;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import at.tugraz.morning08.a_students_life.data.Student;


public class MainPageActivity extends AppCompatActivity{
    private Button energy_popup_btn;
    private GridLayout grid;
    private LinearLayout student_graphic;
    private LinearLayout popupWindow;
    Button[] energy_activites_btn = new Button[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        student_graphic = findViewById(R.id.student_graphic);

        // region: energy_activites
        // TODO: get activites & set size of array
        // TODO: Button -> ImageButton

        for (int i = 0; i < 2; i++) {
            energy_activites_btn[i] = new Button(this);
            energy_activites_btn[i].setBackgroundColor(Color.TRANSPARENT);
            energy_activites_btn[i].setText("Hallo: " + i);
            energy_activites_btn[i].setId(i);
        }
        // end region
    }

    public void energy_popup(View view) {
        LayoutInflater inflater = (LayoutInflater) getBaseContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //grid = findViewById(R.id.gv01);

        View popupView = inflater.inflate(R.layout.activity_popup,null);
        PopupWindow mPopupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true

        );

        // TODO: make buttons dynamics
        /*
        for(int i=0; i<2; i++){
            grid.addView(energy_activites_btn[i]);
        }
        */

        // Set an elevation value for popup window
        // Call requires API level 21
        if(Build.VERSION.SDK_INT>=21){
            mPopupWindow.setElevation(5.0f);
        }

        // get top edge of Student graphic
        int top = findViewById(R.id.stats).getHeight();
        int top_stats = findViewById(R.id.ll_stats).getHeight();

        // get bottom of student graphic
        int bot = student_graphic.getHeight();
        mPopupWindow.setHeight(bot);
        mPopupWindow.showAtLocation(student_graphic, Gravity.NO_GRAVITY,0, (top + top_stats));
    }
}
