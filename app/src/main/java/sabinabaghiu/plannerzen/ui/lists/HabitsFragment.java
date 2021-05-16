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

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import sabinabaghiu.plannerzen.R;
import sabinabaghiu.plannerzen.ui.today.Habit;

public class HabitsFragment extends Fragment {

    private ListsViewModel listsViewModel;
    private RecyclerView habitRecyclerView;
    private TextView habitTextView;
    private HabitListsAdapter habitListsAdapter;
    private List<Habit> habits;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        listsViewModel =
                new ViewModelProvider(this).get(ListsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_habits, container, false);
        listsViewModel.initHabit();



            //habit recycler view
        habitRecyclerView = root.findViewById(R.id.recyclerViewHabitMyLists);
        habitRecyclerView.setHasFixedSize(true);
        habitTextView = root.findViewById(R.id.textViewNoHabitsLists);
        habitRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        FirebaseRecyclerOptions<Habit> options = new FirebaseRecyclerOptions.Builder<Habit>().setQuery(reference, Habit.class).build();




//        reference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    habitRecyclerView.setVisibility(View.VISIBLE);
//                    habitTextView.setVisibility(View.GONE);
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        Habit habit = dataSnapshot.getValue(Habit.class);
//                        habits.add(habit);
//                    }
////                    habitListsAdapter = new HabitListsAdapter(options);
////                    habitRecyclerView.setAdapter(habitListsAdapter);
//                    habitListsAdapter = new HabitListsAdapter(getContext(), habits);
//                    habitRecyclerView.setAdapter(habitListsAdapter);
//                    habitListsAdapter.notifyDataSetChanged();
//                }
//                else {
//                    habitRecyclerView.setVisibility(View.GONE);
//                    habitTextView.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
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
