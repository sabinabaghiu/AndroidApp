package sabinabaghiu.plannerzen.ui.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import sabinabaghiu.plannerzen.R;
import sabinabaghiu.plannerzen.ui.today.TaskAdapter;

public class TasksFragment extends Fragment {


    private ListsViewModel listsViewModel;
    private RecyclerView taskRecyclerView;
    private TextView taskTextView;
    private TaskAdapter taskAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listsViewModel =
                new ViewModelProvider(this).get(ListsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tasks, container, false);
        listsViewModel.initTask();


            //task recycler view
        taskRecyclerView = root.findViewById(R.id.recyclerViewTodoMyLists);
        taskRecyclerView.setHasFixedSize(true);
        taskTextView = root.findViewById(R.id.textViewNoTodoLists);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        taskAdapter = new TaskAdapter(getContext());
        taskRecyclerView.setAdapter(taskAdapter);
        listsViewModel.getTasks().observe(getViewLifecycleOwner(), tasks -> {
            if (tasks.size() == 0) {
                taskRecyclerView.setVisibility(View.INVISIBLE);
                taskTextView.setVisibility(View.VISIBLE);
            }
            else {
                taskRecyclerView.setVisibility(View.VISIBLE);
                taskTextView.setVisibility(View.INVISIBLE);
                taskAdapter.UpdateList(tasks);
            }
        });

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
