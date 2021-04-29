package sabinabaghiu.plannerzen.ui.today;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TodoRepository {
    private static TodoRepository instance;
    private final TodoDAO todoDAO;
    private final LiveData<List<Todo>> allTodos;
    private final ExecutorService executorService;

    private TodoRepository(Application application) {
        TodoDatabase database = TodoDatabase.getInstance(application);
        todoDAO = database.todoDAO();
        allTodos = todoDAO.getAllTodos();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized TodoRepository getInstance(Application application) {
        if (instance == null)
            instance = new TodoRepository(application);

        return instance;
    }

    public LiveData<List<Todo>> getAllTodos() {
        return allTodos;
    }

    public void insert(Todo todo) {
        executorService.execute(() -> todoDAO.insert(todo));
    }

    public void update(Todo todo) {
        executorService.execute(() -> todoDAO.update(todo));
    }

    public void delete(Todo todo) {
        executorService.execute(() -> todoDAO.delete(todo));
    }

    public void deleteAllTodos() {
        executorService.execute(todoDAO::deleteAllTodos);
    }
}
