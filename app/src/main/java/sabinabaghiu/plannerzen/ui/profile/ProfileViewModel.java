package sabinabaghiu.plannerzen.ui.profile;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import sabinabaghiu.plannerzen.ui.login.UserRepository;
import sabinabaghiu.plannerzen.ui.today.Habit;
import sabinabaghiu.plannerzen.ui.today.HabitRepository;
import sabinabaghiu.plannerzen.ui.today.Todo;
import sabinabaghiu.plannerzen.ui.today.TodoRepository;

public class ProfileViewModel extends AndroidViewModel {

//    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public ProfileViewModel(Application application) {
        super(application);
//        habitRepository = HabitRepository.getInstance(application);
        userRepository = UserRepository.getInstance(application);
    }



    public void signOut(){
        userRepository.signOut();
    }

}