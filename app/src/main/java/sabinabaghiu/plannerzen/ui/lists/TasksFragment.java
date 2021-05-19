package sabinabaghiu.plannerzen.ui.lists;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;

import sabinabaghiu.plannerzen.R;
import sabinabaghiu.plannerzen.ui.today.Task;

public class TasksFragment extends Fragment {


    private TasksViewModel tasksViewModel;
    private RecyclerView taskRecyclerView;
    private TextView taskTextView;
    private TaskAdapter taskAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tasksViewModel =
                new ViewModelProvider(this).get(TasksViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tasks, container, false);
        tasksViewModel.init();
        BottomNavigationView navigationView = getActivity().findViewById(R.id.nav_view);
        if (navigationView != null)
            navigationView.setVisibility(View.VISIBLE);


            //task recycler view
        taskRecyclerView = root.findViewById(R.id.recyclerViewTodoMyLists);
        taskRecyclerView.setHasFixedSize(true);
        taskTextView = root.findViewById(R.id.textViewNoTodoLists);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        taskAdapter = new TaskAdapter(getContext());
        taskRecyclerView.setAdapter(taskAdapter);
        tasksViewModel.getTasks().observe(getViewLifecycleOwner(), tasks -> {
            if (tasks.size() == 0) {
                taskRecyclerView.setVisibility(View.INVISIBLE);
                taskTextView.setVisibility(View.VISIBLE);
            }
            else {
                taskRecyclerView.setVisibility(View.VISIBLE);
                taskTextView.setVisibility(View.INVISIBLE);
                try {
                    taskAdapter.UpdateList(tasks);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
//        tasksViewModel.getTasks().observe(getViewLifecycleOwner(), tasks -> {
//            Calendar c = new GregorianCalendar();
//            ArrayList<Task> tasksToShow = (ArrayList<Task>) tasks.stream().filter(f -> f.getDate().after(c)).collect(Collectors.toList());
//            try {
//                taskAdapter.UpdateList(tasksToShow);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            if (tasks.size() == 0) {
//                taskRecyclerView.setVisibility(View.INVISIBLE);
//                taskTextView.setVisibility(View.VISIBLE);
//            }
//            else {
//                taskRecyclerView.setVisibility(View.VISIBLE);
//                taskTextView.setVisibility(View.INVISIBLE);
//                try {
//                    taskAdapter.UpdateList(tasksToShow);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

            //swiping for edit and delete
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeItemTouchHelperTasks(taskAdapter));
        itemTouchHelper.attachToRecyclerView(taskRecyclerView);

            //add button
        CoordinatorLayout coordinatorLayout = root.findViewById(R.id.coordinatorLayoutTodos);
        FloatingActionButton fab = root.findViewById(R.id.fabTodos);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.navigation_add_task);
            }
        });

        return root;
    }
}
