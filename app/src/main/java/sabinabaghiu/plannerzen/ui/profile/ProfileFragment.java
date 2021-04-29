package sabinabaghiu.plannerzen.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import sabinabaghiu.plannerzen.R;
import sabinabaghiu.plannerzen.ui.today.Habit;
import sabinabaghiu.plannerzen.ui.today.Todo;
import sabinabaghiu.plannerzen.ui.today.TodoTodayAdapter;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private RecyclerView profileRecyclerView;
    private TextView profileTextView;
    HabitProfileAdapter habitProfileAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        setHasOptionsMenu(true);

            //habit recycler view
        profileRecyclerView = root.findViewById(R.id.recyclerViewProfile);
        profileTextView = root.findViewById(R.id.textViewNoHabitsProfile);
        profileRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        profileViewModel.getAllHabits().observe(getViewLifecycleOwner(), habits -> {
            if (!habits.isEmpty()) {
                for (Habit h : habits) {
                    profileRecyclerView.setVisibility(View.VISIBLE);
                    habitProfileAdapter = new HabitProfileAdapter((ArrayList<Habit>) habits);
                    profileRecyclerView.setAdapter(habitProfileAdapter);
                    profileTextView.setVisibility(View.GONE);
                }
            } else {
                profileRecyclerView.setVisibility(View.GONE);
                profileTextView.setVisibility(View.VISIBLE);
            }
        });

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.profile_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout){
            Toast toast = Toast.makeText(getContext(), "Ahoy", Toast.LENGTH_SHORT);
            toast.show();
            return true;
//            Intent intent = new Intent(MainActivity.this, MainActivity1.class);
//            startActivity(intent);
        } else return false;
    }
}