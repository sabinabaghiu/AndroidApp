package sabinabaghiu.plannerzen.ui.today;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sabinabaghiu.plannerzen.R;

public class TodayFragment extends Fragment {

    private TodayViewModel todayViewModel;
    private RecyclerView todoRecyclerView;
    private RecyclerView habitRecyclerView;
    TodoAdapter todoAdapter;
    HabitAdapter habitAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        todayViewModel =
                new ViewModelProvider(this).get(TodayViewModel.class);
        View root = inflater.inflate(R.layout.fragment_today, container, false);

        //todo recycler view
        todoRecyclerView = root.findViewById(R.id.recyclerViewTodoToday);
        todoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Todo> todos = new ArrayList<>();
        todos.add(new Todo("Call mom", 10, false));
        todos.add(new Todo("Do laundry", 30, true));
        todos.add(new Todo("Finish assignment", 60, true));
        todos.add(new Todo("Call mom", 10, false));
        todos.add(new Todo("Call mom", 10, false));
        todos.add(new Todo("Call mom", 10, true));

        todoAdapter = new TodoAdapter(todos);
        todoRecyclerView.setAdapter(todoAdapter);

        //habit recycler view
        habitRecyclerView = root.findViewById(R.id.recyclerViewHabitsToday);
        habitRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Habit> habits = new ArrayList<>();
        habits.add(new Habit("Drink water", 0, R.drawable.ic_baseline_priority_high_24, false, 0));
        habits.add(new Habit("Don't eat chocolate", 20, R.drawable.ic_baseline_priority_high_24, true, 6));
        habits.add(new Habit("Drink water", 50, R.drawable.ic_baseline_priority_high_24, false, 0));
        habits.add(new Habit("Drink water", 0, R.drawable.ic_baseline_priority_high_24, false, 0));

        habitAdapter = new HabitAdapter(habits);
        habitRecyclerView.setAdapter(habitAdapter);

        return root;
    }
}