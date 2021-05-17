package sabinabaghiu.plannerzen.ui.today;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;

import sabinabaghiu.plannerzen.R;

public class TodayFragment extends Fragment {

    private TodayViewModel todayViewModel;
    private RecyclerView taskRecyclerView;
    private RecyclerView habitRecyclerView;
    private TextView taskTextView, habitTextView;
    private TaskAdapter taskAdapter;
    private HabitTodayAdapter habitTodayAdapter;
     Application application;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        todayViewModel =
                new ViewModelProvider(this).get(TodayViewModel.class);
        View root = inflater.inflate(R.layout.fragment_today, container, false);
        application = new Application();
        todayViewModel.init();

        //task recycler view
        taskRecyclerView = root.findViewById(R.id.recyclerViewTodoToday);
        taskTextView = root.findViewById(R.id.textViewNoTodosToday);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        taskAdapter = new TaskAdapter(getContext());
        taskRecyclerView.setAdapter(taskAdapter);
        todayViewModel.getTasks().observe(getViewLifecycleOwner(), tasks -> {
            Calendar c = new GregorianCalendar();
            String myDate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
            ArrayList<Task> tasksToday = (ArrayList<Task>) tasks.stream().filter(f -> f.getDate().equals(myDate)).collect(Collectors.toList());
            taskAdapter.UpdateList(tasksToday);
            if (tasksToday.size() == 0) {
                taskRecyclerView.setVisibility(View.INVISIBLE);
                    taskTextView.setVisibility(View.VISIBLE);
            } else {
                taskRecyclerView.setVisibility(View.VISIBLE);
                    taskTextView.setVisibility(View.GONE);
                taskAdapter.UpdateList(tasksToday);
            }
        });


        //habit recycler view
        habitRecyclerView = root.findViewById(R.id.recyclerViewHabitsToday);
        habitTextView = root.findViewById(R.id.textViewNoHabitsToday);
        habitRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        habitTodayAdapter = new HabitTodayAdapter(getContext());
        habitRecyclerView.setAdapter(habitTodayAdapter);
        todayViewModel.getHabits().observe(getViewLifecycleOwner(), habits -> {
            if (habits.size() == 0) {
                habitRecyclerView.setVisibility(View.INVISIBLE);
                habitTextView.setVisibility(View.VISIBLE);
            } else {
                habitRecyclerView.setVisibility(View.VISIBLE);
                habitTextView.setVisibility(View.GONE);
                habitTodayAdapter.updateList(habits);
            }
        });

        return root;
    }
}