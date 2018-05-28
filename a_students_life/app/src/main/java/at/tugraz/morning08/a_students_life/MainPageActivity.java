package at.tugraz.morning08.a_students_life;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import at.tugraz.morning08.a_students_life.components.ButtonInfo;
import at.tugraz.morning08.a_students_life.data.Activities;
import at.tugraz.morning08.a_students_life.data.Event;
import at.tugraz.morning08.a_students_life.data.Student;
import at.tugraz.morning08.a_students_life.handler.LoadSaveHandler;
import at.tugraz.morning08.a_students_life.handler.MainPageHandler;
import at.tugraz.morning08.a_students_life.components.MyProgressBar;


/**
 * Created by Jeremias and Laura on 11.04.18.
 */
public class MainPageActivity extends AppCompatActivity
{
    private LinearLayout student_graphic;
    private AlertDialog backPressedAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Activities.createButtonInfo();
        Event.createLectureList();

        student_graphic = findViewById(R.id.student_graphic);
        updateMainPage(findViewById(R.id.mainPage));
    }

    @Override
    public void onBackPressed() {
        View view = findViewById(R.id.mainPage);
        if(view != null)
        {
            LoadSaveHandler.saveGame(view);

            backPressedAlert = new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(getText(R.string.exit_game))
                    .setMessage(getText(R.string.exit_game_question))
                    .setPositiveButton(getText(R.string.yes_btn), new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {
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
            updateMainPage(findViewById(R.id.mainPage));
        }
    }

    public AlertDialog getBackPressedAlert()
    {
        return backPressedAlert;
    }

    public void energy_popup(View view) {
        popup(view);
    }

    public void stress_popup(View view) {popup(view); }

    public void hunger_popup(View view) {
        popup(view);
    }

    public void social_popup(View view) {
        popup(view);
    }

    public void money_popup(View view) {
        popup(view);
    }

    public void study_popup(View view) {
        popup(view);
    }

    public void popup(View view) {
        List<ButtonInfo> activity_list = new ArrayList<>();
        RecyclerView recycler_view;
        PopupAdapter popup_adapter;

        switch (view.getId()){
            case R.id.energy_img_btn:
                activity_list = Activities.energy;
                break;
            case R.id.stress_img_btn:
                activity_list = Activities.stress;
                break;
            case R.id.hunger_img_btn:
                activity_list = Activities.hunger;
                break;
            case R.id.social_img_btn:
                activity_list = Activities.social;
                break;
            case R.id.money_img_btn:
                activity_list = Activities.money;
                break;
            case R.id.study_img_btn:
                activity_list = Activities.study;
                break;
        }

        LayoutInflater inflater = (LayoutInflater) getBaseContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_activity,null);
        PopupWindow mPopupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
        );

        recycler_view = popupView.findViewById(R.id.popup_activity_rv);
        popup_adapter = new PopupAdapter(findViewById(R.id.mainPage), activity_list, this);
        RecyclerView.LayoutManager popup_layout_manager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(popup_layout_manager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(popup_adapter);

        if(Build.VERSION.SDK_INT>=21){
            mPopupWindow.setElevation(5.0f);
        }

        int top = findViewById(R.id.stats).getHeight() + findViewById(R.id.ll_stats).getHeight();
        mPopupWindow.setHeight(student_graphic.getHeight());
        mPopupWindow.showAtLocation(student_graphic, Gravity.NO_GRAVITY,0, top);
    }

    public void showStatsPage(View view)    {
        setContentView(R.layout.activity_stats_menu);

        updateStatsPage();
    }

    public void updateStatsPage(){
        ((MyProgressBar) findViewById(R.id.energyProgressBar)).setSecondaryProgress(Student.getInstance().getStats().getEnergy());
        ((MyProgressBar) findViewById(R.id.energyProgressBar)).updateProgress();

        ((MyProgressBar) findViewById(R.id.hungerProgressBar)).setSecondaryProgress(Student.getInstance().getStats().getHunger());
        ((MyProgressBar) findViewById(R.id.hungerProgressBar)).updateProgress();

        ((MyProgressBar) findViewById(R.id.stressProgressBar)).setSecondaryProgress(Student.getInstance().getStats().getStress());
        ((MyProgressBar) findViewById(R.id.stressProgressBar)).updateProgress();

        ((MyProgressBar) findViewById(R.id.socialProgressBar)).setSecondaryProgress(Student.getInstance().getStats().getSocial());
        ((MyProgressBar) findViewById(R.id.socialProgressBar)).updateProgress();

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

    public void updateMainPage(View view) {
        ImageView student_pic = findViewById(R.id.student_pic);
        if(Student.getInstance().getGender().equals("female"))
        {
            student_pic.setBackgroundResource(R.drawable.female);
        }
        else
        {
            student_pic.setBackgroundResource(R.drawable.male);
        }
        MainPageHandler.updateMainPage(view);
        showLoseCondition(view);
        showWinCondition(view);
        LoadSaveHandler.saveGame(view);
    }

    private void showWinCondition(final View view) {
        if (Student.getInstance().getEcts() >= 180) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setCancelable(false);
            builder.setTitle(view.getContext().getText(R.string.win_congrats));
            if (Student.getInstance().getStudie().equals(view.getContext().getText(R.string.studies_inf_li))) {
                builder.setMessage(view.getContext().getText(R.string.win_text) + " " + view.getContext().getText(R.string.studies_inf_li) + ".");
            } else if (Student.getInstance().getStudie().equals(view.getContext().getText(R.string.studies_bwl_li))) {
                builder.setMessage(view.getContext().getText(R.string.win_text) + " " + view.getContext().getText(R.string.studies_bwl_li) + ".");
            }
            builder.setPositiveButton(getText(R.string.win_btnOk), new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   Student.getInstance().getStats().initializeStudent();
                   setContentView(R.layout.activity_start_menu);
                   startActivity(new Intent(MainPageActivity.this, StartMenuActivity.class));
                   MainPageActivity.this.finish();
                   LoadSaveHandler.resetSave(view);
               }
            });
            builder.show();
        }
    }

    private void showLoseCondition(final View view) {
        int energy = Student.getInstance().getStats().getEnergy();
        int stress = Student.getInstance().getStats().getStress();
        int social = Student.getInstance().getStats().getSocial();
        int hunger = Student.getInstance().getStats().getHunger();

        if(energy == 0 || stress == 0 || social == 0 || hunger == 0){
            AlertDialog.Builder builder =  new AlertDialog.Builder(view.getContext());
            builder.setCancelable(false);
            builder.setTitle(view.getContext().getText(R.string.lose_gameOver));

            if(hunger == 0)
                builder.setMessage(view.getContext().getText(R.string.lose_hunger));
            else if(energy == 0)
                builder.setMessage(view.getContext().getText(R.string.lose_energy));
            else if(stress == 0)
                builder.setMessage(view.getContext().getText(R.string.lose_stress));
            else
                builder.setMessage(view.getContext().getText(R.string.lose_social));

            builder.setPositiveButton(getText(R.string.lose_btnOk), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Student.getInstance().getStats().initializeStudent();
                    setContentView(R.layout.activity_start_menu);
                    startActivity(new Intent(MainPageActivity.this, StartMenuActivity.class));
                    MainPageActivity.this.finish();
                    LoadSaveHandler.resetSave(view);
                }
            });
            builder.show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        LoadSaveHandler.saveGame(findViewById(R.id.mainPage));
    }
}
