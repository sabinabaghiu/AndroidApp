package sabinabaghiu.plannerzen.ui.lists;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Calendar;

import sabinabaghiu.plannerzen.ui.today.Task;
import sabinabaghiu.plannerzen.ui.today.TaskRepository;

public class EditTaskViewModel extends ViewModel {
    private final TaskRepository taskRepository;
    private static EditTaskViewModel instance;
    private Task task;
    private MutableLiveData<Task> editingTask;

        public static synchronized EditTaskViewModel getInstance(){
        if (instance == null)
            instance = new EditTaskViewModel();
        return instance;
    }
    public EditTaskViewModel() {
            taskRepository = new TaskRepository();
            task = new Task();
            editingTask = new MutableLiveData<>();
    }


//    public void setTask(String title, int time, boolean isImportant, Calendar date, boolean isDone)
//    {
//        task.setTitle(title);
//        task.setTime(time);
//        task.setImportant(isImportant);
//        task.setDone(isDone);
//
//        editingTask.setValue();
//    }

    public LiveData<Task> getEditingTask() {
        return editingTask;
    }

    public void editTask(Task task){
            editingTask.setValue(task);
    }

    public void updateTask(Task task){
            taskRepository.editTask(task);
    }
}
