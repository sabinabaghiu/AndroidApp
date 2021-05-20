package sabinabaghiu.plannerzen.ui.today;

import android.app.Application;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;

import sabinabaghiu.plannerzen.R;

public class TodayFragment extends Fragment implements TaskTodayAdapter.OnItemClickListener, HabitTodayAdapter.HabitCardAdapterOnClickListener{

    private TodayViewModel todayViewModel;
    private RecyclerView taskRecyclerView;
    private RecyclerView habitRecyclerView;
    private TextView taskTextView, habitTextView;
    private TaskTodayAdapter taskTodayAdapter;
    private HabitTodayAdapter habitTodayAdapter;
    ArrayList<Task> tasksToday;
    Application application;
    private DatabaseReference refTask, refHabit;
    private Task selectedTask;
    private Habit selectedHabit;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        todayViewModel =
                new ViewModelProvider(this).get(TodayViewModel.class);
        View root = inflater.inflate(R.layout.fragment_today, container, false);
        application = new Application();
        todayViewModel.init();

        String userId = todayViewModel.getCurrentUser().getValue().getUid();
        refTask = FirebaseDatabase.getInstance("https://plannerzen-43809-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Users").child(userId).child("Tasks");
        refHabit = FirebaseDatabase.getInstance("https://plannerzen-43809-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Users").child(userId).child("Habits");

        //task recycler view
        taskRecyclerView = root.findViewById(R.id.recyclerViewTodoToday);
        taskTextView = root.findViewById(R.id.textViewNoTodosToday);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        taskTodayAdapter = new TaskTodayAdapter(getContext(), this);
        taskRecyclerView.setAdapter(taskTodayAdapter);
        todayViewModel.getTasks().observe(getViewLifecycleOwner(), tasks -> {
            Calendar c = new GregorianCalendar();
            int day = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            int year = c.get(Calendar.YEAR);
            Calendar currentDate = new GregorianCalendar(year, month, day);
            Long myDate = currentDate.getTimeInMillis();

            tasksToday = (ArrayList<Task>) tasks.stream().filter(f -> f.getTimestamp() == (myDate)).collect(Collectors.toList());
            taskTodayAdapter.UpdateList(tasksToday);
            if (tasksToday.size() == 0) {
                taskRecyclerView.setVisibility(View.INVISIBLE);
                taskTextView.setVisibility(View.VISIBLE);
            } else {
                taskRecyclerView.setVisibility(View.VISIBLE);
                taskTextView.setVisibility(View.GONE);
                taskTodayAdapter.UpdateList(tasksToday);
            }
        });


        //habit recycler view
        habitRecyclerView = root.findViewById(R.id.recyclerViewHabitsToday);
        habitTextView = root.findViewById(R.id.textViewNoHabitsToday);
        habitRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        habitTodayAdapter = new HabitTodayAdapter(getContext(), this);
        habitRecyclerView.setAdapter(habitTodayAdapter);
        todayViewModel.getHabits().observe(getViewLifecycleOwner(), habits -> {
            if (habits.size() == 0) {
                habitRecyclerView.setVisibility(View.INVISIBLE);
                habitTextView.setVisibility(View.VISIBLE);
            } else {
                habitRecyclerView.setVisibility(View.VISIBLE);
                habitTextView.setVisibility(View.GONE);
                habitTodayAdapter.updateList(habits);
            }
        });

        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onItemClick(int position) {
        selectedTask = tasksToday.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(taskTodayAdapter.getContext());
        builder.setTitle("Task done");
        builder.setMessage("Did you finish this task: " + selectedTask.getTitle() + "?");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String taskId = selectedTask.getId();
                    selectedTask.setDone(true);
                    refTask.child(taskId).child("done").setValue(true);
                    taskTodayAdapter.update();
                }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onHabitCardClick(int position) {
        selectedHabit = todayViewModel.getHabits().getValue().get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(habitTodayAdapter.getContext());
        builder.setTitle("Habit done");
        builder.setMessage("Did you " + selectedHabit.getTitle() + " today?");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String habitId = selectedHabit.getId();
                int count = selectedHabit.getCount();
                selectedHabit.setDone(true);
                selectedHabit.setCount(count+1);
                refHabit.child(habitId).child("done").setValue(true);
                refHabit.child(habitId).child("count").setValue(count+1);
                habitTodayAdapter.update();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}