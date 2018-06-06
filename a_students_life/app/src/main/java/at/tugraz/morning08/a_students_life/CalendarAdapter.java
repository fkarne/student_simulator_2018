package at.tugraz.morning08.a_students_life;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import at.tugraz.morning08.a_students_life.data.Event;

import static at.tugraz.morning08.a_students_life.R.attr.actionModePasteDrawable;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>{

    private Context context;
    private ArrayList<Event> event_list;
    private CalendarRecyclerViewClickListener listener;

    public CalendarAdapter(Context context, ArrayList<Event> event_list)
    {
        this.event_list = event_list;
        this.context = context;
    }

    @Override
    public CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item_view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calender_item_row, parent, false);

        return new CalendarViewHolder(item_view);
    }

    @Override
    public void onBindViewHolder(CalendarViewHolder holder, int position) {
        holder.itemView.setId(position);
        holder.title.setText(context.getString(event_list.get(position).getNameKey()));
        holder.time.setText(event_list.get(position).getTime().getTimeString());
        holder.day.setText("Day: " + String.valueOf(event_list.get(position).getTime().getDay()));
        if(event_list.get(position).getType() == Event.Type.Lecture) {
            holder.picture.setImageResource(R.drawable.lecture);
            holder.picture.setTag(R.drawable.lecture);
        }
        else
        {
            if(event_list.get(position).getProbabilityPercentage() > 70) {
                holder.picture.setImageResource(R.drawable.certificate_green);
                holder.picture.setTag(R.drawable.certificate_green);
            }
            else if(event_list.get(position).getProbabilityPercentage() > 30) {
                holder.picture.setImageResource(R.drawable.certificate_yellow);
                holder.picture.setTag(R.drawable.certificate_yellow);
            }
            else {
                holder.picture.setImageResource(R.drawable.certificate_red);
                holder.picture.setTag(R.drawable.certificate_red);
            }
        }
        holder.picture.setAlpha(0.5f);

    }

    @Override
    public int getItemCount() {
        return event_list.size();
    }

    public void setClickListener(CalendarRecyclerViewClickListener listener) {this.listener = listener;}

    public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView day;
        public TextView time;
        public ImageView picture;

        public CalendarViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tvEventTitle);
            time = view.findViewById(R.id.tvEventTime);
            day = view.findViewById(R.id.tvEventDay);
            picture = view.findViewById(R.id.ivEventIcon);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }
}