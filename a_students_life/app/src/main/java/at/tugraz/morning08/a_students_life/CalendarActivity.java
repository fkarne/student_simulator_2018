package at.tugraz.morning08.a_students_life;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import at.tugraz.morning08.a_students_life.data.Activities;
import at.tugraz.morning08.a_students_life.data.Calendar;
import at.tugraz.morning08.a_students_life.data.Event;
import at.tugraz.morning08.a_students_life.data.Student;
import at.tugraz.morning08.a_students_life.data.Time;
import at.tugraz.morning08.a_students_life.handler.EventHandler;
import at.tugraz.morning08.a_students_life.handler.MainPageHandler;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class CalendarActivity extends AppCompatActivity implements CalendarRecyclerViewClickListener {
    private RecyclerView recycler_view;
    private CalendarAdapter calendar_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        drawList();

        TextView day_view = findViewById(R.id.tvDay);
        day_view.setText(getText(R.string.sign_day) + " " + String.valueOf(Student.getInstance().getTime().getDay()));
        TextView time_view = findViewById(R.id.tvTime);
        time_view.setText(Student.getInstance().getTime().getTimeString());

        EventHandler.updateCalendarList();
    }

    @Override
    public void onClick(View view, int position) {
        boolean valid_visit = EventHandler.visitLecture(Calendar.getInstance().getEventAt(position));
        //Activities.visitLecture(Student.getInstance(), Calendar.getInstance().getEventAt(position));

        StringBuilder output = new StringBuilder();

        if(valid_visit) {
            output.append(getText(R.string.tst_visited));
            output.append(": ");
            output.append(getText(Calendar.getInstance().getEventAt(position).getNameKey()));
        }
        else{
            output.append(getText(R.string.tst_error_msg));
        }
        Toast toast = Toast.makeText(getApplicationContext(),output.toString(), Toast.LENGTH_SHORT);
        toast.show();

        EventHandler.updateCalendarList();
        updateCalendarPage(view);
    }

    private void updateCalendarPage(View view)
    {
        TextView tv_day = findViewById(R.id.tvDay);
        tv_day.setText(view.getContext().getText(R.string.sign_day) + " " + String.valueOf(Student.getInstance().getTime().getDay()));
        TextView tv_time = findViewById(R.id.tvTime);
        tv_time.setText(Student.getInstance().getTime().getTimeString());
        drawList();
    }

    @Override
    public void onBackPressed() {
        View view = findViewById(R.id.calendarPage);
        if(view != null){
            setContentView(R.layout.activity_main_page);
            startActivity(new Intent(CalendarActivity.this, MainPageActivity.class));
            CalendarActivity.this.finish();
        }
    }

    private void drawList(){
        recycler_view = findViewById(R.id.rvCalenderView);

        calendar_adapter = new CalendarAdapter(getBaseContext(), Calendar.getInstance().getEventList());
        LinearLayoutManager calendar_layout_manager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(calendar_layout_manager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(calendar_adapter);
        calendar_adapter.setClickListener(this);
    }
}