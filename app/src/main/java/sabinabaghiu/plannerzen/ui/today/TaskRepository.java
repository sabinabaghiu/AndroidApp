package sabinabaghiu.plannerzen.ui.today;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class TaskRepository {
    private static TaskRepository instance;
    private DatabaseReference myRef, myRefToday;
    private DatabaseReference userReference;
    private TaskLiveData task;

    private TaskRepository() {}

    public static synchronized TaskRepository getInstance(){
        if (instance == null)
            instance = new TaskRepository();
        return instance;
    }

    public void init(String userId) {
        userReference = FirebaseDatabase.getInstance("https://plannerzen-43809-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Users").child(userId);
        myRef = userReference.child("Tasks");
        task = new TaskLiveData(myRef);
    }

    public void saveTask(String title, int time, boolean isImportant, String date, boolean isDone){
        myRef.push().setValue(new Task(title, time, isImportant, date, isDone));
    }

    public TaskLiveData getTasks() {
        return task;
    }

    public void deleteTask(String id){
        myRef.child(id).removeValue();
    }


}
