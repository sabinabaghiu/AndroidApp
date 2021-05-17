package sabinabaghiu.plannerzen;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AlertDialog;

import sabinabaghiu.plannerzen.ui.lists.HabitListsAdapter;
import sabinabaghiu.plannerzen.ui.today.TaskAdapter;

public class SwipeItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private HabitListsAdapter habitAdapter;
    private TaskAdapter taskAdapter;

    public SwipeItemTouchHelper(HabitListsAdapter habitAdapter){
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.habitAdapter = habitAdapter;
    }

    public SwipeItemTouchHelper(TaskAdapter taskAdapter){
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.taskAdapter = taskAdapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        switch (direction){
            case ItemTouchHelper.LEFT:
                AlertDialog.Builder builder = new AlertDialog.Builder(habitAdapter.getContext());
        }
    }
}
