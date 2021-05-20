package sabinabaghiu.plannerzen.ui.today;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class HabitRepository {
    private static HabitRepository instance;
    private DatabaseReference myRef;
    private HabitLiveData habit;
    private DatabaseReference userReference;

    private HabitRepository() {}

    public static synchronized HabitRepository getInstance() {
        if (instance == null)
            instance = new HabitRepository();
        return instance;
    }

    public void init(String userId) {
        userReference = FirebaseDatabase.getInstance("https://plannerzen-43809-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Users").child(userId);
        myRef = userReference.child("Habits");
        habit = new HabitLiveData(myRef);
    }

    public void saveHabit(String title, String goal, int iconId, boolean isDone, int count) {
        myRef.push().setValue(new Habit(title, goal, iconId, isDone, count));
    }

    public HabitLiveData getHabits(){
        return habit;
    }

    public void deleteHabit(String id){
        myRef.child(id).removeValue();
    }
}
