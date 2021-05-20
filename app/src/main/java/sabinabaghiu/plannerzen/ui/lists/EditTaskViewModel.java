package sabinabaghiu.plannerzen.ui.lists;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
