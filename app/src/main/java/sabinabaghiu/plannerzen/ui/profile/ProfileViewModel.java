package sabinabaghiu.plannerzen.ui.profile;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import sabinabaghiu.plannerzen.ui.login.UserRepository;

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