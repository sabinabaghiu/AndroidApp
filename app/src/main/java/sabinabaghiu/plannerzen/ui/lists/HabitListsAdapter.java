package sabinabaghiu.plannerzen.ui.lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

import sabinabaghiu.plannerzen.R;
import sabinabaghiu.plannerzen.ui.today.Habit;

//public class HabitListsAdapter extends FirebaseRecyclerAdapter<Habit, HabitListsAdapter.ViewHolder> {
//    public HabitListsAdapter(@NonNull FirebaseRecyclerOptions<Habit> options){
//        super(options);
//    }



public class HabitListsAdapter extends RecyclerView.Adapter<HabitListsAdapter.ViewHolder> {
    private List<Habit> habits;
    private Context context;

    HabitListsAdapter(Context context, List<Habit> habits){
        this.habits = habits;
        this.context = context;
    }

    @NonNull
    @Override
    public HabitListsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.habit_list_item, parent, false);
        return new HabitListsAdapter.ViewHolder(view);
    }
//
//    @Override
//    public void onBindViewHolder(@NonNull HabitListsAdapter.ViewHolder holder, int position, Habit habit) {
//        holder.title.setText(habit.getTitle() + "    Goal: " + habit.getGoal());
//        holder.icon.setImageResource(habit.getIconId());
//    }
    @Override
    public void onBindViewHolder(@NonNull HabitListsAdapter.ViewHolder holder, int position) {
        holder.title.setText(habits.get(position).getTitle() + "    Goal: " + habits.get(position).getGoal());
        holder.icon.setImageResource(habits.get(position).getIconId());
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView icon;


        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.habit_title);
            icon = itemView.findViewById(R.id.icon_habit);
        }
    }
}
