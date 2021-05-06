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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;


import java.text.DateFormat;
import java.util.Calendar;

import sabinabaghiu.plannerzen.R;

public class TodoListAddFragment extends Fragment {
    private ListsViewModel listsViewModel;
    private EditText title, date, description;
    private Button saveButton, cancelButton, addTaskButton;
    private DatePickerDialog datePicker;
    private String currentDate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listsViewModel =
                new ViewModelProvider(this).get(ListsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_todo_list_add, container, false);
        title = root.findViewById(R.id.todo_add_title);
        date = root.findViewById(R.id.todo_add_date);
        description = root.findViewById(R.id.todo_add_description);
        saveButton = root.findViewById(R.id.button_save_list);
        cancelButton = root.findViewById(R.id.button_cancel_list);
        addTaskButton = root.findViewById(R.id.button_add_task);

        date.setInputType(InputType.TYPE_NULL);
        date.setOnClickListener(new View.OnClickListener() {
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
                                date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                                currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                            }
                        }, year, month, day);
                datePicker.show();
            }
        });

        addTaskButton.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.navigation_add_task);
        });

        cancelButton.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.navigation_todo_list_add);
        });

        return root;
    }
}
