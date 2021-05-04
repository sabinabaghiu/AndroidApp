package sabinabaghiu.plannerzen.ui.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import sabinabaghiu.plannerzen.R;
import sabinabaghiu.plannerzen.ui.today.Habit;

public class HabitsFragment extends Fragment {

    private ListsViewModel listsViewModel;
    private RecyclerView habitRecyclerView;
    private TextView habitTextView;
    HabitListsAdapter habitListsAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        listsViewModel =
                new ViewModelProvider(this).get(ListsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_habits, container, false);


            //habit recycler view
        habitRecyclerView = root.findViewById(R.id.recyclerViewHabitMyLists);
        habitTextView = root.findViewById(R.id.textViewNoHabitsLists);
        habitRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        listsViewModel.getAllHabits().observe(getViewLifecycleOwner(), habits -> {
//            if (!habits.isEmpty()) {
//                for (Habit h : habits) {
//                    habitRecyclerView.setVisibility(View.VISIBLE);
//                    habitListsAdapter = new HabitListsAdapter((ArrayList<Habit>) habits);
//                    habitRecyclerView.setAdapter(habitListsAdapter);
//                    habitTextView.setVisibility(View.GONE);
//                }
//            } else {
//                habitRecyclerView.setVisibility(View.GONE);
//                habitTextView.setVisibility(View.VISIBLE);
//            }
//        });

             //add button
        CoordinatorLayout coordinatorLayout = root.findViewById(R.id.coordinatorLayoutHabits);
        FloatingActionButton fab = root.findViewById(R.id.fabHabits);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.navigation_add_habit);
            }
        });
        return root;
    }
}
