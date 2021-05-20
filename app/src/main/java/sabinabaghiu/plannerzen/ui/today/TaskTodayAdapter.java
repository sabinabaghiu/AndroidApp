package sabinabaghiu.plannerzen.ui.today;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sabinabaghiu.plannerzen.R;

public class TaskTodayAdapter extends RecyclerView.Adapter<TaskTodayAdapter.ViewHolder> {
    private ArrayList<Task> tasks = new ArrayList<>();
    private OnItemClickListener itemClickListener;
    private Context context;


    public TaskTodayAdapter(){}

    public TaskTodayAdapter(Context context, OnItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    public void UpdateList(ArrayList<Task> tasks) {
        this.tasks = tasks;
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
    public TaskTodayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.task_list_item_today, parent, false);
        return new TaskTodayAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskTodayAdapter.ViewHolder holder, int position) {
        holder.title.setText(tasks.get(position).getTitle() + " - " + tasks.get(position).getTime() + " min");
        holder.important.setImageResource(R.drawable.ic_baseline_priority_high_24);
        if (tasks.get(position).isImportant() == false)
            holder.important.setVisibility(View.INVISIBLE);
        holder.done.setImageResource(R.drawable.ic_baseline_check_box_24);
        holder.done.setVisibility(View.INVISIBLE);
        if (tasks.get(position).isDone() == true)
            holder.done.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        ImageView important, done;


        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.task_today_title);
            important = itemView.findViewById(R.id.task_important);
            done = itemView.findViewById(R.id.task_done);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                itemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemClickListener{
        void onItemClick( int position);
    }
}
