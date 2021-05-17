package sabinabaghiu.plannerzen.ui.lists;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import sabinabaghiu.plannerzen.R;

public class EditTaskFragment extends Fragment {
    private TasksViewModel tasksViewModel;
    private EditText titleEditText, dateEditText, timeEditText;
    private Button saveButton, cancelButton;
    private Switch isImportant;
    private DatePickerDialog datePicker;
    private String currentDate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tasksViewModel =
                new ViewModelProvider(this).get(TasksViewModel.class);
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


//        tasksViewModel.getEditingTask().observe(getViewLifecycleOwner(), task -> {
//            titleEditText.setText(task.getTitle());
//            dateEditText.setText(task.getDate());
//            timeEditText.setText(task.getTime());
//            isImportant.setChecked(task.isImportant());
//        });

//        dateEditText.setInputType(InputType.TYPE_NULL);
//        dateEditText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar calendar = Calendar.getInstance();
//                int day = calendar.get(Calendar.DAY_OF_MONTH);
//                int month = calendar.get(Calendar.MONTH);
//                int year = calendar.get(Calendar.YEAR);
//                datePicker = new DatePickerDialog(getContext(),
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                                calendar.set(Calendar.YEAR, year);
//                                calendar.set(Calendar.MONTH, month);
//                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                                dateEditText.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
//                                currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
//                            }
//                        }, year, month, day);
//                datePicker.show();
//            }
//        });

        saveButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString().trim();
            int time = Integer.parseInt(timeEditText.getText().toString().trim());
            boolean isImp = isImportant.isChecked();
            tasksViewModel.saveTask(title, time, isImp, currentDate, false);
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_navigation_add_task_to_navigation_lists);
        });

        cancelButton.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_navigation_add_task_to_navigation_lists);
        });

        return root;
    }
}
