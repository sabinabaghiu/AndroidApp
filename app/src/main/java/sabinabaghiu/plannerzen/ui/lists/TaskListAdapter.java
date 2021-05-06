package sabinabaghiu.plannerzen.ui.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sabinabaghiu.plannerzen.R;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    private ArrayList<TaskList> list;

    TaskListAdapter(ArrayList<TaskList> list){
        this.list = list;
    }

    @NonNull
    @Override
    public TaskListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.todo_list__item_mylists, parent, false);
        return new TaskListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListAdapter.ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.date.setText(list.get(position).getDate().toString());
        holder.description.setText(list.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;
        TextView description;


        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.todo_title);
            date = itemView.findViewById(R.id.todo_date);
            description = itemView.findViewById(R.id.todo_description);
        }
    }
}
