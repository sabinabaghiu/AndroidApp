package sabinabaghiu.plannerzen.ui.today;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sabinabaghiu.plannerzen.R;

public class HabitTodayAdapter extends RecyclerView.Adapter<HabitTodayAdapter.ViewHolder> {
    private ArrayList<Habit> habits;

    HabitTodayAdapter(ArrayList<Habit> habits){
        this.habits = habits;
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
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;


        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.habit_today);
        }
    }
}
