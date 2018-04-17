package at.tugraz.morning08.a_students_life;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import at.tugraz.morning08.a_students_life.data.Calendar;
import at.tugraz.morning08.a_students_life.data.Event;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class CalendarActivity extends AppCompatActivity {

    private ArrayList<Event> event_list = new ArrayList<>();
    private RecyclerView recyclerView;
    private CalendarAdapter calendar_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);

        calendar_adapter = new CalendarAdapter(event_list);
        RecyclerView.LayoutManager calendar_layout_manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(calendar_layout_manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(calendar_adapter);

        initBookData();
    }

    private void initBookData() {
        Event event = new Event("SW-Prüfung", 1, 1, "Prüfung");
        event_list.add(event);

        event = new Event("2.SW-Prüfung", 2,2, "Prüfung");
        event_list.add(event);

        calendar_adapter.notifyDataSetChanged();
    }
}