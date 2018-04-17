package at.tugraz.morning08.a_students_life;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import at.tugraz.morning08.a_students_life.data.Student;


/**
 * Created by Jeremias and Laura on 11.04.18.
 */

public class MainPageActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        updateMainPage();
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
        ((TextView) findViewById(R.id.text_money)).setText("  "+Student.getInstance().getCash()+",00 €");
    }

    public void updateStatsPage(){
        ((ProgressBar) findViewById(R.id.energyProgressBar)).setSecondaryProgress(Student.getInstance().getStats().getEnergy());
        ((ProgressBar) findViewById(R.id.stressProgressBar)).setSecondaryProgress(Student.getInstance().getStats().getStress());
        ((ProgressBar) findViewById(R.id.hungerProgressBar)).setSecondaryProgress(Student.getInstance().getStats().getHunger());
        ((ProgressBar) findViewById(R.id.socialProgressBar)).setSecondaryProgress(Student.getInstance().getStats().getSocial());
        ((TextView) findViewById(R.id.moneyAmountLabel)).setText(Student.getInstance().getCash()+",00 €");
    }



}
