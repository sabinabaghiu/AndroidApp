package sabinabaghiu.plannerzen.ui.profile;

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

public class ProfileViewModel extends AndroidViewModel {

    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final TaskRepository taskRepository;

    public ProfileViewModel(Application application) {
        super(application);
        habitRepository = HabitRepository.getInstance();
        taskRepository = TaskRepository.getInstance();
        userRepository = UserRepository.getInstance(application);
    }

    public void initHabit() {
        habitRepository.init(getCurrentUser().getValue().getUid());
    }

    public void initTask() {
        taskRepository.init(getCurrentUser().getValue().getUid());
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public LiveData<ArrayList<Habit>> getHabits(){
        return habitRepository.getHabits();
    }
    public LiveData<ArrayList<Task>> getTasks() {
        return taskRepository.getTasks();
    }

    public void signOut(){
        userRepository.signOut();
    }

}