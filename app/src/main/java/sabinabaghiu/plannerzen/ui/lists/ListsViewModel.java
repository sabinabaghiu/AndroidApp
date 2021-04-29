package sabinabaghiu.plannerzen.ui.lists;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import sabinabaghiu.plannerzen.ui.today.Habit;
import sabinabaghiu.plannerzen.ui.today.HabitRepository;
import sabinabaghiu.plannerzen.ui.today.Todo;
import sabinabaghiu.plannerzen.ui.today.TodoRepository;

public class ListsViewModel extends AndroidViewModel {

    private final TodoRepository todoRepository;
    private final HabitRepository habitRepository;
    private final ReminderRepository reminderRepository;

    public ListsViewModel(Application application) {
        super(application);
        todoRepository = TodoRepository.getInstance(application);
        habitRepository = HabitRepository.getInstance(application);
        reminderRepository = ReminderRepository.getInstance(application);
    }

    public LiveData<List<Todo>> getAllTodos() {
        return todoRepository.getAllTodos();
    }

    public LiveData<List<Habit>> getAllHabits() {
        return habitRepository.getAllHabits();
    }

    public LiveData<List<Reminder>> getAllReminders() {
        return reminderRepository.getAllReminders();
    }
}