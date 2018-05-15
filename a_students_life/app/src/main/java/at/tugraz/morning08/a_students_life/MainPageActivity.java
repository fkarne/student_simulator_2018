package at.tugraz.morning08.a_students_life;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import at.tugraz.morning08.a_students_life.components.MyProgressBar;
import at.tugraz.morning08.a_students_life.data.Activities;
import at.tugraz.morning08.a_students_life.data.Student;

/**
 * Created by Jeremias and Laura on 11.04.18.
 */
public class MainPageActivity extends AppCompatActivity
{
    private LinearLayout student_graphic;
    private int top;
    private AlertDialog backPressedAlert;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Activities.createButtons(this);

        student_graphic = findViewById(R.id.student_graphic);        
        updateMainPage();
    }

    @Override
    public void onBackPressed() {
        //View currView = getWindow().getDecorView().getRootView();
        View view = findViewById(R.id.mainPage);
        if(view != null)
        {
            backPressedAlert = new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(getText(R.string.exit_game))
                    .setMessage(getText(R.string.exit_game_question))
                    .setPositiveButton(getText(R.string.yes_btn), new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {
                            saveStats();
                            setContentView(R.layout.activity_start_menu);
                            startActivity(new Intent(MainPageActivity.this, StartMenuActivity.class));
                            MainPageActivity.this.finish();
                            //System.exit(0);
                        }
                    })
                    .setNegativeButton(getText(R.string.no_btn), null)
                    .show();
        }
        else
        {
            setContentView(R.layout.activity_main_page);
            updateMainPage();
        }
    }

    public AlertDialog getBackPressedAlert()
    {
        return backPressedAlert;
    }

    public void energy_popup(View view) {
        popup(R.layout.popup_energy);
    }

    public void stress_popup(View view) {
        popup(R.layout.popup_stress);
    }

    public void hunger_popup(View view) {
        popup(R.layout.popup_hunger);
    }

    public void social_popup(View view) {
        popup(R.layout.popup_social);
    }

    public void money_popup(View view) {
        popup(R.layout.popup_money);
    }

    public void study_popup(View view) {
        popup(R.layout.popup_study);
    }

    private void popup(int res) {
        LayoutInflater inflater = (LayoutInflater) getBaseContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View popupView = inflater.inflate(res,null);
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
        mPopupWindow.showAtLocation(student_graphic, Gravity.NO_GRAVITY,0, (top));
    }

    public void setHeight(PopupWindow pop){
        // get top edge of Student graphic
        top = findViewById(R.id.stats).getHeight() + findViewById(R.id.ll_stats).getHeight();

        pop.setHeight(student_graphic.getHeight());
        pop.showAtLocation(student_graphic, Gravity.NO_GRAVITY,0, (top));
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

        ((MyProgressBar) findViewById(R.id.progressBarEnergyMainPage)).setSecondaryProgress(Student.getInstance().getStats().getEnergy());
        ((MyProgressBar) findViewById(R.id.progressBarEnergyMainPage)).updateProgress();

        ((MyProgressBar) findViewById(R.id.progressBarHungerMainPage)).setSecondaryProgress(Student.getInstance().getStats().getHunger());
        ((MyProgressBar) findViewById(R.id.progressBarHungerMainPage)).updateProgress();

        ((MyProgressBar) findViewById(R.id.progressBarStressMainPage)).setSecondaryProgress(Student.getInstance().getStats().getStress());
        ((MyProgressBar) findViewById(R.id.progressBarStressMainPage)).updateProgress();

        ((MyProgressBar) findViewById(R.id.progressBarSocialMainPage)).setSecondaryProgress(Student.getInstance().getStats().getSocial());
        ((MyProgressBar) findViewById(R.id.progressBarSocialMainPage)).updateProgress();

        ((TextView) findViewById(R.id.text_money)).setText(getText(R.string.sign_money) + " " + Student.getInstance().getCash());
        ((TextView) findViewById(R.id.ects_text)).setText(Student.getInstance().getEcts()+" / 180 ECTS");

        TextView day_view = findViewById(R.id.tvDayMain);
        day_view.setText(getText(R.string.sign_day) + " " + String.valueOf(Student.getInstance().getTime().getDay()));
        TextView time_view = findViewById(R.id.tvTimeMain);
        time_view.setText(Student.getInstance().getTime().getTimeString());
        checkLoseConditions();
        checkWinCondition();

        saveStats();
    }

    public void updateStatsPage(){
        ((ProgressBar) findViewById(R.id.energyProgressBar)).setSecondaryProgress(Student.getInstance().getStats().getEnergy());
        ((ProgressBar) findViewById(R.id.stressProgressBar)).setSecondaryProgress(Student.getInstance().getStats().getStress());
        ((ProgressBar) findViewById(R.id.hungerProgressBar)).setSecondaryProgress(Student.getInstance().getStats().getHunger());
        ((ProgressBar) findViewById(R.id.socialProgressBar)).setSecondaryProgress(Student.getInstance().getStats().getSocial());
        ((TextView) findViewById(R.id.moneyAmountLabel)).setText(Student.getInstance().getCash() + " " + getText(R.string.sign_money));
        int ects = Student.getInstance().getEcts();
        if(ects>=180)
        {
            ects = 180;
        }
        ((ProgressBar)findViewById(R.id.studyProgressBar)).setProgress(ects);
    }

    public void calendar_button_onClick(View view) {
        startActivity(new Intent(MainPageActivity.this, CalendarActivity.class));
    }

    //Activities
    public void sleep_button_onClick(View view) {
        Activities.sleep(Student.getInstance());
        updateMainPage();
    }

    public void nap_button_onClick(View view) {
        Activities.nap(Student.getInstance());
        updateMainPage();
    }

    public void eat_button_onClick(View view) {
        Activities.eat(Student.getInstance());
        updateMainPage();
    }

    public void goingOutToEat_button_onClick(View view) {
        Activities.goingOutToEat(Student.getInstance());
        updateMainPage();
    }

    public void snack_button_onClick(View view) {
        Activities.snack(Student.getInstance());
        updateMainPage();
    }

    public void phoneCall_button_onClick(View view) {
        Activities.phoneCall(Student.getInstance());
        updateMainPage();
    }

    public void meetFriends_button_onClick(View view) {
        Activities.meetFriends(Student.getInstance());
        updateMainPage();
    }

    public void partying_button_onClick(View view) {
        Activities.partying(Student.getInstance());
        updateMainPage();
    }

    public void watchTV_button_onClick(View view) {
        Activities.watchTV(Student.getInstance());
        updateMainPage();
    }

    public void readingBook_button_onClick(View view) {
        Activities.readingBook(Student.getInstance());
        updateMainPage();
    }

    public void sports_button_onClick(View view) {
        Activities.sports(Student.getInstance());
        updateMainPage();
    }

    public void askForMoney_button_onClick(View view) {
        Activities.askForMoney(Student.getInstance());
        updateMainPage();
    }

    public void learning_button_onClick(View view) {
        Activities.learn(Student.getInstance());
        updateMainPage();
    }

    private void checkWinCondition() {
        if(Student.getInstance().getEcts() >= 180) {
            AlertDialog.Builder builder;

            builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle(getText(R.string.win_congrats));
            if(Student.getInstance().getStudie().equals(getText(R.string.studies_inf_li))) {
                builder.setMessage(getText(R.string.win_text)+" "+getText(R.string.studies_inf_li)+".");
            }
            else if(Student.getInstance().getStudie().equals(getText(R.string.studies_bwl_li))) {
                builder.setMessage(getText(R.string.win_text)+" "+getText(R.string.studies_bwl_li)+".");
            }

            builder.setPositiveButton(getText(R.string.win_btnOk), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Student.getInstance().getStats().initializeStudent();
                    setContentView(R.layout.activity_start_menu);
                    startActivity(new Intent(MainPageActivity.this, StartMenuActivity.class));
                    MainPageActivity.this.finish();
                    //System.exit(0);
                }
            });
            builder.show();
        }
    }

    private void checkLoseConditions() {
        int energy = Student.getInstance().getStats().getEnergy();
        int stress = Student.getInstance().getStats().getStress();
        int social = Student.getInstance().getStats().getSocial();
        int hunger = Student.getInstance().getStats().getHunger();


        if(energy == 0 || stress == 0 || social == 0 || hunger == 0){
            AlertDialog.Builder builder;

            builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle(getText(R.string.lose_gameOver));

            if(hunger == 0)
                builder.setMessage(getText(R.string.lose_hunger));
            else if(energy == 0)
                builder.setMessage(getText(R.string.lose_energy));
            else if(stress == 0)
                builder.setMessage(getText(R.string.lose_stress));
            else
                builder.setMessage(getText(R.string.lose_social));
            
            builder.setPositiveButton(getText(R.string.lose_btnOk), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Student.getInstance().getStats().initializeStudent();
                    setContentView(R.layout.activity_start_menu);
                    startActivity(new Intent(MainPageActivity.this, StartMenuActivity.class));
                    MainPageActivity.this.finish();
                    //System.exit(0);
                }
            });
            builder.show();
        }
    }
    public void saveStats() {
        Student student = Student.getInstance();
        SharedPreferences prefs = getSharedPreferences("CommonPrefs",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("energy", student.getStats().getEnergy());
        //editor.putFloat("energy_mul", student.getStats().getEnergy_multiplicator());
        editor.putInt("stress", student.getStats().getStress());
        //editor.putInt("stress_mul", student.getStats().getStress_multiplicator());
        editor.putInt("hunger", student.getStats().getHunger());
        //editor.putInt("hunger_mul", student.getStats().getHunger_multiplicator());
        editor.putInt("social", student.getStats().getSocial());
        //editor.putInt("social_mul", student.getStats().getSocial_multiplicator());
        editor.putInt("money",student.getCash());
        editor.putInt("ects",student.getEcts());
        editor.putInt("time", student.getTime().getTimeUnit());
        editor.putInt("day",student.getTime().getDay());
        editor.commit();
    }
    @Override
    protected void onStop() {
        super.onStop();
        saveStats();
    }
}
