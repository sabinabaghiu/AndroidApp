package sabinabaghiu.plannerzen.ui.lists;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import sabinabaghiu.plannerzen.ui.login.UserRepository;
import sabinabaghiu.plannerzen.ui.today.Habit;
import sabinabaghiu.plannerzen.ui.today.HabitRepository;
import sabinabaghiu.plannerzen.ui.today.Todo;
import sabinabaghiu.plannerzen.ui.today.TodoRepository;

public class ListsViewModel extends AndroidViewModel {

    private final TodoRepository todoRepository;
    private final HabitRepository habitRepository;
        private final UserRepository userRepository;

    public ListsViewModel(Application application) {
        super(application);
        todoRepository = TodoRepository.getInstance(application);
        habitRepository = HabitRepository.getInstance();
               userRepository = UserRepository.getInstance(application);
    }


    public void init() {
        String userId = userRepository.getCurrentUser().getValue().getUid();
        habitRepository.init(userId);
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public LiveData<Habit> getHabit(){
        return habitRepository.getHabit();
    }

    public void saveHabit(String userId, String title, int goal, int iconId, boolean isDone, int count){
        habitRepository.saveHabit(userId, title, goal, iconId, isDone, count);
    }
}