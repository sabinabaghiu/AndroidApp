package sabinabaghiu.plannerzen.ui.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sabinabaghiu.plannerzen.R;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder> {
    private ArrayList<Reminder> reminders;

    ReminderAdapter(ArrayList<Reminder> reminders) {
        this.reminders = reminders;
    }

    @NonNull
    @Override
    public ReminderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.reminder_list_item_mylists, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(reminders.get(position).getTitle());
        holder.date.setText(reminders.get(position).getDate().toString());
        holder.repeat.setText(reminders.get(position).getRepeat());
        if (reminders.get(position).isOn())
            holder.isOn.setChecked(true);
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;
        TextView repeat;
        Switch isOn;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.reminder_title);
            date = itemView.findViewById(R.id.reminder_date);
            repeat = itemView.findViewById(R.id.reminder_repeat);
            isOn = itemView.findViewById(R.id.switch_reminder_on);
        }
    }
}
