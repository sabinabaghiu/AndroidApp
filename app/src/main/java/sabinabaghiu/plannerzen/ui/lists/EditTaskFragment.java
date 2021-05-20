package sabinabaghiu.plannerzen.ui.lists;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

import sabinabaghiu.plannerzen.R;
import sabinabaghiu.plannerzen.ui.today.Task;

public class EditTaskFragment extends Fragment {
    private TasksViewModel tasksViewModel;
    private EditTaskViewModel editTaskViewModel;
    private EditText titleEditText, dateEditText, timeEditText;
    private Button saveButton, cancelButton;
    private Switch isImportant;
    private Task task;
    int selectedYear = -1, selectedMonth = -1, selectedDay = -1;
    private DatePickerDialog datePicker;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tasksViewModel =
                new ViewModelProvider(this).get(TasksViewModel.class);
//        editTaskViewModel = EditTaskViewModel.getInstance();
        editTaskViewModel = new ViewModelProvider(this).get(EditTaskViewModel.class);
        View root = inflater.inflate(R.layout.fragment_edit_task, container, false);
        BottomNavigationView navigationView = getActivity().findViewById(R.id.nav_view);
        if (navigationView != null)
            navigationView.setVisibility(View.INVISIBLE);
        tasksViewModel.init();
        titleEditText = root.findViewById(R.id.task_edit_title);
        dateEditText = root.findViewById(R.id.task_edit_date);
        timeEditText = root.findViewById(R.id.task_edit_time);
        isImportant = root.findViewById(R.id.switch_task_edit);
        saveButton = root.findViewById(R.id.button_edit_save_task);
        cancelButton = root.findViewById(R.id.button_edit_cancel_task);

        editTaskViewModel.getEditingTask().observe(getViewLifecycleOwner(), this :: setFields);


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
                                selectedDay = dayOfMonth;
                                selectedMonth = month;
                                selectedYear = year;
                                dateEditText.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePicker.show();
            }
        });

        saveButton.setOnClickListener(v -> {
//            editTaskViewModel.editTask(task);
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_navigation_add_task_to_navigation_lists);
        });

        cancelButton.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_navigation_add_task_to_navigation_lists);
        });

        return root;
    }

    private void setFields(DateOrTask task){
        titleEditText.setText(task.getTask().getTitle());
        timeEditText.setText(task.getTask().getTime());
        isImportant.setChecked(task.getTask().isImportant());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(task.getTask().getTimestamp());
        if (selectedMonth == -1 && selectedYear == -1 && selectedDay == -1) {
            selectedYear = calendar.get(Calendar.YEAR);
            selectedMonth = calendar.get(Calendar.MONTH);
            selectedDay = calendar.get(Calendar.DATE);
            dateEditText.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
        }
    }
}
