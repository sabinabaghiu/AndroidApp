package sabinabaghiu.plannerzen.ui.lists;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import sabinabaghiu.plannerzen.ui.login.UserRepository;
import sabinabaghiu.plannerzen.ui.today.Habit;
import sabinabaghiu.plannerzen.ui.today.HabitRepository;
import sabinabaghiu.plannerzen.ui.today.Task;
import sabinabaghiu.plannerzen.ui.today.TaskRepository;

public class ListsViewModel extends AndroidViewModel {

    private final TaskRepository taskRepository;
    private final HabitRepository habitRepository;
        private final UserRepository userRepository;

    public ListsViewModel(Application application) {
        super(application);
        taskRepository = TaskRepository.getInstance();
        habitRepository = HabitRepository.getInstance();
        userRepository = UserRepository.getInstance(application);
    }


    public void initHabit() {
        habitRepository.init(getCurrentUser().getValue().getUid());
    }
    public void initTask(){
        taskRepository.init(getCurrentUser().getValue().getUid());
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }


    public void saveHabit(String title, int goal, int iconId, boolean isDone, int count){
        habitRepository.saveHabit(title, goal, iconId, isDone, count);
    }

    public void saveTask(String title, int time, boolean isImportant, String date, boolean isDone){
        taskRepository.saveTask(title, time, isImportant, date, isDone);
    }

    public LiveData<ArrayList<Habit>> getHabits(){
        return habitRepository.getHabits();
    }

    public LiveData<ArrayList<Task>> getTasks() {
        return taskRepository.getTasks();
    }
}