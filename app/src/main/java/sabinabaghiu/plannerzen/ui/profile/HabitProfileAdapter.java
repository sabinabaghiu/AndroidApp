package sabinabaghiu.plannerzen.ui.profile;

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

public class HabitProfileAdapter extends RecyclerView.Adapter<HabitProfileAdapter.ViewHolder> {
    private ArrayList<Habit> habits;

    HabitProfileAdapter(ArrayList<Habit> habits) {
        this.habits = habits;
    }

    @NonNull
    @Override
    public HabitProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.habit_list_item_myprofile, parent, false);
        return new HabitProfileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitProfileAdapter.ViewHolder holder, int position) {
        holder.title.setText(habits.get(position).getTitle() + "    " + habits.get(position).getCount() + " days");
        holder.icon.setImageResource(habits.get(position).getIconId());
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView icon;


        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.habit_today);
            icon = itemView.findViewById(R.id.icon_habit_my_profile);
        }
    }
}
