package at.tugraz.morning08.a_students_life;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import at.tugraz.morning08.a_students_life.data.Event;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>{

    private ArrayList<Event> event_list;

    public CalendarAdapter(ArrayList<Event> event_list) {
        this.event_list = event_list;
    }

    @Override
    public CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item_view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calender_item_row, parent, false);

        return new CalendarViewHolder(item_view);
    }

    @Override
    public void onBindViewHolder(CalendarViewHolder holder, int position) {
        holder.title.setText(event_list.get(position).getName());
        holder.time.setText(event_list.get(position).getTime().getTimeString());
        holder.day.setText("Day: " + String.valueOf(event_list.get(position).getTime().getDay()));

    }

    @Override
    public int getItemCount() {
        return event_list.size();
    }

    public class CalendarViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView day;
        public TextView time;

        public CalendarViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tvEventTitle);
            time = view.findViewById(R.id.tvEventTime);
            day = view.findViewById(R.id.tvEventDay);
        }
    }
}