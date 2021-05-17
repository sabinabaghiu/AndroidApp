package sabinabaghiu.plannerzen.ui.today;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sabinabaghiu.plannerzen.R;

public class HabitTodayAdapter extends RecyclerView.Adapter<HabitTodayAdapter.ViewHolder> {
    private ArrayList<Habit> habits = new ArrayList<>();
    private Context context;

    HabitTodayAdapter(Context context){
        this.context = context;
    }

    public void updateList(ArrayList<Habit> habits){
        this.habits = habits;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HabitTodayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.habit_list_item_today, parent, false);
        return new HabitTodayAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitTodayAdapter.ViewHolder holder, int position) {
        holder.title.setText(habits.get(position).getTitle());
        holder.icon.setImageResource(habits.get(position).getIconId());
        holder.checkBox.setChecked(habits.get(position).isDone());
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView icon;
        CheckBox checkBox;


        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.habit_today_title);
            icon = itemView.findViewById(R.id.icon_habit_today);
            checkBox = itemView.findViewById(R.id.checkBox_habit_done);
        }
    }
}
