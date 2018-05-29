package at.tugraz.morning08.a_students_life;

import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import at.tugraz.morning08.a_students_life.components.ButtonInfo;

public class PopupAdapter extends RecyclerView.Adapter<PopupAdapter.PopupViewHolder>{
    private List<ButtonInfo> activity_list;
    private View view;
    private MainPageActivity mainPageActivity;

    public PopupAdapter(View view, List<ButtonInfo> activity_list, MainPageActivity mainPageActivity) {
        this.activity_list = activity_list;
        this.view = view;
        this.mainPageActivity = mainPageActivity;
    }

    @Override
    public PopupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view_holder = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_row, parent, false);

        return new PopupViewHolder(view_holder);
    }

    @Override
    public void onBindViewHolder(PopupViewHolder holder, final int position) {
        holder.title.setText(view.getContext().getString(activity_list.get(position).getInfoKey()));
        switch (activity_list.get(position).getActivity())
        {
            case SLEEP:
                holder.info.setImageDrawable(null);
                break;
            case NAP:
                holder.info.setImageDrawable(null);
                break;
            case EAT:
                holder.info.setImageDrawable(view.getContext().getDrawable(R.drawable.hunger_btn));
                break;
            case GOINGOUTTOEAT:
                holder.info.setImageDrawable(null);
                break;
            case SNACK:
                holder.info.setImageDrawable(null);
                break;
            case ASKFORMONEY:
                holder.info.setImageDrawable(null);
                break;
            case PHONECALL:
                holder.info.setImageDrawable(null);
                break;
            case PARTYING:
                holder.info.setImageDrawable(null);
                break;
            case MEETFRIENDS:
                holder.info.setImageDrawable(null);
                break;
            case WATCHTV:
                holder.info.setImageDrawable(null);
                break;
            case READINGBOOK:
                holder.info.setImageDrawable(null);
                break;
            case SPORTS:
                holder.info.setImageDrawable(null);
                break;
            case LEARN:
                holder.info.setImageDrawable(null);
                break;
        }
        holder.activity.setId(position);

        holder.activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity_list.get(position).invokeMethod();
                mainPageActivity.updateMainPage(view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return activity_list.size();
    }

    public class PopupViewHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout activity;
        public TextView title;
        public ImageView info;

        public PopupViewHolder(View view) {
            super(view);
            activity = view.findViewById(R.id.rlActivity);
            title = view.findViewById(R.id.tvActivityTitle);
            info = view.findViewById(R.id.tvActivityInfo);
        }
    }
}