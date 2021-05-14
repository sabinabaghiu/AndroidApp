package sabinabaghiu.plannerzen.ui.lists;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import sabinabaghiu.plannerzen.R;
import sabinabaghiu.plannerzen.ui.today.Task;

public class AddTaskFragment extends Fragment {
    private ListsViewModel listsViewModel;
    private EditText titleEditText, dateEditText, timeEditText;
    private Button saveButton, cancelButton;
    private Switch isImportant;
    private DatePickerDialog datePicker;
    private String currentDate;
    private DatabaseReference reference;
    private Task task;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listsViewModel =
                new ViewModelProvider(this).get(ListsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_task, container, false);
        BottomNavigationView navigationView = getActivity().findViewById(R.id.nav_view);
        if (navigationView != null)
            navigationView.setVisibility(View.INVISIBLE);
        titleEditText = root.findViewById(R.id.task_add_title);
        dateEditText = root.findViewById(R.id.task_add_date);
        timeEditText = root.findViewById(R.id.task_add_time);
        isImportant = root.findViewById(R.id.switch_task_add);
        saveButton = root.findViewById(R.id.button_save_task);
        cancelButton = root.findViewById(R.id.button_cancel_task);


        dateEditText.setInputType(InputType.TYPE_NULL);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                datePicker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                dateEditText.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                                currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                            }
                        }, year, month, day);
                datePicker.show();
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://plannerzen-43809-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Tasks");

        saveButton.setOnClickListener(v ->{
            String currentUser = listsViewModel.getCurrentUser().getValue().getUid();
            String title = titleEditText.getText().toString().trim();
            int time = Integer.parseInt(timeEditText.getText().toString().trim());
            boolean isImp = isImportant.isChecked();
            task = new Task(currentUser, title, time, isImp, currentDate, false);
            reference.push().setValue(task);
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.navigation_tasks);

            if (navigationView != null)
                navigationView.setVisibility(View.VISIBLE);
        });


        cancelButton.setOnClickListener(v -> {
            if (navigationView != null)
                navigationView.setVisibility(View.VISIBLE);
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.navigation_add_task);
        });

        return root;
    }
}
