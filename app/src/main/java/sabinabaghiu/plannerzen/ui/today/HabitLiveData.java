package sabinabaghiu.plannerzen.ui.today;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HabitLiveData extends LiveData<ArrayList<Habit>> {
    private final ValueEventListener listener = new ValueEventListener() {

        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            ArrayList<Habit> habits = new ArrayList<>();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                Habit habit = dataSnapshot.getValue(Habit.class);
                habit.setId(dataSnapshot.getKey());
                habits.add(habit);
                setValue(habits);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    DatabaseReference databaseReference;

    public HabitLiveData(DatabaseReference reference) {
        databaseReference = reference;
    }

    @Override
    protected void onActive() {
        super.onActive();
        databaseReference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        databaseReference.removeEventListener(listener);
    }
}
