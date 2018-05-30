package at.tugraz.morning08.a_students_life;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
                //3
                holder.info.setImageDrawable(view.getContext().getDrawable(R.drawable.energy_btn));
                break;
            case NAP:
                //1
                holder.info.setImageDrawable(view.getContext().getDrawable(R.drawable.energy_btn));
                break;
            case EAT:
                //2
                holder.info.setImageDrawable(view.getContext().getDrawable(R.drawable.hunger_btn));
                break;
            case GOINGOUTTOEAT:
                //3
                holder.info.setImageDrawable(view.getContext().getDrawable(R.drawable.hunger_btn));
                break;
            case SNACK:
                //1
                holder.info.setImageDrawable(view.getContext().getDrawable(R.drawable.hunger_btn));
                break;
            case ASKFORMONEY:
                //3
                holder.info.setImageDrawable(view.getContext().getDrawable(R.drawable.money_btn));
                break;
            case PHONECALL:
                //1
                holder.info.setImageDrawable(view.getContext().getDrawable(R.drawable.social_btn));
                break;
            case PARTYING:
                //3
                holder.info.setImageDrawable(view.getContext().getDrawable(R.drawable.social_btn));
                break;
            case MEETFRIENDS:
                //2
                holder.info.setImageDrawable(view.getContext().getDrawable(R.drawable.social_btn));
                break;
            case WATCHTV:
                //2
                holder.info.setImageDrawable(view.getContext().getDrawable(R.drawable.stress_btn));
                break;
            case READINGBOOK:
                //1
                holder.info.setImageDrawable(view.getContext().getDrawable(R.drawable.stress_btn));
                break;
            case SPORTS:
                //3
                holder.info.setImageDrawable(view.getContext().getDrawable(R.drawable.stress_btn));
                break;
            case LEARN:
                //3
                holder.info.setImageDrawable(view.getContext().getDrawable(R.drawable.study_btn));
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