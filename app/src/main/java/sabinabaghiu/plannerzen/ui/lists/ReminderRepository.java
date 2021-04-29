package sabinabaghiu.plannerzen.ui.lists;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ReminderRepository {
    private static ReminderRepository instance;
    private final ReminderDAO reminderDAO;
    private final LiveData<List<Reminder>> allReminders;
    private final ExecutorService executorService;

    private ReminderRepository(Application application) {
        ReminderDatabase database = ReminderDatabase.getInstance(application);
        reminderDAO = database.reminderDAO();
        allReminders = reminderDAO.getAllReminders();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized ReminderRepository getInstance(Application application) {
        if (instance == null)
            instance = new ReminderRepository(application);

        return instance;
    }

    public LiveData<List<Reminder>> getAllReminders() {
        return allReminders;
    }

    public void insert(Reminder reminder) {
        executorService.execute(() -> reminderDAO.insert(reminder));
    }

    public void update(Reminder reminder) {
        executorService.execute(() -> reminderDAO.update(reminder));
    }

    public void delete(Reminder reminder) {
        executorService.execute(() -> reminderDAO.delete(reminder));
    }

    public void deleteAllReminders() {
        executorService.execute(reminderDAO::deleteAllReminders);
    }
}
