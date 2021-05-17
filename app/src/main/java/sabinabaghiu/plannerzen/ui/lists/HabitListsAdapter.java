package sabinabaghiu.plannerzen.ui.lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sabinabaghiu.plannerzen.R;
import sabinabaghiu.plannerzen.ui.today.Habit;
import sabinabaghiu.plannerzen.ui.today.HabitRepository;

public class HabitListsAdapter extends RecyclerView.Adapter<HabitListsAdapter.ViewHolder> {
    private ArrayList<Habit> habits = new ArrayList<>();
    private Context context;

    HabitListsAdapter(Context context){
        this.context = context;
    }

    public void updateList(ArrayList<Habit> habits){
        this.habits = habits;
        notifyDataSetChanged();
    }

    public Context getContext(){
        return context;
    }

    public void deleteItem(int position){
        Habit habit = habits.get(position);
        HabitRepository.getInstance().deleteHabit(habit.getId());

    }

    @NonNull
    @Override
    public HabitListsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.habit_list_item, parent, false);
        return new HabitListsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitListsAdapter.ViewHolder holder, int position) {
        holder.title.setText(habits.get(position).getTitle());
        holder.icon.setImageResource(habits.get(position).getIconId());
        holder.extra.setText("Goal: " + habits.get(position).getGoal() + " days");
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView icon;
        TextView extra;


        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.habit_title);
            icon = itemView.findViewById(R.id.icon_habit);
            extra = itemView.findViewById(R.id.habit_goal);
        }
    }
}
