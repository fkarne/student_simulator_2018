package at.tugraz.morning08.a_students_life.handler;

import android.view.View;
import android.widget.TextView;

import at.tugraz.morning08.a_students_life.R;
import at.tugraz.morning08.a_students_life.components.MyProgressBar;
import at.tugraz.morning08.a_students_life.data.Student;

/**
 * Created by aless on 16.05.2018.
 */

public final class MainPageHandler {
    public static void updateMainPage(View view){
        ((MyProgressBar) view.findViewById(R.id.progressBarEnergyMainPage)).setSecondaryProgress(Student.getInstance().getStats().getEnergy());
        ((MyProgressBar) view.findViewById(R.id.progressBarEnergyMainPage)).updateProgress();

        ((MyProgressBar) view.findViewById(R.id.progressBarHungerMainPage)).setSecondaryProgress(Student.getInstance().getStats().getHunger());
        ((MyProgressBar) view.findViewById(R.id.progressBarHungerMainPage)).updateProgress();

        ((MyProgressBar) view.findViewById(R.id.progressBarStressMainPage)).setSecondaryProgress(Student.getInstance().getStats().getStress());
        ((MyProgressBar) view.findViewById(R.id.progressBarStressMainPage)).updateProgress();

        ((MyProgressBar) view.findViewById(R.id.progressBarSocialMainPage)).setSecondaryProgress(Student.getInstance().getStats().getSocial());
        ((MyProgressBar) view.findViewById(R.id.progressBarSocialMainPage)).updateProgress();

        ((TextView) view.findViewById(R.id.text_money)).setText(view.getContext().getText(R.string.sign_money) + " " + Student.getInstance().getCash());
        ((TextView) view.findViewById(R.id.ects_text)).setText(Student.getInstance().getEcts()+" / 180 ECTS");

        TextView day_view = view.findViewById(R.id.tvDayMain);
        day_view.setText(view.getContext().getText(R.string.sign_day) + " " + String.valueOf(Student.getInstance().getTime().getDay()));
        TextView time_view = view.findViewById(R.id.tvTimeMain);
        time_view.setText(Student.getInstance().getTime().getTimeString());
    }
}
