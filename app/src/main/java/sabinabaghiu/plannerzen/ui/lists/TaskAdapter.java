package sabinabaghiu.plannerzen.ui.lists;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sabinabaghiu.plannerzen.R;
import sabinabaghiu.plannerzen.ui.today.Task;
import sabinabaghiu.plannerzen.ui.today.TaskRepository;

public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private ArrayList<DateOrTask> sections = new ArrayList<>();
        private Context context;
//    private static TasksViewModel instance;

    public TaskAdapter(Context context){
        this.context = context;
    }


    public Context getContext(){
        return context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void UpdateList(List<Task> tasks) throws ParseException {
        Map<String, List<Task>> map = new HashMap<>();
        if (tasks != null){
            for (Task task : tasks) {
                String date = task.getDate();
                Calendar c = new GregorianCalendar();
                if (map.containsKey(date)){
                    List<Task> list = map.get(date);
                    list.add(task);
                }
                else
                {
                    List<Task> list = new ArrayList<Task>();
                    list.add(task);
                    map.put(date, list);
                }
            }
        }

        ArrayList<DateOrTask> groupedTasks = new ArrayList<>();
        List<String> sortByDate = new ArrayList<>(map.keySet());
        Collections.sort(sortByDate);
        Collections.reverse(sortByDate);

        for (String date : sortByDate) {
            groupedTasks.add(DateOrTask.createDate(date));
            List<Task> tasksByDate = map.get(date);
            Collections.sort(tasksByDate);
            Collections.reverse(tasksByDate);
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


//    public void getTask(int position){
//        Task task = tasks.get(position);
//        TasksViewModel tasksViewModel = tasksViewModel.ge
//    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.task_list_item, parent, false);
            return new TaskViewHolder(view);
        } else {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.date_list_item, parent, false);
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
            h.date.setText(section.getDate());
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

    private boolean compareDates(String date1, String date2) throws ParseException {
        SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
        Date d1 = sdformat.parse(date1);
        Date d2 = sdformat.parse(date2);
        return d1.before(d2);
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView icon;


        TaskViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.task_add);
            icon = itemView.findViewById(R.id.icon_task);
        }
    }

    public  class DateViewHolder extends RecyclerView.ViewHolder {
        TextView date;

        DateViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date_item);
        }
    }


}
