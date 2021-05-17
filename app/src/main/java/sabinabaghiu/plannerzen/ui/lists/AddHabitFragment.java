package sabinabaghiu.plannerzen.ui.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;
import sabinabaghiu.plannerzen.R;


public class AddHabitFragment extends Fragment {
    private HabitsViewModel habitsViewModel;
    private Spinner spinner;
    private EditText titleEditText;
    private EditText goalEditText;
    private Button saveButton;
    private Button cancelButton;
    private int iconId;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        habitsViewModel = new ViewModelProvider(this).get(HabitsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_habit, container, false);
        BottomNavigationView navigationView = getActivity().findViewById(R.id.nav_view);
        if (navigationView != null)
            navigationView.setVisibility(View.INVISIBLE);
        habitsViewModel.init();
        titleEditText = root.findViewById(R.id.add_habit_title);
        goalEditText = root.findViewById(R.id.add_habit_goal);
        spinner = root.findViewById(R.id.spinner_habit_icon);
        saveButton = root.findViewById(R.id.button_save_habit);
        cancelButton = root.findViewById(R.id.button_cancel_habit);

        //setting the adapter for spinner
        spinner.setAdapter(new SpinnerAdapter(getContext(), R.layout.spinner_row, getAllRows()));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerRow row = (SpinnerRow) parent.getItemAtPosition(position);
                iconId = row.getIconId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        saveButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString().trim();
            int goal = Integer.parseInt(goalEditText.getText().toString().trim());
            habitsViewModel.saveHabit(title, goal, iconId, false, 0);
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_navigation_add_habit_to_navigation_lists);
        });

        cancelButton.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_navigation_add_habit_to_navigation_lists);
        });
        return root;
    }


    public List<SpinnerRow> getAllRows(){
        List<SpinnerRow> options = new ArrayList<>();
        options.add(new SpinnerRow("Book icon", R.drawable.icon_book));
        options.add(new SpinnerRow("Sport icon", R.drawable.icon_sport));
        options.add(new SpinnerRow("Sun icon", R.drawable.icon_sun));
        return options;
    }
}
