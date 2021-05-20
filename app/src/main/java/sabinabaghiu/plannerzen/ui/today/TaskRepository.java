package sabinabaghiu.plannerzen.ui.today;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import sabinabaghiu.plannerzen.ui.lists.DateOrTask;

public class TaskRepository {
    private static TaskRepository instance;
    private DatabaseReference myRef;
    private DatabaseReference userReference;
    private TaskLiveData task;

    public TaskRepository() {}

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

    public void saveTask(String title, int time, boolean isImportant, Calendar date, boolean isDone){
        myRef.push().setValue(new Task(title, time, isImportant, date, isDone));
    }

    public TaskLiveData getTasks() {
        return task;
    }


    public void deleteTask(String id){
        myRef.child(id).removeValue();
    }

//    public void editTask(DateOrTask task){
//        DatabaseReference editingTask = myRef.child(task.getTask().getId());
//
//        editingTask.child("done").setValue(task.getTask().isDone());
//        editingTask.child("important").setValue(task.getTask().isImportant());
//        editingTask.child("time").setValue(task.getTask().getTime());
//        editingTask.child("timestamp").setValue(task.getTask().getTimestamp());
//        editingTask.child("title").setValue(task.getTask().getTitle());
//    }
}
