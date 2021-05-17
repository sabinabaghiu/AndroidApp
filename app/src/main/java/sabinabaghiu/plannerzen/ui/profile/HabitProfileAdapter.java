package sabinabaghiu.plannerzen.ui.profile;

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

public class HabitProfileAdapter extends RecyclerView.Adapter<HabitProfileAdapter.ViewHolder> {
    private ArrayList<Habit> habits = new ArrayList<>();
    private Context context;

    HabitProfileAdapter(Context context) {
        this.context = context;
    }

    public void updateList(ArrayList<Habit> habits){
        this.habits = habits;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HabitProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.habit_list_item_profile, parent, false);
        return new HabitProfileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitProfileAdapter.ViewHolder holder, int position) {
        holder.title.setText(habits.get(position).getTitle());
        holder.icon.setImageResource(habits.get(position).getIconId());
        holder.extra.setText("Streak: " +  habits.get(position).getCount() + " days");
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView icon;
        TextView extra;


        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.habit_title_profile);
            icon = itemView.findViewById(R.id.icon_habit_profile);
            extra = itemView.findViewById(R.id.habit_streak);
        }
    }
}
