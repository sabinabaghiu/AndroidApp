package sabinabaghiu.plannerzen.ui.today;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TodoRepository {
    private static TodoRepository instance;

//    private final LiveData<List<Todo>> allTodos;
    private final ExecutorService executorService;

    private TodoRepository(Application application) {

        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized TodoRepository getInstance(Application application) {
        if (instance == null)
            instance = new TodoRepository(application);

        return instance;
    }

}
