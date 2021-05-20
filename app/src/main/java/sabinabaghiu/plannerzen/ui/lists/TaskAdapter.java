package sabinabaghiu.plannerzen.ui.lists;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import sabinabaghiu.plannerzen.R;
import sabinabaghiu.plannerzen.ui.today.Task;
import sabinabaghiu.plannerzen.ui.today.TaskRepository;

public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private ArrayList<DateOrTask> sections = new ArrayList<>();
        private Context context;
        private EditTaskViewModel model = new EditTaskViewModel();
        View view;
//        private final OnItemClickListener itemClickListener;

//    public TaskAdapter(Context context, OnItemClickListener itemClickListener){
//        this.context = context;
//        this.itemClickListener = itemClickListener;
//
//    }

    public TaskAdapter(Context context) {
        this.context = context;
    }


    public Context getContext(){
        return getContext();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void UpdateList(List<Task> tasks) {
        Map<Calendar, List<Task>> map = new HashMap<>();
        if (tasks != null){
            for (Task task : tasks) {
                Calendar date = task.getDate();
                date.set(Calendar.HOUR_OF_DAY, 0);
                date.set(Calendar.MINUTE, 0);
                date.set(Calendar.SECOND, 0);
                date.set(Calendar.MILLISECOND, 0);
                if (map.containsKey(date)){
                    List<Task> list = map.get(date);
                    list.add(task);
                }
                else
                {
                    List<Task> list = new ArrayList<>();
                    list.add(task);
                    map.put(date, list);
                }
            }
        }

        ArrayList<DateOrTask> groupedTasks = new ArrayList<>();
        List<Calendar> sortByDate = new ArrayList<>(map.keySet());
        Collections.sort(sortByDate);

        for (Calendar date : sortByDate) {
            groupedTasks.add(DateOrTask.createDate(date));
            List<Task> tasksByDate = map.get(date);
            Collections.sort(tasksByDate);
            for (Task task : tasksByDate) {
                groupedTasks.add(DateOrTask.createTask(task));
            }
        }
        this.sections = groupedTasks;
        notifyDataSetChanged();
    }


    public void deleteItem(int position){
        DateOrTask section = sections.get(position);
        Task task = section.getTask();
        TaskRepository.getInstance().deleteTask(task.getId());
        sections.remove(section);
        notifyItemRemoved(position);
    }

//    public void editItem(int position){
//        DateOrTask section = sections.get(position);
//        model.select(section);
//        Navigation.findNavController((Activity) view.getContext(), R.id.nav_host_fragment).navigate(R.id.navigation_edit_task);
//        notifyItemChanged(position);
//    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.task_list_item, parent, false);
            return new TaskViewHolder(view);
        } else {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.date_list_item, parent, false);
            return new DateViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DateOrTask section = sections.get(position);
        if (section.isTask()) {
            Task task = section.getTask();
            TaskViewHolder h = (TaskViewHolder) holder;
            h.title.setText(task.getTitle() + " - " + task.getTime() + " min");
            h.icon.setImageResource(R.drawable.ic_baseline_priority_high_24);
            if (task.isImportant() == false)
                h.icon.setVisibility(View.INVISIBLE);
        } else {
            DateViewHolder h = (DateViewHolder) holder;
            Calendar date = section.getDate();
            h.date.setText(date.get(Calendar.DATE)+ " " + date.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + date.get(Calendar.YEAR));
        }
    }

    @Override
    public int getItemCount() {
        return sections.size();
    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        DateOrTask item = sections.get(position);
        if (!item.isTask()) {
            return 0;
        } else {
            return 1;
        }
    }

//    public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public class TaskViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView icon;


        TaskViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.task_add);
            icon = itemView.findViewById(R.id.icon_task);
//            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View v) {
//            itemClickListener.onItemClick(sections.get(getAdapterPosition()));
//        }
    }

    public  class DateViewHolder extends RecyclerView.ViewHolder {
        TextView date;

        DateViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date_item);
        }
    }

//    public interface OnItemClickListener{
//        void onItemClick(DateOrTask selectedTask);
//    }


}
