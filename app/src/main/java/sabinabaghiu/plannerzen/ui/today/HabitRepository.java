package sabinabaghiu.plannerzen.ui.today;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HabitRepository {
    private static HabitRepository instance;
    private DatabaseReference myRef;
    private HabitLiveData habit;

    private HabitRepository() {}

    public static synchronized HabitRepository getInstance() {
        if (instance == null)
            instance = new HabitRepository();
        return instance;
    }

    public void init() {
        myRef = FirebaseDatabase.getInstance("https://plannerzen-43809-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Habits");
        habit = new HabitLiveData(myRef);
    }

    public void saveHabit(String userId, String title, int goal, int iconId, boolean isDone, int count) {
        myRef.push().setValue(new Habit(userId, title, goal, iconId, isDone, count));
    }
}
