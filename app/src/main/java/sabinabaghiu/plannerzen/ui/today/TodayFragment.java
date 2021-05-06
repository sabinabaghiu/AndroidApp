package sabinabaghiu.plannerzen.ui.today;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sabinabaghiu.plannerzen.R;
import sabinabaghiu.plannerzen.ui.login.LoginViewModel;

public class TodayFragment extends Fragment {

    private TodayViewModel todayViewModel;
    private RecyclerView todoRecyclerView;
    private RecyclerView habitRecyclerView;
    private TextView todoTextView;
    private TextView habitTextView;
    TodoTodayAdapter todoTodayAdapter;
    HabitTodayAdapter habitTodayAdapter;
    private LoginViewModel loginViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        todayViewModel =
                new ViewModelProvider(this).get(TodayViewModel.class);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        View root = inflater.inflate(R.layout.fragment_today, container, false);


        //todo recycler view
        todoRecyclerView = root.findViewById(R.id.recyclerViewTodoToday);
        todoTextView = root.findViewById(R.id.textViewNoTodosToday);
        todoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        todayViewModel.getAllTodos().observe(getViewLifecycleOwner(), todos -> {
//            if (!todos.isEmpty()) {
//                for (Todo t : todos) {
//                    todoRecyclerView.setVisibility(View.VISIBLE);
//                    todoTodayAdapter = new TodoTodayAdapter((ArrayList<Todo>) todos);
//                    todoRecyclerView.setAdapter(todoTodayAdapter);
//                    todoTextView.setVisibility(View.GONE);
//                }
//            } else {
//                todoRecyclerView.setVisibility(View.GONE);
//                todoTextView.setVisibility(View.VISIBLE);
//            }
//        });

        //habit recycler view
        habitRecyclerView = root.findViewById(R.id.recyclerViewHabitsToday);
        habitTextView = root.findViewById(R.id.textViewNoHabitsToday);
        habitRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        todayViewModel.getAllHabits().observe(getViewLifecycleOwner(), habits -> {
//            if (!habits.isEmpty()) {
//                for (Habit h : habits) {
//                    habitRecyclerView.setVisibility(View.VISIBLE);
//                    habitTodayAdapter = new HabitTodayAdapter((ArrayList<Habit>) habits);
//                    habitRecyclerView.setAdapter(habitTodayAdapter);
//                    habitTextView.setVisibility(View.GONE);
//                }
//            } else {
//                habitRecyclerView.setVisibility(View.GONE);
//                habitTextView.setVisibility(View.VISIBLE);
//            }
//        });

        return root;
    }
}