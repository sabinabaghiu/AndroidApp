package sabinabaghiu.plannerzen.ui.today;

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

public class HabitTodayAdapter extends RecyclerView.Adapter<HabitTodayAdapter.ViewHolder> {
    private ArrayList<Habit> habits = new ArrayList<>();
    private Context context;
    private HabitCardAdapterOnClickListener listener;

    HabitTodayAdapter(Context context, HabitCardAdapterOnClickListener listener){
        this.context = context;
        this.listener = listener;
    }

    public void updateList(ArrayList<Habit> habits){
        this.habits = habits;
        notifyDataSetChanged();
    }

    public void update(){
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
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
        holder.done.setImageResource(R.drawable.ic_baseline_check_box_24);
        holder.done.setVisibility(View.INVISIBLE);
        if (habits.get(position).isDone() == true)
            holder.done.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        ImageView icon, done;


        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.habit_today_title);
            icon = itemView.findViewById(R.id.icon_habit_today);
            done = itemView.findViewById(R.id.habit_done);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onHabitCardClick(getAdapterPosition());
        }
    }

    public interface HabitCardAdapterOnClickListener{
        void onHabitCardClick(int position);
    }
}
