package at.tugraz.morning08.a_students_life;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import at.tugraz.morning08.a_students_life.data.Activities;
import at.tugraz.morning08.a_students_life.data.Student;

/**
 * Created by Jeremias and Laura on 11.04.18.
 */
public class MainPageActivity extends AppCompatActivity
{
    private LinearLayout student_graphic;
    private int top;
    private int bot;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        student_graphic = findViewById(R.id.student_graphic);        
        updateMainPage();
    }

    public void setHeight(PopupWindow pop){
        // get top edge of Student graphic
        top = findViewById(R.id.stats).getHeight() + findViewById(R.id.ll_stats).getHeight();
        // get bottom of student graphic
        bot = student_graphic.getHeight();

        pop.setHeight(bot);
        pop.showAtLocation(student_graphic, Gravity.NO_GRAVITY,0, (top));
    }

    public void energy_popup(View view) {
        LayoutInflater inflater = (LayoutInflater) getBaseContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //grid = findViewById(R.id.gv01);

        View popupView = inflater.inflate(R.layout.popup_energy,null);
        PopupWindow mPopupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true

        );

        // Set an elevation value for popup window
        // Call requires API level 21
        if(Build.VERSION.SDK_INT>=21){
            mPopupWindow.setElevation(5.0f);
        }

        setHeight(mPopupWindow);
    }

    public void stress_popup(View view) {
        LayoutInflater inflater = (LayoutInflater) getBaseContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View popupView = inflater.inflate(R.layout.popup_stress,null);
        PopupWindow mPopupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true

        );

        if(Build.VERSION.SDK_INT>=21){
            mPopupWindow.setElevation(5.0f);
        }

        setHeight(mPopupWindow);
    }

    public void hunger_popup(View view) {
        LayoutInflater inflater = (LayoutInflater) getBaseContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View popupView = inflater.inflate(R.layout.popup_hunger,null);
        PopupWindow mPopupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true

        );

        if(Build.VERSION.SDK_INT>=21){
            mPopupWindow.setElevation(5.0f);
        }

        setHeight(mPopupWindow);
    }

    public void social_popup(View view) {
        LayoutInflater inflater = (LayoutInflater) getBaseContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View popupView = inflater.inflate(R.layout.popup_social,null);
        PopupWindow mPopupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true

        );

        if(Build.VERSION.SDK_INT>=21){
            mPopupWindow.setElevation(5.0f);
        }

        setHeight(mPopupWindow);
    }

    public void money_popup(View view) {
        LayoutInflater inflater = (LayoutInflater) getBaseContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View popupView = inflater.inflate(R.layout.popup_money,null);
        PopupWindow mPopupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true

        );

        if(Build.VERSION.SDK_INT>=21){
            mPopupWindow.setElevation(5.0f);
        }

        setHeight(mPopupWindow);
    }

    public void study_popup(View view) {
        LayoutInflater inflater = (LayoutInflater) getBaseContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View popupView = inflater.inflate(R.layout.popup_study,null);
        PopupWindow mPopupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true

        );

        if(Build.VERSION.SDK_INT>=21){
            mPopupWindow.setElevation(5.0f);
        }

        setHeight(mPopupWindow);
    }

    public void showStatsPage(View view)    {
        setContentView(R.layout.activity_stats_menu);
        updateStatsPage();
    }

    public void backToMainPage(View view) {
        setContentView(R.layout.activity_main_page);
        updateMainPage();
    }

    public void updateMainPage() {
        ((ProgressBar) findViewById(R.id.progressBarEnergyMainPage)).setSecondaryProgress(Student.getInstance().getStats().getEnergy());
        ((ProgressBar) findViewById(R.id.progressBarHungerMainPage)).setSecondaryProgress(Student.getInstance().getStats().getHunger());
        ((ProgressBar) findViewById(R.id.progressBarStressMainPage)).setSecondaryProgress(Student.getInstance().getStats().getStress());
        ((TextView) findViewById(R.id.text_money)).setText("  "+Student.getInstance().getCash());

        checkLoseConditions();
    }

    public void updateStatsPage(){
        ((ProgressBar) findViewById(R.id.energyProgressBar)).setSecondaryProgress(Student.getInstance().getStats().getEnergy());
        ((ProgressBar) findViewById(R.id.stressProgressBar)).setSecondaryProgress(Student.getInstance().getStats().getStress());
        ((ProgressBar) findViewById(R.id.hungerProgressBar)).setSecondaryProgress(Student.getInstance().getStats().getHunger());
        ((ProgressBar) findViewById(R.id.socialProgressBar)).setSecondaryProgress(Student.getInstance().getStats().getSocial());
        ((TextView) findViewById(R.id.moneyAmountLabel)).setText(Student.getInstance().getCash()+" €");
    }


    private void checkLoseConditions() {
        int energy = Student.getInstance().getStats().getEnergy();
        int stress = Student.getInstance().getStats().getStress();
        int social = Student.getInstance().getStats().getSocial();
        int hunger = Student.getInstance().getStats().getHunger();


        if(energy == 0 || stress == 0 || social == 0 || hunger == 0){
            AlertDialog.Builder builder;

            builder = new AlertDialog.Builder(this);
            builder.setTitle("Game over!");

            if(hunger == 0)
                builder.setMessage("You starved to death.");
            else if(energy == 0)
                builder.setMessage("No more running!");
            else if(stress == 0)
                builder.setMessage("Calm down!");
            else
                builder.setMessage("Nobody loves you ....");

            builder.setPositiveButton("Zug zug", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Student.getInstance().getStats().initializeStudent();
                    setContentView(R.layout.activity_start_menu);
                    startActivity(new Intent(MainPageActivity.this, StartMenuActivity.class));
                }
            });
            builder.show();
        }
    }
}
