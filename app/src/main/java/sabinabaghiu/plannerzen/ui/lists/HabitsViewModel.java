package sabinabaghiu.plannerzen.ui.lists;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import sabinabaghiu.plannerzen.ui.login.UserRepository;
import sabinabaghiu.plannerzen.ui.today.Habit;
import sabinabaghiu.plannerzen.ui.today.HabitRepository;

public class HabitsViewModel extends AndroidViewModel {
    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public HabitsViewModel(Application application) {
        super(application);
        habitRepository = HabitRepository.getInstance();
        userRepository = UserRepository.getInstance(application);
    }

    public void init() {
        habitRepository.init(getCurrentUser().getValue().getUid());
    }
    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }


    public void saveHabit(String title, int goal, int iconId, boolean isDone, int count) {
        habitRepository.saveHabit(title, goal, iconId, isDone, count);
    }

    public LiveData<ArrayList<Habit>> getHabits() {
        return habitRepository.getHabits();
    }
}
