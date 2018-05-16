package at.tugraz.morning08.a_students_life;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import at.tugraz.morning08.a_students_life.components.ButtonInfo;
import at.tugraz.morning08.a_students_life.handler.MainPageHandler;

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
        String text = view.getContext().getString(activity_list.get(position).getInfoKey());

        holder.activity.setText(text);

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
        public Button activity;

        public PopupViewHolder(View view) {
            super(view);
            activity = view.findViewById(R.id.btn_activity_recycle);
        }
    }
}