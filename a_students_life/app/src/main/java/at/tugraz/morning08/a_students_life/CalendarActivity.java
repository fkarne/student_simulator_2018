package at.tugraz.morning08.a_students_life;

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

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class CalendarActivity extends AppCompatActivity implements CalendarRecyclerViewClickListener {

    private ArrayList<Event> event_list = Calendar.getInstance().event_list;
    private RecyclerView recycler_view;
    private CalendarAdapter calendar_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        recycler_view = findViewById(R.id.rvCalenderView);

        calendar_adapter = new CalendarAdapter(event_list);
        LinearLayoutManager calendar_layout_manager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(calendar_layout_manager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(calendar_adapter);
        calendar_adapter.setClickListener(this);

        TextView day_view = findViewById(R.id.tvDay);
        day_view.setText(getText(R.string.sign_day) + " " + String.valueOf(Student.getInstance().getTime().getDay()));
        TextView time_view = findViewById(R.id.tvTime);
        time_view.setText(Student.getInstance().getTime().getTimeString());
    }

    @Override
    public void onClick(View view, int position) {
        Activities.visitLecture(Student.getInstance(), event_list.get(position));
    }
}