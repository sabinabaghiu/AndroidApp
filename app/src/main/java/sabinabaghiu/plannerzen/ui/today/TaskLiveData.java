package sabinabaghiu.plannerzen.ui.today;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TaskLiveData extends LiveData<ArrayList<Task>> {
    private final ValueEventListener listener = new ValueEventListener() {

        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            ArrayList<Task> tasks = new ArrayList<>();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                Task task = dataSnapshot.getValue(Task.class);
                task.setId(dataSnapshot.getKey());
                tasks.add(task);
                setValue(tasks);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };


    DatabaseReference databaseReference;

    public TaskLiveData(DatabaseReference reference){
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
