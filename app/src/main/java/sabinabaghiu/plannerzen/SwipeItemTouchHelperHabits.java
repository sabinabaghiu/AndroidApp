package sabinabaghiu.plannerzen;

import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AlertDialog;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import sabinabaghiu.plannerzen.ui.lists.HabitListsAdapter;
import sabinabaghiu.plannerzen.ui.today.TaskAdapter;

public class SwipeItemTouchHelperHabits extends ItemTouchHelper.SimpleCallback {
    private HabitListsAdapter habitAdapter;

    public SwipeItemTouchHelperHabits(HabitListsAdapter habitAdapter){
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.habitAdapter = habitAdapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        //        if (viewHolder.itemView.findViewById(R.id.section_divider) != null) return;
        int position = viewHolder.getAdapterPosition();
        if(direction == ItemTouchHelper.LEFT){
                AlertDialog.Builder builder = new AlertDialog.Builder(habitAdapter.getContext());
                builder.setTitle("Delete habit");
                builder.setMessage("Are you sure you want to delete this habit?");
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        habitAdapter.deleteItem(position);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        habitAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
        }
        else {
            //edit part
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                .addSwipeLeftBackgroundColor(ContextCompat.getColor(habitAdapter.getContext(), R.color.error))
                .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                .addSwipeRightBackgroundColor(ContextCompat.getColor(habitAdapter.getContext(),R.color.my_yellow))
                .addSwipeRightActionIcon(R.drawable.ic_baseline_edit_24)
                .create().decorate();
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
