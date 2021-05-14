package sabinabaghiu.plannerzen.ui.today;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class TaskRepository {
    private static TaskRepository instance;
    private DatabaseReference myRef;
    private TaskLiveData taskList;

    private TaskRepository() {}

    public static synchronized TaskRepository getInstance(){
        if (instance == null)
            instance = new TaskRepository();
        return instance;
    }

    public void init(String userId) {
        myRef = FirebaseDatabase.getInstance().getReference().child("Tasks");
        taskList = new TaskLiveData(myRef);
    }

    public void saveTask(String userId, String title, int time, boolean isImportant, String date, boolean isDone){
        myRef.setValue(new Task(userId, title, time, isImportant, date, isDone));
    }

    public TaskLiveData getTask() {
        return taskList;
    }
}
