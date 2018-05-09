package at.tugraz.morning08.a_students_life;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class MyProgressBar extends ProgressBar {

    public MyProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void updateProgress(){
        if(getSecondaryProgress() > 70)
        {
            setSecondaryProgressTintList(ColorStateList.valueOf(Color.GREEN));
        }
        else if (getSecondaryProgress() > 30)
        {
            setSecondaryProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        }
        else
        {
            setSecondaryProgressTintList(ColorStateList.valueOf(Color.RED));
        }
    }
}
