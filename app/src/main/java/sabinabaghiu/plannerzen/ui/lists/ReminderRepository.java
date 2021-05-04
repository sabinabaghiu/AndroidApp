package sabinabaghiu.plannerzen.ui.lists;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ReminderRepository {
    private static ReminderRepository instance;
//    private final LiveData<List<Reminder>> allReminders;
    private final ExecutorService executorService;

    private ReminderRepository(Application application) {

        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized ReminderRepository getInstance(Application application) {
        if (instance == null)
            instance = new ReminderRepository(application);

        return instance;
    }

}
