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
import sabinabaghiu.plannerzen.ui.lists.TasksViewModel;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Context context;
    private static TasksViewModel instance;

    public TaskAdapter(Context context){
        this.context = context;
    }

    public void UpdateList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public Context getContext(){
        return context;
    }

    public void deleteItem(int position){
        Task task = tasks.get(position);
        TaskRepository.getInstance().deleteTask(task.getId());
    }


    public void getTask(int position){
        Task task = tasks.get(position);
//        TasksViewModel tasksViewModel = tasksViewModel.ge
    }

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.task_list_item, parent, false);
        return new TaskAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        holder.title.setText(tasks.get(position).getTitle() + " - " + tasks.get(position).getTime() + " min");
        holder.icon.setImageResource(R.drawable.ic_baseline_priority_high_24);
        if (tasks.get(position).isImportant() == false)
            holder.icon.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView icon;


        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.task_add);
            icon = itemView.findViewById(R.id.icon_task);
        }
    }
}
