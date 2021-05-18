package sabinabaghiu.plannerzen.ui.lists;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import sabinabaghiu.plannerzen.ui.login.UserRepository;
import sabinabaghiu.plannerzen.ui.today.Task;
import sabinabaghiu.plannerzen.ui.today.TaskRepository;

public class TasksViewModel extends AndroidViewModel {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private static TasksViewModel instance;
    private MutableLiveData<Task> editingTask;
    private Application application;

//    public static synchronized TasksViewModel getInstance(){
//        if (instance == null)
//            instance = new TasksViewModel(a);
//        return instance;
//    }


    public TasksViewModel(Application application) {
        super(application);
        taskRepository = TaskRepository.getInstance();
        userRepository = UserRepository.getInstance(application);
    }

    public void init() {
        taskRepository.init(getCurrentUser().getValue().getUid());
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }

    public void saveTask(String title, int time, boolean isImportant, String date, boolean isDone) {
        taskRepository.saveTask(title, time, isImportant, date, isDone);
    }

    public LiveData<ArrayList<Task>> getTasks() {
        return taskRepository.getTasks();
    }

    public MutableLiveData<Task> getEditingTask() {
        return editingTask;
    }

    public void setEditingTask(Task editingTask) {
        this.editingTask.setValue(editingTask);
    }
}
