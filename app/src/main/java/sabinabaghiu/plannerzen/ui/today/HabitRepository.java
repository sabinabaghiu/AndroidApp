package sabinabaghiu.plannerzen.ui.today;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HabitRepository {
    private static HabitRepository instance;
    private final HabitDAO habitDAO;
    private final LiveData<List<Habit>> allHabits;
    private final ExecutorService executorService;

    private HabitRepository(Application application) {
        HabitDatabase database = HabitDatabase.getInstance(application);
        habitDAO = database.habitDAO();
        allHabits = habitDAO.getAllHabits();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized HabitRepository getInstance(Application application) {
        if (instance == null)
            instance = new HabitRepository(application);

        return instance;
    }

    public LiveData<List<Habit>> getAllHabits() {
        return allHabits;
    }

    public void insert(Habit habit) {
        executorService.execute(() -> habitDAO.insert(habit));
    }

    public void update(Habit habit) {
        executorService.execute(() -> habitDAO.update(habit));
    }

    public void delete(Habit habit) {
        executorService.execute(() -> habitDAO.delete(habit));
    }

    public void deleteAllHabits() {
        executorService.execute(habitDAO::deleteAllHabits);
    }
}
