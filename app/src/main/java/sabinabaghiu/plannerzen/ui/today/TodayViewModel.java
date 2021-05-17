package sabinabaghiu.plannerzen.ui.today;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import sabinabaghiu.plannerzen.ui.login.UserRepository;

public class TodayViewModel extends AndroidViewModel {
    private final TaskRepository taskRepository;
    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public TodayViewModel(Application application) {
        super(application);
        taskRepository = TaskRepository.getInstance();
        habitRepository = HabitRepository.getInstance();
        userRepository = UserRepository.getInstance(application);
    }

    public void init() {
        habitRepository.init(getCurrentUser().getValue().getUid());
        taskRepository.init(getCurrentUser().getValue().getUid());
    }

    public LiveData<ArrayList<Habit>> getHabits(){
        return habitRepository.getHabits();
    }

    public LiveData<ArrayList<Task>> getTasks() {
        return taskRepository.getTasks();
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }
}