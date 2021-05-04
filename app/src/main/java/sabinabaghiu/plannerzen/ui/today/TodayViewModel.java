package sabinabaghiu.plannerzen.ui.today;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class TodayViewModel extends AndroidViewModel {

//    private final HabitRepository habitRepository;
    private final TodoRepository todoRepository;

    public TodayViewModel(Application application) {
        super(application);
//        habitRepository = HabitRepository.getInstance(application);
        todoRepository = TodoRepository.getInstance(application);
    }

    public LiveData<List<Todo>> getAllTodos() {
        return todoRepository.getAllTodos();
    }

}