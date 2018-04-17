package at.tugraz.morning08.a_students_life;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.ArrayList;

import at.tugraz.morning08.a_students_life.data.Event;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>{

    private ArrayList<Event> calendarList;

    public CalendarAdapter(ArrayList<Event> calendarList) {
        this.calendarList = calendarList;
    }

    @Override
    public CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calender_item_row, parent, false);

        return new CalendarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CalendarViewHolder holder, int position) {
        holder.title.setText(calendarList.get(position).getName());
        String time = String.valueOf(calendarList.get(position).getTimeUnit());
        holder.time.setText(String.valueOf(calendarList.get(position).getTimeUnit()));
    }

    @Override
    public int getItemCount() {
        return calendarList.size();
    }

    public class CalendarViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView day;
        public TextView time;

        public CalendarViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tvEventTitle);
            time = view.findViewById(R.id.tvEventTime);
        }
    }
}